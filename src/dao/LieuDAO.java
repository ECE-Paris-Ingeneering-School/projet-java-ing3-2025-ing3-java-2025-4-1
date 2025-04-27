package dao;

import Model.Lieu;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) pour la gestion des lieux dans la base de données.
 * Fournit les opérations de création, lecture, mise à jour et suppression (CRUD).
 * Utilise JDBC avec une connexion via {@link DBConnection}.
 *
 */
public class LieuDAO {

    /**
     * Crée un nouveau lieu dans la base de données.
     *
     * @param lieu L'objet Lieu à insérer.
     * @return true si l'insertion est réussie, false sinon.
     */
    public boolean create(Lieu lieu) {
        String sql = "INSERT INTO Lieu (nom_etablissement, adresse, ville, code_postal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lieu.getNomEtablissement());
            stmt.setString(2, lieu.getAdresse());
            stmt.setString(3, lieu.getVille());
            stmt.setString(4, lieu.getCodePostal());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recherche un lieu par son identifiant.
     *
     * @param id L'identifiant du lieu.
     * @return Le lieu correspondant ou null s'il n'existe pas.
     */
    public Lieu findById(int id) {
        String sql = "SELECT * FROM Lieu WHERE id_lieu = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Lieu(
                        rs.getInt("id_lieu"),
                        rs.getString("nom_etablissement"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("code_postal")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Récupère la liste de tous les lieux de la base de données.
     *
     * @return Liste des lieux.
     */
    public List<Lieu> findAll() {
        List<Lieu> lieux = new ArrayList<>();
        String sql = "SELECT * FROM Lieu";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lieux.add(new Lieu(
                        rs.getInt("id_lieu"),
                        rs.getString("nom_etablissement"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("code_postal")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lieux;
    }

    /**
     * Met à jour un lieu existant.
     *
     * @param lieu Lieu avec les nouvelles informations.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean update(Lieu lieu) {
        String sql = "UPDATE Lieu SET nom_etablissement = ?, adresse = ?, ville = ?, code_postal = ? WHERE id_lieu = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lieu.getNomEtablissement());
            stmt.setString(2, lieu.getAdresse());
            stmt.setString(3, lieu.getVille());
            stmt.setString(4, lieu.getCodePostal());
            stmt.setInt(5, lieu.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Supprime un lieu de la base de données.
     *
     * @param id Identifiant du lieu à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM Lieu WHERE id_lieu = ?";

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
