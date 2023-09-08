package commands;

import collectionManager.FlatCollectionManager;

public class RemoveByIdCommand extends Command{
    private FlatCollectionManager collectionManager;
    public RemoveByIdCommand(FlatCollectionManager collectionManager) {
        super("remove_by_id", "removes an element by their id", CommandAccess.NORMAL, CommandType.ARGUMENT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        try{
            int id = Integer.parseInt(getStringArg());
            if(collectionManager.removeById(id))
                answer = "flat with id #"+id+" has been successfully removed from the collection";
            else answer = "can't delete the flat";
        }catch (NumberFormatException exception){
            answer = "invalid id";
        }
        return answer;
    }
}
