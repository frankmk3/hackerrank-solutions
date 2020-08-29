package string;

import java.util.*;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 *
 *  What if you cannot use additional data structures?
 */
public class IsUnique {

    public static void main(String[] arg) {
        assert isUnique(null);
        assert isUniqueOnlyString(null);
        assert isUnique("");
        assert isUniqueOnlyString("");
        assert isUnique("a");
        assert isUniqueOnlyString("a");
        assert !isUnique("aa");
        assert !isUniqueOnlyString("aa");
        assert !isUnique("aqwertyuiopasdfghjkla");
        assert !isUniqueOnlyString("aqwertyuiopasdfghjkla");

        System.out.println("Correct");
    }

    /**
     * a       yes
     * abc     yes
     * abcda  no
     *
     * brute force
     * compare each element with the rest
     * from 0 to length
     * from cursor+1 to length
     * O(N*N)
     *
     * Using a HashSet until found duplicate
     * If no duplicate so is unique
     * O(N) time and space
     *
     * If only string are allow
     * sort the string and check is character in i is equals to character i+1
     * sorting N log(N)  + N so Big O(N log(N))
     */
    private static boolean isUnique(String text) {
        if (text != null) {
            Set<Character> store = new HashSet<>();
            for (int i = 0; i < text.length(); i++) {
                if(store.contains(text.charAt(i))){
                    return false;
                }
                store.add(text.charAt(i));
            }

        }
        return true;
    }

    private static boolean isUniqueOnlyString(String text) {
        if (text != null) {
            char[] chars = text.toCharArray();
            Arrays.sort(chars);
            for(int i = 1; i < chars.length; i++){
                if(chars[i-1] == chars[i]){
                    return false;
                }
            }
        }
        return true;
    }
}
