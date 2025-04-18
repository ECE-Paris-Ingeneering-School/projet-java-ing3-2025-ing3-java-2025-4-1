package Model;

public class Lieu {
    private int id;
    private String nomEtablissement;
    private String adresse;
    private String ville;
    private String codePostal;

    public Lieu(int id, String nomEtablissement, String adresse, String ville, String codePostal) {
        this.id = id;
        this.nomEtablissement = nomEtablissement;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public Lieu(String nomEtablissement, String adresse, String ville, String codePostal) {
        this(0, nomEtablissement, adresse, ville, codePostal);
    }

    public int getId() {
        return id;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }
}
