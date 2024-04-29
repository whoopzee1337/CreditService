package com.example.creditservice.service.impl;

import com.example.creditservice.constants.ErrorEnum;
import com.example.creditservice.dto.LoanOrderCreateDto;
import com.example.creditservice.dto.LoanOrderDeleteDto;
import com.example.creditservice.dto.LoanOrderDto;
import com.example.creditservice.dto.TariffDto;
import com.example.creditservice.entity.LoanOrderEntity;
import com.example.creditservice.entity.TariffEntity;
import com.example.creditservice.mapper.LoanOrderMapper;
import com.example.creditservice.mapper.TariffMapper;
import com.example.creditservice.repository.LoanOrderRepository;
import com.example.creditservice.repository.TariffRepository;
import com.example.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Сервис для работы с заявками на кредит
 */
@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final TariffRepository tariffRepository;
    private final LoanOrderRepository loanOrderRepository;

    private final TariffMapper tariffMapper;
    private final LoanOrderMapper loanOrderMapper;

    /**
     * Метод для получения тарифов на кредит
     *
     * @return Список тарифов в виде объектов TariffDto
     */
    @Override
    public List<TariffDto> getTariffs() {
        List<TariffDto> tariffDtos = new ArrayList<>();
        for (TariffEntity tariff : tariffRepository.findAll()) {
            tariffDtos.add(tariffMapper.toDto(tariff));
        }
        return tariffDtos;
    }

    /**
     * Метод для получения заявок пользователя
     *
     * @param userId Идентификатор пользователя
     * @return Список заявок пользователя в виде объектов LoanOrderDto
     */
    @Override
    public List<LoanOrderDto> getUserOrders(Long userId) {
        List<LoanOrderDto> loanOrderDtos = new ArrayList<>();
        for (LoanOrderEntity loanOrder : loanOrderRepository.findAll(userId)) {
            loanOrderDtos.add(loanOrderMapper.toDto(loanOrder));
        }
        return loanOrderDtos;
    }

    /**
     * Метод для создания новой заявки на кредит
     *
     * @param dto Информация о создаваемой заявке
     * @return Созданная заявка в виде объекта LoanOrderDto
     */
    @Override
    public LoanOrderDto createOrder(LoanOrderCreateDto dto) {
        if (!tariffRepository.isExist(dto.getTariffId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorEnum.TARIFF_NOT_FOUND.name());
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        List<LoanOrderEntity> listOfOrders = loanOrderRepository.findAll(dto.getUserId());
        for (LoanOrderEntity order : listOfOrders) {
            if (dto.getTariffId() == order.getTariffId()) {
                switch (order.getStatus()) {
                    case "IN_PROGRESS":
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorEnum.LOAN_CONSIDERATION.name());
                    case "APPROVED":
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorEnum.LOAN_ALREADY_APPROVED.name());
                    case "REFUSED":
                        if (currentTimestamp.getTime() - order.getTimeUpdate().getTime() < 120000) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorEnum.TRY_LATER.name());
                        }
                        break;

                }
            }
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');

        LoanOrderEntity loanOrderEntity = new LoanOrderEntity();
        loanOrderEntity.setOrderId(UUID.randomUUID())
                .setUserId(dto.getUserId())
                .setTariffId(dto.getTariffId())
                .setCreditRating(Double.parseDouble(new DecimalFormat("#.##", symbols).format(0.1 + Math.random() * 0.8)))
                .setStatus("IN_PROGRESS")
                .setTimeInsert(currentTimestamp)
                .setTimeUpdate(currentTimestamp);

        loanOrderRepository.save(loanOrderEntity);

        return loanOrderMapper.toDto(loanOrderEntity);
    }

    /**
     * Метод для получения статуса заявки
     *
     * @param orderId Идентификатор заявки
     * @return Информация о статусе заявки в виде объекта LoanOrderDto
     */
    @Override
    public LoanOrderDto getStatusOrder(UUID orderId) {
        if (!loanOrderRepository.isExist(orderId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorEnum.ORDER_NOT_FOUND.name());
        }
        return loanOrderMapper.toDto(loanOrderRepository.getOrder(orderId));
    }

    /**
     * Метод для удаления заявки на кредит
     *
     * @param dto Информация о заявке, которую необходимо удалить
     */
    @Override
    public void deleteOrder(LoanOrderDeleteDto dto) {
        if (!loanOrderRepository.isExist(dto.getUserId(), dto.getOrderId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorEnum.ORDER_IMPOSSIBLE_TO_DELETE.name());
        }
        loanOrderRepository.deleteOrder(dto.getUserId(), dto.getOrderId());
    }

}
