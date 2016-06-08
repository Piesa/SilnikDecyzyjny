package DecisionEngine.Engine;

/**
 * Created by Ksysio on 2016-05-30.
 */
public  class Information implements java.io.Serializable {

    private String name;
    private int value;
    private String parameter;

    public Information(String name, int value){
        this.name = name;
        this.value = value;
    }

    public Information(String name, int value, String parameter){
        this.name = name;
        this.value = value;
        this.parameter = parameter;
    }

    public String toString(){
        if(parameter != null)
            return String.format("%s(%s) == %s", name, parameter, value );
        else
            return String.format("%s == %s", name, value);
    }
    public String Name(){
        if(parameter != null)
            return String.format("%s(%s)", name, parameter);
        else
            return String.format("%s", name);
    }
}
