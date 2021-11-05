import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FA {
    private String filePath;
    private Set<String> states;
    private Set<String> alphabet;
    private String initialState;
    private Set<String> finalStates;
    private ArrayList<AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<String, String>, String>> transitions;


    public FA(String filePath){
        this.filePath = filePath;
        readFile();
    }

    public void readFile() {
        try{
            Scanner scanner = new Scanner(new File(filePath));
            this.states = new HashSet<>(List.of(scanner.nextLine().split(" ")));
            this.alphabet = new HashSet<>(List.of(scanner.nextLine().split(" ")));
            this.initialState = scanner.nextLine();
            this.finalStates = new HashSet<>(List.of(scanner.nextLine().split(" ")));
            this.transitions = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String transitionLine = scanner.nextLine();
                var splitSymbols =  Collections.list(new StringTokenizer(transitionLine, "->(),", false)).stream()
                        .map(token -> (String) token)
                        .collect(Collectors.toList());
                String state1 = splitSymbols.get(0);
                String symbol = splitSymbols.get(1);
                String state2 = splitSymbols.get(2);
                var state1Symbol = new AbstractMap.SimpleEntry<>(state1, symbol);
                var transition = new AbstractMap.SimpleEntry<>(state1Symbol, state2);
                this.transitions.add(transition);
            }
        } catch (FileNotFoundException err){
            err.printStackTrace();
        }

    }
    private boolean checkDeterministic() {
        if (this.initialState.length() != 1) {
            return false;
        }

        return true;
    }


    public Set<String> getStates() {
        return states;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public Set<String> getFinalStates() {
        return finalStates;
    }

    public ArrayList<AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<String, String>, String>> getTransitions() {
        return transitions;
    }
}
