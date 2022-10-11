package com.pavel.app1;

import com.pavel.module1.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App1Controller {

    @GetMapping("/hello")
    public ResponseEntity<?> callHello() {
        String body = new HelloService().sayHello() + "feature1";
        return ResponseEntity.ok(body);
    }
}
