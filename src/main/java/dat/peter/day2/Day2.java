package dat.peter.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/dat/peter/day2/input.txt");
        Scanner scanner = new Scanner(file);
        int maxRedCubes = 12;
        int maxGreenCubes = 13;
        int maxBlueCubes = 14;
        int sum = 0;
        int power = 0;
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(": ");
            int gameNumber = Integer.parseInt(line[0].split(" ")[1]);
            System.out.println("GameNumber: " + gameNumber);
            System.out.println("SumBefore: " + sum);
            String[] subsets = line[1].split("; ");
            boolean isPossible = true;
            int lowestRed = 1;
            int lowestGreen = 1;
            int lowestBlue = 1;
            for (String s : subsets) {
                String[] cubes = s.trim().split(", ");
                for (String s1 : cubes) {
                    int num = Integer.parseInt(s1.split(" ")[0]);
                    if (s1.contains("blue")) {
                        if (num > maxBlueCubes) {
                            isPossible = false;
                        }
                        if (num > lowestBlue) {
                            lowestBlue = num;
                        }
                    } else if (s1.contains("red")) {
                        if (num > maxRedCubes) {
                            isPossible = false;
                        }
                        if (num > lowestRed) {
                            lowestRed = num;
                        }
                    } else if (s1.contains("green")) {
                        if (num > maxGreenCubes) {
                            isPossible = false;
                        }
                        if (num > lowestGreen) {
                            lowestGreen = num;
                        }
                    }
                }
            }
            if (isPossible) {
                System.out.println("GameNumber isPossible: " + gameNumber);
                sum += gameNumber;
            }
            power += (lowestBlue * lowestGreen * lowestRed);
        }
        System.out.println("Sum: " + sum);
        System.out.println("Power: " + power);
    }
}