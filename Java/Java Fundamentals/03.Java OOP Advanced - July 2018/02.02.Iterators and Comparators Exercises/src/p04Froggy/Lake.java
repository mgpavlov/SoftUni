package p04Froggy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Lake<T> implements Iterable<T> {
    private List<T> numbers;

    public Lake(List<T> numbers) {
        this.setNumbers(numbers);
    }

    public void setNumbers(List<T> numbers) {
        if (numbers != null){
            this.numbers = numbers;
        }else {
            this.numbers = new ArrayList<>();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator<T> {
        private int evenIndex;
        private int oddIndex;
        private int count;

        public Frog() {
            this.evenIndex = 0;
            this.oddIndex = 1;
            this.count = 0;
        }

        @Override
        public boolean hasNext() {
            if (!numbers.isEmpty()){
                return this.count < numbers.size();
            }
            return false;
        }

        @Override
        public T next() {
            this.count++;
            int currentIndex;
            if (this.evenIndex < numbers.size()) {
                currentIndex = evenIndex;
                evenIndex +=2;
                return numbers.get(currentIndex);
            } else {
                currentIndex = oddIndex;
                oddIndex+=2;
                return numbers.get(currentIndex);
            }
        }
    }
}
