public class PIFEntry {
    private String token;
    private Position position;
    private int code;

    public PIFEntry(String token, Position position, int code) {
        this.token = token;
        this.position = position;
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
