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

    private int findNumber(String line, boolean withWords) {
        String firstNum = "";
        String lastNum = "";
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Character.isDigit(c)) {
                if (firstNum.isEmpty()) {
                    firstNum = String.valueOf(c);
                }

                lastNum = String.valueOf(c);
            } else if (withWords) {
                for (String s : map.keySet()) {
                    if (line.length() < i + s.length()) {
                        continue;
                    }

                    String substring = line.substring(i, i + s.length());
                    if (s.equals(substring)) {
                        if (firstNum.isEmpty()) {
                            firstNum = String.valueOf(map.get(s));
                        }

                        lastNum = String.valueOf(map.get(s));
                    }
                }
            }
        }

        return Integer.parseInt(firstNum + lastNum);
    }

    @Override
    public int day() {
        return 1;
    }

    @Override
    public String partOne(List<String> input) {
        int sum = 0;
        for (String line : input) {
            sum += findNumber(line, false);
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        int sum = 0;
        for (String line : input) {
            sum += findNumber(line, true);
        }

        return String.valueOf(sum);
    }
}