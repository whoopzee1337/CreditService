package com.example.creditservice.controller;

import com.example.creditservice.dto.LoanOrderCreateDto;
import com.example.creditservice.dto.LoanOrderDeleteDto;
import com.example.creditservice.service.CreditService;
import com.example.creditservice.wrappers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Класс-контроллер, предоставляет эндпоинты для взаимодейсивия с сервисом кредитования
 */
@RestController
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    /**
     * Метод для получения тарифов на кредит
     *
     * @return Обертка с информацией о тарифах
     */
    @GetMapping("getTariffs")
    public ResponseEntity<DataWrap> getTariffs() {
        return ResponseEntity.ok(new DataWrap(new TariffsWrap(creditService.getTariffs())));
    }

    /**
     * Метод для получения заявок пользователя
     *
     * @param userId Идентификатор пользователя
     * @return Обертка с информацией о заявках пользователя
     */
    @PostMapping("getUserOrders")
    public ResponseEntity<DataWrap> getUserOrders(@RequestBody Long userId) {
        return ResponseEntity.ok(new DataWrap(new LoanOrdersWrap(creditService.getUserOrders(userId))));
    }

    /**
     * Метод для создания новой заявки на кредит
     *
     * @param dto Информация о создаваемой заявке
     * @return Обертка с идентификатором созданной заявки
     */
    @PostMapping("order")
    public ResponseEntity<DataWrap> createOrder(@RequestBody LoanOrderCreateDto dto) {
        return ResponseEntity.ok(new DataWrap(new OrderIdWrap(creditService.createOrder(dto).getOrderId())));
    }

    /**
     * Метод для получения статуса заявки
     *
     * @param orderId Идентификатор заявки
     * @return Обертка с информацией о статусе заявки
     */
    @GetMapping("getStatusOrder")
    public ResponseEntity<DataWrap> getStatusOrder(@RequestParam("orderId") UUID orderId) {
        return ResponseEntity.ok(new DataWrap(new OrderStatusWrap(creditService.getStatusOrder(orderId).getStatus())));
    }

    /**
     * Метод для удаления заявки на кредит
     *
     * @param dto Информация о заявке, которую необходимо удалить
     * @return Ответ об успешном удалении заявки
     */
    @DeleteMapping("deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestBody LoanOrderDeleteDto dto) {
        creditService.deleteOrder(dto);
        return ResponseEntity.ok().build();
    }
}

