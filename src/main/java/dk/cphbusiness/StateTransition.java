package dk.cphbusiness;

/**
 * StateTransition
 */
public class StateTransition {

    private Action action;
    private State startState;
    private State endState;
    private long maxDuration;

    public StateTransition(Action action, State startState, State endState, long maxDuration) {
        this.action = action;
        this.startState = startState;
        this.endState = endState;
        this.maxDuration = maxDuration;
    }

    public StateTransition(Action action, State startState, State endState) {
        this(action, startState, endState, Long.MAX_VALUE);
    }

    public Action getAction() {
        return action;
    }

    public long getMaxDuration() {
        return maxDuration;
    }

    public State getStartState() {
        return startState;
    }

    public State getEndState() {
        return endState;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private State from;
        private State to;
        private long duration;
        private Action action;

        private Builder() {}

        public Builder from(State s) {
            from = s;
            return this;
        }

        public Builder to(State s) {
            to = s;
            return this;
        }

        public Builder maxDuration(long l) {
            duration = l;
            return this;
        }

        public Builder action(Action a) {
            action = a;
            return this;
        }

        public StateTransition build() {
            return new StateTransition(action, from, to, duration);
        }
    }
}