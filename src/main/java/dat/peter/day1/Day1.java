package dat.peter.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day1 {

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

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/dat/peter/day1/input.txt");
        Scanner scanner = new Scanner(file);
        long sum = 0;
        int lineNumber = 1;
        while (scanner.hasNextLine()) {
            System.out.println("Linenumber: " + lineNumber++);
            String line = scanner.nextLine();
            String firstNum = "";
            String lastNum = "";
            // Part 1
            /*
            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (firstNum.isEmpty()) {
                        firstNum = String.valueOf(c);
                    }

                    lastNum = String.valueOf(c);
                }
            }
             */

            // Part 2
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isDigit(c)) {
                    if (firstNum.isEmpty()) {
                        firstNum = String.valueOf(c);
                    }

                    lastNum = String.valueOf(c);
                } else {
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

            int num = Integer.parseInt(firstNum + lastNum);
            System.out.println(num);
            sum += num;
        }
        System.out.println(sum);
    }
}