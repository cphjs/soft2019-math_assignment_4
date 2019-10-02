package dk.cphbusiness;

/**
 * State
 */
public class State {

    private String name;
    private boolean isFinal;

    public State(String name, boolean isFinal) {
        this.name = name;
        this.isFinal = isFinal;
    }

    public State(String name) {
        this(name, false);
    }
    
    public boolean isFinal() {
        return isFinal;
    }

    public String getName() {
        return name;
    }

}