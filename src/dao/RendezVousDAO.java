package dao;

import Model.RendezVous;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {

    public boolean create(RendezVous rdv) {
        String sql = "INSERT INTO RendezVous (date_heure, id_patient, id_specialiste, id_lieu) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateHeure()));
            stmt.setInt(2, rdv.getIdPatient());
            stmt.setInt(3, rdv.getIdSpecialiste());
            stmt.setInt(4, rdv.getIdLieu());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RendezVous findById(int id) {
        String sql = "SELECT * FROM RendezVous WHERE id_rdv = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RendezVous(
                        rs.getInt("id_rdv"),
                        rs.getTimestamp("date_heure").toLocalDateTime(),
                        rs.getInt("id_patient"),
                        rs.getInt("id_specialiste"),
                        rs.getInt("id_lieu")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

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
                        rs.getInt("id_lieu")
                );
                liste.add(rdv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    public boolean update(RendezVous rdv) {
        String sql = "UPDATE RendezVous SET date_heure = ?, id_patient = ?, id_specialiste = ?, id_lieu = ? WHERE id_rdv = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateHeure()));
            stmt.setInt(2, rdv.getIdPatient());
            stmt.setInt(3, rdv.getIdSpecialiste());
            stmt.setInt(4, rdv.getIdLieu());
            stmt.setInt(5, rdv.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
                        rs.getInt("id_lieu")
                );
                liste.add(rdv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }
}
