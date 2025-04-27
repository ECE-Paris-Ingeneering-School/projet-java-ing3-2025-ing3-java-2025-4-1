package Model;

/**
 * Représente un spécialiste de l'application.
 * Chaque spécialiste possède une spécialisation, une qualification
 * et peut être affecté à un ou plusieurs lieux.
 *
 */
public class Specialiste {
    private int id;
    private String nom;
    private String prenom;
    private String specialisation;
    private String qualification;

    /**
     * Constructeur complet avec identifiant.
     *
     * @param id Identifiant du spécialiste.
     * @param nom Nom de famille.
     * @param prenom Prénom.
     * @param specialisation Spécialisation (ex: cardiologue).
     * @param qualification Qualification (ex: docteur, professeur).
     */
    public Specialiste(int id, String nom, String prenom, String specialisation, String qualification) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.specialisation = specialisation;
        this.qualification = qualification;
    }

    /**
     * Constructeur sans identifiant (ex: insertion en BDD).
     *
     * @param nom Nom.
     * @param prenom Prénom.
     * @param specialisation Spécialisation médicale.
     * @param qualification Niveau ou type de qualification.
     */
    public Specialiste(String nom, String prenom, String specialisation, String qualification) {
        this(0, nom, prenom, specialisation, qualification);
    }

    /** @return L'identifiant du spécialiste. */
    public int getId() {
        return id;
    }

    /** @return Le nom de famille. */
    public String getNom() {
        return nom;
    }

    /** @return Le prénom. */
    public String getPrenom() {
        return prenom;
    }

    /** @return La spécialisation du spécialiste. */
    public String getSpecialisation() {
        return specialisation;
    }

    /** @return La qualification du spécialiste. */
    public String getQualification() {
        return qualification;
    }
}
