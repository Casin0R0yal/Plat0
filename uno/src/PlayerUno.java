//package uno.src;

import java.util.List;
import java.util.ArrayList;

public class PlayerUno {

    private List<Card> hand = new ArrayList<Card>(); // the cards in the player's hand

    public PlayerUno() {
        this.hand = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public List<Card> getHand() {
        return this.hand;
    }


    public void displayHand() {
        for (Card card : hand) {
            card.displaycard();
        }
    }




}
