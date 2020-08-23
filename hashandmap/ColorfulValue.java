package hashandmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://algorithms.tutorialhorizon.com/colorful-numbers/
 */
public class ColorfulValue {

    public static void main(String[] args) {
        ColorfulNumbers c = new ColorfulNumbers();
        System.out.println(" 326 Colorful?? " + c.isColorful(326));
        System.out.println(" 3245 Colorful?? " + c.isColorful(3245));
    }
}


class ColorfulNumbers {

    private Set<Integer> store = new HashSet<>();

    /**
     * Is colorful if the values does not exist in the store
     */
    private boolean checkColorful(int value) {
        if (store.contains(value)) {
            return false;
        } else {
            store.add(value);
        }
        return true;
    }

    /**
     * Generate a list base on the input value
     */
    private List<Integer> getDigits(int value) {
        String numberString = String.valueOf(value);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numberString.length(); i++) {
            result.add(Integer.parseInt(String.valueOf(numberString.charAt(i))));
        }
        return result;
    }

    /**
     * The approach will use  each number as a pivot and generate all the possible combinations until the end.
     * For each combination will check if the new number product is colorful.
     */
    public boolean isColorful(int number) {
        store = new HashSet<>();
        List<Integer> digits = getDigits(number);
        //Use each number as a pivot
        for (int i = 0; i < digits.size(); i++) {
            //How many number left to generate
            for (int j = 1; j <= digits.size() - i; j++) {
                int currentValue = 1;
                //multiply each number in the sequence
                for (int y = i; y < j + i; y++) {
                    currentValue *= digits.get(y);
                }
                if (!checkColorful(currentValue)) {
                    return false;
                }
            }
        }
        return true;
    }
}