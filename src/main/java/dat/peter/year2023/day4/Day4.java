package dat.peter.year2023.day4;

import dat.peter.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4 extends Day {

    private final Map<Integer, Integer> timesMap = new HashMap<>();
    private final Map<Integer, Integer> cardTimesMap = new HashMap<>();

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
        this.createCards(input)
                .forEach(card -> {
                    int times = this.getTimesFromMap(card);
                    int currentTimes = this.getCurrentTimesFromMap(card);
                    addCards(card, times, currentTimes);
                });

        return String.valueOf(getSumOfCardTimesMap());
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

    private List<Card> createCards(List<String> input) {
        return input.stream()
                .map(Card::fromLine)
                .collect(Collectors.toList());
    }

    private int getTimesFromMap(Card card) {
        int times = timesMap.getOrDefault(card.cardNumber(), -1);
        if (times == -1) {
            times = getTimes(card);
            timesMap.put(card.cardNumber(), times);
        }

        return times;
    }

    private int getCurrentTimesFromMap(Card card) {
        int currentTimes = cardTimesMap.getOrDefault(card.cardNumber(), -1);
        if (currentTimes == -1) {
            cardTimesMap.put(card.cardNumber(), 1);
            currentTimes = cardTimesMap.get(card.cardNumber());
        }

        return currentTimes;
    }

    public void addCards(Card card, int times, int currentTimes) {
        for (int i = 1; i <= times; i++) {
            int nextCardNumber = card.cardNumber() + i;
            cardTimesMap.put(nextCardNumber, cardTimesMap.getOrDefault(nextCardNumber, 1) + (currentTimes));
        }
    }

    private int getSumOfCardTimesMap() {
        return this.cardTimesMap.values().stream()
                .reduce(0, Integer::sum);
    }
}