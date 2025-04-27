package dao;

import Model.Patient;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) pour la gestion des patients dans la base de données.
 * Implémente les opérations de création, lecture, mise à jour et suppression (CRUD),
 * ainsi que l’authentification par email et mot de passe.
 * Utilise JDBC pour accéder aux données.
 *
 */
public class PatientDAO {

    /**
     * Insère un nouveau patient dans la base de données.
     *
     * @param patient Le patient à insérer.
     * @return true si l'insertion est réussie, false sinon.
     */
    public boolean create(Patient patient) {
        String sql = "INSERT INTO Patient (nom, prenom, email, mot_de_passe, type_patient) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getEmail());
            stmt.setString(4, patient.getMotDePasse());
            stmt.setString(5, patient.getTypePatient());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Récupère tous les patients de la base de données.
     *
     * @return Une liste de tous les patients.
     */
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("id_patient"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("type_patient")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    /**
     * Met à jour les informations d’un patient dans la base.
     *
     * @param patient Le patient avec les nouvelles informations.
     * @return true si la mise à jour est réussie, false sinon.
     */
    public boolean update(Patient patient) {
        String sql = "UPDATE Patient SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, type_patient = ? WHERE id_patient = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getEmail());
            stmt.setString(4, patient.getMotDePasse());
            stmt.setString(5, patient.getTypePatient());
            stmt.setInt(6, patient.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Supprime un patient de la base de données.
     *
     * @param id Identifiant du patient à supprimer.
     * @return true si la suppression est réussie, false sinon.
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM Patient WHERE id_patient = ?";

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
     * Recherche un patient à partir de son email et mot de passe.
     *
     * @param email Email saisi.
     * @param password Mot de passe saisi.
     * @return Le patient correspondant ou null s’il n’existe pas.
     */
    public Patient findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM Patient WHERE email = ? AND mot_de_passe = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Patient(
                        rs.getInt("id_patient"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("type_patient")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
