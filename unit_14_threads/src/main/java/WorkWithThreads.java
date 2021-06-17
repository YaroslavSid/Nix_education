import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WorkWithThreads {
    static String path = "output.txt";
    static final List<String> value = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new InputFromConsole().start();
        new OutputToFile(path).start();
    }



    static class InputFromConsole extends Thread {
        @Override
        public void run() {
            System.out.println("Enter something(exit = quit):");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                synchronized (value) {
                    value.add(scanner.nextLine());
                    value.notify();
                    if (value.get(0).equals("quit")) {
                        System.out.println("Exit from system");
                        break;
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



    static class OutputToFile extends Thread {
        String path;
        String flag = "";

        OutputToFile(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (value) {
                    try {
                        value.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (value.get(0).equals("quit")) {
                        break;
                    } else if (!flag.equals(value.get(0))) {
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
                            bufferedWriter.write(value.get(0));
                            flag = value.get(0);
                            value.remove(0);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("repeat, value don't write to file");
                        value.remove(0);
                    }
                }
            }
        }
    }

}


