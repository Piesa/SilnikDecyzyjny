package DecisionEngine.Engine;

/**
 * Created by Ksysio on 2016-05-30.
 */
public class Information implements java.io.Serializable {

    private String name;
    public int value;
    private String parameter;

    public Information(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Information(String name, int value, String parameter) {
        this.name = name;
        this.value = value;
        this.parameter = parameter;
    }

    public String toString() {
        if (parameter != null)
            return String.format("%s(%s) == %s", name, parameter, printfValue(value));
        else
            return String.format("%s == %s", name, printfValue(value));
    }

    private String printfValue(int value){
        if(value == 1)
            return "true";
        if(value == 0)
            return "false";
        else
            return "unknown";
    }


    public String Name() {
        if (parameter != null)
            return String.format("%s(%s)", name, parameter);
        else
            return String.format("%s", name);
    }

    public void setValue(int x) {
        value = x;
    }

}

