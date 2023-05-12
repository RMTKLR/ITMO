package Data;



public class Coordinates implements DataValidate{
    private Double x; //Максимальное значение поля: 171, Поле не может быть null
    private Double y; //Максимальное значение поля: 269, Поле не может быть null
    public Coordinates(Double x,Double y){
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
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
                && (y.doubleValue()<=269&& y!=null));
    }
}
