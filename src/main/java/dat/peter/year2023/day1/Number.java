package dat.peter.year2023.day1;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Number {

    private String firstNumber;
    private String lastNumber;

    public void setChar(char c) {
        set(String.valueOf(c));
    }

    public void setInt(Integer integer) {
        set(String.valueOf(integer));
    }

    public int toInt() {
        return Integer.parseInt(firstNumber + lastNumber);
    }

    private void set(String s) {
        if (firstNumber == null) {
            firstNumber = s;
        }

        lastNumber = s;
    }
}