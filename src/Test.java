import java.io.PrintStream;

public class Test {
    public static void main(String[] args) {
//        var scanner = new LanguageScanner("p3.txt");
//        try {
//            scanner.scan();
//            System.out.println(scanner.getSymbolTable());
//            System.out.println(scanner.getPIF());
//            PrintStream printStream = new PrintStream("ST.out");
//            printStream.println(scanner.getSymbolTable());
//
//            printStream = new PrintStream("PIF.out");
//            printStream.println(scanner.getPIF());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        var fa = new FA("FA.in");
        System.out.println(fa.getStates());
        System.out.println(fa.getInitialState());
        System.out.println(fa.getFinalStates());
        System.out.println(fa.getTransitions());
    }
}
