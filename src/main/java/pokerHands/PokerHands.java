package pokerHands;

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
        String cardName = cardNames[whitePair];
        return String.format("%s wins. - Pair of %ss", winner, cardName);
    }

    public List<Integer> getFirstNumber(String[] pokers) {
        return Arrays.stream(pokers).map(card -> cards.indexOf(card.charAt(0)) + 1).collect(Collectors.toList());
    }

    public boolean isRepeat(List<Integer> firsts) {
        Set<Integer> firstsSet = new HashSet<>(firsts);
        boolean repeatResult = false;
        if (firsts.size() == firstsSet.size()) {
            repeatResult = true;
        }
        return repeatResult;
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
}
