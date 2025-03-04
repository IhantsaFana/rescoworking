package rescoworking;

public class Client {
    private String numeroTel;
    private Salle salle;
    private int heureDebut;
    private int heureFin;
    private int montantTotal;
    private int avance;
    private int reste;

    public Client(String numeroTel, Salle salle, int heureDebut, int heureFin, int montantTotal, int avance, int reste) {
        this.numeroTel = numeroTel;
        this.salle = salle;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.montantTotal = montantTotal;
        this.avance = avance;
        this.reste = reste;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public Salle getSalle() {
        return salle;
    }

    public int getHeureDeDebut() {
        return heureDebut;
    }

    public int getHeureDeFin() {
        return heureFin;
    }

    public int getMontantTotal() {
        return montantTotal;
    }

    public int getAvance() {
        return avance;
    }

    public int getReste() {
        return reste;
    }
}
