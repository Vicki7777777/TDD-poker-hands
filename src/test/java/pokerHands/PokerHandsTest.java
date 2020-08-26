package pokerHands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandsTest {
    @Test
    public void should_return_white_wins_when_handleHighCard_given_2H3D5S9CKD_and_2C3H4S8CAH() {
        //given
        String[] black = {"2H", "3D", "5S", "9C", "KD"};
        String[] white = {"2C", "3H", "4S", "8C", "AH"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleHighCard(black, white);
        //then
        assertEquals("White wins. - with high card: Ace", result);
    }

    @Test
    public void should_return_Tie_when_handleHighCard_given_1H2D3S4CKD_and_5H6D7S8CKD() {
        //given
        String[] black = {"2H", "3D", "5S", "9C", "KD"};
        String[] white = {"2H", "3D", "5S", "9C", "KD"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleHighCard(black, white);
        //then
        assertEquals("Tie", result);

    }
}
