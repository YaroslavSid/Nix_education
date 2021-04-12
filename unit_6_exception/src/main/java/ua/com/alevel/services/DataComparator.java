package ua.com.alevel.services;

import java.util.Comparator;

public class DataComparator implements Comparator<WorkWithDate> {
    @Override
    public int compare(WorkWithDate o1, WorkWithDate o2) {
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
    public Comparator<WorkWithDate> reversed() {
        return (o1, o2) -> -this.compare(o1, o2);
    }
}
