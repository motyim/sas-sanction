package com.sas.sanctiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SanctionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanctionDemoApplication.class, args);
    }

    @GetMapping("/welcome")
    public String getWelcome(){
        return  "Hi Marawan : )";
    }


}
