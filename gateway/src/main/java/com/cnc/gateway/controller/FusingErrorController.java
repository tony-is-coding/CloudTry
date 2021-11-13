package com.cnc.gateway.controller;

import com.cnc.gateway.vo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fusing_error")
public class FusingErrorController {

    @GetMapping("/global")
    public Mono<Response> globalFusing() {
        return Mono.just(Response.GLOBAL_TIMEOUT_ERROR);
    }

    @GetMapping("/echo")
    public Mono<Response> echoFusing() {
        return Mono.just(Response.ECHO_TIMEOUT_ERROR);
    }
}
