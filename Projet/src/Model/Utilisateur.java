package Model;

public class Utilisateur {
    private String identifiant;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String email;

    // Constructeur
    public Utilisateur(String identifiant, String motDePasse, String nom, String prenom, String email) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Utilisateur() {
        this.identifiant = "romeo";
        this.motDePasse = "123456";
        this.nom = "Romeo";
        this.prenom = "Romeo";
        this.email = "romeo@gmail.com";
    }

    // Getters et Setters
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



