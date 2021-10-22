public class Test {
    public static void main(String[] args) {
//        SymbolTable symbolTable = new SymbolTable(6);
//        Position position;
//
//        System.out.println(symbolTable.add("4"));
//        System.out.println(symbolTable.add("hg"));
//        System.out.println(symbolTable.add("9hgs"));
//        System.out.println(symbolTable.add("jhgas"));
//        System.out.println(symbolTable.add("hhagffs"));
//
//        position = symbolTable.getPosition("4");
//        System.out.println(position.getChainIndex() + " " + position.getChainPosition());
//        position = symbolTable.getPosition("hg");
//        System.out.println(position.getChainIndex() + " " + position.getChainPosition());
//        position = symbolTable.getPosition("9hgs");
//        System.out.println(position.getChainIndex() + " " + position.getChainPosition());
//        position = symbolTable.getPosition("jhgas");
//        System.out.println(position.getChainIndex() + " " + position.getChainPosition());
//        position = symbolTable.getPosition("hhagffs");
//        System.out.println(position.getChainIndex() + " " + position.getChainPosition());
        var scanner = new LanguageScanner("p1.txt");
        scanner.scan();
    }
}
