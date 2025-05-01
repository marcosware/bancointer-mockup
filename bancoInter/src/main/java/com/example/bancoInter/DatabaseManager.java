package com.example.bancoInter;
import java.sql.*;

public class DatabaseManager {
    public static String cpfLogged;
    public static String cpfToPay;

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

    public static void defaultTables() throws SQLException {
        Connection conn = ClassConnection.getConnection();
        String[] clientesColumns = {"ID", "Nome", "CPF", "Telefone", "Email", "Senha"};
        String[] clientesDataTypes = {"INT AUTO_INCREMENT UNIQUE",
                "VARCHAR(255) NOT NULL",
                "CHAR(11) NOT NULL PRIMARY KEY",
                "VARCHAR(15)",
                "VARCHAR(255)",
                "VARCHAR(255)"
        };
        DatabaseManager.createTable("Clientes", clientesColumns, clientesDataTypes);
        String[] contasColumns = {"ID", "ClienteCPF", "Tipo", "Saldo", "Agencia"};
        String[] contasDataTypes = {"INT AUTO_INCREMENT PRIMARY KEY",
                "CHAR(11) NOT NULL",
                "VARCHAR(10)",
                "DECIMAL (15,2) DEFAULT 100.00",
                "INT, FOREIGN KEY (ClienteCPF) REFERENCES CLIENTES (CPF)"
        };
        DatabaseManager.createTable("Contas", contasColumns, contasDataTypes);
        String[] loggedColumns = {"ID", "CPF"};
        String[] loggedDataTypes = {"INT AUTO_INCREMENT PRIMARY KEY", "CHAR(11) NOT NULL, FOREIGN KEY (CPF) REFERENCES CLIENTES (CPF)"};
        DatabaseManager.createTable("Logged", loggedColumns, loggedDataTypes);
        String[] nullCPF = {"0"};
        DatabaseManager.insertAll("Logged", nullCPF);
    }

    public static void createTable(String table, String[] columns, String[] datatypes) throws SQLException {
        Connection conn = ClassConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS " + table + " (";
        for(int i = 0; i < columns.length - 1; i++) {
            query += columns[i];
            query += " ";
            query += datatypes[i];
            query += ", ";
        }
        query += columns[columns.length - 1];
        query += " ";
        query += datatypes[columns.length - 1];
        query += ");";
        System.out.println(query);
        stmt.execute(query);
    }

    public static void insertColumns(String table, String[] columns, String[] values) throws SQLException {
        Connection conn = ClassConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "INSERT INTO " + table + " (";
        for (int i = 0 ; i < columns.length - 1 ; i++){
                query += columns[i];
                query += ", ";
        }
        query += columns[columns.length - 1];
        query += ")\nVALUES (";

        for(int i = 0; i < values.length - 1; i++){
            query += "'";
            query += values[i];
            query += "', ";
        }
        query += "'";
        query += values[values.length - 1];
        query += "'";
        query += ");";
        System.out.println(query);
        stmt.execute(query);
    }

    public static void insertAll(String table, String [] values) throws SQLException{
        Connection conn = ClassConnection.getConnection();
        Statement stmt = conn.createStatement();
        String lastIdQuery = "SELECT COUNT(ID) FROM " + table + ";";
        ResultSet lastId = stmt.executeQuery(lastIdQuery);
        lastId.next();
        int id = Integer.parseInt(lastId.getString(1));
        id += 1;
        System.out.println(id);
        String query = "INSERT INTO " + table + " VALUES (" + id + ", ";
        for (int i = 0; i < values.length - 1; i++ ) {
            query += "'";
            query += values[i];
            query += "', ";
        }
        query += "'";
        query += values[values.length - 1];
        query += "'";
        query += ");";
        System.out.println(query);
        stmt.execute(query);
    }
    public static boolean checkLogin(String cpf, String senha) throws SQLException {
        Connection conn = ClassConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT Senha FROM Clientes WHERE CPF = '" + cpf + "'";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        boolean result = false;
        if (!rs.getString("Senha").isBlank() && senha.equals(rs.getString("Senha"))){
            result = true;
            // query = "UPDATE Logged SET CPF = '" + cpf + "'";
            // stmt.execute(query);
        }
        return result;
    }

    public static String select(String table, String column, String where) throws SQLException {
        Connection conn = ClassConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT " + column + " FROM " + table + " WHERE " + where;
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        return rs.getString(1);
    }

    public static void update(String table, String column, String value, String where) throws SQLException {
        Connection conn = ClassConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "UPDATE " + table + " SET " + column + " = " + value + " WHERE " + where;
        System.out.println(query);
        stmt.execute(query);
    }
}
