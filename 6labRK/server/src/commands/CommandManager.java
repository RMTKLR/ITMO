package commands;

import collectionManager.FlatCollectionManager;
import connection.Request;
import connection.Response;

import java.util.LinkedHashMap;

public class CommandManager {
    private LinkedHashMap<String, Command> commandMap;
    private FlatCollectionManager collectionManager;

    public CommandManager(FlatCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commandMap = new LinkedHashMap<>();
        commandMap.put("help", new HelpCommand());
        commandMap.put("add", new AddCommand(collectionManager));
        commandMap.put("info", new InfoCommand(collectionManager));
        commandMap.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandMap.put("update", new UpdateCommand(collectionManager));
        commandMap.put("clear", new ClearCommand(collectionManager));
        commandMap.put("remove_any_by_house", new RemoveAnyByHouseCommand(collectionManager));
        commandMap.put("show", new ShowCommand(collectionManager));
        commandMap.put("remove_last", new RemoveLastCommand(collectionManager));
        commandMap.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandMap.put("insert_at", new InsertAtCommand(collectionManager));
        commandMap.put("print_field_ascending_house", new PrintFieldDescendingHouseCommand(collectionManager));
        commandMap.put("average_number_of_rooms", new AverageNumberOfRoomsCommand(collectionManager));
    }

    private boolean isCommand(String command) {
        return commandMap.containsKey(command);
    }

    public Response runCommand(Request request) {
        Response response = new Response("");
            if (isCommand(request.getCommandName())) {
                if (commandMap.get(request.getCommandName()).getCommandType() == CommandType.ARGUMENT) {
                    if (!request.getCommandArgument().equals(""))
                        commandMap.get(request.getCommandName()).setStringArg(request.getCommandArgument());
                    else response = new Response("no argument has been passed");
                }if(commandMap.get(request.getCommandName()).getCommandType() == CommandType.ARGUMENT_WITH_FLAT){
                    commandMap.get(request.getCommandName()).setFlat(request.getCommandObject());
                }if(commandMap.get(request.getCommandName()).getCommandType() == CommandType.ARGUMENT_WITH_HOUSE){
                    commandMap.get(request.getCommandName()).setHouse(request.getHouse());
                }
                if(commandMap.get(request.getCommandName()).getCommandType() == CommandType.BOTH_ARGUMENT_AND_ARGUMENT_FLAT){
                    commandMap.get(request.getCommandName()).setFlat(request.getCommandObject());
                    commandMap.get(request.getCommandName()).setStringArg(request.getCommandArgument());
                }
                response = new Response(commandMap.get(request.getCommandName()).execute());
            }else response = new Response("no such command");
        return response;
    }
}
