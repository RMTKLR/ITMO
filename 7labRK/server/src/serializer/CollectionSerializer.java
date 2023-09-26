package serializer;

import Data.Flat;
import collectionManager.FlatCollectionManager;
import com.thoughtworks.xstream.XStream;

public class CollectionSerializer {
    private FlatCollectionManager collectionManager;
    public CollectionSerializer(FlatCollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public String serialize(){
        XStream xStream = new XStream();
        xStream.alias("flat", Flat.class);
        return xStream.toXML(collectionManager.getCollection());
    }
}
