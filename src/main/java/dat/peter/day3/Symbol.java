package dat.peter.day3;

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