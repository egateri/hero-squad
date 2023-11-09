package com.moringaschool.student.config;

import com.moringaschool.student.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class DBConfig {

    private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
    public static Connection getConnection(){

        Map<String, String> env = System.getenv();
        String DB_HOST;
        String DB_USER;
        String DB_DATABASE;
        String DB_PASSWORD;
        String DB_PORT;
        String DB_ENVIRONMENT =env.get("DB_ENVIRONMENT ");
        String requestRefId = UUID.randomUUID().toString();

      if(Objects.equals(DB_ENVIRONMENT, "PRODUCTION")){
        DB_HOST =env.get("DB_HOST");
        DB_USER =env.get("DB_USER");
        DB_DATABASE =env.get("DB_DATABASE");
        DB_PASSWORD =env.get("DB_PASSWORD");
       DB_PORT =env.get("DB_PORT");
       }
       else{
         DB_HOST ="localhost";
         DB_DATABASE ="herosquad";
         DB_PORT ="5432";
         DB_USER ="postgres";
         DB_PASSWORD ="postgres";

       }
        //ONLINE DATABASE - INTERNET
        Sql2o sql2o = new Sql2o("jdbc:postgresql://"+DB_HOST+":"+DB_PORT+"/"+DB_DATABASE,DB_USER,DB_PASSWORD);

        //LOCAL DATABASE
//        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/herosquad","postgres","postgres");

        try(Connection connection= sql2o.open()){
            logger.info("requestRefId = "+requestRefId + " | statusCode = 200 | status = Success | message = ** DB CONNECTION SUCCESS ** "+connection.toString()+"  |");
            return connection;
        }
        catch(Exception exception){
            logger.warn("requestRefId = "+requestRefId + " | statusCode = 500 | status = Internal Server Error | message = ** DB CONNECTION ERROR **  "+ exception.getLocalizedMessage()+"  |");
            return null;
        }
    }

}
