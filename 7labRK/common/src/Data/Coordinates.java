package Data;


import java.io.Serializable;

public class Coordinates implements DataValidate, Serializable {
    private Long x; //Максимальное значение поля: 171, Поле не может быть null
    private Float y; //Максимальное значение поля: 269, Поле не может быть null
    public Coordinates(Long x,Float y){
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return " { \n"+
                " X : "+x+"\n"+
                " Y : "+y+"\n"+
                " } ";
    }

    @Override
    public boolean dataValidate() {
        return ((x<=171&&x!=null)
                && (y<=269&& y!=null));
    }
}
