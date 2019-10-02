package dk.cphbusiness;

/**
 * Automaton
 */
public class Automaton {

    private State currentState;
    private StateTransition[] transitions;
    private LogEntry lastLogEntry;

    public Automaton(State initialState, StateTransition ...transitions) {
        this.currentState = initialState;
        this.transitions = transitions;
    }
    
    public State getCurrentState() {
        return currentState;
    }

    public void nextState(LogEntry logEntry) {
        if (currentState.isFinal()) throw new RuntimeException("Automaton already in final state");

        StateTransition[] transitions = getTransitionsForAction(logEntry.getAction());
        if (transitions.length == 0) throw new RuntimeException("Invalid action \"" + logEntry.getAction() + "\" for this automaton");

        StateTransition transition = getTransitionForStartState(transitions, getCurrentState());
        if (transition == null) {
            throw new RuntimeException(String.format("Automaton not in correct state(%s)", currentState.getName()));
        }

        // If it was had to transition in X amount of time, but was too late.
        if (lastLogEntry != null && transition.getMaxDuration() > logEntry.getTimestamp() - lastLogEntry.getTimestamp()) {
            throw new RuntimeException("Transition is out of time");
        }

        System.out.println(String.format("Transitioned %s->%s", getCurrentState().getName(), transition.getEndState().getName()));
        currentState = transition.getEndState();
    }

    private StateTransition[] getTransitionsForAction(Action action) {
        StateTransition[] applicableTransitions = new StateTransition[transitions.length];
        int count = 0;
        for (StateTransition transition : transitions) {
            if (transition.getAction() == action) {
                applicableTransitions[count++] = transition;
            }
        }
        StateTransition[] trimmed = new StateTransition[count];
        System.arraycopy(applicableTransitions, 0, trimmed, 0, count);
        return trimmed;
    }

    private StateTransition getTransitionForStartState(StateTransition[] transitions, State state) {
        for (StateTransition transition : transitions) {
            if (transition.getStartState() == state) {
                return transition;
            }
        }
        return null;
    }
}