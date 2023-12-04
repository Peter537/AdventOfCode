package dat.peter.day4;

import dat.peter.Day;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4 extends Day {

    @Override
    public int day() {
        return 4;
    }

    @Override
    public String partOne(List<String> input) {
        return input.stream()
                .map(Card::fromLine)
                .map(this::getPoints)
                .reduce(0, Integer::sum).toString();
    }

    @Override
    public String partTwo(List<String> input) {
        int sum = 0;
        Map<Integer, Card> cardMap = createCardMap(input);
        Map<Integer, Integer> timesMap = new HashMap<>();
        Map<Integer, Integer> cardTimesMap = new HashMap<>();
        for (int cardNumber : cardMap.keySet()) {
            Card card = cardMap.get(cardNumber);
            int times = timesMap.getOrDefault(cardNumber, -1);
            if (times == -1) {
                times = getTimes(card);
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


    private int getTimes(Card card) {
        return card.myNumbers().stream()
                .filter(number -> card.winningNumbers().contains(number))
                .map(number -> 1)
                .reduce(0, Integer::sum);
    }

    private int getPoints(Card card) {
        return (int) Math.pow(2, getTimes(card) - 1);
    }

    private Map<Integer, Card> createCardMap(List<String> input) {
        return input.stream()
                .map(Card::fromLine)
                .collect(Collectors.toMap(
                        Card::cardNumber,
                        card -> card,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}