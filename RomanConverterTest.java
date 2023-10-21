import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();

    }
}

class RomanConverter {

    public static final String[] ROMAN_NUMBERS = {
            "M", "CM", "DCCC", "DCC", "DC", "D", "CD", "CCC", "CC", "C", "XC", "LXXX", "LXX", "LX", "L",
            "XL", "XXX", "XX", "XIX", "XVIII", "XVII", "XV", "XIV", "XIII", "XII", "XI", "X", "IX",
            "VIII", "VII", "VI", "V", "IV", "III", "II", "I"
    };

    public static final int NUMERAL_NUMBERS[] = {
        1000, 900, 800, 700, 600, 500, 400, 300, 200, 100, 90, 80, 70, 60, 50, 40, 30, 20,
            19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
    };
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */

    public static String toRoman(int n) {
        String str = new String();
        int i = 0;
        while (n>0){
            if(n>=NUMERAL_NUMBERS[i]){
                str+=ROMAN_NUMBERS[i];
                n = n-NUMERAL_NUMBERS[i];
            }
            else i++;
        }

        return str;
    }

}
