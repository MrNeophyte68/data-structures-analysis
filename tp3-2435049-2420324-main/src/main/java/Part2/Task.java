package Part2;

public class Task implements Comparable<Task> {
    public int priorityId;
    public int executionCost;
    public String operator;
    public String name;
    public int decryptionKey;
    public boolean isDecrypted = false;

    public Task(int priorityId,String name, int executionCost, String operator, int decryptionKey) {
        this.priorityId = priorityId;
        this.name = name;
        this.executionCost = executionCost;
        this.operator = operator;
        this.decryptionKey = decryptionKey;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priorityId, other.priorityId);
    }
}
