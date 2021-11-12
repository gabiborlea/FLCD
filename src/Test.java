import java.io.PrintStream;
import java.util.Scanner;

public class Test {
    public static void runScanner(){
        var scanner = new LanguageScanner("perr.txt");
        try {
            scanner.scan();
            System.out.println(scanner.getSymbolTable());
            System.out.println(scanner.getPIF());
            PrintStream printStream = new PrintStream("ST.out");
            printStream.println(scanner.getSymbolTable());

            printStream = new PrintStream("PIF.out");
            printStream.println(scanner.getPIF());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        var fa = new FA("FA_int_constant.in");
        System.out.println("States: " + fa.getStates());
        System.out.println("Alphabet: " + fa.getAlphabet());
        System.out.println("Initial states: " + fa.getInitialState());
        System.out.println("Final States: " + fa.getFinalStates());
        System.out.println("Transitions: " + fa.getTransitions());
        System.out.println("Is deterministic: " + fa.getIsDeterministic());
        System.out.println("Is accepted: " + fa.checkAccepted("078"));

        runScanner();

        boolean ok = true;
        Scanner scanner = new Scanner(System.in);
        while (ok) {
            System.out.println("0. Exit");
            System.out.println("1. Get states");
            System.out.println("2. Get alphabet");
            System.out.println("3. Get final states");
            System.out.println("4. Get transitions");
            var line = scanner.nextLine();

            switch (line) {
                case "0":
                    ok = false;
                    break;
                case "1":
                    System.out.println("States: " + fa.getStates());
                    break;
                case "2":
                    System.out.println("Alphabet: " + fa.getAlphabet());
                    break;
                case "3":
                    System.out.println("Final States: " + fa.getFinalStates());
                    break;
                case "4":
                    System.out.println("Transitions: " + fa.getTransitions());
                    break;
            }



        }
    }
}
