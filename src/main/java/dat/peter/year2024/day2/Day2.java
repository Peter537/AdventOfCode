package dat.peter.year2024.day2;

import dat.peter.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 extends Day {

    @Override
    public int day() {
        return 2;
    }

    @Override
    public String partOne(List<String> input) {
        int safeReports = 0;
        for (String s : input) {
            List<Integer> integers = Arrays.stream(s.split(" ")).map(Integer::parseInt).toList();
            if (amountOfErrors(integers) == 0) {
                safeReports++;
            }
        }

        return String.valueOf(safeReports);
    }

    @Override
    public String partTwo(List<String> input) {
        int safeReports = 0;
        for (String s : input) {
            List<Integer> integers = Arrays.stream(s.split(" ")).map(Integer::parseInt).toList();
            if (amountOfErrors(integers) == 0) {
                safeReports++;
                continue;
            }

            for (int i = 0; i < integers.size(); i++) {
                List<Integer> modifiedList = new ArrayList<>(integers);
                modifiedList.remove(i);
                if (amountOfErrors(modifiedList) == 0) {
                    safeReports++;
                    break;
                }
            }
        }

        return String.valueOf(safeReports);
    }

    private int amountOfErrors(List<Integer> integers) {
        int errors = 0;
        int a = (integers.get(1) > integers.get(0)) ? 1 : 0;
        for (int i = 1; i < integers.size(); i++) {
            int diff = Math.abs(integers.get(i) - integers.get(i - 1));
            if (diff > 3 || diff == 0) {
                errors++;
            }

            if (((integers.get(i) > integers.get(i - 1)) ? 1 : 0) != a) {
                errors++;
            }
        }

        return errors;
    }
}