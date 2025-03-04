package rescoworking;

import java.util.Scanner;

public class Admin {
    private static final String motDePasse = "admin!";

    public static boolean login(String mdp) {
        return motDePasse.equals(mdp);
    }

    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        int tentatives = 0;
        while (tentatives < 3) {
            System.out.print("Entrez le mot de passe administrateur : ");
            String mdp = sc.nextLine();
            if (login(mdp)) {
                break;
            }
            tentatives++;
            if (tentatives < 3) {
                System.out.println("Mot de passe incorrect. Il vous reste " + (3 - tentatives) + " tentative(s).");
            }
        }
        if (tentatives == 3) {
            System.out.println("Erreur : Trop de tentatives erronées. Redémarrez le système.");
            System.exit(0);
        }

        afficherDashboard();
    }

    private static void afficherDashboard() {
        System.out.println("=========================================");
        System.out.println("|---- Tableau de bord Administrateur ---|");
        System.out.println("=========================================");
        
        int[] totalRecettes = new int[5];
        int[] totalRestes = new int[5];

        if (ClientManager.nombreClients == 0) {
            System.out.println("Aucune réservation enregistrée.");
        } else {
            for (int i = 0; i < ClientManager.nombreClients; i++) {
                Client client = ClientManager.listeClients[i];
                int indexSalle = client.getSalle().getNumeroDeLaSalle() - 1;
                
                totalRecettes[indexSalle] += client.getAvance();
                totalRestes[indexSalle] += client.getReste();
            }

            for (int i = 0; i < totalRecettes.length; i++) {
                System.out.println("Salle " + (i + 1) + " : Reçue : " + totalRecettes[i] + " Ar, Restes : " + totalRestes[i] + " Ar");
            }
        }
    }
}
