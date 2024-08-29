package task;

public class Event extends Task{
    private final String startDate;
    private final String endDate;

    public Event(String title, String startDate, String endDate) {
        this(title, startDate, endDate, false);
    }

    public Event(String title, String startDate, String endDate, boolean isDone) {
        super(title, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String getStartDateString() {
        return String.format(" (from: %s to: %s)", startDate, endDate);
    }

    @Override
    public String storageFormat() {
        return String.format("EVENT | %s | %s | %s | %s", super.getStatus(), super.getTitle(), this.startDate, this.endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getStartDateString();
    }
}
