package Model;

/**
 * Représente un administrateur de l'application.
 * Un administrateur possède un identifiant, un nom, un prénom,
 * un email et un mot de passe pour accéder aux fonctions d'administration.
 *
 */
public class Administrateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    /**
     * Constructeur complet avec identifiant.
     *
     * @param id Identifiant unique.
     * @param nom Nom de famille de l'administrateur.
     * @param prenom Prénom.
     * @param email Adresse email.
     * @param motDePasse Mot de passe.
     */
    public Administrateur(int id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    /**
     * Constructeur sans identifiant (ex. pour insertion en base).
     *
     * @param nom Nom de famille.
     * @param prenom Prénom.
     * @param email Email.
     * @param motDePasse Mot de passe.
     */
    public Administrateur(String nom, String prenom, String email, String motDePasse) {
        this(0, nom, prenom, email, motDePasse);
    }

    /** @return l'identifiant de l'administrateur. */
    public int getId() {
        return id;
    }

    /** @return l'adresse email. */
    public String getEmail() {
        return email;
    }

    /** @return le nom de famille. */
    public String getNom() {
        return nom;
    }

    /** @return le prénom. */
    public String getPrenom() {
        return prenom;
    }

    /** @return le mot de passe. */
    public String getMotDePasse() {
        return motDePasse;
    }
}
