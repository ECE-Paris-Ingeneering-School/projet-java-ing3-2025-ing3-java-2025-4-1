package dao;

import Model.RendezVous;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * DAO pour la gestion des rendez-vous.
 * Fournit les opérations CRUD, les statistiques, et la génération de créneaux.
 * Utilise JDBC pour la communication avec la base de données.
 *
 */
public class RendezVousDAO {

    /**
     * Crée un nouveau rendez-vous.
     *
     * @param rdv Le rendez-vous à insérer.
     * @return true si l'insertion a réussi.
     */
    public boolean create(RendezVous rdv) {
        String sql = "INSERT INTO RendezVous (date_heure, id_patient, id_specialiste, id_lieu, note) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateHeure()));
            stmt.setInt(2, rdv.getIdPatient());
            stmt.setInt(3, rdv.getIdSpecialiste());
            stmt.setInt(4, rdv.getIdLieu());
            stmt.setInt(5, rdv.getNote());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Vérifie si un créneau est disponible pour un spécialiste et un lieu donnés.
     *
     * @param idSpecialiste Spécialiste concerné.
     * @param idLieu Lieu concerné.
     * @param dateHeure Date et heure souhaitée.
     * @return true si le créneau est libre.
     */
    public boolean isCreneauDisponible(int idSpecialiste, int idLieu, LocalDateTime dateHeure) {
        String sql = "SELECT COUNT(*) FROM RendezVous WHERE date_heure = ? AND (id_specialiste = ? OR id_lieu = ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(dateHeure));
            stmt.setInt(2, idSpecialiste);
            stmt.setInt(3, idLieu);

            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) == 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Récupère tous les rendez-vous.
     *
     * @return Liste complète des rendez-vous.
     */
    public List<RendezVous> findAll() {
        List<RendezVous> liste = new ArrayList<>();
        String sql = "SELECT * FROM RendezVous";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RendezVous rdv = new RendezVous(
                        rs.getInt("id_rdv"),
                        rs.getTimestamp("date_heure").toLocalDateTime(),
                        rs.getInt("id_patient"),
                        rs.getInt("id_specialiste"),
                        rs.getInt("id_lieu"),
                        rs.getInt("note")
                );
                liste.add(rdv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    /**
     * Met à jour un rendez-vous existant.
     *
     * @param rdv Le rendez-vous avec les nouvelles informations.
     * @return true si la mise à jour a réussi.
     */
    public boolean update(RendezVous rdv) {
        String sql = "UPDATE RendezVous SET date_heure = ?, id_patient = ?, id_specialiste = ?, id_lieu = ?, note = ? WHERE id_rdv = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateHeure()));
            stmt.setInt(2, rdv.getIdPatient());
            stmt.setInt(3, rdv.getIdSpecialiste());
            stmt.setInt(4, rdv.getIdLieu());
            stmt.setInt(5, rdv.getNote());
            stmt.setInt(6, rdv.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Supprime un rendez-vous.
     *
     * @param id L'identifiant du rendez-vous.
     * @return true si la suppression est réussie.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM RendezVous WHERE id_rdv = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Récupère les rendez-vous d’un patient donné.
     *
     * @param idPatient Identifiant du patient.
     * @return Liste des rendez-vous du patient.
     */
    public List<RendezVous> findByPatientId(int idPatient) {
        List<RendezVous> liste = new ArrayList<>();
        String sql = "SELECT * FROM RendezVous WHERE id_patient = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPatient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RendezVous rdv = new RendezVous(
                        rs.getInt("id_rdv"),
                        rs.getTimestamp("date_heure").toLocalDateTime(),
                        rs.getInt("id_patient"),
                        rs.getInt("id_specialiste"),
                        rs.getInt("id_lieu"),
                        rs.getInt("note")
                );
                liste.add(rdv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    /**
     * Récupère les rendez-vous enrichis de noms/prénoms pour affichage GUI.
     *
     * @return Liste détaillée de rendez-vous.
     */
    public List<String[]> findAllDetailed() {
        List<String[]> result = new ArrayList<>();
        String sql = "SELECT r.id_rdv, " +
                "p.nom AS patient_nom, p.prenom AS patient_prenom, " +
                "s.nom AS specialiste_nom, s.prenom AS specialiste_prenom, " +
                "l.nom_etablissement, l.ville, r.date_heure " +
                "FROM RendezVous r " +
                "JOIN Patient p ON r.id_patient = p.id_patient " +
                "JOIN Specialiste s ON r.id_specialiste = s.id_specialiste " +
                "JOIN Lieu l ON r.id_lieu = l.id_lieu " +
                "ORDER BY r.date_heure DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.add(new String[]{
                        String.valueOf(rs.getInt("id_rdv")),
                        rs.getString("patient_prenom") + " " + rs.getString("patient_nom"),
                        rs.getString("specialiste_prenom") + " " + rs.getString("specialiste_nom"),
                        rs.getString("nom_etablissement") + " - " + rs.getString("ville"),
                        rs.getTimestamp("date_heure").toString()
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Retourne le nombre de rendez-vous par spécialité.
     *
     * @return Map (spécialité → nombre de RDV).
     */
    public Map<String, Integer> countBySpecialite() {
        Map<String, Integer> result = new HashMap<>();
        String sql = "SELECT s.specialisation, COUNT(*) AS total FROM RendezVous r " +
                "JOIN Specialiste s ON r.id_specialiste = s.id_specialiste " +
                "GROUP BY s.specialisation";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.put(rs.getString("specialisation"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Retourne le nombre de rendez-vous par lieu.
     *
     * @return Map (lieu → nombre de RDV).
     */
    public Map<String, Integer> countByLieu() {
        Map<String, Integer> result = new HashMap<>();
        String sql = "SELECT l.nom_etablissement, COUNT(*) AS total FROM RendezVous r " +
                "JOIN Lieu l ON r.id_lieu = l.id_lieu " +
                "GROUP BY l.nom_etablissement";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.put(rs.getString("nom_etablissement"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Retourne le nombre de rendez-vous par mois (format YYYY-MM).
     *
     * @return Map (mois → nombre de RDV).
     */
    public Map<String, Integer> countByMois() {
        Map<String, Integer> result = new HashMap<>();
        String sql = "SELECT DATE_FORMAT(date_heure, '%Y-%m') AS mois, COUNT(*) AS total FROM RendezVous " +
                "GROUP BY mois ORDER BY mois";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                result.put(rs.getString("mois"), rs.getInt("total"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
