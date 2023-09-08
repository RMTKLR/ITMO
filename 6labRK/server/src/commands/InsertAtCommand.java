package commands;

import collectionManager.FlatCollectionManager;

public class InsertAtCommand extends Command{
    private FlatCollectionManager collectionManager;
    public InsertAtCommand(FlatCollectionManager collectionManager) {
        super("insert_at", "insert an element in the given index", CommandAccess.NORMAL, CommandType.BOTH_ARGUMENT_AND_ARGUMENT_FLAT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        try {
           int index = Integer.parseInt(getStringArg());
           if(collectionManager.insertAt(index,getFlat()))
               answer = " given flat has been successfully inserted in index #"+index;
           else answer = " can't insert the flat in the given index";
        }catch (NumberFormatException exception){
           answer = "invalid index";
        }
        return answer;
    }
}
