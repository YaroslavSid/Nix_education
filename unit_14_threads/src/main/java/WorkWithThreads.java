import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkWithThreads {
    static String path = "output.txt";
    static final BlockingQueue<String> value = new ArrayBlockingQueue<>(16);

    public static void main(String[] args) {
        new Producer().start();
        new Consumer(path).start();
    }


    static class Producer extends Thread {
        @Override
        public void run() {
            System.out.println("Enter something(exit = quit):");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                value.add(scanner.nextLine());
                if (value.contains("quit")) {
                    System.out.println("Exit from system");
                    break;
                }
            }
        }
    }


    static class Consumer extends Thread {
        String path;
        String flag = "";
        String input;

        Consumer(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    input = value.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (input.equals("quit")) {
                    break;
                } else if (!flag.equals(input)) {
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
                        bufferedWriter.write(input);
                        flag = input;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("repeat, value don't write to file");
                }
            }
        }
    }
}


