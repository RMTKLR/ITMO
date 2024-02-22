package data;

public enum Transport {
    FEW(0),
    LITTLE(1),
    NORMAL(2),
    ENOUGH(3);
    private final int value;
     Transport(int value){
        this.value = value;
    }
}