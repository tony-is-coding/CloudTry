package com.cnc.echo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FusingController {

    private static int sleep = 0;
    private static final int SLEEP_INCREMENT = 50;

    @GetMapping("/fusing")
    public String fusing() {
        try {
//            System.out.println("current sleep time:" + sleep);
//            Thread.sleep(sleep);
//            sleep += SLEEP_INCREMENT;
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello! this is /api/fusing";
    }
}
