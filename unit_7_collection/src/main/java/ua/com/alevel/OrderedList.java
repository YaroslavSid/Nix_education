package ua.com.alevel;

import java.util.Iterator;
import java.util.ListIterator;

public interface OrderedList <E>  {
    boolean add(E element);
    boolean addAll(OrderedList<E> element);
    void add(int index, E element);
    boolean addAll(int index, OrderedList<E> element);
    int size();
    E get(int index);
    boolean isEmpty();
    boolean contains(E element);
    boolean remove(E element);
    boolean containsAll(OrderedList<E> c);
    void clear();
    int indexOf(E element);
    Object[] toArray();
    boolean removeAll(OrderedList<E> element);
    Iterator<E> iterator();
    Object[] toArray(E [] a);
    ListIterator<E> listIterator();
    ListIterator<E> listIterator(int index);
    Object removeIndex(int index);
    int lastIndexOf(E element);
    boolean retainAll(OrderedList<E> element);

}
