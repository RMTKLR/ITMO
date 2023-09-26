package commands;

import Data.Flat;
import bd.BdMain;
import collectionManager.FlatCollectionManager;

import java.sql.Connection;
import java.util.Comparator;
import java.util.Optional;

public class AddIfMaxCommand extends Command {
    private FlatCollectionManager collectionManager;

    public AddIfMaxCommand(FlatCollectionManager collectionManager, Connection con) {
        super("add_if_max", "adds an element to the collection if it is greater than the greatest element in the collection", CommandAccess.NORMAL, CommandType.ARGUMENT_WITH_FLAT, con);
        this.collectionManager = collectionManager;
    }

    @Override
    public String execute() {
        String answer = "";
        // Find the maximum element in the collection based on the number of rooms
        Optional<Flat> max = collectionManager.getCollection().stream()
                .max(Comparator.comparingLong(Flat::getNumberOfRooms));

        if (max.isPresent()) {
            try {
                // Retrieve the Flat object from the command arguments
                Flat f = this.getFlat();

                // Create an instance of BdMain to interact with the database
                BdMain bd = new BdMain();

                // Generate unique IDs for the new flat, house, and user
                int idHouse = bd.getHouseNextId(this.getCon());
                int idFlat = bd.getFlatNextId(this.getCon());
                int idUser = bd.getIdUser(this.getCon(), this.getUser());

                // Set the generated IDs for the Flat object
                f.getHouse().setId(idHouse);
                f.setId(idFlat);
                f.setUserId(idUser);

                // Save the new house record to the database
                bd.savehouse(f, this.getCon());

                // Save the new flat record to the database
                bd.saveToMainTable(f, this.getCon());

                return "Успешно добавлено!"; // Return a success message
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "Что-то пошло не так!"; // Return an error message
            }
        } else {
            return "Не максимальный!"; // Return a message indicating that it's not the maximum element
        }
    }
}
