package inputMessage;

public class InputMessage {

    public String[] handleInput(String input) {
        String[] inputArray = new String[5];
        inputArray[0] = input.substring(0,2);
        inputArray[1] = input.substring(2,4);
        inputArray[2] = input.substring(4,6);
        inputArray[3] = input.substring(6,8);
        inputArray[4] = input.substring(8,10);
        return inputArray;
    }

    public String checkInput(String[] black, String[] white) {
        return null;
    }
}
