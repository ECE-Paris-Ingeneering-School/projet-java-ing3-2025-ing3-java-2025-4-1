package dao;

import util.DBConnection;
import Model.Specialiste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la gestion des affectations entre spécialistes et lieux.
 * Gère la relation many-to-many via la table associative `specialiste_lieu`.
 * Permet de lier/délier des spécialistes à des lieux, et d'effectuer des requêtes croisées.
 *
 * Cette classe est essentielle pour :
 * - Déterminer les lieux où exerce un spécialiste
 * - Filtrer les spécialistes disponibles dans un lieu donné
 *
 */
public class SpecialisteLieuDAO {

    /**
     * Affecte un spécialiste à un lieu donné.
     *
     * @param idSpecialiste ID du spécialiste.
     * @param idLieu ID du lieu.
     * @return true si l'insertion a réussi.
     */
    public boolean create(int idSpecialiste, int idLieu) {
        String sql = "INSERT INTO specialiste_lieu (id_specialiste, id_lieu) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idSpecialiste);
            stmt.setInt(2, idLieu);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Supprime une affectation spécialiste-lieu.
     *
     * @param idSpecialiste ID du spécialiste.
     * @param idLieu ID du lieu.
     * @return true si la suppression a réussi.
     */
    public boolean delete(int idSpecialiste, int idLieu) {
        String sql = "DELETE FROM specialiste_lieu WHERE id_specialiste = ? AND id_lieu = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idSpecialiste);
            stmt.setInt(2, idLieu);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retourne toutes les affectations actuelles entre spécialistes et lieux.
     *
     * @return Liste de chaînes de caractères représentant les couples affectés.
     */
    public List<String[]> findAll() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT s.id_specialiste, s.nom, s.prenom, l.id_lieu, l.nom_etablissement, l.ville " +
                "FROM specialiste_lieu sl " +
                "JOIN Specialiste s ON sl.id_specialiste = s.id_specialiste " +
                "JOIN Lieu l ON sl.id_lieu = l.id_lieu";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new String[] {
                        String.valueOf(rs.getInt("id_specialiste")),
                        rs.getString("nom") + " " + rs.getString("prenom"),
                        String.valueOf(rs.getInt("id_lieu")),
                        rs.getString("nom_etablissement") + " - " + rs.getString("ville")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Récupère tous les spécialistes affectés à un lieu spécifique.
     *
     * @param idLieu ID du lieu concerné.
     * @return Liste des spécialistes rattachés à ce lieu.
     */
    public List<Specialiste> findSpecialistesByLieu(int idLieu) {
        List<Specialiste> list = new ArrayList<>();
        String sql = "SELECT s.* FROM Specialiste s " +
                "JOIN specialiste_lieu sl ON s.id_specialiste = sl.id_specialiste " +
                "WHERE sl.id_lieu = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLieu);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Specialiste(
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

        return list;
    }
}
