package dat.peter.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public record Type(String name, int valueType, int[] valueCards) {

    private static final Map<String, Integer> CARD_VALUES = new HashMap<>(){
        {
            put("2", 0);
            put("3", 1);
            put("4", 2);
            put("5", 3);
            put("6", 4);
            put("7", 5);
            put("8", 6);
            put("9", 7);
            put("T", 8);
            put("J", 9);
            put("Q", 10);
            put("K", 11);
            put("A", 12);
        }
    };

    public static Type fromHand(String name, int valueType, String hand) {
        int[] valueCards = new int[5];
        for (int i = 0; i < hand.length(); i++) {
            valueCards[i] = CARD_VALUES.get(hand.substring(i, i + 1));
        }

        return new Type(name, valueType, valueCards);
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                ", valueType=" + valueType +
                ", valueCards=" + Arrays.toString(valueCards) +
                '}';
    }
}