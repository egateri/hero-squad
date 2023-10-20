package com.moringaschool.student.config;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DBConfig {
    public static Connection getConnection(){
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/herosquad","postgres","postgres");
        try(Connection connection= sql2o.open()){
            System.out.println("SUCCESS "+connection.toString());
            return connection;
        }
        catch(Exception exception){

            System.out.println("ERROR "+exception.getLocalizedMessage());
            return null;
        }
    }

}
