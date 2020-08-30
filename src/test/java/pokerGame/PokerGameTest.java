package pokerGame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
