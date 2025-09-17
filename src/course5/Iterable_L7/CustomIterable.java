package course5.Iterable_L7;

import java.util.Iterator;

public class CustomIterable {
    public static void main(String[] args) {
        NumberRange range = new NumberRange(1, 5);
        for (int num : range) {
            System.out.println(num); // Prints 1, 2, 3, 4, 5
        }
    }
}


class NumberRange implements Iterable<Integer> {
    private final int start;
    private final int end;

    public NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

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
                return current++;
            }
        };
    }
}