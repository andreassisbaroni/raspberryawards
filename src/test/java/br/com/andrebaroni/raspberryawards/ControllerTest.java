package br.com.andrebaroni.raspberryawards;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.Serializable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ControllerTest implements Serializable {

    @LocalServerPort
    private int port;

    private final TestRestTemplate testRestTemplate;

    protected ControllerTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }


    protected String getUrl() {
        return "http://localhost:" + this.port + "/api/" + this.getResource();
    }

    protected TestRestTemplate getTestRestTemplate() {
        return testRestTemplate;
    }

    protected abstract String getResource();
}
