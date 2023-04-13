package Monopoly;

import java.util.Random;
import java.util.ArrayList;

interface traduction {
    void perform(board b, ArrayList<player> p, int i);
}

public class luck {
    static traduction action;
    static String text;

    public luck() {
        String[] luck = {
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
        traduction[] trad = {
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).setPosition(39);b.getbox(39).action.perform(b, p, i);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).setPosition(0);b.getbox(0).action.perform(b, p, i);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {
                if (p.get(i).getPosition()>24) {p.get(i).addMoney(200);}p.get(i).setPosition(24);b.getbox(24).action.perform(b, p, i);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {
                if (p.get(i).getPosition()>11) {p.get(i).addMoney(200);}p.get(i).setPosition(11);b.getbox(11).action.perform(b, p, i);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {
                int nbMaison=0;int nbHotel=0;
                for(int j=0; j<p.get(i).getNbProperties();j++){
                    if(p.get(i).getProperties(j).type==type.LAND) { land pr = p.get(i).getLand(j);
                    try{if(pr.getNbHotels()==1){nbHotel++;}else{nbMaison+=pr.getNbHouses();}}catch(Exception e){}}}
                nbMaison = nbMaison*100 + nbHotel*150; p.get(i).removeMoney(nbMaison,p);}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {
                if (p.get(i).getPosition()>5) {p.get(i).addMoney(200);}p.get(i).setPosition(5);b.getbox(5).action.perform(b, p, i);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).addMoney(100);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).addMoney(50);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).setJailCard(true);} },
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {
                int pos = p.get(i).getPosition();
                if (pos<3) {p.get(i).setPosition(pos+37);b.getbox(pos+37).action.perform(b, p, i);}
                else{p.get(i).setPosition(pos-3);b.getbox(pos-3).action.perform(b, p, i);}}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).GoToJail();}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {
                int nbMaison=0;int nbHotel=0;
                for(int j=0; j<p.get(i).getNbProperties();j++){
                    if(p.get(i).getProperties(j).type==type.LAND) { land pr = p.get(i).getLand(j);
                    try{if(pr.getNbHotels()==1){nbHotel++;}else{nbMaison+=pr.getNbHouses();}}catch(Exception e){}}}
                nbMaison = nbMaison*100 + nbHotel*150; p.get(i).removeMoney(nbMaison,p);}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) { p.get(i).removeMoney(50,p);}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) { p.get(i).removeMoney(100,p);}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) { p.get(i).removeMoney(100,p);}},
            new traduction() {public void perform(board b, ArrayList<player> p, int i) {p.get(i).addMoney(200);} }
            };
		Random rand = new Random();
        int x=rand.nextInt(16);
        text = luck[x];
        action = trad[x];
    }

    public int onLuck(board b,ArrayList<player> p, int i) {
        System.out.println(text);
        action.perform(b,p,i);
        return 0;
    }
}
