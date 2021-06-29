package ua.com.alevel;

import java.util.*;

public class RaceHorses {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int checkPointDistance = 100 + random.nextInt(200 - 100);
        int sleepTime = 400 + random.nextInt(500 - 400);
        System.out.println("10 horses take part in the race");
        int horseNumber = 10;
        List<Horse> horses = Collections.synchronizedList(new ArrayList<>());
        List<Horse> finishHorses = Collections.synchronizedList(new ArrayList<>());
        System.out.println("What the horse you choose?");
        int horseBet = scanner.nextInt();

        if (horseBet <= 0 || horseBet >= 10) {
            System.out.println("The race started without you");
            return;
        }

        for (int i = 0; i < horseNumber; i++) {
            horses.add(new Horse(checkPointDistance, sleepTime, finishHorses, i + 1));
            System.out.println(horses.get(i) + " ready to start.");
        }

        horses.forEach(Thread::start);
        System.err.println("The race has begun!!!");
        Thread.sleep(3000);
        Optional<Horse> findHorse = finishHorses.stream()
                .filter(horse -> horse.getHorseNumber() == horseBet)
                .findFirst();

//        System.out.println(new ArrayList<>(finishHorses));

        if (findHorse.isPresent()) {
            int numberFinishHorse = finishHorses.indexOf(findHorse.get());
            System.out.println("Your horse has come to position = " + (numberFinishHorse + 1));
        }
    }

}
