package dat.peter.day4;

import dat.peter.Day;

import java.util.*;

public class Day4 extends Day {

    private int getTimes(List<Integer> winningNumbers, List<Integer> myNumbers) {
        int times = 0;
//        System.out.println(winningNumbers);
//        System.out.println(myNumbers);
        for (int num : myNumbers) {
            if (winningNumbers.contains(num)) {
                times += 1;
            }
        }

        return times;
    }

    private int getPoints(List<Integer> winningNumbers, List<Integer> myNumbers) {
        int times = 0;
//        System.out.println(winningNumbers);
//        System.out.println(myNumbers);
        for (int num : myNumbers) {
            if (winningNumbers.contains(num)) {
                times += 1;
            }
        }

//        System.out.println("Times: " + times);
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

    public static List<Integer> getNumbers(String str) {
        List<Integer> integers = new ArrayList<>();
        String[] array = str.split(" ");
        for (String s : array) {
            if (Objects.equals(s, "")) {
                continue;
            }

            integers.add(Integer.parseInt(s));
        }

        return integers;
    }

    @Override
    public int day() {
        return 4;
    }

    @Override
    public String partOne(List<String> input) {
        int sum = 0;
//        input = new ArrayList<>();
//        input.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
//        input.add("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
//        input.add("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
//        input.add("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
//        input.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
//        input.add("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
        for (String line : input) {
            String[] split1 = line.split(": ");
            String[] split2 = split1[1].split(" \\| ");
            List<Integer> winningNumbers = getNumbers(split2[0]).stream().sorted().toList();
            List<Integer> myNumbers = getNumbers(split2[1]).stream().sorted().toList();
            int points = getPoints(winningNumbers, myNumbers);
//            System.out.println(split1[0] + ": " + points);
            sum += points;
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        int sum = 0;
//        input = new ArrayList<>();
//        input.add("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
//        input.add("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19");
//        input.add("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1");
//        input.add("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83");
//        input.add("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36");
//        input.add("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11");
        Map<Integer, List<Card>> cardMap = new LinkedHashMap<>();

        for (String line : input) {
//            String[] split1 = line.split(": ");
//            String[] split2 = split1[1].split(" \\| ");
//            List<Integer> winningNumbers = getNumbers(split2[0]).stream().sorted().toList();
//            List<Integer> myNumbers = getNumbers(split2[1]).stream().sorted().toList();
            Card card = Card.fromLine(line);
            List<Card> cards = new LinkedList<>();
            cards.add(card);
            cardMap.put(card.cardNumber(), cards);
        }

        Map<Integer, Integer> timesMap = new HashMap<>();
        for (int cardNumber : cardMap.keySet()) {
            List<Card> cards = cardMap.get(cardNumber);
            System.out.println("Going through: " + cardNumber + "(" + cards.size() + ")");
            for (Card card : cards) {
                int times = timesMap.getOrDefault(cardNumber, -1);
                if (times == -1) {
                    times = getTimes(card.winningNumbers(), card.myNumbers());
                    timesMap.put(cardNumber, times);
                }
//                int times = getTimes(card.winningNumbers(), card.myNumbers());
//                System.out.println(card.cardNumber);
                sum += 1;
//                System.out.println("times: " + times);
//                cardMap.values().forEach(System.out::println);
                for (int i = 1; i <= times; i++) {
                    List<Card> cards1 = cardMap.get(cardNumber + i);
                    List<Card> newList = new ArrayList<>(cards1);
                    newList.add(cards1.get(0));
                    cardMap.put(cardNumber + i, newList);
                }
            }
        }

        return String.valueOf(sum);
    }

    public record Card(int cardNumber, List<Integer> winningNumbers, List<Integer> myNumbers) {

        public static Card fromLine(String line) {
            String[] split1 = line.split(": ");
//            System.out.println("Going through: " + line);
            int num = Integer.parseInt(split1[0].replace("Card", "").replace(" ", ""));
//            System.out.println("num: " + num);
            String[] split2 = split1[1].split(" \\| ");
            List<Integer> winningNumbers = getNumbers(split2[0]);
            List<Integer> myNumbers = getNumbers(split2[1]);
            return new Card(num, winningNumbers, myNumbers);
        }
    }
}