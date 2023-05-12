package input;

import CollectionManager.FlatCollectionManager;
import CommandManager.CommandManager;
import FileManager.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Input {
    private Scanner input;
    private CommandManager commandManager;
    private FileManager fileManager;

    public Input(FileManager fileManager,FlatCollectionManager collectionManager) {
        commandManager = new CommandManager(fileManager,collectionManager);
        this.fileManager = fileManager;
        input = new Scanner(System.in);
    }

    public void getInput() {
        for (; ; ) {
            try {
                System.out.println("Enter your command (help) = ");
                String commandAndArg = input.nextLine();
                try {
                    String[] separate = commandAndArg.split(" ");
                    switch (separate[0]) {
                        case "info":
                            commandManager.info();
                            break;
                        case "help":
                            commandManager.help();
                            break;
                        case "add":
                            commandManager.add();
                            break;
                        case "exit":
                            commandManager.exit();
                            break;
                        case "save":
                            commandManager.save();
                            break;
                        case "show":
                            commandManager.show();
                            break;
                        case "clear":
                            commandManager.clear();
                            break;
                        case "remove_by_id":
                            commandManager.removeById(separate[1]);
                            break;
                        case "execute_script":
                            commandManager.execute_script(separate[1]);
                            break;
                        case "insert_at":
                            commandManager.insert_at(separate[1]);
                            break;
                        case "update":
                            commandManager.updateById(separate[1]);
                            break;
                        case "remove_last":
                            commandManager.remove_last();
                            break;
                        case "add_if_max":
                            commandManager.add_if_max();
                            break;
                        case "remove_any_by_house":
                            commandManager.remove_any_by_house();
                            break;
                        case "average_of_number_of_rooms":
                            commandManager.average_of_number_of_rooms();
                            break;
                        case "print_field_descending_house":
                            commandManager.print_field_descending_house();
                            break;
                        default:
                            System.out.println("No such command!!!");
                            break;
                    }
                } catch (IndexOutOfBoundsException exception) {
                    System.err.println("Invalid argument!!");
                }

            } catch (IndexOutOfBoundsException e) {
                System.err.println("No argument has been passed!!");
            }
        }
    }
    public void scirpt(){
        String command;
        String[] finalCommand;
        try {
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileManager.getPath())));
            command = inputStreamReader.readLine();
            while (command != null) {
                finalCommand = command.trim().split(" ", 2);
                switch (finalCommand[0]) {
                    case "info":
                        commandManager.info();
                        break;
                    case "help":
                        commandManager.help();
                        break;
                    case "add":
                        commandManager.add();
                        break;
                    case "exit":
                        commandManager.exit();
                        break;
                    case "save":
                        commandManager.save();
                        break;
                    case "show":
                        commandManager.show();
                        break;
                    case "clear":
                        commandManager.clear();
                        break;
                    case "remove_by_id":
                        commandManager.removeById(finalCommand[1]);
                        break;
                    case "execute_script":
                        commandManager.execute_script(finalCommand[1]);
                        break;
                    case "insert_at":
                        commandManager.insert_at(finalCommand[1]);
                        break;
                    case "remove_last":
                        commandManager.remove_last();
                        break;
                    case "remove_any_by_house":
                        commandManager.remove_any_by_house();
                        break;
                    case "add_if_max":
                        commandManager.add_if_max();
                        break;
                    case "update":
                        commandManager.updateById(finalCommand[1]);
                        break;
                    case "average_of_number_of_rooms":
                        commandManager.average_of_number_of_rooms();
                        break;
                    case "print_field_ascending_house":
                        commandManager.print_field_descending_house();
                        break;
                    default:
                        System.out.println("No such command!!!");
                }
                command = inputStreamReader.readLine();
                System.out.println("script " + fileManager.getPath() + " has been finished");


            }
        }catch (Exception exception){
            System.out.println("Invalid input");
        }
    }
}
