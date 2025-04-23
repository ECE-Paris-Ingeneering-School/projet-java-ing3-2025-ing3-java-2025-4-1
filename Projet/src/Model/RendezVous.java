package Model;

import java.time.LocalDateTime;

public class RendezVous {
    private int id;
    private LocalDateTime dateHeure;
    private int idPatient;
    private int idSpecialiste;
    private int idLieu;

    public RendezVous(int id, LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.idPatient = idPatient;
        this.idSpecialiste = idSpecialiste;
        this.idLieu = idLieu;
    }

    public RendezVous(LocalDateTime dateHeure, int idPatient, int idSpecialiste, int idLieu) {
        this(0, dateHeure, idPatient, idSpecialiste, idLieu);
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
