package com.oanda.learning.springreactordemo.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExternalService {
    Mono<String> getSampleString(){
        return Mono.just("This is a sample String!");
    }

    Flux<String> getStringsFromSvc() {
//        throw new Exception("Intentional Exception");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
        return Flux.just("String 1 ", "String 2 ");
    }

//    String getSampleString(){
//        return "This is a sample String!";
//    }
}
