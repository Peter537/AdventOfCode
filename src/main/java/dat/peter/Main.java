package dat.peter;

import dat.peter.year2023.day1.Day1;
import dat.peter.year2023.day2.Day2;
import dat.peter.year2023.day3.Day3;
import dat.peter.year2023.day4.Day4;
import dat.peter.year2023.day5.Day5;
import dat.peter.year2023.day6.Day6;
import dat.peter.year2023.day7.Day7;
import dat.peter.year2023.day8.Day8;
import dat.peter.year2023.day9.Day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final List<Day> DAY_MAP = List.of(
            new Day1(),
            new Day2(),
            new Day3(),
            new Day4(),
            new Day5(),
            new Day6(),
            new Day7(),
            new Day8(),
            new Day9()
    );

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<Day> days = new ArrayList<>();
        while (true) {
            System.out.println("All answers: a(ll)");
            System.out.println("Otherwise type numbers like: 1 3 4");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("a") || answer.equals("all")) {
                days.addAll(DAY_MAP);
                break;
            } else {
                try {
                    days.addAll(Arrays.stream(answer.split(" "))
                            .map(Integer::parseInt)
                            .map(index -> DAY_MAP.get(index - 1))
                            .toList());
                    break;
                } catch (Exception ignored) {
                }
            }
        }

        days.forEach(Day::run);
    }
}