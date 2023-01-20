package com.task.bodmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class BodmerApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BodmerApplication.class, args);
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bodmer_db", "postgres", "1234");
        PreparedStatement st = conn.prepareStatement("INSERT INTO users (id,user_name,email,\"password\") VALUES ('1','bodmer','pewpew308@gmail.com','1234') ON CONFLICT DO NOTHING");
        st.executeUpdate();
        st.close();
    }

}
