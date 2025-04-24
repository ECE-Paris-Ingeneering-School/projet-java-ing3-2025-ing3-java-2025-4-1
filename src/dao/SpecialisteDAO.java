package dao;

import Model.Specialiste;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialisteDAO {

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

    public List<String> findAllSpecialisations() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT specialisation FROM Specialiste";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getString("specialisation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> findAllQualifications() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT qualification FROM Specialiste";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getString("qualification"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Specialiste> findBySpecialisationAndQualification(String specialisation, String qualification) {
        List<Specialiste> list = new ArrayList<>();
        String sql = "SELECT * FROM Specialiste WHERE specialisation = ? AND qualification = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, specialisation);
            stmt.setString(2, qualification);
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
