import java.util.AbstractMap;
import java.util.ArrayList;

public class ProgramInternalForm {
    ArrayList<PIFEntry> programInternalForm = new ArrayList<>();

    public ProgramInternalForm() {}

    public void add(String symbol, Position position, int code){
        PIFEntry entry = new PIFEntry(symbol, position, code);
        programInternalForm.add(entry);
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < this.programInternalForm.size(); i++) {
            data.append(i).append(" = ").append("code:")
                    .append(programInternalForm.get(i).getCode())
                    .append(" ").append(programInternalForm.get(i).getToken());
            if (programInternalForm.get(i).getPosition().getChainIndex() != -1){
                data.append(" ").append(programInternalForm.get(i).getPosition());
            }
            data.append("\n");

        }
        return data.toString();
    }
}
