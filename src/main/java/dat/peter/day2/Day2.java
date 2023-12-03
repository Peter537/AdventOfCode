package dat.peter.day2;

import dat.peter.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 extends Day {

    private static final Map<String, Integer> maxCubeMap = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    public boolean isGamePossible(String game) {
        boolean isPossible = true;
        String[] subsets = game.split("; ");
        for (String subset : subsets) {
            String[] cubes = subset.trim().split(", ");
            for (String cube : cubes) {
                String[] cubeData = cube.split(" ");
                int num = Integer.parseInt(cubeData[0]);
                if (num > maxCubeMap.get(cubeData[1])) {
                    isPossible = false;
                }
            }
        }

        return isPossible;
    }

    public int getPowerOfGame(String line) {
        Map<String, Integer> powerMap = new HashMap<>();
        String[] lineData = line.split(": ");
        String[] subsets = lineData[1].split("; ");
        for (String subset : subsets) {
            String[] cubes = subset.trim().split(", ");
            for (String cube : cubes) {
                String[] cubeData = cube.split(" ");
                int num = Integer.parseInt(cubeData[0]);
                String color = cubeData[1];
                if (!powerMap.containsKey(color)) {
                    powerMap.put(color, num);
                } else {
                    if (num > powerMap.get(color)) {
                        powerMap.put(color, num);
                    }
                }
            }
        }

        int sum = 1;
        for (int i : powerMap.values()) {
            sum *= i;
        }

        return sum;
    }

    @Override
    public int day() {
        return 2;
    }

    @Override
    public String partOne(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] lineSplit = line.split(": ");
            if (isGamePossible(lineSplit[1])) {
                int gameNumber = Integer.parseInt(lineSplit[0].split(" ")[1]);
                sum += gameNumber;
            }
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        int sum = 0;
        for (String line : input) {
            int power = getPowerOfGame(line);
            sum += power;
        }

        return String.valueOf(sum);
    }
}