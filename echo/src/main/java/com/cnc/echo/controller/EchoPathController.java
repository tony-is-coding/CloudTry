package com.cnc.echo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EchoPathController {


    @GetMapping(value = "/path1")
    public String echoPath() {
        return "hello world, /path1";
    }

}
