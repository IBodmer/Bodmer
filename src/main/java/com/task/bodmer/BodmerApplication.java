package com.task.bodmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class BodmerApplication {


    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BodmerApplication.class, args);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String sqlStr = "INSERT INTO users (id,user_name,email,\"password\") VALUES ('1','bodmer','pewpew308@gmail.com', '" + passwordEncoder.encode("1234") +"') ON CONFLICT DO NOTHING";
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bodmer_db", "postgres", "1234");
        PreparedStatement st = conn.prepareStatement(sqlStr);
        st.executeUpdate();
        st.close();
    }

}
