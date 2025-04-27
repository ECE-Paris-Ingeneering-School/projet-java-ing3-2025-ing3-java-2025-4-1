package Model;

/**
 * Représente un patient de l'application.
 * Chaque patient possède un identifiant, des informations personnelles
 * et un type (nouveau ou ancien).
 *
 */
public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String typePatient;

    /**
     * Constructeur complet avec identifiant.
     *
     * @param id Identifiant du patient.
     * @param nom Nom de famille.
     * @param prenom Prénom.
     * @param email Adresse email.
     * @param motDePasse Mot de passe.
     * @param typePatient Type de patient (nouveau ou ancien).
     */
    public Patient(int id, String nom, String prenom, String email, String motDePasse, String typePatient) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.typePatient = typePatient;
    }

    /**
     * Constructeur sans identifiant (utilisé avant insertion en BDD).
     *
     * @param nom Nom de famille.
     * @param prenom Prénom.
     * @param email Adresse email.
     * @param motDePasse Mot de passe.
     * @param typePatient Type de patient (nouveau ou ancien).
     */
    public Patient(String nom, String prenom, String email, String motDePasse, String typePatient) {
        this(0, nom, prenom, email, motDePasse, typePatient);
    }

    /** @return l'identifiant du patient. */
    public int getId() { return id; }

    /** @return l'adresse email du patient. */
    public String getEmail() { return email; }

    /** @return le nom du patient. */
    public String getNom() { return nom; }

    /** @return le prénom du patient. */
    public String getPrenom() { return prenom; }

    /** @return le type de patient (nouveau / ancien). */
    public String getTypePatient() { return typePatient; }

    /** @return le mot de passe du patient. */
    public String getMotDePasse() { return motDePasse; }

    /** @param nom le nouveau nom à définir. */
    public void setNom(String nom) { this.nom = nom; }

    /** @param prenom le nouveau prénom à définir. */
    public void setPrenom(String prenom) { this.prenom = prenom; }

    /** @param email la nouvelle adresse email. */
    public void setEmail(String email) { this.email = email; }

    /** @param motDePasse le nouveau mot de passe. */
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    /** @param id l'identifiant à définir (utile pour set après insertion). */
    public void setId(int id) { this.id = id; }
}
