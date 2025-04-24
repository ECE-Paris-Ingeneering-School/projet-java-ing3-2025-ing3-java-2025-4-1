package Model;

import java.time.LocalDateTime;

public class RendezVous {
    private int id;
    private LocalDateTime dateHeure;
    private int idPatient;
    private int idSpecialiste;
    private int idLieu;
    private int note;

    public RendezVous(int id, LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu, int note) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.idPatient = idPatient;
        this.idSpecialiste = idSpecialiste;
        this.idLieu = idLieu;
        this.setNote(note);
    }

    public RendezVous(LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu) {
        this(0, dateHeure, idPatient, idSpecialiste, idLieu, 0); // null pour la note
    }

    public RendezVous(int id, LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu) {
        this(0, dateHeure, idPatient, idSpecialiste, idLieu, 0); // null pour la note
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        if (note < 0 || note > 5) {
            throw new IllegalArgumentException("La note doit être entre 0 et 5");
        }
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public int getIdSpecialiste() {
        return idSpecialiste;
    }

    public int getIdLieu() {
        return idLieu;
    }
}
