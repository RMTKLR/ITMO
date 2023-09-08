package commands;

import collectionManager.FlatCollectionManager;

public class AddIfMaxCommand extends Command{
    private FlatCollectionManager collectionManager;
    public AddIfMaxCommand(FlatCollectionManager collectionManager) {
        super("add_if_max", "adds an element to the collection if it is greater than the greatest element in the collection", CommandAccess.NORMAL, CommandType.ARGUMENT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer= "";
        if(collectionManager.getCollection().isEmpty())
            answer = " collection is empty so there is no element to compare ";
        else {
            if(collectionManager.addIfMax(getFlat()))
                answer = " given flat has been successfully added to the collection";
            else answer = " because given flat wasn't max it hasn't been added to the collection";
        }
        return answer;
    }
}
