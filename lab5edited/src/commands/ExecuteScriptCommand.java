package commands;


import Manager.CommandManager;
import Manager.FileManager;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


//public class ExecuteScriptCommand implements Command{
//    private Set<String> setPath;
//    private CommandManager commandManager;
//    public ExecuteScriptCommand(CommandManager commandManager){
//        this.commandManager = commandManager;
//        setPath = new HashSet<>();
//    }
//    @Override
//    public void execute(String argument) {
//        if (!isRecursiveScript(argument)) setPath.add(argument);
//        FileManager f = new FileManager(argument);
//        String fileContent = f.reader();
//        String [] spilt = fileContent.trim().split("\n");
//        for(int i=0;i<spilt.length;i++){
//            String[] commandAndArg = spilt[i].trim().split(" ",2);
//            if (commandAndArg[0].equals("executeScript")) {
//                if (!isRecursiveScript(commandAndArg[1])){
//                    setPath.add(commandAndArg[1]);
//                    actionIfArg(commandAndArg);
//                }
//                else {
//                    recursiveScript(commandAndArg);
//
//                }
//            }else actionIfArg(commandAndArg);
//        }
//    }
//
//    private void recursiveScript(String[] commandAndArg){
//        while(true){
//            System.out.println("recursive script detected enter c to execute and enter S to terminate =");
//            String a = new Scanner(System.in).nextLine();
//            if(a.equals("c")) {
//                actionIfArg(commandAndArg);
//            }
//            else
//                break;
//        }
//    }
//    private void actionIfArg(String[] commandAndArg){
//        try{
//            runScript(commandAndArg[0],commandAndArg[1]);
//        }catch (ArrayIndexOutOfBoundsException e){
//            runScript(commandAndArg[0],"");
//        }
//    }
//    private void runScript (String command,String arg){
//        commandManager.getCommandMap().get(command).execute(arg);
//    }
//
//    private boolean isRecursiveScript(String path){
//        return setPath.contains(path);
//    }
//
//    @Override
//    public String getDescription() {
//        return "To execute all commands from the file";
//    }
//}




import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScriptCommand implements Command {
    private Set<String> setPath;
    private CommandManager commandManager;

    public ExecuteScriptCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
        setPath = new HashSet<>();
    }

    @Override
    public void execute(String argument) {
        if (!isRecursiveScript(argument)) {
            setPath.add(argument);
            FileManager fileManager = new FileManager(argument);
            String fileContent = fileManager.reader();
            String[] split = fileContent.trim().split("\n");

            for (int i = 0; i < split.length; i++) {
                String[] commandAndArg = split[i].trim().split(" ", 2);
                if (commandAndArg[0].equals("executeScript")) {
                    if (!isRecursiveScript(commandAndArg[1])) {
                        setPath.add(commandAndArg[1]);
                        actionIfArg(commandAndArg);
                    } else {
                        recursiveScript(commandAndArg[1]);
                    }
                } else {
                    actionIfArg(commandAndArg);
                }
            }
        }else {
           recursiveScript(argument);
        }
    }

    private void recursiveScript(String path) {
        while (true) {
            System.out.println("Recursive script detected. Enter 'c' to execute and 's' to terminate: ");
            String input = new Scanner(System.in).nextLine();
            if (input.equals("c")) {
                FileManager fileManager = new FileManager(path);
                String fileContent = fileManager.reader();
                String[] split = fileContent.trim().split("\n");

                for (int i = 0; i < split.length; i++) {
                    String[] commandAndArg = split[i].trim().split(" ", 2);
                    actionIfArg(commandAndArg);
                }
            } else {
                break;
            }
        }
    }

    private void actionIfArg(String[] commandAndArg) {
        try {
            runScript(commandAndArg[0], commandAndArg.length > 1 ? commandAndArg[1] : "");
        } catch (ArrayIndexOutOfBoundsException e) {
            runScript(commandAndArg[0], "");
        }
    }

    private void runScript(String command, String arg) {
        commandManager.getCommandMap().get(command).execute(arg);
    }

    private boolean isRecursiveScript(String path) {
        return setPath.contains(path);
    }

    @Override
    public String getDescription() {
        return "To execute all commands from the file";
    }
}
