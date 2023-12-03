package dat.peter.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {

    private static final Set<Character> characters = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/dat/peter/day3/input.txt");
//        file = new File("src/main/java/dat/peter/day3/sample.txt");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        for (String s : lines) {
            for (Character c : s.toCharArray()) {
                if (!Character.isDigit(c) && c != '.') {
                    characters.add(c);
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String lineAbove = i > 0 ? lines.get(i - 1) : "";
            String lineBelow = i < lines.size() - 1 ? lines.get(i + 1) : "";
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (Character.isDigit(c)) {
                    boolean shouldAdd = false;
                    int num = findNumber(line, j);
                    int numSize = String.valueOf(num).length();
//                    System.out.println("Current: " + num + " (" + numSize + ")");
                    if (j > 0) {
                        char charLeft = line.charAt(j - 1);
                        if (isSymbol(charLeft)) {
                            shouldAdd = true;
                        }
                    }

                    if (j + numSize < line.length() - 1) {
                        char charRight = line.charAt(j + numSize);
                        if (isSymbol(charRight)) {
                            shouldAdd = true;
                        }
                    }

                    for (String s : new String[]{lineBelow, lineAbove}) {
                        for (int k = j - 1; k < j + numSize + 1; k++) {
                            if (k < 0 || k >= s.length()) {
                                continue;
                            }

                            char c1 = s.charAt(k);
                            if (isSymbol(c1)) {
                                shouldAdd = true;
                                break;
                            }
                        }
                    }

                    if (shouldAdd) {
                        sum += num;
                    }

                    j += numSize;
                }
            }
        }
        System.out.println("Sum: " + sum);

        int gearRatios = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c == '*') {
//                    System.out.println(c);
                    int gearRatio = findNear(j, line, (i > 0 ? lines.get(i - 1) : ""), (i < lines.size() - 1 ? lines.get(i + 1) : ""));
                    gearRatios += gearRatio;
                }
            }
        }

        System.out.println("GearRatios: " + gearRatios);
        System.out.println(characters);
    }

    public static boolean isSymbol(char c) {
        for (char c1 : characters) {
            if (c == c1) {
                return true;
            }
        }

        return false;
    }

    public static int findNumber(String line, int j) {
        try {
            return Integer.parseInt(line.substring(j, j + 3));
        } catch (Exception e) {
            try {
                return Integer.parseInt(line.substring(j, j + 2));
            } catch (Exception e2) {
                try {
                    return Integer.parseInt(line.substring(j, j + 1));
                } catch (Exception e3) {
                    return 0;
                }
            }
        }
    }

    public static int findNumberLeft(String line, int j) {
        try {
            return Integer.parseInt(line.substring(j - 3, j));
        } catch (Exception e) {
            try {
                return Integer.parseInt(line.substring(j - 2, j));
            } catch (Exception e2) {
                try {
                    return Integer.parseInt(line.substring(j - 1, j));
                } catch (Exception e3) {
                    return 0;
                }
            }
        }
    }

    public static int findNear(int starIndex, String line, String lineAbove, String lineBelow) {
        List<Integer> ints = new ArrayList<>();
        List<String> lines = Arrays.asList(lineAbove, line, lineBelow);
        System.out.println("lines");
        List<List<Integer>> intLists = new ArrayList<>();
        for (String l : lines) {
            if (Objects.equals(l, "")) {
                continue;
            }

            List<Integer> integers = new ArrayList<>();
            for (int i = -1; i <= 1; i++) {
                integers.add(findNumber(l, starIndex + i));
            }

            for (int i = 1; i >= -1; i--) {
                integers.add(findNumberLeft(l, starIndex + i));
            }
//                System.out.println(integers);
            List<Integer> sorted = integers.stream().sorted().toList();
            System.out.println(sorted);
//                System.out.println("num: " + num);
            intLists.add(sorted);
        }

        List<Integer> list = new ArrayList<>();
        intLists.forEach(list::addAll);
        System.out.println(list);
        List<Integer> sortedList = list.stream().sorted().toList();
        System.out.println(sortedList);
        System.out.println("ints: " + ints);
        if (ints.size() == 2) {
            return ints.get(0) * ints.get(1);
        }

        return 0;
    }
}