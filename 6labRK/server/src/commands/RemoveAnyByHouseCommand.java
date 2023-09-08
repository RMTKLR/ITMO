package commands;

import collectionManager.FlatCollectionManager;

public class RemoveAnyByHouseCommand extends Command{
    private FlatCollectionManager collectionManager;
    public RemoveAnyByHouseCommand(FlatCollectionManager collectionManager) {
        super("remove_any_by_house", "removes an element from the collection whose house field is same as the given one ", CommandAccess.NORMAL, CommandType.ARGUMENT_WITH_HOUSE);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
       if(collectionManager.getCollection().isEmpty()) answer = "collection is empty";
       else{
           if(collectionManager.removeAnyByHouse(getHouse()))
               answer = " omition has been executed successfully";
           else answer = " can't deleted";
       }
       return answer;
    }
}
