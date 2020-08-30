package pokerGame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokerGameTest {
    @Test
    public void should_return_STRAIGHT_FLUSH_when_judgePokerHandsType_given_3S3D3C3H6D_4C5C6C7C8C() {
        //given
        String[] white = {"3S", "3D", "3C", "3H", "6D"};
        String[] black = {"4C", "5C", "6C", "7C", "8C"};
        PokerGame pokerGame = new PokerGame();
        //when
        String pokersType = pokerGame.judgePokerHandsType(white,black);
        //then
        assertEquals("STRAIGHT_FLUSH", pokersType);
    }

    @Test
    public void should_return_false_when_isStraightFlush_given_4C5C6C7C8S() {
        //given
        String[] pokers = {"4C", "5C", "6C", "7C", "8S"};
        PokerGame pokerGame = new PokerGame();
        //when
        boolean result = pokerGame.isStraightFlush(pokers);
        //then
        assertFalse(result);
    }

    @Test
    public void should_return_FOUR_OF_A_KIND_when_judgePokerHandsType_given_3H3D3C3S6D_4H4D4C5H5D() {
        //given
        String[] white = {"3H", "3D", "3C", "3S", "6D"};
        String[] black = {"4H", "4D", "4C", "5H", "5D"};
        PokerGame pokerGame = new PokerGame();
        //when
        String pokersType = pokerGame.judgePokerHandsType(white,black);
        //then
        assertEquals("FOUR_OF_A_KIND", pokersType);
    }

    @Test
    public void should_return_FULL_HOUSE_when_judgePokerHandsType_given_3H3D3C6H6D_4H4H4H8H7H() {
        //given
        String[] white = {"3H", "3D", "3C", "6H", "6D"};
        String[] black = {"4H", "4D", "4C", "8H", "7H"};
        PokerGame pokerGame = new PokerGame();
        //when
        String pokersType = pokerGame.judgePokerHandsType(white,black);
        //then
        assertEquals("FULL_HOUSE", pokersType);
    }

    @Test
    public void should_return_FLUSH_when_judgePokerHandsType_given_3H2H9H6H7H_3C4C5H6S7S() {
        //given
        String[] white = {"3H", "2H", "9H", "6H", "7H"};
        String[] black = {"3C", "4C", "5H", "6S", "7S"};
        PokerGame pokerGame = new PokerGame();
        //when
        String pokersType = pokerGame.judgePokerHandsType(white,black);
        //then
        assertEquals("FLUSH", pokersType);
    }

    @Test
    public void should_return_STRAIGHT_when_judgePokerHandsType_given_3C4C5H6S7S_3H3D5S9C3S() {
        //given
        String[] white = {"3C", "4C", "5H", "6S", "7S"};
        String[] black = {"3H", "3D", "5S", "6C", "3S"};
        PokerGame pokerGame = new PokerGame();
        //when
        String pokersType = pokerGame.judgePokerHandsType(white,black);
        //then
        assertEquals("STRAIGHT", pokersType);
    }

    @Test
    public void should_return_THREE_OF_A_KIND_when_judgePokerHandsType_given_3H3D5S9C3S_7H7D6S9C5D() {
        //given
        String[] white = {"3H", "3D", "5S", "6C", "3S"};
        String[] black = {"7H", "7D", "6S", "9C", "5D"};
        PokerGame pokerGame = new PokerGame();
        //when
        String pokersType = pokerGame.judgePokerHandsType(white,black);
        //then
        assertEquals("THREE_OF_A_KIND", pokersType);
    }

}
