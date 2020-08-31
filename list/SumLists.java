package list;

/**
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit.
 * The digits are stored in reverse order, such that the 1 's digit is at the head of the list.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input:(7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295. Output:2 -> 1 -> 9.That is,912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem. EXAMPLE
 * lnput:(6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295. Output:9 -> 1 -> 2.That is,912.
 */
public class SumLists {

    public static void main(String[] args) {
        LinkedList<Integer> value1 = new LinkedList<>();
        value1.add(7);
        value1.add(1);
        value1.add(6);
        LinkedList<Integer> value2 = new LinkedList<>();
        value2.add(5);
        value2.add(9);
        value2.add(2);

        System.out.println(sumReverse(value1, value2) == 912);
        System.out.println(sumForward(value1, value2) == 1308);
        value1 = new LinkedList<>();
        value1.add(1);
        value1.add(2);
        value2 = new LinkedList<>();
        value2.add(6);
        System.out.println(sumReverse(value1, value2) == 27);
        System.out.println(sumForward(value1, value2) == 18);
    }

    private static int sumReverse(LinkedList<Integer> value1, LinkedList<Integer> value2) {
        return getListReverseIntValue(value1) + getListReverseIntValue(value2);
    }

    private static int sumForward(LinkedList<Integer> value1, LinkedList<Integer> value2) {
        return getListForwardIntValue(value1) + getListForwardIntValue(value2);
    }


    private static int getListReverseIntValue(LinkedList<Integer> value) {
        int exponent = 0;
        int sum = 0;
        Node<Integer> currentDigit = value.getHead();
        while (currentDigit != null) {
            sum += Math.pow(10, exponent++) * currentDigit.getValue();
            currentDigit = currentDigit.getNext();
        }
        return sum;
    }

    private static int getListForwardIntValue(LinkedList<Integer> value) {
        return sumValue(value.getHead()).value;
    }

    /**
     * Recursive method to calculate the number sum
     */
    private static Digit sumValue(Node<Integer> currentValue) {
        if (currentValue == null) {
            return new Digit(0, -1);
        }
        Digit digit = sumValue(currentValue.getNext());
        double newValue = currentValue.getValue() * Math.pow(10, (double) digit.place + 1);
        return new Digit((int) newValue + digit.value, digit.place + 1);
    }
}

/**
 * This class is for handle the cumulative values
 */
class Digit {

    public final Integer value;
    public final Integer place;

    public Digit(Integer value, Integer place) {
        this.value = value;
        this.place = place;
    }
}

class Node<T> {

    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}

class LinkedList<T> {

    private Node<T> head;

    public Node<T> getHead() {
        return head;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            Node<T> last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(node);
        }
    }
}