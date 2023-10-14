package com.moringaschool.student.config;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseConfig {
    public static Connection getConnection(){
        Sql2o sql20 = new Sql2o("jdbc:postgresql://localhost:5432/herosquad","postgres1","postgres");
        try(Connection connection= sql20.open()){
            System.out.println("SUCCESS "+connection.toString());
            return connection;
        }
        catch(Exception exception){
            System.out.println("ERROR "+exception.getLocalizedMessage());
            return null;
        }
    }

}
