package dao;

import Model.Administrateur;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) pour la gestion des administrateurs.
 * Fournit l'accès à la base de données pour authentifier un administrateur
 * via son email et son mot de passe.
 *
 */
public class AdministrateurDAO {

    /**
     * Recherche un administrateur en base par email et mot de passe.
     *
     * @param email L'adresse email saisie.
     * @param password Le mot de passe saisi.
     * @return Un objet {@link Administrateur} si les identifiants sont valides, sinon null.
     */
    public Administrateur findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM administrateur WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Administrateur(
                        rs.getInt("id_admin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
