package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour obtenir une connexion JDBC à la base de données MySQL.
 * Elle utilise les paramètres définis en constantes (URL, USER, PASSWORD).
 *
 * Cette classe permet à toutes les DAO de centraliser leur connexion via
 * un simple appel à {@link #getConnection()}.
 *
 *
 */
public class DBConnection {

    /** URL de connexion à la base de données MySQL. */
    private static final String URL = "jdbc:mysql://localhost:3306/rdv_specialiste";

    /** Nom d'utilisateur MySQL. */
    private static final String USER = "root";

    /** Mot de passe MySQL (vide par défaut). */
    private static final String PASSWORD = "";

    /**
     * Fournit une connexion JDBC active à la base de données.
     *
     * @return Une instance {@link Connection} valide.
     * @throws SQLException si la connexion échoue.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
