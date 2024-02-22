package starter;

import commands.Command;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    private Map<String, Command> commandMap;

    public Runner(Map<String, Command> commandMap) {
        this.commandMap = commandMap;

    }
    public void runner() {
        try {
            while (true) {
                System.out.println("Enter help to know the commands:) ");
                String input = new Scanner(System.in).nextLine();
                String[] split = input.trim().split(" ", 2);
                if (commandMap.containsKey(split[0])) {
                    try {
                        commandMap.get(split[0]).execute(split[1]);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        commandMap.get(split[0]).execute("");
                    }
                } else System.out.println("No such command!!!");
            }
        }catch (NoSuchElementException e){
            System.err.println("CTRL + D terminate the program!!!");
        }
    }
}
