package dat.peter;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Day {

    public abstract int day();

    public abstract String partOne(List<String> input);

    public abstract String partTwo(List<String> input);

    protected final List<String> getInputLines() {
        return getInputLines(getInputFile());
    }

    public final void run() {
        System.out.println("Day " + day());
        long start = System.currentTimeMillis();
        String partOne = partOne(getInputLines());
        long diff = System.currentTimeMillis() - start;
        System.out.println("| Part 1: " + partOne);
        System.out.println("|  > Took: " + diff + "ms");
        start = System.currentTimeMillis();
        String partTwo = partTwo(getInputLines());
        diff = System.currentTimeMillis() - start;
        System.out.println("| Part 2: " + partTwo);
        System.out.println("|  > Took: " + diff + "ms");
        System.out.println();
    }

    private File getInputFile() {
        try {
            URL url = getClass().getResource("/day" + day() + ".txt");
            if (url == null) {
                throw new FileNotFoundException("Couldn't find input file day " + day());
            }

            return new File(url.toURI());
        } catch (URISyntaxException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getInputLines(File file) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}