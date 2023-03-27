package Proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Luck {
    List<String> luck = new ArrayList<String>();

    String[] lucktochange = {
        "Rendez-vous à la Rue de la Paix",
        "Avancer jusqu’à la case départ. Recevez M200",
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
        while (luck.size() < 15) {
            int alea = rand.nextInt(lucktochange.length-1);
            luck.add(lucktochange[alea]);
            lucktochange = RemoveTheElement(lucktochange, alea);
        }
    }

    // change the position of the player for all players and change the money of the player if he pass by the start
    public void ChangeMoneyPosition(int playerId, int money, int position) {
        Server.positions.set(playerId, position);
        Server.players.get(playerId).affichage.ChangePosition();
        if (Server.positions.get(playerId) > position || position == 0) {
            Server.players.get(playerId).sold += money;
            Server.players.get(playerId).affichage.all = "player " + playerId + " with " + Server.players.get(playerId).sold + Server.players.get(playerId).affichage.allproperties;
            for (Player player : Server.players) {
                if (playerId == 0) {
                    player.affichage.list1.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 1) {
                    player.affichage.list2.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 2) {
                    player.affichage.list3.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 3) {
                    player.affichage.list4.setText(Server.players.get(playerId).affichage.all);
                }
                player.affichage.ActPlayers(player, playerId);
            }
        }
        else {
            for (Player player : Server.players) {
                player.affichage.ActPlayers(player, playerId);
            }
        }
    }

    public void PayToPlayer(int playerId, int amount) {
        Server.players.get(playerId).sold += amount;
        Server.players.get(playerId).affichage.all = "player " + playerId + " with " + Server.players.get(playerId).sold + Server.players.get(playerId).affichage.allproperties;
        for (Player player : Server.players) {
            if (playerId == 0) {
                player.affichage.list1.setText(Server.players.get(playerId).affichage.all);
            }
            else if (playerId == 1) {
                player.affichage.list2.setText(Server.players.get(playerId).affichage.all);
            }
            else if (playerId == 2) {
                player.affichage.list3.setText(Server.players.get(playerId).affichage.all);
            }
            else if (playerId == 3) {
                player.affichage.list4.setText(Server.players.get(playerId).affichage.all);
            }
            player.affichage.ActPlayers(player, playerId);
        }
    }

    // if the case is not owned, the player can buy it or bid on it but if the case is owned, the player has to pay the owner
    public void ActionOfCase(int playerId) {
        if (Board.cases.get(Server.positions.get(playerId)).owner == null) {
            Server.players.get(playerId).affichage.playButton.setVisible(false);
            Server.players.get(playerId).affichage.Buy.setVisible(true);
            Server.players.get(playerId).affichage.Bid.setVisible(true);
        }
        else if (Board.cases.get(Server.positions.get(playerId)).owner != Server.players.get(playerId)) {
            Server.players.get(playerId).affichage.DisplayPaySomebody(Board.cases.get(Server.positions.get(playerId)));
        }
        else {
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }
    }

    public void Action(int playerId) {
        Server.players.get(playerId).affichage.message.setText(luck.get(0));
        System.out.println("Player " + playerId + " has drawn a card: " + luck.get(0));
        String action = luck.remove(0);
        luck.add(action);

        if (action.equals("Rendez-vous à la Rue de la Paix")) {
            ChangeMoneyPosition(playerId, 0, 39);
            ActionOfCase(playerId);
        }

        else if (action.equals("Avancer jusqu’à la case départ. Recevez M200")) {
            ChangeMoneyPosition(playerId, 200, 0);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Rendez-vous à l’Avenue Henri-Martin. Si vous passez par la case départ, recevez M200")) {
            ChangeMoneyPosition(playerId, 200, 24);
            ActionOfCase(playerId);
        }

        else if (action.equals("Avancez au Boulevard de La Villette. Si vous passez par la case départ, recevez M200")) {
            ChangeMoneyPosition(playerId, 200, 11);
            ActionOfCase(playerId);
        }

        else if (action.equals("Vous êtes imposé pour les réparations de voirie à raison de M100 par maison et M150 par hôtel.")) {
            int nbhouse = 0;
            int nbhotel = 0;
            for (Case c : Board.cases) {
                if (c.owner != null) {
                    if (c.owner.affichage.playerId == Server.currentPlayer) {
                        if (c.house == 5) {
                            nbhotel++;
                        }
                        else {
                            nbhouse+=c.house;
                        }
                    }
                }
            }
            int paytobank = nbhouse*100+nbhotel*150;
            Server.players.get(Server.currentPlayer).affichage.paytobank = paytobank;
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(paytobank);
        }

        else if (action.equals("Avancez jusqu’à la Gare de Lyon. Si vous passez par la case départ, recevez M200")) {
            ChangeMoneyPosition(playerId, 200, 15);
            ActionOfCase(playerId);
        }

        else if (action.equals("Vous avez gagné le prix de mots croisés. Recevez M100")) {
            PayToPlayer(playerId, 100);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("La banque vous verse un dividende de M50")) {
            PayToPlayer(playerId, 50);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Vous êtes libéré de prison. Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue.")) {
            Server.players.get(playerId).jailcard++;
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Reculez de trois cases")) {
            ChangeMoneyPosition(playerId, 0, Server.positions.get(playerId)-3);
            if (Server.positions.get(playerId) == 19) {
                ActionOfCase(playerId);
            }
            else if (Server.positions.get(playerId) == 33) {
                for (Player player : Server.players) {
                    player.affichage.ActPlayers(player, playerId);
                }
                Server.community.Action(playerId);
            }
            else {
                Server.players.get(playerId).affichage.DisplayPayBank(200);
            }   
        }

        else if (action.equals("Aller en prison. Rendez-vous directement en prison. Ne franchissez pas par la case départ, ne touchez pas M200")) {
            Server.players.get(playerId).inJail = true;
            Server.positions.set(playerId, 10);
            Server.players.get(playerId).affichage.ChangePosition();
            for (Player player : Server.players) {
                player.affichage.ActPlayers(player, playerId);
            }
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Vous êtes imposé pour les réparations de voirie à raison de M50 par maison et M100 par hôtel.")) {
            int nbhouse = 0;
            int nbhotel = 0;
            for (Case c : Board.cases) {
                if (c.owner != null) {
                    if (c.owner.affichage.playerId == Server.currentPlayer) {
                        if (c.house == 5) {
                            nbhotel++;
                        }
                        else {
                            nbhouse+=c.house;
                        }
                    }
                }
            }
            int paytobank = nbhouse*50+nbhotel*100;
            Server.players.get(Server.currentPlayer).affichage.paytobank = paytobank;
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(paytobank);
        }

        else if (action.equals("Amende pour excès de vitesse M50")) {
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(50);
        }

        else if (action.equals("Payez pour frais de scolarité M100")) {
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(100);
        }
        
        else if (action.equals("Amende pour ivresse M100")) {
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(100);
        }

        else if (action.equals("Votre immeuble et votre prêt rapportent. Vous devez toucher M200")) {
            PayToPlayer(playerId, 200);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }
    }
}

class Community {

    List<String> comission = new ArrayList<String>();

    String[] commissiontochange = {
        "Placez-vous sur la case départ",
        "Erreur de la banque en votre faveur. Recevez M200",
        "Payez la note du médecin M100",
        "La vente de votre stock vous rapporte M100",
        "Vous êtes libéré de prison. Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue.",
        "Aller en prison. Rendez-vous directement à la prison. Ne franchissez pas par la case départ, ne touchez pas M200",
        "Retournez à Belleville",
        "Recevez votre revenu annuel M200",
        "C’est votre anniversaire. Chaque joueur doit vous donner M100",
        "Les contributions vous remboursent la somme de M200",
        "Recevez votre intérêt sur l’emprunt à 7% M250",
        "Payez votre Police d’Assurance M150",
        "Payez une amende de M100",
        "Rendez-vous à la gare la plus proche. Si vous passez par la case départ, recevez M200",
        "Vous avez gagné le deuxième Prix de Beauté. Recevez M100",
        "Vous héritez M100"
    };

    public Community() {
        Aleatoire();
    }

    public String[] RemoveTheElement(String[] lucktochange, int index) {
        String[] comissiontochange2 = new String[lucktochange.length - 1];
        for (int i = 0, k = 0; i < lucktochange.length; i++) {
            if (i == index) {
                continue;
            }
            comissiontochange2[k++] = lucktochange[i];
        }
        return comissiontochange2;
    }

    public void Aleatoire() {
        Random rand = new Random();
        while (comission.size() < 15) {
            int alea = rand.nextInt(commissiontochange.length-1);
            comission.add(commissiontochange[alea]);
            commissiontochange = RemoveTheElement(commissiontochange, alea);
        }
    }

    // change the position of the player for all players and change the money of the player if he pass by the start
    public void ChangeMoneyPosition(int playerId, int money, int position) {
        Server.positions.set(playerId, position);
        Server.players.get(playerId).affichage.ChangePosition();
        if (Server.positions.get(playerId) > position || position == 0) {
            Server.players.get(playerId).sold += money;
            Server.players.get(playerId).affichage.all = "player " + playerId + " with " + Server.players.get(playerId).sold + Server.players.get(playerId).affichage.allproperties;
            for (Player player : Server.players) {
                if (playerId == 0) {
                    player.affichage.list1.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 1) {
                    player.affichage.list2.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 2) {
                    player.affichage.list3.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 3) {
                    player.affichage.list4.setText(Server.players.get(playerId).affichage.all);
                }
                player.affichage.ActPlayers(player, playerId);
            }
        }
        else {
            for (Player player : Server.players) {
                player.affichage.ActPlayers(player, playerId);
            }
        }
    }

    public void PayToPlayer(int playerId, int amount) {
        Server.players.get(playerId).sold += amount;
        Server.players.get(playerId).affichage.all = "player " + playerId + " with " + Server.players.get(playerId).sold + Server.players.get(playerId).affichage.allproperties;
        for (Player player : Server.players) {
            if (playerId == 0) {
                player.affichage.list1.setText(Server.players.get(playerId).affichage.all);
            }
            else if (playerId == 1) {
                player.affichage.list2.setText(Server.players.get(playerId).affichage.all);
            }
            else if (playerId == 2) {
                player.affichage.list3.setText(Server.players.get(playerId).affichage.all);
            }
            else if (playerId == 3) {
                player.affichage.list4.setText(Server.players.get(playerId).affichage.all);
            }
        }
    }

    // if the case is not owned, the player can buy it or bid on it but if the case is owned, the player has to pay the owner
    public void ActionOfCase(int playerId) {
        if (Board.cases.get(Server.positions.get(playerId)).owner == null) {
            Server.players.get(playerId).affichage.playButton.setVisible(false);
            Server.players.get(playerId).affichage.Buy.setVisible(true);
            Server.players.get(playerId).affichage.Bid.setVisible(true);
        }
        else if (Board.cases.get(Server.positions.get(playerId)).owner != Server.players.get(playerId)) {
            Server.players.get(playerId).affichage.DisplayPaySomebody(Board.cases.get(Server.positions.get(playerId)));
        }
        else {
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }
    }

    public void Action(int playerId) {
        Server.players.get(playerId).affichage.message.setText(comission.get(0));
        System.out.println("Player " + playerId + " has drawn a card: " + comission.get(0));
        String action = comission.remove(0);
        comission.add(action);

        if (action.equals("Placez-vous sur la case départ")) {
            ChangeMoneyPosition(playerId, 200, 0);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Erreur de la banque en votre faveur. Recevez M200")) {
            PayToPlayer(playerId, 200);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Payez la note du médecin M100")) {
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(100);
        }

        else if (action.equals("La vente de votre stock vous rapporte M100")) {
            PayToPlayer(playerId, 100);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Vous êtes libéré de prison. Cette carte peut être conservée jusqu’à ce qu’elle soit utilisée ou vendue.")) {
            Server.players.get(playerId).jailcard++;
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Aller en prison. Rendez-vous directement en prison. Ne franchissez pas par la case départ, ne touchez pas M200")) {
            Server.players.get(playerId).inJail = true;
            Server.positions.set(playerId, 10);
            Server.players.get(playerId).affichage.ChangePosition();
            for (Player player : Server.players) {
                player.affichage.ActPlayers(player, playerId);
            }
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Retournez à Belleville")) {
            ChangeMoneyPosition(playerId, 200, 1);
            ActionOfCase(playerId);
        }
        
        else if (action.equals("Recevez votre revenu annuel M200")) {
            PayToPlayer(playerId, 200);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("C’est votre anniversaire. Chaque joueur doit vous donner M100")) {
            for (Player player : Server.players) {
                if (player != Server.players.get(playerId)) {
                    player.sold -= 100;
                    Server.players.get(playerId).sold += 100;
                    player.affichage.all = "player " + playerId + " with " + Server.players.get(playerId).sold + Server.players.get(playerId).affichage.allproperties;
                    for (Player player2 : Server.players) {
                        if (playerId == 0) {
                            player2.affichage.list1.setText(Server.players.get(playerId).affichage.all);
                        }
                        else if (playerId == 1) {
                            player2.affichage.list2.setText(Server.players.get(playerId).affichage.all);
                        }
                        else if (playerId == 2) {
                            player2.affichage.list3.setText(Server.players.get(playerId).affichage.all);
                        }
                        else if (playerId == 3) {
                            player2.affichage.list4.setText(Server.players.get(playerId).affichage.all);
                        }
                        player2.affichage.ActPlayers(player2, playerId);
                    }
                }
            }
            Server.players.get(playerId).affichage.all = "player " + playerId + " with " + Server.players.get(playerId).sold + Server.players.get(playerId).affichage.allproperties;
            for (Player player : Server.players) {
                if (playerId == 0) {
                    player.affichage.list1.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 1) {
                    player.affichage.list2.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 2) {
                    player.affichage.list3.setText(Server.players.get(playerId).affichage.all);
                }
                else if (playerId == 3) {
                    player.affichage.list4.setText(Server.players.get(playerId).affichage.all);
                }
                player.affichage.ActPlayers(player, playerId);
            }
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Les contributions vous remboursent la somme de M200")) {
            PayToPlayer(playerId, 200);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Recevez votre intérêt sur l’emprunt à 7% M250")) {
            PayToPlayer(playerId, 250);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }
        
        else if (action.equals("Payez votre Police d’Assurance M150")) {
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(150);
        }

        else if (action.equals("Payez une amende de M100")) {
            Server.players.get(Server.currentPlayer).affichage.DisplayPayBank(100);
        }

        else if (action.equals("Rendez-vous à la gare la plus proche. Si vous passez par la case départ, recevez M200")) {
            if (Server.positions.get(playerId) < 5) {
                ChangeMoneyPosition(playerId, 200, 5);
                ActionOfCase(playerId);
            }
            else if (Server.positions.get(playerId) < 15) {
                ChangeMoneyPosition(playerId, 200, 15);
                ActionOfCase(playerId);
            }
            else if (Server.positions.get(playerId) < 25) {
                ChangeMoneyPosition(playerId, 200, 25);
                ActionOfCase(playerId);
            }
            else if (Server.positions.get(playerId) < 35) {
                ChangeMoneyPosition(playerId, 200, 35);
                ActionOfCase(playerId);
            }
            else if (Server.positions.get(playerId) < 40) {
                ChangeMoneyPosition(playerId, 200, 5);
                ActionOfCase(playerId);
            }
        }

        else if (action.equals("Vous avez gagné le deuxième Prix de Beauté. Recevez M100")) {
            PayToPlayer(playerId, 100);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }

        else if (action.equals("Vous héritez M100")) {
            PayToPlayer(playerId, 100);
            Server.currentPlayer = Server.players.get(playerId).affichage.nextplayer();
        }
    }

}
