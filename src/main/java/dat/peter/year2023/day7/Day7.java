package dat.peter.year2023.day7;

import dat.peter.Day;

import java.util.ArrayList;
import java.util.List;

public class Day7 extends Day {

    @Override
    public int day() {
        return 7;
    }

    @Override
    public String partOne(List<String> input) {
        List<Hand> hands = createHands(input, 1);
        hands.sort(Hand::compareTo);
        int sum = 0;
        for (int i = 1; i <= hands.size(); i++) {
            int bid = hands.get(i - 1).bid();
            int add = i * bid;
            sum += add;
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        Type.CARD_VALUES.put("J", -1);
        List<Hand> hands = createHands(input, 2);
        hands.sort(Hand::compareTo);
        int sum = 0;
        for (int i = 1; i <= hands.size(); i++) {
            int bid = hands.get(i - 1).bid();
            int add = i * bid;
            sum += add;
        }

        return String.valueOf(sum);
    }

    private List<Hand> createHands(List<String> input, int part) {
        List<Hand> hands = new ArrayList<>();
        for (String string : input) {
            hands.add(Hand.fromLine(string, part));
        }

        return hands;
    }
}