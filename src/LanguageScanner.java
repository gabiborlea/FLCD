import com.sun.jdi.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LanguageScanner {
    private ArrayList<String> operators;
    private ArrayList<String> separators;
    private ArrayList<String> reservedWords;

    private SymbolTable symbolTable;
    private ProgramInternalForm PIF;
    private String pathSourceCode;
    private ArrayList<String> errors;
    private FA faIdentifier;
    private FA faIntConstant;

    public LanguageScanner(String pathSourceCode) {
        this.pathSourceCode = pathSourceCode;
        this.symbolTable = new SymbolTable(10);
        this.PIF = new ProgramInternalForm();
        this.errors = new ArrayList<>();
        this.faIdentifier = new FA("FA_identifier.in");
        this.faIntConstant = new FA("FA_int_constant.in");
        readTokens();
    }

    private void readTokens(){
        try {
            Scanner scanner = new Scanner(new File("token.in"));
            operators = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            separators = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            separators.add("\n");
            separators.add(",");
            reservedWords = new ArrayList<>(List.of(scanner.nextLine().split(",")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String readSourceCode() {
        StringBuilder sourceCode = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(this.pathSourceCode));
            while (scanner.hasNextLine()) {
                sourceCode.append(scanner.nextLine()).append("\n");
            }
            return sourceCode.toString().replace("\t", "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<String> tokenize() {
        String sourceCode = this.readSourceCode();
        String separators = this.separators.stream().reduce("", (s1, s2) -> s1 + s2);

        return Collections.list(new StringTokenizer(sourceCode, separators, true)).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());


    }
    private boolean isIdentifier(String symbol) {
//        return Pattern.compile("[A-Za-z][a-zA-Z0-9]*").matcher(symbol).matches();
        return this.faIdentifier.checkAccepted(symbol);
    }

    private boolean isConstant(String symbol, boolean isString) {
//        if(symbol.matches("-?\\d+") && !isString)
        if (this.faIntConstant.checkAccepted(symbol) && !isString)
            return true;
        if(symbol.matches("\"[a-zA-Z0-9 ]*\"") && isString)
            return true;
        if(symbol.matches("'[A-Za-z0-9 ]'") && !isString)
            return true;

        return false;
    }

    void addToPIF(String token, int line, boolean isString){
        if (this.operators.contains(token)){
            this.PIF.add(token, new Position(-1, -1), 0);
        }
        else if (this.separators.contains(token)){
            this.PIF.add(token, new Position(-1, -1), 1);
        }
        else if (this.reservedWords.contains(token)){
            this.PIF.add(token, new Position(-1, -1), 2);
        }
        else if (this.isIdentifier(token)){
            this.symbolTable.add(token);
            this.PIF.add(token, this.symbolTable.getPosition(token), 3);
        }
        else if (this.isConstant(token, isString)) {
            this.symbolTable.add(token);
            this.PIF.add(token, this.symbolTable.getPosition(token), 4);
        } else {
            errors.add("Lexical error at line " + line + " token " + token);
        }
    }

    public void scan() {
        var tokens = this.tokenize();
        boolean inside = false;
        StringBuilder newString = new StringBuilder("\"");
        int line = 1;

        for (var token: tokens) {
            if (token.equals("\"")) {
                if (inside) {
                    newString.append("\"");
                    addToPIF(newString.toString(), line, true);
                    newString = new StringBuilder();
                }
                inside = !inside;
            }
            else if (token.equals("\n")) {
                if (inside) {
                    errors.add("Lexical error at line " + line + " token " + token);
                    inside = false;
                }
                line++;
            }
            else {
                if (inside) {
                    newString.append(token);
                }
                else if (!token.equals(" ")){
                    addToPIF(token, line, false);
                }
            }
        }

        if(errors.size() > 0) {
            errors.forEach(System.out::println);
        }
        else {
            System.out.println("No lexical errors");
        }

    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public ProgramInternalForm getPIF() {
        return PIF;
    }
}
