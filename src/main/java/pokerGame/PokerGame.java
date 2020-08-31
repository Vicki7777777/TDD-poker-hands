package pokerGame;

import inputMessage.InputMessage;
import pokerHands.PokerHands;

import java.util.*;
import java.util.stream.Collectors;

public class PokerGame {
    public static final String STRAIGHT_FLUSH = "STRAIGHT_FLUSH";
    public static final String FOUR_OF_A_KIND = "FOUR_OF_A_KIND";
    public static final String FULL_HOUSE = "FULL_HOUSE";
    public static final String FLUSH = "FLUSH";
    public static final String STRAIGHT = "STRAIGHT";
    public static final String THREE_OF_A_KIND = "THREE_OF_A_KIND";
    public static final String TWO_PAIRS = "TWO_PAIRS";
    public static final String PAIRS = "PAIRS";
    public static final String HIGH_CARD = "HIGH_CARD";

    String cards = "0123456789TJQKA";

    InputMessage inputMessage;
    PokerHands pokerHands;

    public PokerGame(InputMessage inputMessage, PokerHands pokerHands) {
        this.inputMessage = inputMessage;
        this.pokerHands = pokerHands;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Poker Hands Game Begin!\n");
        System.out.println("Black:\n");
        String black = sc.nextLine();
        System.out.println("White:\n");
        String white = sc.nextLine();
        String[] blackArray = inputMessage.handleInput(black);
        String[] whiteArray = inputMessage.handleInput(white);
        String checkResultMessage = inputMessage.checkInput(blackArray, whiteArray);
        if (checkResultMessage == null) {
            String pokerHansType = judgePokerHandsType(blackArray, whiteArray,null);
            handlePokerHands(blackArray, whiteArray, pokerHansType);
        } else {
            System.out.println(checkResultMessage);
        }
    }

    private void handlePokerHands(String[] blackArray, String[] whiteArray, String pokerHandsType) {
        String gameResult;
        switch (pokerHandsType) {
            case STRAIGHT_FLUSH:
                gameResult = pokerHands.handleStraightFlush(blackArray, whiteArray);
                break;
            case FOUR_OF_A_KIND:
                gameResult = pokerHands.handleFourOfAKind(blackArray, whiteArray);
                break;
            case FULL_HOUSE:
                gameResult = pokerHands.handleFullHouse(blackArray, whiteArray);
                break;
            case FLUSH:
                gameResult = pokerHands.handleFlush(blackArray, whiteArray);
                break;
            case STRAIGHT:
                gameResult = pokerHands.handleStraight(blackArray, whiteArray);
                break;
            case THREE_OF_A_KIND:
                gameResult = pokerHands.handleThreeOfAKind(blackArray, whiteArray);
                break;
            case TWO_PAIRS:
                gameResult = pokerHands.handleTwoPair(blackArray, whiteArray);
                break;
            case PAIRS:
                gameResult = pokerHands.handlePair(blackArray, whiteArray);
                break;
            default:
                gameResult = pokerHands.handleHighCard(blackArray, whiteArray);
                break;
        }
        if (pokerHandsType.equals(FLUSH) && "Tie".equals(gameResult)) {
            pokerHandsType = judgePokerHandsType(blackArray, whiteArray,pokerHandsType);
            handlePokerHands(blackArray,whiteArray,pokerHandsType);
        } else {
            System.out.println(gameResult);
        }
    }


    public List<Integer> getFirstNumber(String[] pokers) {
        return Arrays.stream(pokers).map(card -> cards.indexOf(card.charAt(0))).collect(Collectors.toList());
    }

    public List<String> getSuits(String[] pokers) {
        return Arrays.stream(pokers).map(card -> String.valueOf(card.charAt(1))).collect(Collectors.toList());
    }

    public String judgePokerHandsType(String[] blackArray, String[] whiteArray, String notEqual) {
        if (!STRAIGHT_FLUSH.equals(notEqual) && (isStraightFlush(blackArray) || isStraightFlush(whiteArray))) {
            return STRAIGHT_FLUSH;
        }
        if (!FOUR_OF_A_KIND.equals(notEqual) && (isFourOfAKind(blackArray) || isFourOfAKind(whiteArray))) {
            return FOUR_OF_A_KIND;
        }
        if (!FULL_HOUSE.equals(notEqual) && (isFullHouse(blackArray) || isFullHouse(whiteArray))) {
            return FULL_HOUSE;
        }
        if (!FLUSH.equals(notEqual) && (isFlush(blackArray) || isFlush(whiteArray))) {
            return FLUSH;
        }
        if (!STRAIGHT.equals(notEqual) && (isStraight(blackArray) || isStraight(whiteArray))) {
            return STRAIGHT;
        }
        if (!THREE_OF_A_KIND.equals(notEqual) && (isThreeOfAKind(blackArray) || isThreeOfAKind(whiteArray))){
            return THREE_OF_A_KIND;
        }
        if (!TWO_PAIRS.equals(notEqual) && (isTwoPairs(blackArray) || isTwoPairs(whiteArray))) {
            return TWO_PAIRS;
        }
        if (!PAIRS.equals(notEqual) && (isPairs(blackArray) || isPairs(whiteArray))) {
            return PAIRS;
        }
        return HIGH_CARD;
    }


    private boolean isPairs(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        int[] buckets = new int[cards.length()];
        for (Integer value : firstNumbers) {
            buckets[value]++;
        }
        for (int i = 0; i < buckets.length - 1; i++) {
            if (buckets[i] == 2) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPairs(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        int[] buckets = new int[cards.length()];
        int competeTimes = 0;
        for (Integer value : firstNumbers) {
            buckets[value]++;
        }
        for (int i = 0; i < buckets.length - 1; i++) {
            if (buckets[i] == 2) {
                competeTimes++;
            }
        }
        return competeTimes == 2;
    }

    private boolean isThreeOfAKind(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        int[] buckets = new int[cards.length()];
        for (Integer value : firstNumbers) {
            buckets[value]++;
        }
        for (int i = 0; i < buckets.length - 1; i++) {
            if (buckets[i] == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isStraight(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        firstNumbers.sort(Comparator.reverseOrder());
        for (int i = 0; i < firstNumbers.size() - 1; i++) {
            if (firstNumbers.get(i + 1) != firstNumbers.get(i) - 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isFlush(String[] pokers) {
        List<String> suits = getSuits(pokers);
        Set<String> suitsSet = new HashSet<>(suits);
        return suitsSet.size() == 1;
    }

    public boolean isFullHouse(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        Set<Integer> firstNumbersSet = new HashSet<>(firstNumbers);
        return firstNumbersSet.size() == 2;
    }

    public boolean isFourOfAKind(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        int[] buckets = new int[cards.length()];
        for (Integer value : firstNumbers) {
            buckets[value]++;
        }
        for (int i = 0; i < buckets.length - 1; i++) {
            if (buckets[i] == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        List<String> suits = getSuits(pokers);
        firstNumbers.sort(Comparator.reverseOrder());
        for (int i = 0; i < firstNumbers.size() - 1; i++) {
            if (firstNumbers.get(i + 1) != firstNumbers.get(i) - 1) {
                return false;
            }
        }
        for (int i = 0; i < suits.size() - 1; i++) {
            if (!suits.get(i).equals(suits.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

}
