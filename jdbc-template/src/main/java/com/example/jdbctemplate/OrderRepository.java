package com.example.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.List;

@Repository
public class OrderRepository {
    
    private final JdbcTemplate jdbcTemplate;
    // SQL queries
    private static final String INSERT_ORDER_SQL = "INSERT INTO orders (customer_id) VALUES (?)";
    private static final String INSERT_ORDER_ITEM_SQL = "INSERT INTO order_items (order_id, product_id, amount) VALUES (?, ?, ?)";
    private static final String SELECT_ORDER_BY_ID_SQL = "SELECT * FROM orders WHERE order_id = ?";
    private static final String SELECT_ORDER_ITEMS_BY_ORDER_ID_SQL = "SELECT * FROM order_items WHERE order_id = ?";
    
    // RowMapper for mapping Order entity from ResultList
    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private final RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
        long id = rs.getLong("order_id");
        long customerId = rs.getLong("customer_id");
        return new Order(id, customerId); // Items will be populated separately
    };

    // RowMapper for mapping OrderItem entity from ResultList
    private final RowMapper<OrderItem> orderItemRowMapper = (rs, rowNum) -> {
        long productId = rs.getLong("product_id");
        int amount = rs.getInt("amount");
        return new OrderItem(productId, amount);
    };

    // Save an order and its items to the database
    public long save(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getCustomerId());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);

        long orderId = keyHolder.getKey().longValue();

        for (OrderItem item : order.getItems()) {
            jdbcTemplate.update(INSERT_ORDER_ITEM_SQL, orderId, item.productId(), item.amount());
        }
        return orderId;
    }

    // Retrieve an order by its ID
    public Optional<Order> findOrderById(long orderId) {
        try {
            Order order = jdbcTemplate.queryForObject(SELECT_ORDER_BY_ID_SQL, orderRowMapper, orderId);
            List<OrderItem> items = jdbcTemplate.query(SELECT_ORDER_ITEMS_BY_ORDER_ID_SQL, orderItemRowMapper, orderId);
            return Optional.of(order.withItems(items));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
