package pokerGame;

import java.util.*;
import java.util.stream.Collectors;

public class PokerGame {
    String cards = "0123456789TJQKA";
    String[] cardNames = {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"
    };
    Map<String, String> cardSuit = new HashMap<String, String>() {{
        put("H", "Hearts");
        put("C", "Clubs");
        put("D", "Diamonds");
        put("S", "Spades");
    }};

    public List<Integer> getFirstNumber(String[] pokers) {
        return Arrays.stream(pokers).map(card -> cards.indexOf(card.charAt(0))).collect(Collectors.toList());
    }

    public List<String> getSuits(String[] pokers) {
        return Arrays.stream(pokers).map(card -> String.valueOf(card.charAt(1))).collect(Collectors.toList());
    }

    public String judgePokerHandsType(String[] blackArray, String[] whiteArray) {
        if (isStraightFlush(blackArray) || isStraightFlush(whiteArray)) {
            return "STRAIGHT_FLUSH";
        }
        return null;
    }

    public boolean isStraightFlush(String[] pokers) {
        List<Integer> blackFirsts = getFirstNumber(pokers);
        List<String> whiteSuits= getSuits(pokers);
        blackFirsts.sort(Comparator.reverseOrder());
        for(int i = 0;i< blackFirsts.size()-1;i++){
            if(blackFirsts.get(i+1) != blackFirsts.get(i)-1){
                return false;
            }
        }
        for(int i = 0;i< whiteSuits.size()-1;i++){
            if(!whiteSuits.get(i).equals(whiteSuits.get(i + 1))){
                return false;
            }
        }
        return true;
    }

}
