package Model;

import java.time.LocalDateTime;

/**
 * Représente un rendez-vous entre un patient et un spécialiste dans un lieu donné,
 * à une date et heure spécifique. Le rendez-vous peut être noté après sa réalisation.
 *
 */
public class RendezVous {
    private int id;
    private LocalDateTime dateHeure;
    private int idPatient;
    private int idSpecialiste;
    private int idLieu;
    private int note;

    /**
     * Constructeur complet avec note.
     *
     * @param id Identifiant du rendez-vous.
     * @param dateHeure Date et heure du rendez-vous.
     * @param idPatient Identifiant du patient.
     * @param idSpecialiste Identifiant du spécialiste.
     * @param idLieu Identifiant du lieu.
     * @param note Note du rendez-vous (0 à 5).
     */
    public RendezVous(int id, LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu, int note) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.idPatient = idPatient;
        this.idSpecialiste = idSpecialiste;
        this.idLieu = idLieu;
        this.setNote(note);
    }

    /**
     * Constructeur sans identifiant ni note (valeur par défaut à 0).
     *
     * @param dateHeure Date et heure du rendez-vous.
     * @param idPatient Identifiant du patient.
     * @param idSpecialiste Identifiant du spécialiste.
     * @param idLieu Identifiant du lieu.
     */
    public RendezVous(LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu) {
        this(0, dateHeure, idPatient, idSpecialiste, idLieu, 0);
    }

    /**
     * Retourne la note du rendez-vous.
     *
     * @return Note entre 0 et 5.
     */
    public int getNote() {
        return note;
    }

    /**
     * Définit une note pour le rendez-vous (entre 0 et 5).
     *
     * @param note Note à attribuer.
     * @throws IllegalArgumentException si la note n’est pas entre 0 et 5.
     */
    public void setNote(int note) {
        if (note < 0 || note > 5) {
            throw new IllegalArgumentException("La note doit être entre 0 et 5");
        }
        this.note = note;
    }

    /** @return L'identifiant du rendez-vous. */
    public int getId() {
        return id;
    }

    /** @return La date et l'heure du rendez-vous. */
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    /** @return L'identifiant du patient. */
    public int getIdPatient() {
        return idPatient;
    }

    /** @return L'identifiant du spécialiste. */
    public int getIdSpecialiste() {
        return idSpecialiste;
    }

    /** @return L'identifiant du lieu. */
    public int getIdLieu() {
        return idLieu;
    }
}
