package ua.com.alevel.lib;

import java.util.Arrays;

public class MathSet<E extends Number> {
    private int size = 5;
    @SuppressWarnings("unchecked")
    private E[] data = (E[]) new Number[size];
    private int position = 0;

    public MathSet() {
    }

    public MathSet(int capacity) {
        size = capacity;
    }

    @SuppressWarnings("unchecked")
    public MathSet(Number[] numbers) {
        for (Number value : numbers) {
            add((E) value);
        }
    }

    @SuppressWarnings("unchecked")
    public MathSet(Number[]... numbers) {
        for (Number[] valueArray : numbers) {
            for (Number value : valueArray)
                add((E) value);
        }
    }


    @SuppressWarnings("unchecked")
    public MathSet(MathSet<E> numbers) {
        for (Number value : numbers.data) {
            add((E) value);
        }
    }

    @SafeVarargs
    public MathSet(MathSet<E>... numbers) {
        for (MathSet<E> valueArray : numbers) {
            join(valueArray);
        }
    }

//    -----------------------------------------------------------------------------------------


    @SuppressWarnings("unchecked")
    public void add(E numbers) {
        if (position >= data.length) {
            E[] data2 = (E[]) new Number[data.length + 1];
            System.arraycopy(data, 0, data2, 0, data.length);
            data = data2;
        }
        for (int i = 0; i < position; i++) {
            if (numbers.equals(data[i])) {
                remove(numbers);
            }
        }
        data[position] = numbers;
        position += 1;
    }

    @SafeVarargs
    public final void add(E... numbers) {
        for (E number : numbers) {
            add(number);
        }
    }

    @SuppressWarnings("unchecked")
    public void join(MathSet<E> ms) {
        E[] data = (E[]) ms.toArray();
        for (E element : data) {
            add(element);
        }
    }

    @SuppressWarnings("unchecked")
    public void join(MathSet<E>... ms) {
        for (MathSet<E> several : ms) {
            join(several);
        }
    }


    public Number get(int index) {
        return data[index];
    }

    public Number getMax() {
        sort();
        return data[position - 1];
    }

    public Number getMin() {
        sort();
        return data[0];
    }

    public Number getAverage() {
        double average = 0;
        for (int i = 0; i < position; i++) {
            average += data[i].doubleValue();
        }
        return (average / position);
    }

    public Number getMedian() {
        int mid = position / 2;
        return get(mid);
    }


    public void sortAsc() {
        sort();
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }

    public void sortAsc(E value) {
        int firstIndex = 0;
        for (int i = 0; i < position; i++) {
            if (data[i].equals(value)) {
                firstIndex = i;
                break;
            }
        }
        sortAsc(firstIndex, position);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        sort();
        for (int i = firstIndex; i < lastIndex; i++) {
            System.out.print(data[i] + " ");
        }
    }


    public void sortDesc(int firstIndex, int lastIndex) {
        boolean sort = false;
        while (!sort) {
            sort = true;
            for (int j = 0; j < position - 1; j++) {
                if (data[j].doubleValue() < data[j + 1].doubleValue()) {
                    E t = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = t;
                    sort = false;
                }
            }
        }
        for (int i = firstIndex; i < lastIndex; i++) {
            System.out.print(data[i] + " ");
        }

    }

    public void sortDesc(E value) {
        int firstIndex = 0;
        for (int i = 0; i < position; i++) {
            if (data[i].equals(value)) {
                firstIndex = i;
                break;
            }
        }
        sortDesc(firstIndex, position);
    }


    public void sortDesc() {
        sort();
        for (int i = data.length - 1; i >= 0; i--) {
            System.out.print(data[i] + " ");
        }
    }


    @SuppressWarnings("unchecked")
    public Number[] toArray() {
        E[] objects = (E[]) new Number[position];
        if (size() >= 0) System.arraycopy(data, 0, objects, 0, size());
        return objects;
    }

    @SuppressWarnings("unchecked")
    public Number[] toArray(int firstIndex, int lastIndex) {
        E[] objects = (E[]) new Number[position];
        int indexForPosition = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            objects[indexForPosition] = data[i];
            indexForPosition++;
        }
        return objects;
    }

    public MathSet<E> squash(int firstIndex, int lastIndex) {
        MathSet<E> newMathSet = new MathSet<>();
        for (int i = 0; i < firstIndex; i++) {
            newMathSet.add(data[i]);
        }
        for (int i = lastIndex; i < position; i++) {
            newMathSet.add(data[i]);
        }
        return newMathSet;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        position = 0;
        data = (E[]) new Number[size];
    }

    public void clear(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            remove(data[i]);
        }
    }

    private void sort() {
        boolean sort = false;
        while (!sort) {
            sort = true;
            for (int j = 1; j < position; j++) {
                if (data[j].doubleValue() < data[j - 1].doubleValue()) {
                    E t = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = t;
                    sort = false;
                }
            }
        }
    }

    private void remove(E element) {
        for (int i = 0; i < position; i++) {
            if (data[i].equals(element)) {
                if (i <= position - 1) {
                    if (data.length - 1 - i >= 0) System.arraycopy
                            (data, i + 1, data, i, data.length - 1 - i);
                }
                data[data.length - 1] = null;
                position -= 1;
            }
        }
    }

    private int size() {
        return position;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}



