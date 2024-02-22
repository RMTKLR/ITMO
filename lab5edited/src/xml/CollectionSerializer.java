package xml;

import Manager.CollectionManager;
import com.thoughtworks.xstream.XStream;

public class CollectionSerializer {
    private CollectionManager collection;

    public CollectionSerializer(CollectionManager collectionManager){this.collection = collectionManager;}

    public String xmlSerialize(){
        XStream xStream = new XStream();
        return xStream.toXML(collection.getCollection());
    }
}
