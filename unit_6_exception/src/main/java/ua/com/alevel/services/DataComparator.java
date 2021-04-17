package ua.com.alevel.services;

import java.util.Comparator;

public class DataComparator implements Comparator<AddAndSubtractDate> {
    @Override
    public int compare(AddAndSubtractDate o1, AddAndSubtractDate o2) {
        int res = (int) (o1.years - o2.years);
        if (res == 0) {
            res = (int) (o1.months - o2.months);
        }
        if (res == 0) {
            res = (int) (o1.days - o2.days);
        }
        if (res == 0) {
            res = (int) (o1.minute - o2.minute);
        }
        return res;

    }

    @Override
    public Comparator<AddAndSubtractDate> reversed() {
        return (o1, o2) -> -this.compare(o1, o2);
    }
}
