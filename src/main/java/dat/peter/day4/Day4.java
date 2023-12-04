package dat.peter.day4;

import dat.peter.Day;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day4 extends Day {

    private int getTimes(List<Integer> winningNumbers, List<Integer> myNumbers) {
        int times = 0;
        for (int num : myNumbers) {
            if (winningNumbers.contains(num)) {
                times += 1;
            }
        }

        return times;
    }

    private int getPoints(Card card) {
        int times = 0;
        for (int num : card.myNumbers()) {
            if (card.winningNumbers().contains(num)) {
                times += 1;
            }
        }

        int points = 0;
        for (int i = 0; i < times; i++) {
            if (i == 0) {
                points += 1;
            } else {
                points *= 2;
            }
        }

        return points;
    }

    @Override
    public int day() {
        return 4;
    }

    @Override
    public String partOne(List<String> input) {
        int sum = 0;
        for (String line : input) {
            Card card = Card.fromLine(line);
            sum += getPoints(card);
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        int sum = 0;
        Map<Integer, Card> cardMap = new LinkedHashMap<>();
        for (String line : input) {
            Card card = Card.fromLine(line);
            cardMap.put(card.cardNumber(), card);
        }

        Map<Integer, Integer> timesMap = new HashMap<>();
        Map<Integer, Integer> cardTimesMap = new HashMap<>();
        for (int cardNumber : cardMap.keySet()) {
            Card card = cardMap.get(cardNumber);
            int times = timesMap.getOrDefault(cardNumber, -1);
            if (times == -1) {
                times = getTimes(card.winningNumbers(), card.myNumbers());
                timesMap.put(cardNumber, times);
            }

            int currentTimes = cardTimesMap.getOrDefault(cardNumber, -1);
            if (currentTimes == -1) {
                cardTimesMap.put(cardNumber, 1);
                currentTimes = cardTimesMap.get(cardNumber);
            }

            for (int i = 1; i <= times; i++) {
                int nextCardNumber = cardNumber + i;
                cardTimesMap.put(nextCardNumber, cardTimesMap.getOrDefault(nextCardNumber, 1) + (currentTimes));
            }
        }

        for (Map.Entry<Integer, Integer> entry : cardTimesMap.entrySet()) {
            sum += entry.getValue();
        }

        return String.valueOf(sum);
    }
}