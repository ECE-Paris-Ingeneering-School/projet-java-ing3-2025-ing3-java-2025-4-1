package Model;

public class Specialiste {
    private int id;
    private String nom;
    private String prenom;
    private String specialisation;
    private String qualification;

    public Specialiste(int id, String nom, String prenom, String specialisation, String qualification) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialisation = specialisation;
        this.qualification = qualification;
    }

    public Specialiste(String nom, String prenom, String specialisation, String qualification) {
        this(0, nom, prenom, specialisation, qualification);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getQualification() {
        return qualification;
    }

}
