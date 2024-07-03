package com.example.jdbcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcClient jdbcClient;

    // SQL queries
    private static final String INSERT_ORDER_SQL = "INSERT INTO ORDERS (CUSTOMER_ID) VALUES (?)";
    private static final String INSERT_ORDER_ITEM_SQL = "INSERT INTO ORDER_ITEMS (ORDER_ID, PRODUCT_ID, AMOUNT) VALUES (?, ?, ?)";
    private static final String SELECT_ORDER_BY_ID_SQL = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
    private static final String SELECT_ORDER_ITEMS_BY_ORDER_ID_SQL = "SELECT * FROM ORDER_ITEMS WHERE ORDER_ID = ?";

    // RowMapper for mapping Order entity from ResultSet
    private final RowMapper<Order> orderRowMapper = (rs, nowNum) -> {
        long id = rs.getLong("order_id");
        long customerId = rs.getLong("customer_id");
        return new Order(id, customerId);
    };

    // RowMapper for mapping OrderItem entity from ResultSet
    private final RowMapper<OrderItem> orderItemRowMapper = (rs, rowNum) -> {
        long productId = rs.getLong("product_id");
        int amount = rs.getInt("amount");
        return new OrderItem(productId, amount);
    };

    // Save an order and its items to the database
    public long save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(INSERT_ORDER_SQL)
                .param(order.getCustomerId())
                .update(keyHolder);


        for (OrderItem item : order.getItems()) {
            jdbcClient.sql(INSERT_ORDER_ITEM_SQL)
                    .param(keyHolder.getKey().longValue())
                    .param(item.productId())
                    .param(item.amount())
                    .update();
        }

        return keyHolder.getKey().longValue();
    }

    // Retrieve an order by its ID
    public Optional<Order> findOrderById(long orderId) {
        Optional<Order> order = jdbcClient
                .sql(SELECT_ORDER_BY_ID_SQL)
                .param(orderId)
                .query(orderRowMapper)
                .optional();

        if (order.isEmpty()) return Optional.empty();

        List<OrderItem> itmes = jdbcClient
                .sql(SELECT_ORDER_ITEMS_BY_ORDER_ID_SQL)
                .param(orderId)
                .query(orderItemRowMapper)
                .list();

        return Optional.of(order.get().withItems(itmes));
    }
}
