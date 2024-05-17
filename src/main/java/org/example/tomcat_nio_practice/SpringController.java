package org.example.tomcat_nio_practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.util.logging.Logger;

@RestController
public class SpringController {

    Logger logger = Logger.getLogger(SpringController.class.getName());


    // 동기 처리 방식
//    @GetMapping("/spring")
//    public String spring() throws InterruptedException {
//        RestTemplate restTemplate = new RestTemplate();
//        logger.info("start spring");
//        System.out.println(Thread.currentThread().getName());
//        String result = restTemplate.getForObject("http://localhost:5000/ai", String.class);
//        logger.info("end spring");
//        return "success";
//    }

    @GetMapping("/health_check")
    public String healthCheck() {
        return "OK";
    }


    @GetMapping("/spring")
    public String spring() {
        logger.info("start spring");
        WebClient webClient = WebClient.create("http://localhost:5000");

        webClient.get()
                .uri("/ai")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(result -> {
                    logger.info("결과: " + result);
                });

        logger.info("end spring");
        return "a";
    }
}
