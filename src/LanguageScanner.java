import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LanguageScanner {
    private static final ArrayList<String> operators = new ArrayList<>(
            List.of("+", "-", "*", "/", "%", "<", ">", "<=", "==", ">=" ));
    private static final ArrayList<String> separators = new ArrayList<>(
            List.of("(", ")", ".", "{", "}", "[", "]"));
    private static final ArrayList<String> reservedWords = new ArrayList<>(
            List.of("int", "bool", "string", "char", "const", "start", "stop", "if", "else", "giveInput", "getOutput", "for", "while"));

    private SymbolTable symbolTable;
    private String pathSourceCode;

    public LanguageScanner(String pathSourceCode) {
        this.pathSourceCode = pathSourceCode;
        this.symbolTable = new SymbolTable(50);
    }

    private String readSourceCode() throws FileNotFoundException {
        StringBuilder sourceCode = new StringBuilder();
        Scanner scanner = new Scanner(new File(this.pathSourceCode));
        while (scanner.hasNextLine()) {
            sourceCode.append(scanner.nextLine());
        }
        System.out.println(sourceCode);
        return sourceCode.toString();
    }

    private List<String> tokenize() {
        try {
            String sourceCode = this.readSourceCode();
            return new ArrayList<>(List.of(sourceCode.split("[()., \\[\\]\"\t\n]")))
                    .stream()
                    .filter(token -> token.length() > 0)
                    .collect(Collectors.toList());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void scan() {
        List<String> tokens = this.tokenize();
        if (tokens == null) {
            return;
        }
        for (var token: tokens) {
            System.out.print(token + ";");
        }
    }
}
