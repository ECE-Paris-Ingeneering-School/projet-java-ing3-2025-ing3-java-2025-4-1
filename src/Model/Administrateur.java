package Model;

public class Administrateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    public Administrateur(int id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Administrateur(String nom, String prenom, String email, String motDePasse) {
        this(0, nom, prenom, email, motDePasse);
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

    public String getMotDePasse() {
        return motDePasse;
    }
}
