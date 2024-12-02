package dat.peter.year2024.day1;

import dat.peter.Day;

import java.util.*;

public class Day1 extends Day {

    @Override
    public int day() {
        return 1;
    }

    @Override
    public String partOne(List<String> input) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String line : input) {
            String[] split = line.split("   ");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }

        Collections.sort(left);
        Collections.sort(right);
        int sum = 0;
        for (int i = 0; i < left.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        List<Integer> left = new ArrayList<>();
        Map<Integer, Integer> right = new HashMap<>();
        for (String line : input) {
            String[] split = line.split("   ");
            left.add(Integer.parseInt(split[0]));
            int rightNumber = Integer.parseInt(split[1]);
            if (right.containsKey(rightNumber)) {
                right.put(rightNumber, right.get(rightNumber) + 1);
            } else {
                right.put(rightNumber, 1);
            }
        }

        int sum = 0;
        for (Integer integer : left) {
            sum += integer * right.getOrDefault(integer, 0);
        }

        return String.valueOf(sum);
    }
}