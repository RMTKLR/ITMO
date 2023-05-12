package XML;

import CollectionManager.CollectionManager;
import CollectionManager.FlatCollectionManager;
import com.thoughtworks.xstream.XStream;
import Data.*;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.security.AnyTypePermission;
import exceptions.InputException;

import java.util.ArrayList;
public class CollectionDeserializer {
    private FlatCollectionManager collectionManager;
    public CollectionDeserializer(FlatCollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public boolean deserialize(String xml){
        boolean success = true;
            if(xml.equals("")||xml==null){
                success = false;
            }else {
                XStream xStream = new XStream();
                xStream.alias("Flat", Flat.class);
                xStream.alias("ArrayList", ArrayList.class);
                xStream.addPermission(AnyTypePermission.ANY);
                try {
                    ArrayList<Flat> arrayList = (ArrayList<Flat>) xStream.fromXML(xml);
                    ArrayList<Flat> validated = new ArrayList<>();
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (valid(arrayList.get(i)) && !isUnique(arrayList.get(i).getId())) {
                            validated.add(arrayList.get(i));
                            collectionManager.getIds().add(arrayList.get(i).getId());
                        } else {
                            System.out.println("Flat with id #" + arrayList.get(i).getId() + " is not valid !!!");
                        }
                    }
                    if (validated.size() == 0) success = false;
                    collectionManager.setCollection(validated);
                } catch (StreamException exception) {
                    System.err.println("the file is not deserialized!! ");
                }
            }

        return success;
    }
    public boolean valid (Flat flat){
        return flat.dataValidate();
    }
    private boolean isUnique(int id){
        return collectionManager.getIds().contains(id);
    }
}
