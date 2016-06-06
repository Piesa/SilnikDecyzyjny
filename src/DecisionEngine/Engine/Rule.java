package DecisionEngine.Engine;

/**
 * Created by Ksysio on 2016-05-30.
 */
public class Rule implements java.io.Serializable {

    private int howMany;
    private String dataName[];
    private boolean isNegated[];
    private int relation;
    private String relationc;
    private String dataNegatedNames[];

    public Rule(int howMany, String dataName[], int relation, boolean isNegated[]) {
        this.howMany = howMany;
        this.relation = relation;
        int i = 0;
        while (i < howMany) {
            this.dataName[i] = dataName[i];
            this.isNegated[i] = isNegated[i];
            if (isNegated[i] == true)
                this.dataNegatedNames[i] = "!" + dataName[i];
            else
                this.dataNegatedNames[i] = dataName[i];
            i++;
        }
        if (relation == 1)
            this.relationc = "&&";
        else
            this.relationc = "||";
    }

    public String toString(){
        if(howMany == 2)
            return String.format("%s == %s", dataNegatedNames[0], dataNegatedNames[1]);
        else
            return String.format("%s %s %s == %s", dataNegatedNames[0], relationc, dataNegatedNames[1], dataNegatedNames[2]);

        }
}
