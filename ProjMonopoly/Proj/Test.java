package Proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    List<String> luck = new ArrayList<String>();

    String[] lucktochange = {
            "Rendez-vous à la Rue de la Paix",
            "Avancer jusqu’à la case départ",
            "Rendez-vous à l’Avenue Henri-Martin. Si vous passez par la case départ, recevez M200",
            "Avancez au Boulevard de La Villette. Si vous passez par la case départ, recevez M200",
            "Vous êtes imposé pour les réparations de voirie à raison de M100 par maison et M150 par hôtel.",
            "Avancez jusqu’à la Gare de Lyon. Si vous passez par la case départ, recevez M200",
            "Vous avez gagné le prix de mots croisés. Recevez M100",
            "La banque vous verse un dividende de M50",
            "Vous êtes libéré de prison. Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue.",
            "Reculez de trois cases",
            "Aller en prison. Rendez-vous directement en prison. Ne franchissez pas par la case départ, ne touchez pas M200",
            "Faites des réparations dans toutes vos maisons. Versez pour chaque maison M50. Versez pour chaque hôtel M100",
            "Amende pour excès de vitesse M50",
            "Payez pour frais de scolarité M100",
            "Amende pour ivresse M100",
            "Votre immeuble et votre prêt rapportent. Vous devez toucher M200"
    };


    public Test() {
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
        System.out.println("1");
        Random rand = new Random();
        while (luck.size() < 15) {
            System.out.println("2");
            int alea = rand.nextInt(lucktochange.length-1);
            luck.add(lucktochange[alea]);
            lucktochange = RemoveTheElement(lucktochange, alea);
        }
        System.out.println("3");
    }

    public static void main(String[] args) {
        Test test = new Test();
        for (String s : test.luck) {
            System.out.println(s);
        }
    }
    
}
