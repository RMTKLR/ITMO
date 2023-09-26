package commands;

import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicReference;

public class PrintFieldDescendingHouseCommand extends Command{
    private FlatCollectionManager collectionManager;
    public PrintFieldDescendingHouseCommand(FlatCollectionManager collectionManager, Connection con) {
        super("print_field_descending_house", "print from the collection field house in descending order", CommandAccess.NORMAL, CommandType.NON_ARGUMENT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer;
        if(collectionManager.getCollection().isEmpty()) answer = " collection is empty";
        else {
            AtomicReference<String> toString = new AtomicReference<>();
            collectionManager.printFieldDescendingHouse().forEach(house->{
                toString.set(toString.get()+"\n"+house.toString());
            });
            answer = toString.get();
        }
        return answer;
    }
}
