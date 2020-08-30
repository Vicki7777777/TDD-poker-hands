import inputMessage.InputMessage;
import pokerGame.PokerGame;
import pokerHands.PokerHands;

public class PokerHandsApplication {
    public static void main(String[] args) {
        PokerGame pokerGame = new PokerGame(new InputMessage(),new PokerHands());
        pokerGame.start();
        };
}

