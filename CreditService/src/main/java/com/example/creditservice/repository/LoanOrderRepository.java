package com.example.creditservice.repository;

import com.example.creditservice.entity.LoanOrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с заявками на кредит
 */
@Repository
@AllArgsConstructor
public class LoanOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ORDERS_BY_USER_ID = "SELECT * FROM LOAN_ORDER WHERE USER_ID = ?";
    private static final String SQL_INSERT = "INSERT INTO LOAN_ORDER(order_id, user_id, tariff_id, credit_rating, status, time_insert, time_update) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_CHECK_EXISTING_BY_ORDER_ID = "SELECT COUNT(*) FROM LOAN_ORDER WHERE ORDER_ID = ?";
    private static final String SQL_CHECK_EXISTING_BY_ORDER_ID_AND_USER_ID = "SELECT COUNT(*) FROM LOAN_ORDER WHERE USER_ID = ? AND ORDER_ID = ?";
    private static final String SQL_SELECT_ORDER_BY_ORDER_ID = "SELECT * FROM LOAN_ORDER WHERE ORDER_ID = ?";
    private static final String SQL_DELETE_ORDER_BY_USER_ID_AND_ORDER_ID = "DELETE FROM LOAN_ORDER WHERE USER_ID = ? AND ORDER_ID = ?";
    private static final String SQL_SELECT_ORDERS_BY_STATUS = "SELECT * FROM LOAN_ORDER WHERE STATUS = ?";
    private static final String SQL_UPDATE_STATUS = "UPDATE LOAN_ORDER SET STATUS = ?, TIME_UPDATE = NOW() WHERE USER_ID = ? AND ORDER_ID = ?";

    /**
     * Метод для поиска всех заявок пользователя
     *
     * @param userId Идентификатор пользователя
     * @return Список заявок пользователя в виде объектов LoanOrderEntity
     */
    public List<LoanOrderEntity> findAll(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_ORDERS_BY_USER_ID, new BeanPropertyRowMapper<>(LoanOrderEntity.class), userId);
    }

    /**
     * Метод для сохранения заявки на кредит
     *
     * @param loanOrderEntity Объект заявки на кредит
     */
    public void save(LoanOrderEntity loanOrderEntity) {
        jdbcTemplate.update(SQL_INSERT,
                loanOrderEntity.getOrderId(),
                loanOrderEntity.getUserId(),
                loanOrderEntity.getTariffId(),
                loanOrderEntity.getCreditRating(),
                loanOrderEntity.getStatus(),
                loanOrderEntity.getTimeInsert(),
                loanOrderEntity.getTimeUpdate());

    }

    /**
     * Метод для проверки существования заявки по идентификатору
     *
     * @param orderId Идентификатор заявки
     * @return true, если заявка существует, иначе false
     */
    public boolean isExist(UUID orderId) {
        int count = jdbcTemplate.queryForObject(SQL_CHECK_EXISTING_BY_ORDER_ID, Integer.class, orderId);
        return count > 0;
    }

    /**
     * Метод для проверки существования заявки по идентификатору пользователя и заявки
     *
     * @param userId  Идентификатор пользователя
     * @param orderId Идентификатор заявки
     * @return true, если заявка существует, иначе false
     */
    public boolean isExist(Long userId, UUID orderId) {
        int count = jdbcTemplate.queryForObject(SQL_CHECK_EXISTING_BY_ORDER_ID_AND_USER_ID, Integer.class, userId, orderId);
        return count > 0;
    }

    /**
     * Метод для получения информации о заявке по идентификатору
     *
     * @param orderId Идентификатор заявки
     * @return Объект заявки на кредит
     */
    public LoanOrderEntity getOrder(UUID orderId) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ORDER_ID, new BeanPropertyRowMapper<>(LoanOrderEntity.class), orderId);
    }

    /**
     * Метод для удаления заявки на кредит по идентификатору пользователя и заявки
     *
     * @param userId  Идентификатор пользователя
     * @param orderId Идентификатор заявки
     */
    public void deleteOrder(Long userId, UUID orderId) {
        jdbcTemplate.update(SQL_DELETE_ORDER_BY_USER_ID_AND_ORDER_ID, userId, orderId);
    }

    /**
     * Метод для поиска всех заявок по статусу
     *
     * @param status Статус заявок
     * @return Список заявок в указанном статусе
     */
    public List<LoanOrderEntity> findAll(String status) {
        return jdbcTemplate.query(SQL_SELECT_ORDERS_BY_STATUS, new BeanPropertyRowMapper<>(LoanOrderEntity.class), status);
    }

    /**
     * Метод для обновления статуса заявки на кредит
     *
     * @param status  Новый статус заявки
     * @param userId  Идентификатор пользователя
     * @param orderId Идентификатор заявки
     */
    public void updateStatus(String status, Long userId, UUID orderId) {
        jdbcTemplate.update(SQL_UPDATE_STATUS, status, userId, orderId);
    }

}
