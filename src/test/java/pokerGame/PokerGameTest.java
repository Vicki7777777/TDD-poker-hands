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

}
