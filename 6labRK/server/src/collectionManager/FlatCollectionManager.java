package collectionManager;


import Data.Flat;
import Data.House;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


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
            return 1;
        }else{
            Integer id = (int) collection.get((collection.size()-1)).getId() +1 ;
            if(ids.contains(id)){
                while (ids.contains(id)){
                    id +=1;
                }
            }
            ids.add(id);
            return id;
        }
    }
    public String add(Flat flat) {
        flat.setId(autoId());
        collection.add(flat);
        return "New Element that was added to the collection is : "+"\n"+
                flat;

    }

    public boolean removeById(int id) {
        return collection.removeIf(flat->flat.getId() == id);
    }


    public void clear() {
        collection.clear();
    }


    public boolean insertAt(int index,Flat flat) {
        if(collection.size()-1<index) return false;
        collection.add(index,flat);
        Optional<Flat> optionalFlat = Optional.ofNullable(collection.get(index));
        if(optionalFlat.isPresent())
            return true;
        else return false;
    }


    public boolean update(int id, Flat flat){
        boolean success;
        if(ids.contains(id)){
            AtomicBoolean isUpdated = new AtomicBoolean(false);
            collection.stream()
                    .filter(f -> f.getId() == id)
                    .forEach(f -> {
                        f.setName(flat.getName());
                        f.setCoordinates(flat.getCoordinates());
                        f.setCreationDate(flat.getCreationDate());
                        f.setArea(flat.getArea());
                        f.setNumberOfBathrooms(flat.getNumberOfBathrooms());
                        f.setNumberOfRooms(flat.getNumberOfRooms());
                        f.setNew(flat.isNew());
                        f.setTransport(flat.getTransport());
                        f.setHouse(flat.getHouse());
                        isUpdated.set(true);
                    });
            success = isUpdated.get();
        }else success = false;
        return success;
    }


    public boolean removeLast() {
        Optional<Flat> optionalFlat = collection.stream().reduce((first, second) -> second);
        if (optionalFlat.isPresent()) {
            collection.remove(optionalFlat.get());
            return true;
        } else {
            return false;
        }

    }


    public boolean addIfMax(Flat flat) {
        Optional<Flat> max = collection.stream()
                .max(Comparator.comparingLong(Flat::getNumberOfRooms));
        if (max.isPresent() && flat.compareTo(max.get())>0) {
            add(flat);
            return true;
        }
        else return false;
    }


    public boolean removeAnyByHouse(House house) {
        return collection.removeIf(flat -> flat.getHouse() == house);
    }


    public int averageNumberOfRooms() {
        return (int) collection.stream()
                .mapToInt(Flat::getNumberOfRooms)
                .average()
                .orElse(0);
    }

    public List<House> printFieldDescendingHouse() {
        return collection.stream()
                .map(Flat::getHouse)
                .sorted(Comparator.comparingDouble(House::getNumberOfFloors))
                .collect(Collectors.toList());
    }


}
