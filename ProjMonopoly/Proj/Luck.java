package Proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Luck {
    List<String> luck = new ArrayList<String>();

    String[] lucktochange = {"Avancez tout droit jusqu'à la case Départ.",
                 "Payez une amende de 10 euros ou tirez une carte Chance.",
                 "Allez en prison. Ne passez pas par la case Départ, ne recevez pas 200 euros.",
                 "Rendez-vous à la gare la plus proche et payez le double du loyer à son propriétaire s'il en a un.",
                 "Avancez jusqu'à la case Rue de la Paix.",
                 "Prenez une carte de la Caisse de communauté.",
                 "Avancez jusqu'à la case Rue de la Paix. Si vous passez par la case Départ, recevez 200 euros.",
                 "Payez pour les réparations de vos maisons et hôtels : 25 euros par maison, 100 euros par hôtel.",
                 "Rendez-vous à l'avenue Henri-Martin. Si vous passez par la case Départ, recevez 200 euros.",
                 "Retournez à la case départ.",
                 "Rendez-vous à la rue de la Gare. Si vous passez par la case Départ, recevez 200 euros.",
                 "Allez en prison. Ne passez pas par la case Départ, ne recevez pas 200 euros.",
                 "Payez une amende de 50 euros pour excès de vitesse.",
                 "Avancez jusqu'à la case la plus proche où vous pouvez acheter une maison. Si elle n'a pas de propriétaire, vous pouvez l'acheter. Si elle a un propriétaire, vous devez payer le double du loyer.",
                 "Payez une taxe de 15 euros pour les réparations de voirie.",
                 "Rendez-vous à la case 'Service public' le plus proche. Si elle n'a pas de propriétaire, vous pouvez l'acheter. Si elle a un propriétaire, vous devez payer le double du loyer.",
                 "Avancez jusqu'à la case 'Echec et mat'. Si vous passez par la case Départ, recevez 200 euros.",
                 "Avancez jusqu'à la case 'Boulevard de la Villette'. Si vous passez par la case Départ, recevez 200 euros.",
                 "Avancez jusqu'à la case 'Rue de la Paix'. Si vous passez par la case Départ, recevez 200 euros.",
                 "Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce que vous en ayez besoin, ou vendue."
                };


    public Luck() {
        Aleatoire();
    }

    public String[] RemoveTheElement(String[] lucktochange, int index) {
        String[] lucktochange2 = new String[lucktochange.length - 1];
        for (int i = 0, k = 0; i < lucktochange.length; i++) {
            if (i == index) {
                continue;
            }
            lucktochange2[k++] = lucktochange[i];
        }
        return lucktochange2;
    }

    public void Aleatoire() {
        Random rand = new Random();
        while (luck.size() < 20) {
            int alea = rand.nextInt(lucktochange.length-1);
            luck.add(lucktochange[alea]);
            lucktochange = RemoveTheElement(lucktochange, alea);
        }
    }

    public void Action(int playerId) {
        System.out.println("Player " + playerId + " has drawn a card: " + luck.get(0));
        String action = luck.remove(0);
        luck.add(action);
    }
}
