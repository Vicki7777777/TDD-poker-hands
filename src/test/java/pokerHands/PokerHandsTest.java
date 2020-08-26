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
    public void should_return_Tie_when_handleHighCard_given_1H2D3S4CKD_and_5H6D7S8CKC() {
        //given
        String[] black = {"1H", "2D", "3S", "4C", "KD"};
        String[] white = {"5H", "6D", "7S", "8C", "KC"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleHighCard(black, white);
        //then
        assertEquals("Tie", result);

    }

    @Test
    public void should_white_wins_when_handlePair_given_2H3D5S9CKD_and_3H3D5S9CKC() {
        //given
        String[] black = {"1H", "2D", "3S", "4C", "KD"};
        String[] white = {"3H", "3D", "5S", "9C", "KC"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handlePair(black, white);
        //then
        assertEquals("White wins. - Pair of Threes", result);
    }

    @Test
    public void should_white_wins_when_handlePair_given_2H3D5SKCKD_and_3H3D5S9CKH() {
        //given
        String[] black = {"1H", "2D", "3S", "KC", "KD"};
        String[] white = {"3H", "3D", "5S", "9C", "KH"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handlePair(black, white);
        //then
        assertEquals("Black wins. - Pair of Kings", result);
    }

    @Test
    public void should_Tie_when_handlePair_given_2H3D5SKCKD_and_KH3D5S9CKS() {
        //given
        String[] black = {"1H", "2D", "3S", "KC", "KD"};
        String[] white = {"KH", "3D", "5S", "9C", "KS"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handlePair(black, white);
        //then
        assertEquals("Tie", result);
    }

    @Test
    public void should_black_wins_when_handle_handleTwoPair_given_2H2D5S6CKD_and_2C2S3S3CKS() {
        //given
        String[] black = {"2H", "2D", "5S", "6C", "KD"};
        String[] white = {"2C", "2S", "3S", "3C", "KS"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleTwoPair(black, white);
        //then
        assertEquals("White wins. - Two Pair of Twos and Threes", result);
    }

    @Test
    public void should_black_wins_when_handle_handleTwoPair_given_2H2D5S5CKD_and_2C2S3S3CKS() {
        //given
        String[] black = {"2H", "2D", "5S", "5C", "KD"};
        String[] white = {"2C", "2S", "3S", "3C", "KS"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleTwoPair(black, white);
        //then
        assertEquals("Black wins. - Two Pair of Twos and Fives", result);
    }
}
