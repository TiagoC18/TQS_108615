# a) Identify a couple of examples that use AssertJ expressive methods chaining.

```
assertThat(found).isEqualTo(alex);
assertThat(fromDb).isNull();
assertThat(fromDb).isNotNull();
assertThat(fromDb.getEmail()).isEqualTo(emp.getEmail());
```
# b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

```
@BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```


# c) What is the difference between standard @Mock and @MockBean?

- `@Mock` is used with Mockito for unit testing without Spring context, creating mocks directly in test classes. `@MockBean` is specific to Spring Boot tests, adding or replacing beans in the Spring context for integration testing. While `@Mock` isolates tests from Spring, `@MockBean` integrates them with the Spring lifecycle for broader application testing.


# d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

- The `application-integrationtest.properties` file is used in Spring Boot for specifying configurations unique to integration testing, isolating these tests from main application settings. It’s activated when the `integrationtest` profile is specified, allowing for environment-specific settings like in-memory databases for tests. This ensures tests run with the necessary conditions without affecting other environments.

# e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 

1. C - Mocking Service Layer: Uses @MockBean to mock the service layer, isolating the web layer for testing without starting the server.

2. D - Integration Testing with Mock MVC: Runs tests against the actual MVC layer using MockMvc, starting the Spring context but not the server, allowing for more detailed web layer testing.

3. E - Real Server Integration Testing: Utilizes @SpringBootTest with a random port and TestRestTemplate for full integration tests, starting the entire Spring context and the embedded server to test real requests and responses.