/**
 * Created by Ksysio on 2016-05-30.
 */
public  class Information implements java.io.Serializable {

    private String name;
    private int value;

    public Information(String name, int value){
        this.name = name;
        this.value = value;

    }

    public void setValue(int value){

        this.value = value;

    }

    public String getName(){

        return name;

    }

    public int getValue(){

        return value;

    }

}
