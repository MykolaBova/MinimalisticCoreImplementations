# MinimalisticCoreImplementations

Minimalistic implementations of different core components like Spring DI, JUnit 



\## 🎯 Project Goal



Reimplement core concepts from modern Java frameworks:



\- Dependency Injection and Application Context (inspired by Spring Boot)

\- Annotation processing and lifecycle handling (inspired by JUnit)



This project serves as an educational deep dive into how frameworks work internally.



\## 🎓 Practical Usage



These projects were actively used as part of my Spring course, where I explained core framework concepts through simplified implementations.



Instead of relying only on theory, I used MiniJUnit and MySpring to demonstrate:



\- how dependency injection works internally

\- how annotation-driven frameworks are built

\- how test lifecycle and execution are organized



This allowed students to understand not just how to use frameworks, but how they actually work under the hood.



\# MySpring



A minimalistic implementation of core ideas behind the Spring Boot framework.



This project is built to explore how dependency injection, configuration, and application bootstrapping work under the hood — without hiding complexity behind abstractions.



\---



\## 🚀 Features



\- Dependency Injection (DI container)

\- Annotation-based configuration

\- `@Component`, `@Autowired`, `@Configuration`

\- Basic Application Context

\- Auto-configuration mechanism

\- Property loading (`application.properties`)

\- Lightweight bootstrapping (`MyApplication.run(...)`)



\---



\## 🧠 Motivation



Modern frameworks like Spring Boot provide powerful abstractions, but they often hide the internal mechanics.



This project answers questions like:

\- How does dependency injection actually work?

\- How are beans discovered and instantiated?

\- How does auto-configuration decide what to load?

\- What happens during application startup?



\---



\## 🏗️ Architecture Overview



The system is divided into several core modules:



\### 1. Annotations



Custom annotations used to mark components and configure behavior:

\- `@Component`

\- `@Autowired`

\- `@Configuration`

\- `@Bean`

\- `@ConditionalOnClass`

\- `@Value`



\---



\### 2. Application Context



Central part of the framework responsible for:

\- scanning classes

\- creating objects (beans)

\- resolving dependencies

\- managing lifecycle



Key class:

\- `MyApplicationContext`



\---



\### 3. Dependency Injection



The container:

\- scans for components

\- creates instances via reflection

\- injects dependencies into fields marked with `@Autowired`



Supports:

\- constructor / field injection (depending on implementation)



\---



\### 4. Configuration System



Supports Java-based configuration via:

\- `@Configuration`

\- `@Bean`



Allows defining custom beans manually.



\---



\### 5. Auto-Configuration



Inspired by Spring Boot:



\- Uses `myboot.factories`

\- Loads configurations dynamically

\- Applies conditions via `@ConditionalOnClass`



\---



\### 6. Bootstrapping



Entry point:



```java

MyApplication.run(MyApplication.class);







\# MiniJUnit



A minimalistic implementation of core ideas behind modern testing frameworks like JUnit.



This project explores how annotation-driven test execution, lifecycle management, and assertions work internally — without relying on external libraries.



\---



\## 🚀 Features



\- Annotation-based test definition

\- `@Test`, `@Before`, `@After`

\- `@BeforeClass`, `@AfterClass`

\- `@Ignore` support

\- Custom assertion utilities

\- Reflection-based test discovery and execution

\- Simple test runner



\---



\## 🎯 Project Goal



Reimplement core concepts from modern Java testing frameworks:



\- Annotation-driven execution model

\- Test lifecycle management

\- Reflection-based test discovery

\- Assertion handling



This project serves as an educational deep dive into how frameworks like JUnit work internally.



\---



\## 🧠 Motivation



Frameworks like JUnit are widely used but often treated as a “black box”.



This project answers questions like:

\- How does a test runner find test methods?

\- How are lifecycle hooks executed in the correct order?

\- How are assertions implemented?

\- How does the framework decide what to run and what to ignore?



\---



\## 🏗️ Architecture Overview



The system is divided into several core modules:



\---



\### 1. Annotations



Custom annotations used to define test structure:



\- `@Test` — marks a test method

\- `@Before` — runs before each test

\- `@After` — runs after each test

\- `@BeforeClass` — runs once before all tests

\- `@AfterClass` — runs once after all tests

\- `@Ignore` — skips test execution



\---



\### 2. Test Runner



Core execution engine responsible for:



\- discovering test classes

\- scanning methods via reflection

\- executing lifecycle hooks

\- running test methods

\- reporting results



Key class:

\- `MiniJUnit`



\---



\### 3. Assertions



Custom assertion utilities:



\- equality checks

\- boolean conditions

\- failure handling



Key class:

\- `Assert`



\---



\### 4. Test Discovery



The framework:



\- loads test classes

\- inspects methods using reflection

\- filters based on annotations



\---



\## ⚙️ How It Works (Step-by-Step)



1\. Test runner starts (`MiniJUnit`)

2\. Test class is loaded

3\. Methods are scanned via reflection

4\. Methods are grouped by annotations:

&#x20;  - lifecycle methods

&#x20;  - test methods

5\. `@BeforeClass` is executed once

6\. For each test:

&#x20;  - `@Before` is executed

&#x20;  - test method runs

&#x20;  - `@After` is executed

7\. `@AfterClass` is executed once

8\. Results are printed



\---



\## 📦 Example



```java

public class ExampleTests {



&#x20;   @BeforeClass

&#x20;   public static void setupAll() {

&#x20;       System.out.println("Before all tests");

&#x20;   }



&#x20;   @Before

&#x20;   public void setup() {

&#x20;       System.out.println("Before each test");

&#x20;   }



&#x20;   @Test

&#x20;   public void testSomething() {

&#x20;       Assert.assertTrue(1 + 1 == 2);

&#x20;   }



&#x20;   @After

&#x20;   public void cleanup() {

&#x20;       System.out.println("After each test");

&#x20;   }



&#x20;   @AfterClass

&#x20;   public static void teardownAll() {

&#x20;       System.out.println("After all tests");

&#x20;   }

}













