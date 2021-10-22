import java.util.ArrayList;

public class SymbolTable {
    private int capacity;
    private ArrayList<ArrayList<String>> symbolTable;

    public SymbolTable(int capacity) {
        this.capacity = capacity;
        this.symbolTable = new ArrayList<>();
        for (int i = 0; i < this.capacity; i++) {
            this.symbolTable.add(new ArrayList<>());
        }
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash + key.charAt(i)) % capacity;
        }
        return hash % capacity;
    }

    public Position getPosition(String element) {
        int chainIndex = this.hash(element);

        if (this.symbolTable.get(chainIndex).isEmpty())
            return null;

        ArrayList<String> chain = this.symbolTable.get(chainIndex);
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).equals(element)) {
                return new Position(chainIndex, i);
            }
        }
        return null;
    }

    public String get(Position position) {
        if (this.symbolTable.size() <= position.getChainIndex()) {
            return null;
        }

        if (this.symbolTable.get(position.getChainIndex()).size() <= position.getChainPosition()) {
            return null;
        }

        return this.symbolTable.get(position.getChainIndex()).get(position.getChainPosition());
    }

    public boolean contains(String element) {
        return this.getPosition(element) != null;
    }

    public boolean add(String element) {
        if(contains(element)) {
            return false;
        }
        int key = this.hash(element);
        this.symbolTable.get(key).add(element);

        return true;
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "symbolTable=" + symbolTable +
                '}';
    }
}
