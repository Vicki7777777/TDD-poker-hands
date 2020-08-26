package pokerHands;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHands {
    String cards = "123456789TJQKA";
    String[] cardNames = {
            "Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten", "Jack", "Queen", "King", "Ace"
    };

    public String handleHighCard(String[] black, String[] white) {
        List<Integer> blackFirsts = getFirstNumber(black);
        List<Integer> whiteFirsts = getFirstNumber(white);
        Integer blackMax = blackFirsts.stream().max(Integer::compare).orElse(-1);
        Integer whiteMax = whiteFirsts.stream().max(Integer::compare).orElse(-1);
        String winner = "Black";
        int max = blackMax;
        if (blackMax < whiteMax) {
            winner = "White";
            max = whiteMax;
        } else if (blackMax.equals(whiteMax)) {
            return "Tie";
        }
        String cardName = Integer.toString(max);
        if (max > 10) {
            cardName = cardNames[max];
        }
        return String.format("%s wins. - with high card: %s", winner, cardName);
    }

    public String handlePair(String[] black, String[] white) {
        List<Integer> blackFirsts = getFirstNumber(black);
        List<Integer> whiteFirsts = getFirstNumber(white);
        String winner = "White";
        int whitePair = getPairsNumber(whiteFirsts);
        int blackPair = getPairsNumber(blackFirsts);
        String cardName = cardNames[whitePair];
        if(whitePair<blackPair){
            winner = "Black";
            cardName = cardNames[blackPair];
        }else if (whitePair == blackPair){
            return "Tie";
        }
        return String.format("%s wins. - Pair of %ss", winner, cardName);
    }

    public List<Integer> getFirstNumber(String[] pokers) {
        return Arrays.stream(pokers).map(card -> cards.indexOf(card.charAt(0)) + 1).collect(Collectors.toList());
    }


    public Integer getPairsNumber(List<Integer> firsts) {
        int[] buget = new int[15];
        for (int value : firsts) {
            buget[value]++;
            if (buget[value] == 2) {
                return value;
            }
        }
        return -1;
    }

    public List<Integer> getPairNumbers(List<Integer> pokers){
        int[] buckets = new int[cardNames.length];
        List<Integer> pairNumbers=new ArrayList<>();
        for(Integer value : pokers){
            buckets[value]++;
            if(buckets[value]==2){
                pairNumbers.add(value);
            }
        }
        pairNumbers.sort(Comparator.reverseOrder());
        return pairNumbers;
    }

    public String handleTwoPair(String[] black, String[] white) {
        List<Integer> whiteFirsts = getFirstNumber(white);
        List<Integer> blackFirsts = getFirstNumber(black);
        String winner = "White";
        List<Integer> whitePairNumbers = getPairNumbers(whiteFirsts);
        List<Integer> blackPairNumbers = getPairNumbers(blackFirsts);
        if(whitePairNumbers.size() !=0 && blackPairNumbers.size() !=0 && whitePairNumbers.get(0)<blackPairNumbers.get(0)){
            winner = "Black";
            return String.format("%s wins. - Two Pair of %ss and %ss", winner,cardNames[blackPairNumbers.get(1)],cardNames[blackPairNumbers.get(0)]);
        }else if(whitePairNumbers.size() !=0 && blackPairNumbers.size() !=0 && whitePairNumbers.get(0).equals(blackPairNumbers.get(0))){
            return "Tie";
        }else if(blackPairNumbers.size() == 2){
            winner = "Black";
            return String.format("%s wins. - Two Pair of %ss and %ss", winner,cardNames[blackPairNumbers.get(1)],cardNames[blackPairNumbers.get(0)]);
        }
        return String.format("%s wins. - Two Pair of %ss and %ss", winner,cardNames[whitePairNumbers.get(1)],cardNames[whitePairNumbers.get(0)]);
    }
}
