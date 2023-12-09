package dat.peter.year2023.day3;

import dat.peter.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 extends Day {

    private final Set<Character> characters = new HashSet<>();

    public char[][] getGrid(List<String> input) {
        char[][] grid = new char[140][140];
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                grid[i][j] = c;
                if (!Character.isDigit(c) && c != '.') {
                    this.characters.add(c);
                }
            }
        }

        return grid;
    }

    public List<Symbol> getSymbols(char[][] grid) {
        List<Symbol> symbols = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                char current = grid[y][x];
                if (isSymbol(current)) {
                    symbols.add(new Symbol(current, x, y));
                }
            }
        }

        return symbols;
    }

    public Set<Integer> numbersAdjacentTo(char[][] data, Symbol symbol) {
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

    public boolean isSymbol(char c) {
        return this.characters.stream().anyMatch(c1 -> c1 == c);
    }

    public int getNumber(char[][] data, int x, int y) {
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

    @Override
    public int day() {
        return 3;
    }

    @Override
    public String partOne(List<String> input) {
        int sum = 0;
        char[][] grid = getGrid(input);
        for (Symbol symbol : getSymbols(grid)) {
            Set<Integer> numbers = numbersAdjacentTo(grid, symbol);
            int symbolSum = numbers.stream().reduce(0, Integer::sum);
            sum += symbolSum;
        }

        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        int sum = 0;
        char[][] grid = getGrid(input);
        List<Symbol> gears = getSymbols(grid).stream().filter(symbol -> symbol.symbol() == '*').toList();
        for (Symbol symbol : gears) {
            Set<Integer> integers = numbersAdjacentTo(grid, symbol);
            if (integers.size() != 2) {
                continue;
            }

            List<Integer> integerList = integers.stream().toList();
            int gearRatio = integerList.get(0) * integerList.get(1);
            sum += gearRatio;
        }

        return String.valueOf(sum);
    }
}