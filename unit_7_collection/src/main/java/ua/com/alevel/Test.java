package ua.com.alevel;

import ua.com.alevel.impl.OrderedListImpl;

import java.util.ListIterator;

import static java.lang.System.out;


public class Test {
    public static void main(String[] args) {
        out.println("Representation library by basic methods (based on <Integer>):");
        OrderedListImpl<Integer> orderedListImpl = new OrderedListImpl<>();
        out.println("1)create list and add: 3 4 5 1");
        orderedListImpl.add(3);
        orderedListImpl.add(4);
        orderedListImpl.add(5);
        orderedListImpl.add(1);
        out.println("List = " + orderedListImpl + "\n");
        out.println("2)remove = 4; add = -9 and check if the list is empty");
        orderedListImpl.remove(4);
        orderedListImpl.add(-9);
        out.println("List = " + orderedListImpl + "  is empty = " + orderedListImpl.isEmpty());
        out.println();
        out.println("3)size = " + orderedListImpl.size() + " and find index  element 3 -> index = "
                + orderedListImpl.indexOf(3));
        out.println();
        out.println("4)let's go from element 3 to the  beginning  with help of the listIterator: ");
        ListIterator<Integer> listIterator = orderedListImpl.listIterator(3);
        while (listIterator.hasPrevious()) {
            out.println("Value is : " + listIterator.previous());
        }
        out.println();
        orderedListImpl.clear();
        out.println("5) clean and check is the list empty = " + orderedListImpl.isEmpty());
    }


// For check with other data types...

//    public static void main(String[] args) {
//        OrderedListImpl<String> stringOrderedList = new OrderedListImpl<>();
//        stringOrderedList.add("Hi");
//        out.println(stringOrderedList);
//    }


}

