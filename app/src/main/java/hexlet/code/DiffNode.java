package hexlet.code;
public class DiffNode {
    private String key;
    private Object oldValue;
    private Object newValue;
    private Status status;

    public DiffNode(String key, Object oldValue, Object newValue, Status status) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Status getStatus() {
        return status;
    }
}
