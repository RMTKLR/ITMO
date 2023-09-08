package commands;

import collectionManager.FlatCollectionManager;

public class UpdateCommand extends Command{
    private FlatCollectionManager collectionManager;
    public UpdateCommand(FlatCollectionManager collectionManager) {
        super("update", "update s element in the collection with the given id", CommandAccess.NORMAL, CommandType.BOTH_ARGUMENT_AND_ARGUMENT_FLAT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        try {
            int id = Integer.parseInt(getStringArg());
            if (collectionManager.getIds().contains(id)){
                if(collectionManager.update(id,getFlat()))
                    answer = "Flat with id #"+id+" has been successfully updated";
                else answer = "can't update the flat";
            } else answer = "no such id";
        }catch (NumberFormatException exception){
            answer = "Invalid id";
        }
        return answer;
    }
}
