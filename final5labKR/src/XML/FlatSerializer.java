package XML;

import CollectionManager.FlatCollectionManager;
import com.thoughtworks.xstream.XStream;

public class FlatSerializer {
    private FlatCollectionManager collection;
    public FlatSerializer(FlatCollectionManager collection){
        this.collection = collection;
    }
    public String xmlSerialize(){
        XStream xstream = new XStream();
       return  xstream.toXML(collection.getCollection());
    }
}
