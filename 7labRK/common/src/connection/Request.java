package connection;

import Data.Flat;
import Data.House;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String commandArgument;
    private Flat commandObject;
    private House house;

    private String user;
    private String pswd;

    private Flat flat;
    //new arguments user and user_password

    public Request(String commandName,String commandArgument,Flat commandObject,House house, String user, String pswd){
        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.commandObject = commandObject;
        this.house = house;
        this.user = user;
        this.pswd = pswd;
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

    // Getter for additionalData
    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandArgument(String commandArgument) {
        this.commandArgument = commandArgument;
    }

    public void setCommandObject(Flat commandObject) {
        this.commandObject = commandObject;
    }

    public void setHouse(House house) {
        this.house = house;
    }

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

    public Flat getFlat() {
        return flat;
    }
}
