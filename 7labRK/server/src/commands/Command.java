package commands;

import Data.Flat;
import Data.House;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Command {
    private String commandName;
    private String commandDescription;
    private CommandAccess commandAccess;
    private CommandType commandType;
    private String argument;
    private Flat flat;
    private House house;
    ///new arguments user and user_password
    private String user;
    private String pswd;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    private Connection con;


    public Command(String commandName,String commandDescription,CommandAccess commandAccess,CommandType commandType, Connection con){
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.commandAccess = commandAccess;
        this.commandType = commandType;
        this.con = con;
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
    public abstract String execute() throws SQLException;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
}
