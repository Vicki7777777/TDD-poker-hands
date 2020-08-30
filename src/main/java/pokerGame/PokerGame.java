package pokerGame;

import java.util.*;
import java.util.stream.Collectors;

public class PokerGame {
    String cards = "0123456789TJQKA";

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
        if(isFourOfAKind(blackArray) || isFourOfAKind(whiteArray)){
            return "FOUR_OF_A_KIND";
        }
        if(isFullHouse(blackArray) || isFullHouse(whiteArray)){
            return "FULL_HOUSE";
        }
        if(isFlush(blackArray) || isFlush(whiteArray)){
            return "FLUSH";
        }
        if(isStraight(blackArray) || isStraight(whiteArray)){
            return "STRAIGHT";
        }
        if(isThreeOfAKind(blackArray) || isThreeOfAKind(whiteArray)){
            return "THREE_OF_A_KIND";
        }
        return null;
    }

    private boolean isThreeOfAKind(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        int[] buckets = new int[cards.length()];
        for(Integer value : firstNumbers){
            buckets[value]++;
        }
        for(int i = 0;i<buckets.length-1;i++){
            if(buckets[i] == 3){
                return true;
            }
        }
        return false;
    }

    private boolean isStraight(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        firstNumbers.sort(Comparator.reverseOrder());
        for(int i = 0;i< firstNumbers.size()-1;i++){
            if(firstNumbers.get(i+1) != firstNumbers.get(i)-1){
                return false;
            }
        }
        return true;
    }

    public boolean isFlush(String[] pokers) {
        List<String> suits= getSuits(pokers);
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
        for(Integer value : firstNumbers){
            buckets[value]++;
        }
        for(int i = 0;i<buckets.length-1;i++){
            if(buckets[i] == 4){
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush(String[] pokers) {
        List<Integer> firstNumbers = getFirstNumber(pokers);
        List<String> suits= getSuits(pokers);
        firstNumbers.sort(Comparator.reverseOrder());
        for(int i = 0;i< firstNumbers.size()-1;i++){
            if(firstNumbers.get(i+1) != firstNumbers.get(i)-1){
                return false;
            }
        }
        for(int i = 0;i< suits.size()-1;i++){
            if(!suits.get(i).equals(suits.get(i + 1))){
                return false;
            }
        }
        return true;
    }

}
