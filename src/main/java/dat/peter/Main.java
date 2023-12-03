package dat.peter;

import dat.peter.day1.Day1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Day> days = new ArrayList<>();
        days.add(new Day1());

        days.forEach(Day::run);
    }
}