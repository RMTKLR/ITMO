package commands;

import Data.Flat;
import Data.House;

public abstract class Command {
    private String commandName;
    private String commandDescription;
    private CommandAccess commandAccess;
    private CommandType commandType;
    private String argument;
    private Flat flat;
    private House house;
    public Command(String commandName,String commandDescription,CommandAccess commandAccess,CommandType commandType){
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.commandAccess = commandAccess;
        this.commandType = commandType;
    }
    public final void setFlat(Flat flat){
        this.flat = flat;
    }
    public final Flat getFlat(){
        return flat;
    }
    public final void setHouse(House house){
        this.house = house;
    }
    public final House getHouse(){
        return house;
    }
    public final void setStringArg(String argument){
        this.argument = argument;
    }
    public final String getStringArg(){
        return argument;
    }
    public final CommandType getCommandType(){
        return commandType;
    }
    public final CommandAccess getCommandAccess(){
        return commandAccess;
    }
    public abstract String execute();
}
