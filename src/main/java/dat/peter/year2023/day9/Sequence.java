package dat.peter.year2023.day9;

public record Sequence(long[] sequence) {

    public static Sequence fromLine(String line) {
        String[] split = line.split(" ");
        long[] sequence = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            sequence[i] = Long.parseLong(split[i]);
        }

        return new Sequence(sequence);
    }

    public Sequence getDifference() {
        long[] difference = new long[sequence.length - 1];
        for (int i = 0; i < sequence.length - 1; i++) {
            difference[i] = sequence[i + 1] - sequence[i];
        }

        return new Sequence(difference);
    }
}