package xb.note.springmodule.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest("service.message=hello")
public class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    public void contextLoads(){
        Assertions.assertNotNull(myService.message());
    }

    @SpringBootApplication
    static class TestConfiguration{

    }

}
