package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver não encontrado!"+e);
            }
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/devWeb-Trabalho?user='root'&password='2723@Kza&useSSL=false&allowPublicKeyRetrieval=true", "root", "2723@Kza");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}