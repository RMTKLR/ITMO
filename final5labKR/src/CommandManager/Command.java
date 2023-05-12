package CommandManager;

import exceptions.InputException;

public interface Command {
    /**
     * adds command
     * @param command command name
     * @param command command callback
     */
    boolean addCommand(String command, Command arg);

    /**
     * Method for adding new element to the collection
     */
    void add();

    /**
     * Method for instruction
     */
    void help();

    /**
     * Method for exiting from the program
     */
    void exit();

    /**
     * Method for showing all the elements
     */
    void show();

    /**
     * Method for saving elements
     */
    void save();

    /**
     * Method for deleting the elements
     */
    void clear();

    /**
     * Method for removing by the id of an elements
     * @param id
     */
    void removeById(String id);

    /**
     * Method for adding a new element at a given position
     * @param index
     */
    void insert_at(String index);

    /**
     * Method for giving information about history of the collection
     */
    void info();

    /**
     * Method for removing the last element of the collection
     */
    void remove_last();

    /**
     * Method for removing by their house name
     */
    void remove_any_by_house();

    /**
     * Method for printing by the their average of the rooms
     */
    void average_of_number_of_rooms();

    /**
     * Method for printing with desceding format of the elements
     */
    void print_field_descending_house();

    /**
     * Method for and command in the script file and
     * it is going to run all the commad that we have already mentioned in the script
     * @param path
     */
    void execute_script(String path);

    /**
     * Method for updating the elements
     * @param id
     * @throws InputException
     */
    void updateById(String id) throws InputException;

    /**
     * Method for adding a new element to the collection if its value is greater than the value of the largest element in this collection
     */
    void add_if_max();
}
