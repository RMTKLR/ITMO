package commands;


import java.sql.Connection;

public class HelpCommand extends Command{
    public HelpCommand(Connection con) {
        super("help", "shows help to available commands", CommandAccess.NORMAL, CommandType.NON_ARGUMENT, con);
    }

    @Override
    public String execute() {
        return "\r\nhelp: display help on available commands" +
                "\r\ninfo: print information about the collection to standard output (type initialization date, number of elements, etc.)" +
                "\r\nshow: print to standard output all elements of the collection in string representation" +
                "\r\nadd {element}: add a new element to the collection" +
                "\r\nupdate id {element}: update the value of the collection element whose id is equal to the given one" +
                "\r\nremove_by_id id: remove an element from the collection by its id" +
                "\r\nclear: clear collection" +
                "\r\nexecute_script file_name: read and execute a script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode." +
                "\r\nexit: end program (without saving to file)" +
                "\r\ninsert_at index {element}: add a new element at a given position" +
                "\r\nremove_last: remove the last element from the collection" +
                "\r\nadd_if_max {element}: add a new element to the collection if its value is greater than the value of the largest element in this collection" +
                "\r\nremove_any_by_house house: remove one element from the collection whose house field value is equivalent to the given one" +
                "\r\naverage_number_of_rooms: display the average value of the numberOfRooms field for all elements of the collection" +
                "\r\nprint_field_ascending_house: print house field values of all elements in ascending order" +
                "\r\naddUser: add a new USER" +
                "\r\nlogin: login a user" +
                "\r\nlogout: logout a user" +
                "\r\nuser: user";
    }
}
