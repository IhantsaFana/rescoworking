package rescoworking;

public class Salle {
    private int numeroDeLaSalle;
    private int tarif;

    public Salle(int numeroDeLaSalle, int tarif) {
        this.numeroDeLaSalle = numeroDeLaSalle;
        this.tarif = tarif;
    }

    public int getNumeroDeLaSalle() {
        return numeroDeLaSalle;
    }

    public int getTarif() {
        return tarif;
    }
}
