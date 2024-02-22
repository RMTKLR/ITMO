package Manager;

import commands.*;
import java.util.*;

public class CommandManager  {
    private Map<String, Command> commandMap;
    private FileManager fileManager;

    public CommandManager(FileManager fileManager, CollectionManager collectionManager){
        commandMap =new HashMap<>();
        commandMap.put("add", new AddCommand(collectionManager));
        commandMap.put("clear", new ClearCommand(collectionManager));
        commandMap.put("help", new HelpCommand(this));
        commandMap.put("show", new ShowCommand(collectionManager));
        commandMap.put("addIfMax", new AddIfMaxCommand(collectionManager));
        commandMap.put("updateByAnId", new UpdateByAnIdCommand(collectionManager));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("save", new SaveCommand(fileManager,collectionManager));
        commandMap.put("executeScript",new ExecuteScriptCommand(this));
        commandMap.put("info", new InfoCommand(collectionManager));
        commandMap.put("removeById", new RemoveByIdCommand(collectionManager));
        commandMap.put("removeLast", new RemoveLastCommand(collectionManager));
        commandMap.put("removeAnyByHouse", new RemoveAnyByHouseCommand(collectionManager));
        commandMap.put("printFieldDescendingHouse", new PrintFieldDescendingHouseCommand(collectionManager));
        commandMap.put("insertAt", new InsertAtCommand(collectionManager)); //it has problem!
        commandMap.put("averageNumberOfRooms", new AverageOfNumberOfRoomsCommand(collectionManager));
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

}
