package Model;

public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String typePatient;

    public Patient(int id, String nom, String prenom, String email, String motDePasse, String typePatient) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.typePatient = typePatient;
    }

    public Patient(String nom, String prenom, String email, String motDePasse, String typePatient) {
        this(0, nom, prenom, email, motDePasse, typePatient);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTypePatient() {
        return typePatient;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

}
