package DecisionEngine.Engine;

/**
 * Created by Ksysio on 2016-05-30.
 */
public class Rule implements java.io.Serializable {

    private int howMany;
    public String dataName1;
    public String dataName2;
    public String dataName3;
    public boolean isNegated1;
    public boolean isNegated2;
    public boolean isNegated3;
    public int relation;
    private String relationc;
    public String dataNegatedNames1;
    public String dataNegatedNames2;
    public String dataNegatedNames3;

    public Rule(int howMany, String dataName1, String dataName2, boolean isNegated1, boolean isNegated2) {
        this.howMany = howMany;
        this.dataName1 = dataName1;
        this.isNegated1 = isNegated1;
        if (isNegated1)
            this.dataNegatedNames1 = "!" + dataName1;
        else
            this.dataNegatedNames1 = dataName1;
        this.dataName3 = dataName2;
        this.isNegated3 = isNegated2;
        if (isNegated2)
            this.dataNegatedNames3 = "!" + dataName2;
        else
            this.dataNegatedNames3 = dataName2;
    }

    public Rule(int howMany, String dataName1, String dataName2, String dataName3, int relation, boolean isNegated1, boolean isNegated2, boolean isNegated3) {
        this.howMany = howMany;
        this.relation = relation;
        if (relation == 1)
            this.relationc = "&&";
        else
            this.relationc = "||";
        this.dataName1 = dataName1;
        this.isNegated1 = isNegated1;
        if (isNegated1)
            this.dataNegatedNames1 = "!" + dataName1;
        else
            this.dataNegatedNames1 = dataName1;
        this.dataName2 = dataName2;
        this.isNegated2 = isNegated2;
        if (isNegated2)
            this.dataNegatedNames2 = "!" + dataName2;
        else
            this.dataNegatedNames2 = dataName2;
        this.dataName3 = dataName3;
        this.isNegated3 = isNegated3;
        if (isNegated3)
            this.dataNegatedNames3 = "!" + dataName3;
        else
            this.dataNegatedNames3 = dataName3;
    }

    public String toString(){
        if(howMany == 2)
            return String.format("%s == %s", dataNegatedNames1, dataNegatedNames3);
        else
            return String.format("%s %s %s == %s", dataNegatedNames1, relationc, dataNegatedNames2, dataNegatedNames3);

        }
}
