package pokerHands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PokerHands {
    String cards= "23456789TJQKA";
    String[] cardNames = {
            "Jack","Queen","King","Ace"
    };
    public String handleHighCard(String[] black, String[] white) {
        List<Integer> blackFirsts = Arrays.stream(black).map(card->cards.indexOf(card.charAt(0))+1).collect(Collectors.toList());
        List<Integer> whiteFirsts = Arrays.stream(white).map(card->cards.indexOf(card.charAt(0))+1).collect(Collectors.toList());
        Integer blackMax = blackFirsts.stream().max(Integer::compare).orElse(-1);
        Integer whiteMax = whiteFirsts.stream().max(Integer::compare).orElse(-1);
        String winner = "Black";
        Integer max = blackMax;
        if(blackMax<whiteMax){
            winner = "White";
            max = whiteMax;
        }
        String cardName = max.toString();
        if(max>10){
            cardName = cardNames[max-10];
        }
        return String.format("%s wins. - with high card: %s", winner,cardName);
    }
}
