package mylo.command;

import mylo.task.TaskList;
import mylo.ui.Ui;

/**
 * Represents a command to find tasks in the task list based on a keyword.
 * <p></p>
 * <p>The {@code FindCommand} class searches for tasks that contain the specified
 * keyword in their titles and displays the matching tasks through the user interface.</p>
 *
 * @author cweijin
 */
public class FindCommand extends Command {
    private final String KEYWORD;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task titles.
     */
    public FindCommand(String keyword) {
        KEYWORD = keyword;
    }

    /**
     * Executes the find command, displaying tasks that match the specified keyword.
     *
     * @param list The task list to search through.
     * @param ui The user interface to display the results.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        String message = list.tasksWithKeyword(KEYWORD).toString();
        ui.show(message);
    }
}
