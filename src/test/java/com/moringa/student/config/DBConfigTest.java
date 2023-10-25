package com.moringa.student.config;

import com.google.common.annotations.VisibleForTesting;
import com.moringaschool.student.config.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import java.util.Collection;

public class DBConfigTest {


    @Test
    public void test_database_connection(){
        Connection connection = DBConfig.getConnection();

    }
}
