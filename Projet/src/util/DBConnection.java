package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/rdv_specialiste"; // adapte si besoin
    private static final String USER = "root";
    private static final String PASSWORD = ""; // ajoute ton mot de passe si t’en as mis un

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
