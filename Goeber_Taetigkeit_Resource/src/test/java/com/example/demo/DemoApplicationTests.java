package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

@SpringBootTest
class DemoApplicationTests {

    private URL base;

    @BeforeEach
    public void setUp()  throws Exception{
        this.base = new URL("http://localhost:8080/");
    }

    private final TestRestTemplate template = new TestRestTemplate();

    @Test
    void getWorkingTimeEmployee() throws Exception {
        ResponseEntity<Integer> response = template.getForEntity(base.toString() + "employees/totalworkingtime/SCMI", Integer.class);
        assert(response.getBody().equals(570));
    }

}
