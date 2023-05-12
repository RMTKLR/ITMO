package CommandManager;

import CollectionManager.FlatCollectionManager;
import input.Input;
import input.InputData;
import XML.FlatSerializer;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import FileManager.*;
import exceptions.InputException;

public class CommandManager implements Command {
    private FlatCollectionManager collection;
    private Scanner scanner;
    private FlatSerializer flatSerializer;
    private InputData inputData;
    private FileManager fileManager;
    private HashMap<String,Command> commands;
    public CommandManager(FileManager fileManager,FlatCollectionManager collection) {
        this.collection = collection;
        scanner = new Scanner(System.in);
        flatSerializer = new FlatSerializer(collection);
        inputData = new InputData(collection);
        this.fileManager = fileManager;
    }

    @Override
    public boolean addCommand(String command, Command arg){
        boolean result = false;
        if(command!=null&&command.equals("")) {
            commands.put(command,arg);
            result = true;
        }
        return result;
    }
    @Override
    public void add(){
        collection.add(inputData.readFlat());
    }

    @Override
    public void help() {
        System.out.println("\r\nhelp: display help on available commands" +
                "\r\ninfo: print information about the collection to standard output (type initialization date, number of elements, etc.)" +
                "\r\nshow: print to standard output all elements of the collection in string representation" +
                "\r\nadd {element}: add a new element to the collection" +
                "\r\nupdate id {element}: update the value of the collection element whose id is equal to the given one" +
                "\r\nremove_by_id id: remove an element from the collection by its id" +
                "\r\nclear: clear collection" +
                "\r\nsave: save collection to file" +
                "\r\nexecute_script file_name: read and execute a script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode." +
                "\r\nexit: end program (without saving to file)" +
                "\r\ninsert_at index {element}: add a new element at a given position" +
                "\r\nremove_last: remove the last element from the collection" +
                "\r\nadd_if_max {element}: add a new element to the collection if its value is greater than the value of the largest element in this collection" +
                "\r\nremove_any_by_house house: remove one element from the collection whose house field value is equivalent to the given one" +
                "\r\naverage_of_number_of_rooms: display the average value of the numberOfRooms field for all elements of the collection" +
                "\r\nprint_field_ascending_house: print house field values of all elements in ascending order");
    }

    @Override
    public void exit(){
        System.out.println("Good bye!!!");
        System.exit(0);
    }
    @Override
    public void show(){
        if(collection.getCollection().isEmpty()) System.out.println("collection is empty");
      try{
            collection.getCollection().forEach(System.out::println);
        }catch (NullPointerException exception){
          System.err.println("collection is empty");
      }
    }
    @Override
    public void save(){
        fileManager.write(flatSerializer.xmlSerialize());
        System.out.println("Date has been saved.");
    }
    @Override
    public void clear(){
        collection.clear();
    }
    @Override
    public void removeById(String id){
        try {
            int id1 = Integer.parseInt(id);
            collection.removeById(id1);
        }catch (NumberFormatException |InputException exception){
        }
    }
    @Override
    public void insert_at(String index){
        try {
            int index1 = Integer.parseInt(index);
            if (collection.getCollection().size() > index1)
                collection.insert_at(index1, inputData.readFlat());
            else {
                System.out.println("no such index");
            }
        }catch (NumberFormatException exception){
            System.err.println("no such index");
        }
    }
    @Override
    public void info(){
        System.out.println("ArrayList of Flats initialized in "+collection.getLocalDateTime()+" with size of "+collection.getCollection().size());
    }
    @Override
    public void remove_last(){
        if(!collection.getCollection().isEmpty()){
          collection.getCollection().remove(collection.getCollection().size()-1);
            System.out.println("Removed successfully");
        }else{
            System.err.println("last element is not removed!!");
        }
    }
    @Override
    public void remove_any_by_house() {
        collection.remove_any_by_house(inputData.readHouse());
    }
    @Override
    public void average_of_number_of_rooms(){
        System.out.println(collection.average_of_number_of_rooms());
    }
    @Override
    public void print_field_descending_house(){
        collection.print_field_descending_house();
    }
    @Override
    public void execute_script(String path){
        FileManager fileManager1 = new FileManager();
        fileManager1.setPath(path);
        Input input1 = new Input(fileManager1,collection);
        input1.scirpt();
    }
    @Override
   public void updateById(String id)throws InputException{
        if(id.equals(""))throw new InputException();
        else{
            int n = 0;
            try{
                n = Integer.parseInt(id);
                if(collection.updateid(n,inputData.readFlat())) System.out.println("Object with id of " + n + " has been updated successfully.");
            }catch (NumberFormatException e){
                System.err.println("Invalid id!!");
            }
        }
   }
    @Override
   public void add_if_max(){
        collection.add_if_max(inputData.readFlat());
   }
}
