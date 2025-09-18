package cpp;

// Mark.java
public enum Mark {
    X, O, EMPTY;


    public String toSymbol() {
        return switch (this) {
            case X -> "X";
            case O -> "O";
            case EMPTY -> " ";
        };
    }
}