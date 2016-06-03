/**
 * Created by Ksysio on 2016-05-30.
 */
public class Information {

    private String Name;
    private int Value;

    public Information(String name, int value){
        this.Name = name;
        this.Value = value;

    }

    public void setValue(int value){

        this.Value = value;

    }

    public String getName(){

        return Name;

    }

    public int getValue(){

        return Value;

    }

}
