public class Position {
    private int chainIndex;
    private int chainPosition;

    public Position(int chainIndex, int chainPosition) {
        this.chainIndex = chainIndex;
        this.chainPosition = chainPosition;
    }

    public Integer getChainIndex() {
        return this.chainIndex;
    }

    public Integer getChainPosition() {
        return this.chainPosition;
    }
}
