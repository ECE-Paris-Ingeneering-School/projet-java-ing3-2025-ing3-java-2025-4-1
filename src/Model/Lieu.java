package Model;

/**
 * Représente un lieu où un spécialiste peut exercer.
 * Un lieu contient un nom d'établissement, une adresse, une ville et un code postal.
 *
 */
public class Lieu {
    private int id;
    private String nomEtablissement;
    private String adresse;
    private String ville;
    private String codePostal;

    /**
     * Constructeur complet.
     *
     * @param id Identifiant unique du lieu.
     * @param nomEtablissement Nom de l'établissement.
     * @param adresse Adresse postale complète.
     * @param ville Ville où se situe le lieu.
     * @param codePostal Code postal de la ville.
     */
    public Lieu(int id, String nomEtablissement, String adresse, String ville, String codePostal) {
        this.id = id;
        this.nomEtablissement = nomEtablissement;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    /**
     * Constructeur sans identifiant, utilisé pour les insertions en base.
     *
     * @param nomEtablissement Nom de l'établissement.
     * @param adresse Adresse postale.
     * @param ville Ville.
     * @param codePostal Code postal.
     */
    public Lieu(String nomEtablissement, String adresse, String ville, String codePostal) {
        this(0, nomEtablissement, adresse, ville, codePostal);
    }

    /**
     * Récupère l'identifiant du lieu.
     * @return L'identifiant.
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom de l'établissement.
     * @return Le nom de l'établissement.
     */
    public String getNomEtablissement() {
        return nomEtablissement;
    }

    /**
     * Récupère l'adresse du lieu.
     * @return L'adresse.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Récupère la ville du lieu.
     * @return La ville.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Récupère le code postal du lieu.
     * @return Le code postal.
     */
    public String getCodePostal() {
        return codePostal;
    }
}
