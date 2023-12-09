package dat.peter.year2023.day7;

import java.util.HashMap;
import java.util.Map;

public record Hand(String hand, Type type, int bid) {

    public static Hand fromLine(String line, int part) {
        String[] split = line.split(" ");
        String hand = split[0];
        Type type = part == 1 ? getType1(hand) : getType2(hand);
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

    private static Type getType1(String hand) {
        return type(getHandMap(hand), hand);
    }

    private static Type getType2(String hand) {
        Map<String, Integer> map = getHandMap(hand);
        if (map.containsKey("J") && map.size() > 1) {
            String otherLargestChar = "";
            int otherLargestAmount = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (!entry.getKey().equals("J")) {
                    if (entry.getValue() > otherLargestAmount) {
                        otherLargestChar = entry.getKey();
                        otherLargestAmount = entry.getValue();
                    }
                }
            }

            map.put(otherLargestChar, map.get(otherLargestChar) + map.getOrDefault("J", 0));
            map.remove("J");
        }

        return type(map, hand);
    }

    private static Map<String, Integer> getHandMap(String hand) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : hand.split("")) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        return map;
    }

    private static Type type(Map<String, Integer> map, String hand) {
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