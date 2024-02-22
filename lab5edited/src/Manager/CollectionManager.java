package Manager;

import data.Flat;
import data.House;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CollectionManager {
    private ArrayList<Flat> collection;
    private HashSet<Integer> ids;
    private LocalDateTime localDateTime;

    public CollectionManager (){
        collection =  new ArrayList<>();
        ids = new HashSet<>();
        localDateTime = LocalDateTime.now();
    }

    public LocalDateTime getLocalDateTime(){
        return localDateTime;
    }

    public HashSet<Integer> getIds(){
        return ids;
    }
    public ArrayList<Flat> getCollection(){
        return collection;
    }

    public void setIds(HashSet<Integer> ids){
        this.ids = ids;
    }

    public void setCollection(ArrayList<Flat> collection){
        this.collection = collection;
    }

    public int autoId(){
        if(ids.isEmpty() || ids == null){
            ids.add(1);
            return 1;
        }
        else{
            Integer id = (int) collection.get((collection.size() - 1)).getId();
            if(ids.contains(id)){
                while(ids.contains(id)){
                    id += 1;
                }
            }
            ids.add(id);
            return id;
        }
    }

    public boolean add(Flat flat){
        boolean s = false;
        flat.setId(autoId());
        s = collection.add(flat);
        System.out.println("New element that was added to he collection : ");
        System.out.println(flat);
        return s;
    }

    public boolean removeById(int id){
        AtomicBoolean found = new AtomicBoolean(false);
        if (collection.removeIf(flat -> flat.getId() == id) && ids.removeIf(id1 -> id1 == id)){
            System.out.println("flat with id of #" + " has been successfully removed.");
            found.set(true);
        }
        else{
            System.out.println(id + " not found!");
        }

        return found.get();
    }

    public void clear(){
        collection.clear();
    }

    public void insert_at(long index, Flat flat) {
        if (index < 0 || index > collection.size()) System.err.println("invalid index!!!");
        else {
            flat.setId(autoId());
            collection.add((int) index, flat);
            System.out.println("New element that was added to the collection in \" + index + \" the position is : \"");
        }

    }

    public void updateByAnId(int id, Flat flat){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        if(collection.removeIf(flat2 -> flat2.getId() == id && ids.removeIf(ids1->ids1 == id))){
            flat.setId(id);
            collection.add(flat);
            atomicBoolean.set(true);
            System.out.println("this id is updated.");
        }
        atomicBoolean.get();
    }


    public void removeLast() {
        if(collection.isEmpty()) System.out.println("The collection is Empty");
        else {
            collection.remove(collection.size() - 1);
            System.out.println("The last element has been successfully removed.");
        }
    }


   public boolean addIfMax(Flat flat){
        Flat max = collection.get(0);
        for(int counter = 1; collection.size()-1 > counter ; counter += 1){
            if (max.compareTo(collection.get(counter)) < 1){
                max = collection.get(counter);
            }
        }
        if(flat.compareTo(max) >= 1){
            add(flat);
            return  true;
        }

        return false;
   }


    public void remove_any_by_house(House house) {
        collection.forEach(flat -> {
            if(flat.getHouse().equals(house)){
                collection.remove(flat);
            }
        });
//        if(collection.removeIf(flat-> flat.getHouse().equals(house))){
//            System.out.println("The house has been successfully removed.");
//        }else System.err.println("invalid.");

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

    public void info(){
        System.out.println("Name of collection " + collection.getClass().getName() + ", size of the collection " + collection.size() + ", initialization date " + localDateTime);
    }

}
