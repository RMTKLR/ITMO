package commands;

import Manager.CommandManager;

/**
 * A command to print all the commands with their description
 */
public class HelpCommand implements Command{
    /**
     * An object of class {@code CommandManger} to access all commands
     */
    private final CommandManager commandManager;

    /**
     * Constructor {@code ExecuteScriptCommand} with the specified object of {@code CommandManger}
     * @param commandManager Specified object of {@code CommandManager}
     */
    public HelpCommand(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    /**
     * @param argument It is a string used to check the correctness of the inserted command(For this command the argument must be empty)
     * @return The command has been successfully executed or not
     */
    @Override
    public void execute(String argument) {
        if (!argument.equals("")) System.out.println("This command can't have argument!!!");
        System.out.printf("%-25s:%s%n", "help", getDescription());
        commandManager.getCommandMap().forEach((s, command) -> {
            if (!s.equals("help")) System.out.printf("%-25s:%s%n", s, command.getDescription());
        });
    }

    /**
     * A method for providing descriptions about the command
     *
     * @return Returns a descriptions the {@code HelpCommand}
     */
    @Override
    public String getDescription() {
        return "To display help on available commands";
    }
}
