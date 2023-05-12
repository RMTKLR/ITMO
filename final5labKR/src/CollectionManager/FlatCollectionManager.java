package CollectionManager;

import Data.Flat;
import Data.House;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@XStreamAlias("Collection")
public class FlatCollectionManager {

    private  ArrayList<Flat> collection;
    private HashSet<Integer> ids;
    private LocalDateTime localDateTime;

    public FlatCollectionManager(){
        collection = new ArrayList<>();
        localDateTime = LocalDateTime.now();
        ids = new HashSet<>();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public HashSet<Integer> getIds() {
        return ids;
    }

    public void setIds(HashSet<Integer> ids) {
        this.ids = ids;
    }

    public void setCollection(ArrayList<Flat> collection) {
        this.collection = collection;
    }

    public ArrayList<Flat> getCollection() {
        return collection;
    }

    public int autoId() {
        if(ids.isEmpty() || ids == null){
            ids.add(1);
            return 1;
        }else{
            Integer id = (int) collection.get((collection.size()-1)).getId();
            if(ids.contains(id)){
                while (ids.contains(id)){
                    id +=1;
                }
            }
            ids.add(id);
            return id;
        }
    }
    public void add(Flat flat) {
        flat.setId(autoId());
        collection.add(flat);
        System.out.println("New Element that was added to the collection is : ");
        System.out.println(flat);
    }

    public boolean removeById(int id) {
     AtomicBoolean found = new AtomicBoolean(false);
     if(collection.removeIf(flat->flat.getId()==id)&&ids.removeIf(id1->id1==id)){
         System.out.println("flat with id of #"+id+" has been successfully deleted!!");
         found.set(true);
     }else{
         System.out.println(id+" not found ");
     }
     return found.get();
    }


    public void clear() {
        collection.clear();
    }


    public void insert_at(long index,Flat flat) {
            flat.setId(autoId());
            collection.add((int)index, flat);
            System.out.println("New Element that was added to the collection in " + index + "th position is : ");
            System.out.println(flat);
    }


    public boolean updateid(int id, Flat flat){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if(collection.removeIf(flat2 -> flat2.getId() == id && ids.removeIf(ids1->ids1 == id))){
           flat.setId(id);
           collection.add(flat);
           atomicBoolean.set(true);
           System.out.println("this id is updated.");
        }
        return atomicBoolean.get();
    }


    public void remove_last() {
        collection.remove(collection.size()-1);
    }


    public boolean add_if_max(Flat flat) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AtomicReference<Flat> max = new AtomicReference<>(collection.get(0));
        collection.forEach(flat1 -> {
            if(max.get().compareTo(flat1)<0){
                max.set(flat1);
            }
        });
        if(max.get().compareTo(flat)<0){
            add(flat);
            atomicBoolean.set(true);
            System.out.println("Successfully added.");
        }else{
            System.out.println("The number of the room is not maxiumum.");
        }

        return atomicBoolean.get();
    }


    public void remove_any_by_house(House house) {
//        collection.forEach(flat -> {
//            if(flat.getHouse().equals(house)){
//                collection.remove(flat);
//            }
//        });
        if(collection.removeIf(flat-> flat.getHouse().equals(house))){

        }

    }


    public int average_of_number_of_rooms() {
        AtomicInteger sum = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);
        collection.forEach(flat -> {
            sum.addAndGet(flat.getNumberOfRooms());
            count.getAndIncrement();
        });
        return sum.get() / count.get();
    }

    public void print_field_descending_house() {
        ArrayList<House> descend = new ArrayList<>();
        AtomicReference<House>max = new AtomicReference<>(collection.get(0).getHouse());
        collection.forEach(flat->{
            if(flat.getHouse().compareTo(max.get())<0)
                max.set(flat.getHouse());
        });
        collection.forEach(flat->{
            if(flat.getHouse().compareTo(max.get())>=0)
                descend.add(flat.getHouse());
        });
        descend.forEach(System.out::println);
    }


}
