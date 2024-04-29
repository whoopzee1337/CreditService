package com.example.mtsfinalprojectsecurity.service.impl;

import com.example.mtsfinalprojectsecurity.dto.LoanOrderCreateDto;
import com.example.mtsfinalprojectsecurity.dto.LoanOrderDeleteDto;
import com.example.mtsfinalprojectsecurity.model.Response;
import com.example.mtsfinalprojectsecurity.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class WebClientServiceImpl implements WebClientService {

    private WebClient.Builder webClientBuilder;

    @Override
    public Response getTariffs() {
        return webClientBuilder.build()
                .get()
                .uri("/loan-service/getTariffs")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.empty();
                })
                .bodyToMono(Response.class)
                .block();
    }

    @Override
    public Response getUserOrders(Long userId) {
        return webClientBuilder.build()
                .post()
                .uri("/loan-service/getUserOrders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.empty();
                })
                .bodyToMono(Response.class)
                .block();
    }

    @Override
    public Response getStatus(String orderId) {
        return webClientBuilder.build()
                .get()
                .uri("/loan-service/getStatusOrder?orderId=" + orderId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.empty();
                })
                .bodyToMono(Response.class)
                .block();
    }

    @Override
    public Response deleteOrder(LoanOrderDeleteDto dto) {
        return webClientBuilder.build()
                .method(HttpMethod.DELETE)
                .uri("/loan-service/deleteOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), LoanOrderDeleteDto.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.empty();
                })
                .bodyToMono(Response.class)
                .block();
    }

    @Override
    public Response createOrder(LoanOrderCreateDto dto) {
        return webClientBuilder.build()
                .post()
                .uri("/loan-service/order")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), LoanOrderCreateDto.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    return Mono.empty();
                })
                .bodyToMono(Response.class)
                .block();
    }
}


