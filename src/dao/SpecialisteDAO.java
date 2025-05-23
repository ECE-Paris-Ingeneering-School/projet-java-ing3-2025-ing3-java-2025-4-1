package dao;

import Model.Specialiste;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la gestion des spécialistes dans la base de données.
 * Fournit les opérations CRUD (Create, Read, Update, Delete).
 * Utilise JDBC via {@link DBConnection} pour la communication avec MySQL.
 *
 * Cette classe permet notamment de :
 * - Créer de nouveaux spécialistes.
 * - Les retrouver par ID.
 * - Mettre à jour ou supprimer des spécialistes existants.
 *
 */
public class SpecialisteDAO {

    /**
     * Insère un nouveau spécialiste dans la base de données.
     *
     * @param specialiste L'objet spécialiste à insérer.
     * @return true si l'insertion a réussi.
     */
    public boolean create(Specialiste specialiste) {
        String sql = "INSERT INTO Specialiste (nom, prenom, specialisation, qualification) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, specialiste.getNom());
            stmt.setString(2, specialiste.getPrenom());
            stmt.setString(3, specialiste.getSpecialisation());
            stmt.setString(4, specialiste.getQualification());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recherche un spécialiste par son identifiant.
     *
     * @param id Identifiant du spécialiste.
     * @return L'objet {@link Specialiste} trouvé, ou null si aucun résultat.
     */
    public Specialiste findById(int id) {
        String sql = "SELECT * FROM Specialiste WHERE id_specialiste = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Specialiste(
                        rs.getInt("id_specialiste"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialisation"),
                        rs.getString("qualification")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Récupère la liste de tous les spécialistes présents dans la base.
     *
     * @return Une liste d'objets {@link Specialiste}.
     */
    public List<Specialiste> findAll() {
        List<Specialiste> specialistes = new ArrayList<>();
        String sql = "SELECT * FROM Specialiste";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                specialistes.add(new Specialiste(
                        rs.getInt("id_specialiste"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("specialisation"),
                        rs.getString("qualification")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialistes;
    }

    /**
     * Met à jour les informations d’un spécialiste.
     *
     * @param specialiste Le spécialiste contenant les nouvelles valeurs.
     * @return true si la mise à jour est effectuée.
     */
    public boolean update(Specialiste specialiste) {
        String sql = "UPDATE Specialiste SET nom = ?, prenom = ?, specialisation = ?, qualification = ? WHERE id_specialiste = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, specialiste.getNom());
            stmt.setString(2, specialiste.getPrenom());
            stmt.setString(3, specialiste.getSpecialisation());
            stmt.setString(4, specialiste.getQualification());
            stmt.setInt(5, specialiste.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Supprime un spécialiste de la base.
     *
     * @param id Identifiant du spécialiste à supprimer.
     * @return true si la suppression a été réussie.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM Specialiste WHERE id_specialiste = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
