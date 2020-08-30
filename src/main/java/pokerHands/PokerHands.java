package pokerHands;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHands {
    private final String cards = "0123456789TJQKA";
    String[] cardNames = {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"
    };
    Map<String, String> cardSuit = new HashMap<String, String>() {{
        put("H", "Hearts");
        put("C", "Clubs");
        put("D", "Diamonds");
        put("S", "Spades");
    }};

    public String handleHighCard(String[] black, String[] white) {
        List<Integer> blackFirsts = getCardNumbers(black);
        List<Integer> whiteFirsts = getCardNumbers(white);
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
        List<Integer> blackFirsts = getCardNumbers(black);
        List<Integer> whiteFirsts = getCardNumbers(white);
        String winner = "White";
        int whitePair = getPairsNumber(whiteFirsts);
        int blackPair = getPairsNumber(blackFirsts);
        String cardName = cardNames[whitePair];
        if (whitePair < blackPair) {
            winner = "Black";
            cardName = cardNames[blackPair];
        } else if (whitePair == blackPair) {
            return "Tie";
        }
        return String.format("%s wins. - Pair of %ss", winner, cardName);
    }

    public List<Integer> getCardNumbers(String[] pokers) {
        return Arrays.stream(pokers).map(card -> cards.indexOf(card.charAt(0))).collect(Collectors.toList());
    }

    public List<String> getSuits(String[] pokers) {
        return Arrays.stream(pokers).map(card -> String.valueOf(card.charAt(1))).collect(Collectors.toList());
    }

    public Integer getPairsNumber(List<Integer> pokers) {
        int[] buckets = new int[cardNames.length];
        for (int value : pokers) {
            buckets[value]++;
            if (buckets[value] == 2) {
                return value;
            }
        }
        return -1;
    }

    public List<Integer> getPairNumbers(List<Integer> pokers) {
        int[] buckets = new int[cardNames.length];
        List<Integer> pairNumbers = new ArrayList<>();
        for (Integer value : pokers) {
            buckets[value]++;
            if (buckets[value] == 2) {
                pairNumbers.add(value);
            }
        }
        pairNumbers.sort(Comparator.reverseOrder());
        return pairNumbers;
    }

    public String handleTwoPair(String[] black, String[] white) {
        List<Integer> whiteFirsts = getCardNumbers(white);
        List<Integer> blackFirsts = getCardNumbers(black);
        String winner = "White";
        List<Integer> whitePairNumbers = getPairNumbers(whiteFirsts);
        List<Integer> blackPairNumbers = getPairNumbers(blackFirsts);
        if (whitePairNumbers.size() != 0 && blackPairNumbers.size() != 0 && whitePairNumbers.get(0) < blackPairNumbers.get(0)) {
            winner = "Black";
            return String.format("%s wins. - Two Pair of %ss and %ss", winner, cardNames[blackPairNumbers.get(1)], cardNames[blackPairNumbers.get(0)]);
        } else if (whitePairNumbers.size() != 0 && blackPairNumbers.size() != 0 && whitePairNumbers.get(0).equals(blackPairNumbers.get(0))) {
            return "Tie";
        } else if (blackPairNumbers.size() == 2) {
            winner = "Black";
            return String.format("%s wins. - Two Pair of %ss and %ss", winner, cardNames[blackPairNumbers.get(1)], cardNames[blackPairNumbers.get(0)]);
        }
        return String.format("%s wins. - Two Pair of %ss and %ss", winner, cardNames[whitePairNumbers.get(1)], cardNames[whitePairNumbers.get(0)]);
    }

    private Integer getThreeOfPairNumbers(List<Integer> pokers) {
        int[] buckets = new int[cardNames.length];
        Integer threeOfPairNumbers = null;
        for (Integer value : pokers) {
            buckets[value]++;
            if (buckets[value] == 3) {
                threeOfPairNumbers = value;
            }
        }
        return threeOfPairNumbers;
    }

    public String handleThreeOfAKind(String[] black, String[] white) {
        List<Integer> whiteFirsts = getCardNumbers(white);
        List<Integer> blackFirsts = getCardNumbers(black);
        String winner = "White";
        Integer whiteThreeOfPairNumbers = getThreeOfPairNumbers(whiteFirsts);
        Integer blackThreeOfPairNumbers = getThreeOfPairNumbers(blackFirsts);
        if (whiteThreeOfPairNumbers == null) {
            winner = "Black";
            return String.format("%s wins. - Three of a kind: %ss", winner, cardNames[blackThreeOfPairNumbers]);
        }
        if (blackThreeOfPairNumbers == null) {
            return String.format("%s wins. - Three of a kind: %ss", winner, cardNames[whiteThreeOfPairNumbers]);
        }

        if (blackThreeOfPairNumbers > whiteThreeOfPairNumbers) {
            winner = "Black";
            return String.format("%s wins. - Three of a kind: %ss", winner, cardNames[blackThreeOfPairNumbers]);
        }
        if (blackThreeOfPairNumbers < whiteThreeOfPairNumbers) {
            return String.format("%s wins. - Three of a kind: %ss", winner, cardNames[whiteThreeOfPairNumbers]);
        }
        return "Tie";
    }

    public String handleStraight(String[] black, String[] white) {
        List<Integer> whiteFirsts = getCardNumbers(white);
        List<Integer> blackFirsts = getCardNumbers(black);
        String winner = "White";
        Integer whiteStraightNumbers = getStraight(whiteFirsts);
        Integer blackStraightNumbers = getStraight(blackFirsts);
        if (whiteStraightNumbers == null) {
            winner = "Black";
            return String.format("%s wins. - Straight:max is %s", winner, cardNames[blackStraightNumbers]);
        }
        if (blackStraightNumbers == null) {
            return String.format("%s wins. - Straight:max is %s", winner, cardNames[whiteStraightNumbers]);
        }
        if (blackStraightNumbers > whiteStraightNumbers) {
            winner = "Black";
            return String.format("%s wins. - Straight:max is %s", winner, cardNames[blackStraightNumbers]);
        }
        if (blackStraightNumbers < whiteStraightNumbers) {
            return String.format("%s wins. - Straight:max is %s", winner, cardNames[whiteStraightNumbers]);
        }
        return "Tie";
    }

    private Integer getStraight(List<Integer> pokers) {
        pokers.sort(Comparator.reverseOrder());
        for (int i = 0; i < pokers.size() - 1; i++) {
            if (pokers.get(i + 1) != pokers.get(i) - 1) {
                return null;
            }
        }
        return pokers.get(0);
    }

    public String handleFlush(String[] black, String[] white) {
        List<String> whiteSuits = getSuits(white);
        List<String> blackSuits = getSuits(black);
        String winner = "White";
        String whiteFlushSuites = getFlush(whiteSuits);
        String blackFlushSuites = getFlush(blackSuits);
        if (whiteFlushSuites == null) {
            winner = "Black";
            return String.format("%s wins. - Flush:Suit is %s", winner, cardSuit.get(blackFlushSuites));
        }
        if (blackFlushSuites == null) {
            return String.format("%s wins. - Flush:Suit is %s", winner, cardSuit.get(whiteFlushSuites));
        }
        return "Tie";
    }

    private String getFlush(List<String> suits) {
        for (int i = 0; i < suits.size() - 1; i++) {
            if (!suits.get(i).equals(suits.get(i + 1))) {
                return null;
            }
        }
        return suits.get(0);
    }

    public String handleFullHouse(String[] black, String[] white) {
        List<Integer> whiteFirsts = getCardNumbers(white);
        List<Integer> blackFirsts = getCardNumbers(black);
        String winner = "White";
        Map<String, Integer> whiteFullHouse = getFullHouse(whiteFirsts);
        Map<String, Integer> blackFullHouse = getFullHouse(blackFirsts);
        if (whiteFullHouse == null) {
            winner = "Black";
            return String.format("%s wins. - with full house: %s over %s", winner, cardNames[blackFullHouse.get("kind")], cardNames[blackFullHouse.get("pair")]);
        }
        if (blackFullHouse == null) {
            return String.format("%s wins. - with full house: %s over %s", winner, cardNames[whiteFullHouse.get("kind")], cardNames[whiteFullHouse.get("pair")]);
        }
        if (blackFullHouse.get("kind") > whiteFullHouse.get("kind")) {
            winner = "Black";
            return String.format("%s wins. - with full house: %s over %s", winner, cardNames[blackFullHouse.get("kind")], cardNames[blackFullHouse.get("pair")]);
        }
        if (blackFullHouse.get("kind") < whiteFullHouse.get("kind")) {
            return String.format("%s wins. - with full house: %s over %s", winner, cardNames[whiteFullHouse.get("kind")], cardNames[whiteFullHouse.get("pair")]);
        }
        if (blackFullHouse.get("kind").equals(whiteFullHouse.get("kind")) && blackFullHouse.get("pair") > whiteFullHouse.get("pair")) {
            winner = "Black";
            return String.format("%s wins. - with full house: %s over %s", winner, cardNames[blackFullHouse.get("kind")], cardNames[blackFullHouse.get("pair")]);
        }
        if (blackFullHouse.get("kind").equals(whiteFullHouse.get("kind")) && blackFullHouse.get("pair") < whiteFullHouse.get("pair")) {
            return String.format("%s wins. - with full house: %s over %s", winner, cardNames[whiteFullHouse.get("kind")], cardNames[whiteFullHouse.get("pair")]);
        }
        return "Tie";
    }

    private Map<String, Integer> getFullHouse(List<Integer> pokers) {
        Set<Integer> pokersSet = new HashSet<>(pokers);
        Map<String, Integer> pokersDetail = new HashMap<>();
        if (pokersSet.size() != 2) {
            return null;
        }
        int[] budget = new int[cardNames.length];
        for (int value : pokers) {
            budget[value]++;
        }
        for (int i = 0; i < budget.length - 1; i++) {
            if (budget[i] == 3) {
                pokersDetail.put("kind", i);
            }
            if (budget[i] == 2) {
                pokersDetail.put("pair", i);
            }
        }
        return pokersDetail;

    }

    public String handleFourOfAKind(String[] black, String[] white) {
        List<Integer> whiteFirsts = getCardNumbers(white);
        List<Integer> blackFirsts = getCardNumbers(black);
        String winner = "White";
        Map<String, Integer> whiteFourOfAKindNumbers = getFourOfAKindNumbers(whiteFirsts);
        Map<String, Integer> blackFourOfAKindNumbers = getFourOfAKindNumbers(blackFirsts);
        if (whiteFourOfAKindNumbers.get("kind") == null) {
            winner = "Black";
            return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[blackFourOfAKindNumbers.get("kind")]);
        }
        if (blackFourOfAKindNumbers.get("kind") == null) {
            return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[whiteFourOfAKindNumbers.get("kind")]);
        }
        if (blackFourOfAKindNumbers.get("kind") > whiteFourOfAKindNumbers.get("kind")) {
            winner = "Black";
            return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[blackFourOfAKindNumbers.get("kind")]);
        }
        if (blackFourOfAKindNumbers.get("kind") < whiteFourOfAKindNumbers.get("kind")) {
            return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[whiteFourOfAKindNumbers.get("kind")]);
        }
        if (blackFourOfAKindNumbers.get("kind").equals(whiteFourOfAKindNumbers.get("kind")) && blackFourOfAKindNumbers.get("single") > whiteFourOfAKindNumbers.get("single")) {
            winner = "Black";
            return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[blackFourOfAKindNumbers.get("kind")]);
        }
        if (blackFourOfAKindNumbers.get("kind").equals(whiteFourOfAKindNumbers.get("kind")) && blackFourOfAKindNumbers.get("single") < whiteFourOfAKindNumbers.get("single")) {
            return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[whiteFourOfAKindNumbers.get("kind")]);
        }
        return "Tie";
    }

    private Map<String, Integer> getFourOfAKindNumbers(List<Integer> pokers) {
        int[] buckets = new int[cardNames.length];
        Map<String, Integer> pokersDetail = new HashMap<>();
        for (Integer value : pokers) {
            buckets[value]++;
        }
        for (int i = 0; i < buckets.length - 1; i++) {
            if (buckets[i] == 4) {
                pokersDetail.put("kind", i);
            }
            if (buckets[i] == 1) {
                pokersDetail.put("single", i);
            }
        }
        return pokersDetail;
    }

    public String handleStraightFlush(String[] black, String[] white) {
        List<Integer> whiteFirsts = getCardNumbers(white);
        List<Integer> blackFirsts = getCardNumbers(black);
        List<String> whiteSuits = getSuits(white);
        List<String> blackSuits = getSuits(black);
        String winner = "White";
        Integer whiteStraightNumbers = getStraight(whiteFirsts);
        Integer blackStraightNumbers = getStraight(blackFirsts);
        String whiteFlushSuites = getFlush(whiteSuits);
        String blackFlushSuites = getFlush(blackSuits);
        if (whiteStraightNumbers == null || whiteFlushSuites == null) {
            winner = "Black";
            return String.format("%s wins. - Straight Flush", winner);
        }
        if (blackStraightNumbers == null || blackFlushSuites == null) {
            return String.format("%s wins. - Straight Flush", winner);
        }
        if (blackStraightNumbers > whiteStraightNumbers) {
            winner = "Black";
            return String.format("%s wins. - Straight Flush", winner);
        }
        if (blackStraightNumbers < whiteStraightNumbers) {
            return String.format("%s wins. - Straight Flush", winner);
        }
        return "Tie";
    }
}
