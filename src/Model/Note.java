package Model;

public class Note {
    private int id;
    private int note;
    private String commentaire;
    private int idRdv;

    public Note(int id, int note, String commentaire, int idRdv) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.idRdv = idRdv;
    }

    public Note(int note, String commentaire, int idRdv) {
        this(0, note, commentaire, idRdv);
    }

    public int getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getIdRdv() {
        return idRdv;
    }
}
