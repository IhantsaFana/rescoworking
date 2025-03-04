package rescoworking;

import java.util.Scanner;

public class Main {
    public static Salle[] salles = new Salle[5];

    public static void main(String[] args) {
        salles[0] = new Salle(1, 100000);
        salles[1] = new Salle(2, 200000);
        salles[2] = new Salle(3, 300000);
        salles[3] = new Salle(4, 400000);
        salles[4] = new Salle(5, 500000);

        Scanner sc = new Scanner(System.in);
        boolean quitter = false;  
        while (!quitter) {
            System.out.println("=================================================================");
            System.out.println("|---- Bienvenue dans le système de réservation de co-working ---|");
            System.out.println("=================================================================");
            System.out.println("1. Client");
            System.out.println("2. Administrateur");
            System.out.println("3. Quitter");
            System.out.print("Choisissez votre rôle : ");
            String choix = sc.nextLine();

            switch (choix) {
                case "1":
                    ClientManager.clientMenu(salles);
                    break;
                case "2":
                    Admin.adminMenu();
                    break;
                case "3":
                    quitter = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
        System.out.println("Au revoir ! Merci de votre visite et à bientôt dans notre espace de coworking.");
        sc.close();
    }
}
