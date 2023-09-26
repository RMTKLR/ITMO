package deserializer;

import Data.Flat;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionDeserializer {
    private HashSet<Integer> ids ;
    public CollectionDeserializer(){
        ids = new HashSet<>();
    }

    public HashSet<Integer> getIds() {
        return ids;
    }

    public ArrayList<Flat> deserialize(String xml){
        XStream xStream = new XStream();
        xStream.alias("flat",Flat.class);
        xStream.addPermission(AnyTypePermission.ANY);
        ArrayList<Flat> toValidate = (ArrayList<Flat>) xStream.fromXML(xml);
        ArrayList<Flat> collection = new ArrayList<>();
        toValidate.forEach(flat -> {
            if(validate(flat)) {
                ids.add(flat.getId());
                collection.add(flat);
            }
        });
        return collection;
    }
    private boolean validate(Flat flat){
        return flat.dataValidate();
    }
}
