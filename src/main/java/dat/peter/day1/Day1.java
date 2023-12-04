package dat.peter.day1;

import dat.peter.Day;

import java.util.List;
import java.util.Map;

public class Day1 extends Day {

    private static final Map<String, Integer> map = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    @Override
    public int day() {
        return 1;
    }

    @Override
    public String partOne(List<String> input) {
        return input.stream()
                .map(line -> findNumber(line, false))
                .reduce(0, Integer::sum)
                .toString();
    }

    @Override
    public String partTwo(List<String> input) {
        return input.stream()
                .map(line -> findNumber(line, true))
                .reduce(0, Integer::sum)
                .toString();
    }

    private int findNumber(String line, boolean withWords) {
        Number number = new Number();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                number.setChar(c);
            } else if (withWords) {
                for (String s : map.keySet()) {
                    if (line.length() < i + s.length()) {
                        continue;
                    }

                    String substring = line.substring(i, i + s.length());
                    if (s.equals(substring)) {
                        number.setInt(map.get(s));
                    }
                }
            }
        }

        return number.toInt();
    }
}