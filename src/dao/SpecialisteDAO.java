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
}