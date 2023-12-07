package dat.peter.day7;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record Hand(String hand, Type type, int bid) {

    public static Hand fromLine(String line) {
        String[] split = line.split(" ");
        String hand = split[0];
        Type type = getType(hand);
        int bid = Integer.parseInt(split[1]);
        return new Hand(hand, type, bid);
    }

    public int compareTo(Hand other) {
        if (this.type.valueType() == other.type.valueType()) {
            for (int i = 0; i < this.type.valueCards().length; i++) {
                if (this.type.valueCards()[i] != other.type.valueCards()[i]) {
                    if (this.type.valueCards()[i] > other.type.valueCards()[i]) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }

            return 0;
        } else if (this.type.valueType() > other.type.valueType()) {
            return 1;
        } else {
            return -1;
        }
    }

    private static Type getType(String hand) {
        String sortedHand = Arrays.stream(hand.split("")).sorted().collect(Collectors.joining());
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : sortedHand.split("")) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        if (map.size() == 1) {
            return Type.fromHand("Five of a kind", 7, hand);
        } else if (map.size() == 2) {
            if (map.containsValue(4)) {
                return Type.fromHand("Four of a kind", 6, hand);
            } else {
                return Type.fromHand("Full house", 5, hand);
            }
        } else if (map.size() == 3) {
            if (map.containsValue(3)) {
                return Type.fromHand("Three of a kind", 4, hand);
            } else {
                return Type.fromHand("Two pair", 3, hand);
            }
        } else if (map.size() == 4) {
            return Type.fromHand("One pair", 2, hand);
        } else {
            return Type.fromHand("High card", 1, hand);
        }
    }
}