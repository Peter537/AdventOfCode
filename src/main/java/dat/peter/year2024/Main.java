package dat.peter.year2024;

import dat.peter.Day;
import dat.peter.year2024.day1.Day1;
import dat.peter.year2024.day2.Day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final List<Day> DAY_MAP = List.of(
            new Day1(),
            new Day2()
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

        days.forEach(day -> {
            day.run(2024);
        });
    }
}