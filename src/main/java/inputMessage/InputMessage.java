package inputMessage;

import java.util.*;

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
        if((black.length+white.length) != 10){
            return "Should give fives pokers!";
        }
        List<String> porkers = new ArrayList<>(Arrays.asList(black));
        porkers.addAll(Arrays.asList(white));
        Set<String> blackAndWhiteSet = new HashSet<>(porkers);
        if(blackAndWhiteSet.size()<10){
            return "Can't input repeat poker!";
        }
        return null;
    }


}
