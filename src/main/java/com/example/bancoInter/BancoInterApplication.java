package com.example.bancoInter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class BancoInterApplication {

	public static void main(String[] args) throws SQLException {
		// SpringApplication.run(BancoInterApplication.class, args);
		connectDB();
	}

	public static void connectDB() throws SQLException {
		String url = "jdbc:mysql://localhost:3306";
		String user = "root";
		String password = null;
		try (Connection conn = DriverManager.getConnection(url, user, null)) {
			System.out.println("Successfully connected with server!");
			createDB("DB_BancoInter", conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createDB(String db_name, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE DATABASE IF NOT EXISTS " + db_name);
		conn = ClassConnection.getConnection();
		System.out.println("Successfully created database " + db_name + "!");
		conn.close();
	}
}


