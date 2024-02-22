package commands;

import Manager.CollectionManager;

public class ShowCommand implements Command{
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String argument) {
        if(!collectionManager.getCollection().isEmpty()) collectionManager.getCollection().forEach(System.out::println);
        else System.out.println("collection is empty!!!");
    }

    @Override
    public String getDescription() {
        return "To display all data in the file.";
    }
}
