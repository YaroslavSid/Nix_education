package ua.com.alevel;

import java.util.List;


public class Horse extends Thread {
    public int getHorseNumber() {
        return horseNumber;
    }

    private final int horseNumber;
    int checkPointDistance;
    int sleepTime;
    List<Horse> horseList;

    public Horse(int checkPointDistance, int sleepTime, List<Horse> horsesFinish, int horseNumber) {
        this.checkPointDistance = checkPointDistance;
        this.sleepTime = sleepTime;
        this.horseNumber = horseNumber;
        this.horseList = horsesFinish;
    }

    @Override
    public void run() {
        for (int i = 1; i < 1000; i++) {
            if (i == checkPointDistance) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        horseList.add(this);
    }

    @Override
    public String toString() {
        return "Horse " + horseNumber;
    }

}
