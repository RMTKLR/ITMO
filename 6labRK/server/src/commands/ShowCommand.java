package commands;

import collectionManager.FlatCollectionManager;

import java.util.concurrent.atomic.AtomicReference;

public class ShowCommand extends Command{
    private FlatCollectionManager collectionManager;
    public ShowCommand(FlatCollectionManager collectionManager) {
        super("show", "shows collection elements", CommandAccess.NORMAL, CommandType.NON_ARGUMENT);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        if(collectionManager.getCollection().isEmpty()) answer = "collection is empty";
        else {
            AtomicReference<String> toString = new AtomicReference<>("");
            collectionManager.getCollection().forEach(flat->{
                toString.set(toString.get()+"\n"+flat.toString());
            });
            answer = toString.get();
        }
        return answer;
    }
}
