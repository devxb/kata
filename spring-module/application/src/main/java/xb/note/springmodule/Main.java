package xb.note.springmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.note.springmodule.service.MyService;

@SpringBootApplication
@RestController
public class Main {

    private final MyService myService;

    public Main(MyService myService){
        this.myService = myService;
    }

    @GetMapping("/message")
    public String message(){
        return myService.message();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}