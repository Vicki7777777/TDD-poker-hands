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
    public void should_white_wins_when_handlePair_given_1H2D3SKCKD_and_3H3D5S9CKH() {
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
    public void should_Tie_when_handlePair_given_1H2D3SKCKD_and_KH3D5S9CKS() {
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

    @Test
    public void should_Tie_when_handle_handleTwoPair_given_2H2D5S5CKD_and_2C2S5H5DKS() {
        //given
        String[] black = {"2H", "2D", "5S", "5C", "KD"};
        String[] white = {"2C", "2S", "5H", "5D", "KS"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleTwoPair(black, white);
        //then
        assertEquals("Tie", result);
    }

    @Test
    public void should_white_wins_when_return_handleThreeOfAKind_given_2H2D5S5CKDand3H3D3S5S9C() {
        //given
        String[] black = {"2H", "2D", "5S", "5C", "KD"};
        String[] white = {"3H", "3D", "3S", "5S", "9C"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleThreeOfAKind(black, white);
        //then
        assertEquals("White wins. - Three of a kind: Threes", result);
    }
    @Test
    public void should_return_Tie_when_handleThreeOfAKind_given_3H3D3S5S9Cand3H3D3S5S9C() {
        //given
        String[] black = {"3H", "3D", "3S", "5S", "9C"};
        String[] white = {"3H", "3D", "3S", "5S", "9C"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleThreeOfAKind(black, white);
        //then
        assertEquals("Tie", result);
    }
    @Test
    public void should_black_wins_when_handleThreeOfAKind_given_2H2D5S5CKDand3H3D3S5S9C() {
        //given
        String[] white = {"2H", "2D", "5S", "5C", "KD"};
        String[] black = {"3H", "3D", "3S", "5S", "9C"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleThreeOfAKind(black, white);
        //then
        assertEquals("Black wins. - Three of a kind: Threes", result);
    }

    @Test
    public void should_white_wins_when_handleStraight_given_3H4D5S6C7D_3H3D3S5S9C() {
        //given
        String[] white = {"3H", "4D", "5S", "6C", "7D"};
        String[] black = {"3H", "3D", "3S", "5S", "9C"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleStraight(black, white);
        //then
        assertEquals("White wins. - Straight:max is Seven", result);
    }

    @Test
    public void should_black_wins_when_handleStraight_given_3H4D5S6C7D_4H5D6S7S8C() {
        //given
        String[] white = {"3H", "4D", "5S", "6C", "7D"};
        String[] black = {"4H", "5D", "6S", "7S", "8C"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleStraight(black, white);
        //then
        assertEquals("Black wins. - Straight:max is Eight", result);
    }

    @Test
        public void should_tie_when_handleStraight_given_3H4D5S6C7D_3H4D5S6C7D() {
        //given
        String[] white = {"3H", "4D", "5S", "6C", "7D"};
        String[] black = {"3H", "4D", "5S", "6C", "7D"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleStraight(black, white);
        //then
        assertEquals("Tie", result);
    }

    @Test
    public void should_white_wins_when_handleFlush_given_3H4D5S6C7D_3H2H9H6H7H() {
        //given
        String[] white = {"3H", "4D", "5S", "6C", "7D"};
        String[] black = {"3H", "2H", "9H", "6H", "7H"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleFlush(black, white);
        //then
        assertEquals("Black wins. - Flush:Suit is Hearts", result);
    }

    @Test
    public void should_tie_when_handleFlush_given_3H2H9H6H7H_3H2H9H6H7H() {
        //given
        String[] white = {"3H", "2H", "9H", "6H", "7H"};
        String[] black = {"3H", "2H", "9H", "6H", "7H"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleFlush(black, white);
        //then
        assertEquals("Tie", result);
    }

    @Test
    public void should_white_win_when_handleFullHouse_given_3H3D3C6H6D_3H2H9H6H7H() {
        //given
        String[] white = {"3H", "3D", "3C", "6H", "6D"};
        String[] black = {"3H", "2H", "9H", "6H", "7H"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleFullHouse(black, white);
        //then
        assertEquals("White wins. - with full house: Three over Six", result);
    }

    @Test
    public void should_black_win_when_handleFullHouse_given_3H3D3C6H6D_4H4H4H6H6H() {
        //given
        String[] white = {"3H", "3D", "3C", "6H", "6D"};
        String[] black = {"4H", "4H", "4H", "6H", "6H"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleFullHouse(black, white);
        //then
        assertEquals("Black wins. - with full house: Four over Six", result);
    }

    @Test
    public void should_Tie_when_handleFullHouse_given_3H3D3C6H6D_3H3D3C6H6D() {
        //given
        String[] white = {"3H", "3D", "3C", "6H", "6D"};
        String[] black = {"3H", "3D", "3C", "6H", "6D"};
        //when
        PokerHands pokerHands = new PokerHands();
        String result = pokerHands.handleFullHouse(black, white);
        //then
        assertEquals("Tie", result);
    }

}
