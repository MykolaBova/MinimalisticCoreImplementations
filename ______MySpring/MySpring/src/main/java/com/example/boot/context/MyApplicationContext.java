package com.example.boot.context;

import com.example.boot.annotations.*;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class MyApplicationContext {
    private final String basePackage;
    private final Map<Class<?>, Object> beans = new HashMap<>();
    private final PropertyLoader props;

    public MyApplicationContext(String basePackage) {
        this.basePackage = basePackage;
        this.props = new PropertyLoader("application.properties");
        scanAndInstantiate();
    }

    private void scanAndInstantiate() {
        Reflections refl = new Reflections(basePackage);

        Set<Class<?>> configs = refl.getTypesAnnotatedWith(Configuration.class);
        for (Class<?> cfg : configs) {
            instantiateConfig(cfg);
        }

        Set<Class<?>> comps = refl.getTypesAnnotatedWith(Component.class);
        for (Class<?> cmp : comps) {
            instantiateBean(cmp);
        }
    }

    private void instantiateConfig(Class<?> cfg) {
        try {
            Object configInstance = cfg.getDeclaredConstructor().newInstance();
            for (Method m : cfg.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Bean.class)) {
                    Object bean = m.invoke(configInstance);
                    beans.put(m.getReturnType(), bean);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void instantiateBean(Class<?> cls) {
        try {
            Object bean = cls.getDeclaredConstructor().newInstance();
            beans.put(cls, bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void autowire() {
        beans.forEach((cls, bean) -> {
            for (Field f : cls.getDeclaredFields()) {
                if (f.isAnnotationPresent(Autowired.class)) {
                    Object dep = beans.get(f.getType());
                    injectField(bean, f, dep);
                }
                if (f.isAnnotationPresent(Value.class)) {
                    String expr = f.getAnnotation(Value.class).value();
                    String key = expr.replaceAll("[\\$\\{\\}]", "");
                    String val = props.get(key, "");
                    injectField(bean, f, cast(f.getType(), val));
                }
            }
        });
    }

    private void injectField(Object bean, Field f, Object val) {
        try {
            f.setAccessible(true);
            f.set(bean, val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object cast(Class<?> type, String val) {
        if (type == int.class || type == Integer.class) return Integer.parseInt(val);
        if (type == long.class || type == Long.class) return Long.parseLong(val);
        if (type == String.class) return val;
        return null;
    }

    private void loadAutoConfiguration() {
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream("META-INF/myboot.factories")) {

            List<String> autoConfigs = new ArrayList<>();
            if (in != null) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = rd.readLine()) != null) {
                    if (!line.startsWith("#") && !line.isBlank()) {
                        autoConfigs.add(line.trim());
                    }
                }
            }

            for (String className : autoConfigs) {
                Class<?> ac = Class.forName(className);

                if (ac.isAnnotationPresent(ConditionalOnClass.class)) {
                    String[] names = ac.getAnnotation(ConditionalOnClass.class).value();
                    boolean ok = true;
                    for (String n : names) {
                        try {
                            Class.forName(n);
                        } catch (ClassNotFoundException e) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) continue;
                }

                if (ac.isAnnotationPresent(Configuration.class)) {
                    instantiateConfig(ac);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() {
        autowire();
        loadAutoConfiguration();
        autowire();
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> type) {
        return (T) beans.get(type);
    }
}
