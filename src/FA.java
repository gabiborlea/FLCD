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
    private Map<String, Set<AbstractMap.SimpleEntry<String, String>>> transitions;
    private final boolean isDeterministic;


    public FA(String filePath){
        this.filePath = filePath;
        readFile();
        isDeterministic = checkDeterministic();
    }

    private void readFile() {
        try{
            Scanner scanner = new Scanner(new File(filePath));
            this.states = new HashSet<>(List.of(scanner.nextLine().split(" ")));
            this.alphabet = new HashSet<>(List.of(scanner.nextLine().split(" ")));
            this.initialState = scanner.nextLine();
            this.finalStates = new HashSet<>(List.of(scanner.nextLine().split(" ")));
            this.transitions = new HashMap<>();
            while (scanner.hasNextLine()) {
                String transitionLine = scanner.nextLine();
                var splitSymbols =  Collections.list(new StringTokenizer(transitionLine, ">(),", false)).stream()
                        .map(token -> (String) token)
                        .collect(Collectors.toList());
                String state1 = splitSymbols.get(0);
                String symbol = splitSymbols.get(1);
                String state2 = splitSymbols.get(2);

                transitions.putIfAbsent(state1, new HashSet<>());
                var symbolState2 = new AbstractMap.SimpleEntry<>(symbol, state2);
                transitions.get(state1).add(symbolState2);

            }
        } catch (FileNotFoundException err){
            err.printStackTrace();
        }

    }
    private boolean checkDeterministic() {
        if (this.initialState.length() != 1) {
            return false;
        }

        for (var state : this.transitions.keySet()) {
            var symbols = this.transitions.get(state)
                    .stream()
                    .map(AbstractMap.SimpleEntry::getKey)
                    .distinct()
                    .count();
            if (symbols != this.transitions.get(state).size()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAccepted(String sequence) {
        if (!isDeterministic){
            return false;
        }
        if (sequence.equals("") && this.finalStates.contains(initialState)){
            return true;
        }

        var state = this.initialState;
        for (int i = 0; i < sequence.length(); i++) {
            String symbol = String.valueOf(sequence.charAt(i));
            if (!this.transitions.containsKey(state)) {
                return false;
            }

            boolean found = false;
            for (var symbolState: this.transitions.get(state)) {
                if (symbolState.getKey().equals(symbol)) {
                    state = symbolState.getValue();
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }

        }

        return this.finalStates.contains(state);
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

    public Map<String, Set<AbstractMap.SimpleEntry<String, String>>> getTransitions() {
        return transitions;
    }

    public boolean getIsDeterministic() {
        return isDeterministic;
    }
}
