package dat.peter.day6;

import dat.peter.Day;

import java.util.ArrayList;
import java.util.List;

public class Day6 extends Day {

    @Override
    public int day() {
        return 6;
    }

    @Override
    public String partOne(List<String> input) {
        List<Integer> time = new ArrayList<>();
        for (String s : input.get(0).split(":")[1].split(" ")) {
            if (s.isEmpty()) {
                continue;
            }

            time.add(Integer.parseInt(s));
        }
        List<Integer> distance = new ArrayList<>();
        for (String s : input.get(1).split(":")[1].split(" ")) {
            if (s.isEmpty()) {
                continue;
            }

            distance.add(Integer.parseInt(s));
        }

        List<Race> races = new ArrayList<>();
        for (int i = 0; i < time.size(); i++) {
            races.add(new Race(time.get(i), distance.get(i)));
        }

        long sum = 1;
        for (Race race : races) {
            sum *= race.amountOfWinningNumbers();
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        long time = Long.parseLong(input.get(0).split(":")[1].replace(" ", ""));
        long distance = Long.parseLong(input.get(1).split(":")[1].replace(" ", ""));
        Race race = new Race(time, distance);
        return String.valueOf(race.amountOfWinningNumbers());
    }
}