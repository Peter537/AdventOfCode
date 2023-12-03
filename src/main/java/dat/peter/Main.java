package dat.peter;

import dat.peter.day1.Day1;
import dat.peter.day2.Day2;
import dat.peter.day3.Day3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Day> days = new ArrayList<>();
        days.add(new Day1());
        days.add(new Day2());
        days.add(new Day3());

        days.forEach(Day::run);
    }
}