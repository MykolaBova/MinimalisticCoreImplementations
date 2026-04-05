package tests;

import annotations.*;
import framework.Assert;

public class ExampleTests {

    private String data;

    @Before
    public void setUp() {
        System.out.println("Инициализация перед тестом.");
        data = "Тестовые данные";
    }

    @After
    public void tearDown() {
        System.out.println("Очистка после теста.");
        data = null;
    }

    @Test
    public void testSuccess() {
        if (!"Тестовые данные".equals(data)) {
            throw new AssertionError("Данные не инициализированы правильно.");
        }
    }

//    @Test
//    public void testFailure() {
//        throw new RuntimeException("Ошибка в тесте!");
//    }

    @Test
    public void anotherTestSuccess() {
        if (data == null) {
            throw new AssertionError("Данные не должны быть null.");
        }
    }

    @Test
    @Ignore(reason = "Функциональность ещё не реализована")
    public void ignoredTest() {
        throw new RuntimeException("Этот тест должен быть проигнорирован");
    }

    @Test
    public void testEquality() {
        String actual = "Привет";
        Assert.assertEquals("Привет", actual);
    }

    @BeforeClass
    public void beforeAllTests() {
        System.out.println("🔧 Подготовка перед запуском всех тестов");
    }

    @AfterClass
    public void afterAllTests() {
        System.out.println("🧹 Очистка после завершения всех тестов");
    }
}
