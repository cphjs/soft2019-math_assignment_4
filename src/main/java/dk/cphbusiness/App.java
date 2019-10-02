package dk.cphbusiness;

/**
 * Hello world!
 *
 */
public class App {

    private static final State START = new State("Initial state");
    private static final State MAIN_MENU = new State("Main menu");
    private static final State SUBMENU1 = new State("Submenu 1");
    private static final State SUBMENU2 = new State("Submenu 2");
    private static final State END = new State("END", true);

    private static final StateTransition[] VALID_TRANSITIONS = new StateTransition[] {
            StateTransition.builder().action(Action.LOGIN).from(START).to(MAIN_MENU).build(),
            StateTransition.builder().action(Action.REFRESH).from(MAIN_MENU).to(MAIN_MENU).build(),
            StateTransition.builder().action(Action.ACTION1).from(MAIN_MENU).to(SUBMENU1).build(),
            StateTransition.builder().action(Action.LOGOUT).from(MAIN_MENU).to(END).build(),
            StateTransition.builder().action(Action.LOGOUT).from(SUBMENU1).to(END).build(),
            StateTransition.builder().action(Action.ACTION2).from(SUBMENU1).to(SUBMENU2).build(),
            StateTransition.builder().action(Action.BACK).from(SUBMENU1).to(MAIN_MENU).build(),
            StateTransition.builder().action(Action.BACK).from(SUBMENU2).to(SUBMENU1).build(),
            StateTransition.builder().action(Action.TOSTART).from(SUBMENU2).to(MAIN_MENU).build(),
            StateTransition.builder().action(Action.LOGOUT).from(SUBMENU2).to(END).build(), };


    public static void main(String[] args) {
        LogEntry[] validLogs = new LogEntry[] {
            new LogEntry(Action.LOGIN),
            new LogEntry(Action.ACTION1),
            new LogEntry(Action.ACTION2),
            new LogEntry(Action.TOSTART),
            new LogEntry(Action.LOGOUT)
        };

        LogEntry[] invalidLogs = new LogEntry[] {
            new LogEntry(Action.LOGOUT)
        };

        runAutomaton(validLogs);
        runAutomaton(invalidLogs);       
    }

    private static void runAutomaton(LogEntry[] logs) {
        Automaton automaton = new Automaton(START, VALID_TRANSITIONS);
        for (LogEntry entry: logs) {
            automaton.nextState(entry);
        }
        System.out.println("Final state? " + automaton.getCurrentState().isFinal());
    }
}

