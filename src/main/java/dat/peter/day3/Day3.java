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
        char[][] data = new char[140][140];
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                data[i][j] = line.charAt(j);
            }
        }

        for (char[] chars : data) {
            for (char c : chars) {
                if (!Character.isDigit(c) && c != '.') {
                    characters.add(c);
                }
            }
        }

        int sum = 0;
        // PART 1
        List<Symbol> symbols = new ArrayList<>();
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                char current = data[y][x];
                if (isSymbol(current)) {
                    symbols.add(new Symbol(current, x, y));
                }
            }
        }

        for (Symbol symbol : symbols) {
            Set<Integer> numbers = numbersAdjacentTo(data, symbol);
            int symbolSum = numbers.stream().reduce(0, Integer::sum);
            sum += symbolSum;
        }

        // PART 2
        int gearRatios = 0;
        List<Symbol> gears = symbols.stream().filter(symbol -> symbol.symbol == '*').toList();
        for (Symbol symbol : gears) {
            Set<Integer> integers = numbersAdjacentTo(data, symbol);
            if (integers.size() != 2) {
                continue;
            }

            List<Integer> integerList = integers.stream().toList();
            int gearRatio = integerList.get(0) * integerList.get(1);
            gearRatios += gearRatio;
        }

        System.out.println("Sum: " + sum);
        System.out.println("GearRatios: " + gearRatios);
        System.out.println(characters);
    }

    public static Set<Integer> numbersAdjacentTo(char[][] data, Symbol symbol) {
        Set<Integer> numbers = new HashSet<>();
        for (int[] direction : symbol.adjacentPositions()) {
            int x = direction[0];
            int y = direction[1];
            if (x < 0 || y < 0 || y >= data.length || x >= data[y].length) {
                continue;
            }

            char position = data[y][x];
            if (Character.isDigit(position)) {
                int num = getNumber(data, x, y);
                numbers.add(num);
            }
        }
        return numbers;
    }

    public static boolean isSymbol(char c) {
        for (char c1 : characters) {
            if (c == c1) {
                return true;
            }
        }

        return false;
    }

    public static int getNumber(char[][] data, int x, int y) {
        int start = x;
        int end = x;
        while (start >= 0 && Character.isDigit(data[y][start])) {
            start--;
        }
        while (end < data[y].length && Character.isDigit(data[y][end])) {
            end++;
        }

        start++;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(data[y][i]);
        }
        return Integer.parseInt(sb.toString());
    }

    public record Symbol(char symbol, int x, int y) {

        public int[][] adjacentPositions() {
            return new int[][]{
                    {x - 1, y - 1},
                    {x, y - 1},
                    {x + 1, y - 1},
                    {x - 1, y},
                    {x + 1, y},
                    {x - 1, y + 1},
                    {x, y + 1},
                    {x + 1, y + 1},
            };
        }
    }
}