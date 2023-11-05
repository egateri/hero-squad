package com.moringaschool.student.config;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Map;

public class DBConfig {
    public static Connection getConnection(){

        Map<String, String> env = System.getenv();
        String DB_HOST =env.get("DB_HOST");
        String DB_USER =env.get("DB_USER");
        String DB_DATABASE =env.get("DB_USER");
        String DB_PASSWORD =env.get("DB_PASSWORD");
        String DB_PORT =env.get("PORT");
                for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName,
                    env.get(envName));
        }
        Sql2o sql2o = new Sql2o("jdbc:postgresql://"+DB_HOST+":"+DB_PORT+"/"+DB_DATABASE,DB_USER,DB_PASSWORD);

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
