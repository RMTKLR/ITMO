package commands;

import collectionManager.FlatCollectionManager;
import connection.Request;
import connection.Response;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class CommandManager {
    private LinkedHashMap<String, Command> commandMap;
    private FlatCollectionManager collectionManager;

    public CommandManager(FlatCollectionManager collectionManager, Connection con) {
        this.collectionManager = collectionManager;
        commandMap = new LinkedHashMap<>();
        commandMap.put("help", new HelpCommand(con));
        commandMap.put("add", new AddCommand(collectionManager, con));
        commandMap.put("info", new InfoCommand(collectionManager, con));
        commandMap.put("remove_by_id", new RemoveByIdCommand(collectionManager, con));
        commandMap.put("update", new UpdateCommand(collectionManager, con));
        commandMap.put("clear", new ClearCommand(collectionManager, con));
        commandMap.put("remove_any_by_house", new RemoveAnyByHouseCommand(collectionManager, con));
        commandMap.put("show", new ShowCommand(collectionManager, con));
        commandMap.put("remove_last", new RemoveLastCommand(collectionManager, con));
        commandMap.put("add_if_max", new AddIfMaxCommand(collectionManager, con));
        commandMap.put("insert_at", new InsertAtCommand(collectionManager, con));
        commandMap.put("print_field_ascending_house", new PrintFieldDescendingHouseCommand(collectionManager, con));
        commandMap.put("average_number_of_rooms", new AverageNumberOfRoomsCommand(collectionManager, con));
        commandMap.put("addUser", new AddUserCmd(collectionManager, con));
        commandMap.put("login", new Logincmd(collectionManager, con));
    }

    private boolean isCommand(String command) {
        return commandMap.containsKey(command);
    }

    public Response runCommand(Request request, Connection conn) throws SQLException {
        Response response = new Response("", request.getUser(), request.getPswd());
            if (isCommand(request.getCommandName())) {
                commandMap.get(request.getCommandName()).setUser(request.getUser());
                commandMap.get(request.getCommandName()).setPswd(request.getPswd());

                if (commandMap.get(request.getCommandName()).getCommandType() == CommandType.ARGUMENT) {
                    if (!request.getCommandArgument().equals(""))
                        commandMap.get(request.getCommandName()).setStringArg(request.getCommandArgument());
                    else response = new Response("no argument has been passed", request.getUser(), request.getPswd());
                }if(commandMap.get(request.getCommandName()).getCommandType() == CommandType.ARGUMENT_WITH_FLAT){
                    commandMap.get(request.getCommandName()).setFlat(request.getCommandObject());
                }if(commandMap.get(request.getCommandName()).getCommandType() == CommandType.ARGUMENT_WITH_HOUSE){
                    commandMap.get(request.getCommandName()).setHouse(request.getHouse());
                }
                if(commandMap.get(request.getCommandName()).getCommandType() == CommandType.BOTH_ARGUMENT_AND_ARGUMENT_FLAT){
                    commandMap.get(request.getCommandName()).setFlat(request.getCommandObject());
                    commandMap.get(request.getCommandName()).setStringArg(request.getCommandArgument());
                }
                response = new Response(commandMap.get(request.getCommandName()).execute(), request.getUser(), request.getPswd());
            }else response = new Response("no such command", request.getUser(), request.getPswd());
        return response;
    }
}
