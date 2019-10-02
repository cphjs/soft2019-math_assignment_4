package dk.cphbusiness;

/**
 * LogEntry
 */
public class LogEntry {

    private String level;
    private long systemId;
    private long instanceId;
    private Action action;
    private long timestamp;

    public LogEntry(Action action) {
        this.action = action;
    }

    public String getLevel() {
        return level;
    }

    public long getSystemId() {
        return systemId;
    }

    public long getInstanceId() {
        return instanceId;
    }

   public Action getAction() {
        return action;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
}