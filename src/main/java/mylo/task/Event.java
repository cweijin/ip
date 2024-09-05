package mylo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import mylo.utils.exceptions.IllegalValueException;
import mylo.utils.formatters.Formatter;
import mylo.utils.helpers.HelperFunctions;

/**
 * Represents an event task with a start and end date/time in the task list.
 * <p></p>
 * <p>The {@code Event} class stores the task title, start date/time, and end date/time.
 * It extends the {@code Task} class and adds functionality specific to tasks that have a duration.</p>
 * <p></p>
 * <p>The event is represented by two {@code LocalDateTime} objects: one for the start time and one for the
 *  end time.</p>
 *
 * @author cweijin
 */
public class Event extends Task {
    private final LocalDateTime START_DATETIME;
    private final LocalDateTime END_DATETIME;

    /**
     * Constructs an {@code Event} task with the specified title, start date/time, and end date/time.
     * <p></p>
     * <p>This constructor initializes the task with the title, start date/time, and end date/time.
     * The task is considered incomplete upon creation.</p>
     *
     * @param title        The title or description of the event task.
     * @param startDateTime The start date and time of the event in string format, which will be parsed
     *                      into {@code LocalDateTime}.
     * @param endDateTime  The end date and time of the event in string format, which will be parsed into
     *                     {@code LocalDateTime}.
     * @throws IllegalValueException If the provided date/time strings cannot be parsed into valid date and
     *                               time values.
     */
    public Event(String title, String startDateTime, String endDateTime) throws IllegalValueException {
        this(title, startDateTime, endDateTime, false);
    }

    /**
     * Constructs an {@code Event} task with the specified title, start date/time, end date/time, and
     * completion status.
     * <p></p>
     * <p>This constructor allows specifying whether the event task is done at the time of creation.</p>
     *
     * @param title         The title or description of the event task.
     * @param startDateTime The start date and time of the event in string format, which will be parsed
     *                      into {@code LocalDateTime}.
     * @param endDateTime   The end date and time of the event in string format, which will be parsed
     *                      into {@code LocalDateTime}.
     * @param isDone        Whether the task is marked as done upon creation.
     * @throws IllegalValueException If the provided date/time strings cannot be parsed into valid date
     *                               and time values.
     */
    public Event(String title, String startDateTime, String endDateTime, boolean isDone)
            throws IllegalValueException {
        super(title, isDone);
        this.START_DATETIME = HelperFunctions.stringToDateTime(startDateTime);
        this.END_DATETIME = HelperFunctions.stringToDateTime(endDateTime);
    }

    /**
     * Checks if the event is ongoing on the specified date.
     * <p></p>
     * <p>This method checks whether the event is happening on the given {@code LocalDateTime} by comparing
     * it with the start and end dates of the event.</p>
     *
     * @param dateTime The date to check if the event is ongoing.
     * @return {@code true} if the event is happening on the specified date, {@code false} otherwise.
     */
    public boolean isOngoing(LocalDateTime dateTime) {
        LocalDate startDate = START_DATETIME.toLocalDate();
        LocalDate endDate = END_DATETIME.toLocalDate();
        LocalDate date = dateTime.toLocalDate();
        return (startDate.isBefore(date) && endDate.isAfter(date)) || startDate.isEqual(date)
                || endDate.isEqual(date);
    }

    /**
     * Returns a formatted string representing the event task for storage.
     * <p></p>
     * <p>This string is used for saving the task to storage and includes the task type, status,
     * title, start date, and end date formatted for storage.</p>
     *
     * @return A string formatted for storage.
     */
    @Override
    public String storageFormat() {
        return String.format("EVENT | %s | %s | %s | %s", super.getStatus(), super.getTitle(),
                Formatter.dateTimeStorage(this.START_DATETIME), Formatter.dateTimeStorage(this.END_DATETIME));
    }

    /**
     * Returns a string representation of the event task, including its status and date range.
     * <p></p>
     * <p>The string includes the task type "[E]", the task's status (done or not),
     * the title, and the date range formatted for display.</p>
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + getStartDateString();
    }

    /**
     * Returns a formatted string of the event's start and end date/times.
     * <p></p>
     * <p>This private method formats the start and end dates for display purposes.</p>
     *
     * @return A string representing the formatted start and end dates.
     */
    private String getStartDateString() {
        return String.format(" (from: %s to: %s)", Formatter.dateTimeDisplay(START_DATETIME),
                Formatter.dateTimeDisplay(END_DATETIME));
    }

}
