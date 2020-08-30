package pokerHands;

import com.sun.javafx.image.IntPixelGetter;

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
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        List<Integer> whitePorkerNumbers = getCardNumbers(white);
        Integer blackMax = blackPokerNumbers.stream().max(Integer::compare).orElse(-1);
        Integer whiteMax = whitePorkerNumbers.stream().max(Integer::compare).orElse(-1);
        if (blackMax.equals(whiteMax)) {
            return "Tie";
        }
        String winner = blackMax > whiteMax ? "Black" : "White";
        int max = blackMax > whiteMax ? blackMax : whiteMax;

        String cardName = Integer.toString(max);
        if (max > 10) {
            cardName = cardNames[max];
        }
        return String.format("%s wins. - with high card: %s", winner, cardName);
    }

    public String handlePair(String[] black, String[] white) {
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        List<Integer> whitePorkerNumbers = getCardNumbers(white);
        Integer whitePair = getPairsNumber(whitePorkerNumbers);
        Integer blackPair = getPairsNumber(blackPokerNumbers);
        if (whitePair.equals(blackPair)) {
            return "Tie";
        }
        String winner = whitePair > blackPair ? "White" : "Black";
        int pair = whitePair > blackPair ? whitePair : blackPair;
        String cardName = cardNames[pair];
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
        List<Integer> whitePokerNumbers = getCardNumbers(white);
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        List<Integer> whitePairNumbers = getPairNumbers(whitePokerNumbers);
        List<Integer> blackPairNumbers = getPairNumbers(blackPokerNumbers);
        String winner = null;
        List<Integer> cardNumbers = null;
        if (blackPairNumbers.size() != 2) {
            winner = "White";
            cardNumbers = whitePairNumbers;
        } else if (whitePairNumbers.size() != 2) {
            winner = "Black";
            cardNumbers = blackPairNumbers;
        } else {
            if (whitePairNumbers.get(0) > blackPairNumbers.get(0)) {
                winner = "White";
                cardNumbers = whitePairNumbers;
            } else if (whitePairNumbers.get(0) < blackPairNumbers.get(0)) {
                winner = "Black";
                cardNumbers = blackPairNumbers;
            } else {
                return "Tie";
            }
        }
        return String.format("%s wins. - Two Pair of %ss and %ss", winner, cardNames[cardNumbers.get(1)], cardNames[cardNumbers.get(0)]);
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
        List<Integer> whitePokerNumbers = getCardNumbers(white);
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        Integer whiteThreeOfPairNumbers = getThreeOfPairNumbers(whitePokerNumbers);
        Integer blackThreeOfPairNumbers = getThreeOfPairNumbers(blackPokerNumbers);
        String winner = null;
        Integer cardNumber = null;
        if (whiteThreeOfPairNumbers == null) {
            winner = "Black";
            cardNumber = blackThreeOfPairNumbers;
        } else if (blackThreeOfPairNumbers == null) {
            winner = "White";
            cardNumber = whiteThreeOfPairNumbers;
        } else {
            if (blackThreeOfPairNumbers > whiteThreeOfPairNumbers) {
                winner = "Black";
                cardNumber = blackThreeOfPairNumbers;
            } else if (blackThreeOfPairNumbers < whiteThreeOfPairNumbers) {
                winner = "White";
                cardNumber = whiteThreeOfPairNumbers;
            } else {
                return "Tie";
            }
        }
        return String.format("%s wins. - Three of a kind: %ss", winner, cardNames[cardNumber]);
    }

    public String handleStraight(String[] black, String[] white) {
        List<Integer> whitePokerNumbers = getCardNumbers(white);
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        Integer whiteStraightNumbers = getStraight(whitePokerNumbers);
        Integer blackStraightNumbers = getStraight(blackPokerNumbers);
        String winner = null;
        Integer cardNumber = null;
        if (whiteStraightNumbers == null) {
            winner = "Black";
            cardNumber = blackStraightNumbers;
        } else if (blackStraightNumbers == null) {
            winner = "White";
            cardNumber = whiteStraightNumbers;
        } else {
            if (blackStraightNumbers > whiteStraightNumbers) {
                winner = "Black";
                cardNumber = blackStraightNumbers;
            } else if (blackStraightNumbers < whiteStraightNumbers) {
                winner = "White";
                cardNumber = whiteStraightNumbers;
            } else {
                return "Tie";
            }
        }
        return String.format("%s wins. - Straight:max is %s", winner, cardNames[cardNumber]);
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
        String whiteFlushSuites = getFlush(whiteSuits);
        String blackFlushSuites = getFlush(blackSuits);
        String winner = null;
        String suite = null;
        if (whiteFlushSuites == null) {
            winner = "Black";
            suite = blackFlushSuites;
        }else if (blackFlushSuites == null) {
            winner = "White";
            suite = whiteFlushSuites;
        }else {
            return "Tie";
        }
        return String.format("%s wins. - Flush:Suit is %s", winner, cardSuit.get(suite));
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
        List<Integer> whitePokerNumbers = getCardNumbers(white);
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        Map<String, Integer> whiteFullHouse = getFullHouse(whitePokerNumbers);
        Map<String, Integer> blackFullHouse = getFullHouse(blackPokerNumbers);
        String winner = null;
        Map<String, Integer> fullHouse = null;
        if (whiteFullHouse == null) {
            winner = "Black";
            fullHouse = blackFullHouse;
        }else if (blackFullHouse == null) {
            winner = "White";
            fullHouse = whiteFullHouse;
        }else {
            if (blackFullHouse.get("kind") > whiteFullHouse.get("kind")) {
                winner = "Black";
                fullHouse = blackFullHouse;
            }else if (blackFullHouse.get("kind") < whiteFullHouse.get("kind")) {
                winner = "White";
                fullHouse = whiteFullHouse;
            }else if (blackFullHouse.get("kind").equals(whiteFullHouse.get("kind")) && blackFullHouse.get("pair") > whiteFullHouse.get("pair")) {
                winner = "Black";
                fullHouse = blackFullHouse;
            }else if (blackFullHouse.get("kind").equals(whiteFullHouse.get("kind")) && blackFullHouse.get("pair") < whiteFullHouse.get("pair")) {
                winner = "White";
                fullHouse = whiteFullHouse;
            }else {
                return "Tie";
            }
        }
        return String.format("%s wins. - with full house: %s over %s", winner, cardNames[fullHouse.get("kind")], cardNames[fullHouse.get("pair")]);
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
        List<Integer> whitePokerNumbers = getCardNumbers(white);
        List<Integer> blackPokerNumbers = getCardNumbers(black);
        Map<String, Integer> whiteFourOfAKindNumbers = getFourOfAKindNumbers(whitePokerNumbers);
        Map<String, Integer> blackFourOfAKindNumbers = getFourOfAKindNumbers(blackPokerNumbers);
        String winner = null;
        Map<String, Integer> fourOfAKindNumbers = null;
        if (whiteFourOfAKindNumbers.get("kind") == null) {
            winner = "Black";
            fourOfAKindNumbers = blackFourOfAKindNumbers;
        }else if (blackFourOfAKindNumbers.get("kind") == null) {
            winner = "White";
            fourOfAKindNumbers = whiteFourOfAKindNumbers;
        }else if (blackFourOfAKindNumbers.get("kind") > whiteFourOfAKindNumbers.get("kind")) {
            winner = "Black";
            fourOfAKindNumbers = blackFourOfAKindNumbers;
        }else {
            if (blackFourOfAKindNumbers.get("kind") < whiteFourOfAKindNumbers.get("kind")) {
                winner = "White";
                fourOfAKindNumbers = whiteFourOfAKindNumbers;
            }else if (blackFourOfAKindNumbers.get("kind").equals(whiteFourOfAKindNumbers.get("kind")) && blackFourOfAKindNumbers.get("single") > whiteFourOfAKindNumbers.get("single")) {
                winner = "Black";
                fourOfAKindNumbers = blackFourOfAKindNumbers;
            }else if (blackFourOfAKindNumbers.get("kind").equals(whiteFourOfAKindNumbers.get("kind")) && blackFourOfAKindNumbers.get("single") < whiteFourOfAKindNumbers.get("single")) {
                winner = "White";
                fourOfAKindNumbers = whiteFourOfAKindNumbers;
            }else {
                return "Tie";
            }
        }
        return String.format("%s wins. - Four of a kind: %ss", winner, cardNames[fourOfAKindNumbers.get("kind")]);
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
