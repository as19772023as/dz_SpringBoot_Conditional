package ru.strebkov.dz_SpringBoot_Conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@DisplayName("Проверка работоспособности логики dev/prod")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootExampleConditionalApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    private final static GenericContainer<?> containerDev = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    private final static GenericContainer<?> containerProd = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);
    private static final String ENDPOINT = "/profile";

    @BeforeAll
    public static void setUp() {
        containerDev.start();
        containerProd.start();
    }

    @DisplayName("Тест на логику dev")
    @Test
    void contextLoadsDev() {
        Integer port = containerDev.getMappedPort(8080);
        final String expected = "Current profile is production";

        ResponseEntity<String> actual = restTemplate.getForEntity("http://localhost:" +
                port + ENDPOINT, String.class);
//        System.out.println(actual.getBody());

        Assertions.assertEquals(expected, actual.getBody());
    }

    @DisplayName("Тест на логику prod")
    @Test
    void contextLoadsProd() {
        Integer port = containerProd.getMappedPort(8081);
        final String expected = "Current profile is dev";

        ResponseEntity<String> actual = restTemplate.getForEntity("http://localhost:" +
                port + ENDPOINT, String.class);

        Assertions.assertEquals(expected, actual.getBody());
    }

}
