package dat.peter.year2023.day6;

public record Race(long raceLengthTime, long recordDistance) {

    public long amountOfWinningNumbers() {
        long firstWinningNumber = -1;
        for (int i = 0; i < raceLengthTime; i++) {
            long remainingTime = raceLengthTime - i;
            long length = remainingTime * (i);
            if (length > recordDistance) {
                firstWinningNumber = i;
                break;
            }
        }

        long lastWinningNumber = (raceLengthTime - firstWinningNumber);
        return (lastWinningNumber - firstWinningNumber) + 1;
    }
}