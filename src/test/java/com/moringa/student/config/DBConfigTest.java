package com.moringa.student.config;
import com.moringaschool.student.config.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;


public class DBConfigTest {
    @Test
    public void test_database_connection(){
        Connection connection = DBConfig.getConnection();
    }
}
