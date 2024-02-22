package commands;

public class ExitCommand implements Command{
    @Override
    public void execute(String argument) {
        System.out.println("SEE YOU!!!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "To exit from the program";
    }
}
