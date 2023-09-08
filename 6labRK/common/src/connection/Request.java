package connection;

import Data.Flat;
import Data.House;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String commandArgument;
    private Flat commandObject;
    private House house;
    public Request(String commandName,String commandArgument,Flat commandObject,House house){
        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.commandObject = commandObject;
        this.house = house;
    }

    public String getCommandName() {
        return commandName;
    }

    public Flat getCommandObject() {
        return commandObject;
    }

    public String getCommandArgument() {
        return commandArgument;
    }
    public House getHouse(){
        return house;
    }
}
