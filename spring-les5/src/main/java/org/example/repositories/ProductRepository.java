package org.example.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void addProject(String name) {
        String sql = "insert into product_s_les5 values(null, ?)";
        jdbcTemplate.update(sql, name);
    }
}
