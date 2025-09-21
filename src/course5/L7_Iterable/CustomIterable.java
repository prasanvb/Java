package course5.L7_Iterable;

import java.util.Iterator;

public class CustomIterable {
    public static void main(String[] args) {
        NumberRange range = new NumberRange(1, 5);
        for (int num : range) {
            System.out.println(num); // Prints 1, 2, 3, 4, 5
        }
    }
}


record NumberRange(int start, int end) implements Iterable<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int current = start;

            @Override
            public boolean hasNext() {
                return current <= end;
            }

            @Override
            public Integer next() {
                return (Integer) current++;
            }
        };
    }
}