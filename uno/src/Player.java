import java.util.List;
import java.util.ArrayList;
public class Player {

    private List<Card> hand = new ArrayList<Card>();

    public Player() {
        this.hand = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public List<Card> getHand() {
        return hand;
    }


    public void displayHand() {
        for (Card card : hand) {
            card.displaycard();
        }
    }


}
