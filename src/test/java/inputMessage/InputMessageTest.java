package inputMessage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputMessageTest {
    @Test
    public void should_return_string_array_when_handleInput_given_2H3D5S9CKD_and_2H3H4S8CAH() {
        //given
        String black = "2H3D5S9CKD";
        String white = "2H3H4S8CAH";
        InputMessage inputMessage = new InputMessage();
        //when
        String[] blackResult = inputMessage.handleInput(black);
        String[] whiteResult = inputMessage.handleInput(white);
        //then
        String[] blackArray = {"2H", "3D", "5S", "9C", "KD"};
        String[] whiteArray = {"2H", "3H", "4S", "8C", "AH"};
        for (int i = 0;i<blackResult.length-1;i++){
                assertEquals(blackArray[i],blackResult[i]);
        }
        for (int i = 0;i<whiteResult.length-1;i++){
            assertEquals(whiteArray[i],whiteResult[i]);
        }
    }

    @Test
    public void should_return_wrong_message_when_handleInputCheck_given_2H3D5S9CKD_and_2H3H4S8CAH() {
        //given
        String[] black = {"2H", "3D", "5S", "9C", "KD"};
        String[] white = {"2H", "3H", "4S", "8C", "AH"};
        InputMessage inputMessage = new InputMessage();
        //when
        String result = inputMessage.checkInput(black,white);
        //then
        assertEquals("Can't input repeat poker!", result);
    }

    @Test
    public void should_return_wrong_message_when_handleInputCheck_given_2H3D5S9C_and_2H3H4S8CAH() {
        //given
        String[] black = {"2H", "3D", "5S", "9C"};
        String[] white = {"2H", "3H", "4S", "8C", "AH"};
        InputMessage inputMessage = new InputMessage();
        //when
        String result = inputMessage.checkInput(black,white);
        //then
        assertEquals("Should give fives pokers!", result);
    }

    @Test
    public void should_return_wrong_message_when_handleInputCheck_given_2H3D5S9CKD7C_and_2H3H4S8CAH() {
        //given
        String[] black = {"2H", "3D", "5S", "9C", "KD","7C"};
        String[] white = {"2H", "3H", "4S", "8C", "AH"};
        InputMessage inputMessage = new InputMessage();
        //when
        String result = inputMessage.checkInput(black,white);
        //then
        assertEquals("Should give fives pokers!", result);
    }
}
