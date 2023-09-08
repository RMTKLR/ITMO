package commands;

import collectionManager.FlatCollectionManager;

public class RemoveLastCommand extends Command{
    private FlatCollectionManager collectionManager;
    public RemoveLastCommand(FlatCollectionManager collectionManager) {
        super("remove_last", "removes from the collection last element", CommandAccess.NORMAL, CommandType.NON_ARGUMENT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        if(collectionManager.getCollection().isEmpty()) answer = "collection is empty";
        else{
            if(collectionManager.removeLast())
                answer = "last element has been successfully deleted";
            else answer = " can't delete the last element";
        }
        return answer;
    }
}
