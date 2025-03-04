package rescoworking;

import java.util.Scanner;

public class ClientManager {
    public static Client[] listeClients = new Client[50];
    public static int nombreClients = 0;

    public static void clientMenu(Salle[] salles) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=======================================");
        System.out.println("|---- Menu de réservation client ----|");
        System.out.println("=======================================");
        System.out.println("Liste des salles disponibles :");
        for (int i = 0; i < salles.length; i++) {
            System.out.println("Salle " + salles[i].getNumeroDeLaSalle() + " - Tarif : " + salles[i].getTarif() + " Ar/heure");
        }

        // Vérification du numéro de téléphone
        String numeroTel;
        while (true) {
            System.out.print("Entrez votre numéro de téléphone (10 chiffres) : ");
            numeroTel = sc.nextLine();
            if (numeroTel.matches("03\\d{8}")) {
                break;
            } else {
                System.out.println("❌ Numéro invalide ! Il doit commencer par 0 et contenir 10 chiffres.");
            }
        }

        // Choix de la salle avec vérification
        int numeroSalle = -1;
        while (true) {
            System.out.print("Choisissez une salle (1 à " + salles.length + ") : ");
            if (sc.hasNextInt()) {
                numeroSalle = sc.nextInt();
                if (numeroSalle >= 1 && numeroSalle <= salles.length) {
                    break;
                }
            }
            sc.nextLine(); // Éviter une boucle infinie
            System.out.println("❌ Numéro de salle invalide. Veuillez réessayer.");
        }

        // Vérification des heures de réservation
        int heureDebut, heureFin;
        while (true) {
            System.out.print("Entrez l'heure de début (8 à 18) : ");
            if (sc.hasNextInt()) {
                heureDebut = sc.nextInt();
                if (heureDebut >= 8 && heureDebut <= 18) {
                    break;
                }
            }
            sc.nextLine();
            System.out.println("❌ L'heure de début doit être entre 8h et 18h.");
        }

        while (true) {
            System.out.print("Entrez l'heure de fin (doit être après " + heureDebut + "h et ≤ 18h) : ");
            if (sc.hasNextInt()) {
                heureFin = sc.nextInt();
                if (heureFin > heureDebut && heureFin <= 18) {
                    break;
                }
            }
            sc.nextLine();
            System.out.println("❌ L'heure de fin doit être après l'heure de début et au maximum à 18h.");
        }

        sc.nextLine(); // Nettoyage du buffer

        Salle salleChoisie = salles[numeroSalle - 1];
        int montantTotal = salleChoisie.getTarif() * (heureFin - heureDebut);

        // Vérification du paiement
        int avance;
        while (true) {
            System.out.print("Montant à payer en avance : ");
            if (sc.hasNextInt()) {
                avance = sc.nextInt();
                if (avance >= 0 && avance <= montantTotal) {
                    break;
                }
            }
            sc.nextLine();
            System.out.println("❌ L'avance doit être entre 0 et " + montantTotal + " Ar.");
        }

        int reste = montantTotal - avance;
        sc.nextLine(); // Nettoyage du buffer

        // Vérification de la disponibilité de la salle
        if (!estDisponible(salleChoisie, heureDebut, heureFin)) {
            System.out.println("❌ La salle " + salleChoisie.getNumeroDeLaSalle() + " est déjà réservée sur ce créneau !");
            return;
        }

        // Enregistrement du client
        Client nouveauClient = new Client(numeroTel, salleChoisie, heureDebut, heureFin, montantTotal, avance, reste);
        listeClients[nombreClients++] = nouveauClient;

        System.out.println("✅ Réservation enregistrée avec succès !");
    }

    private static boolean estDisponible(Salle salle, int debut, int fin) {
        for (int i = 0; i < nombreClients; i++) {
            Client client = listeClients[i];
            if (client.getSalle().getNumeroDeLaSalle() == salle.getNumeroDeLaSalle()) {
                if ((debut >= client.getHeureDeDebut() && debut < client.getHeureDeFin()) ||
                    (fin > client.getHeureDeDebut() && fin <= client.getHeureDeFin()) ||
                    (debut <= client.getHeureDeDebut() && fin >= client.getHeureDeFin())) {
                    return false; // Conflit d'horaire détecté
                }
            }
        }
        return true;
    }
}
