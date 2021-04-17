package ua.com.alevel.impl;

import ua.com.alevel.OrderedList;

import java.util.*;


public class OrderedListImpl<E extends Comparable<E>> implements OrderedList<E> {
    private final int size = 5;
    @SuppressWarnings("unchecked")
    private E[] data = (E[]) new Comparable[size];
    private int position = 0;


    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(E number) {
        if (position == 0 ){
            data[position] = number;
            position += 1;
            return true;
        }
        if (position >= data.length) {
            E[] data2 = (E[]) new Comparable[data.length + 5];
            System.arraycopy(data, 0, data2, 0, data.length);
            data = data2;
        }
        for (int i = 0; i < position; i++) {
            if (data[i].compareTo(number) >= 0) {
                System.arraycopy(data, i, data, i + 1, position - i);
                data[i] = number;
                position++;
                return true;
            }
        }
        data[position] = number;
        position += 1;
        return true;
    }

    @Override
    @Deprecated
    public boolean addAll(int index, OrderedList<E> element) {
        throw new RuntimeException("Not use");
    }
    @Override
    public boolean addAll(OrderedList<E> element) {
        for (int i = 0; i < element.size(); i++) {
            add(element.get(i));
        }
        return true;
    }

    @Override
    @Deprecated
    public void add(int index, E element) {
        throw new RuntimeException("Not use");
    }

    @Override
    public boolean removeAll(OrderedList<E> element) {
        for (int i = 0; i < element.size(); i++) {
            if (contains(element.get(i))){
                remove(element.get(i));
            }
        }
        return true;
    }


    @Override
    public boolean retainAll(OrderedList<E> element) {
        for (int i = 0; i < element.size(); i++) {
            if (!contains(element.get(i))){
                if (i <= position - 1) {
                    if (data.length - 1 - i >= 0) System.arraycopy
                            (data, i + 1, data, i, data.length - 1 - i);
                }
                data[data.length - 1] = null;
                position -= 1;
            }
        }
        return true;
    }

    public int size() {
        return position;
    }


    public E get(int index) {
        E value = data[index];
        return (E) value;
    }

    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size(); i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size(); i++) {
            if (data[i].equals(element)) {
                if (i <= position - 1) {
                    if (data.length - 1 - i >= 0) System.arraycopy
                            (data, i + 1, data, i, data.length - 1 - i);
                }
                data[data.length - 1] = null;
                position -= 1;
                return true;
            }
        }
        return false;
    }
    @Override
    public E removeIndex(int index) {
        E value = data[index];
        remove(value);
        return value;

    }
    @Override
    public boolean containsAll(OrderedList<E> element) {
        for (int i = 0; i < element.size(); i++) {
            if (!contains(element.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        position = 0;
        data = (E[]) new Comparable[size];
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[position];
        if (size() >= 0) System.arraycopy(data, 0, objects, 0, size());
        return objects;
    }

    @Override
    public int lastIndexOf(E  element) {
        for (int i = size(); i > 0; i--)
            if (element.equals(data[i])){
                return i;
            }
        return -1;
    }


    @Override
    @SuppressWarnings("unchecked")
    public E [] toArray(E [] a) {
        if (a.length < size)
            return (E[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;

    }
    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size(); i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }



    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(index);


    }

    private class IteratorImpl implements Iterator<E> {
        IteratorImpl(){}
        int cursor;

        public boolean hasNext() {
            return cursor != size();
        }

        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;
            if (i >= size())
                throw new NoSuchElementException();
            Object[] elementData = OrderedListImpl.this.data;
            cursor = i + 1;
            return (E) elementData[i];
        }
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator<E> {

        ListIteratorImpl(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() { return cursor != 0; }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        @Deprecated
        public void remove() {
            throw new RuntimeException("Not use");
        }

        @Override
        @Deprecated
        public void set(E e) {
            throw new RuntimeException("Not use");
        }

        @Override
        @Deprecated
        public void add(E e) {
            throw new RuntimeException("Not use");
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] data2 = OrderedListImpl.this.data;
            if (i >= data2.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) data2[i];
        }

    }

}
