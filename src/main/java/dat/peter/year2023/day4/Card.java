package dat.peter.year2023.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Card(int cardNumber, List<Integer> winningNumbers, List<Integer> myNumbers) {

    public static Card fromLine(String line) {
        String[] split1 = line.split(": ");
        int num = Integer.parseInt(split1[0].replace("Card", "").replace(" ", ""));
        String[] split2 = split1[1].split(" \\| ");
        List<Integer> winningNumbers = getNumbers(split2[0]);
        List<Integer> myNumbers = getNumbers(split2[1]);
        return new Card(num, winningNumbers, myNumbers);
    }

    private static List<Integer> getNumbers(String str) {
        List<Integer> integers = new ArrayList<>();
        String[] array = str.split(" ");
        for (String s : array) {
            if (Objects.equals(s, "")) {
                continue;
            }

            integers.add(Integer.parseInt(s));
        }

        return integers;
    }
}