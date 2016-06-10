package DecisionEngine.Engine;

/**
 * Created by Ksysio on 2016-05-30.
 */
public class Rule implements java.io.Serializable {

    public int howMany; // declares how many variables :)
    public String dataName1;
    public String dataName2;
    public String dataName3;
    private boolean isNegated1;
    private boolean isNegated2;
    private boolean isNegated3;
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

    public String toString() {
        if (howMany == 2)
            return String.format("%s == %s", dataNegatedNames1, dataNegatedNames3);
        else
            return String.format("%s %s %s => %s", dataNegatedNames1, relationc, dataNegatedNames2, dataNegatedNames3);

    }



    public String getdataName(int x) {
        if (x == 1)
            return dataName1;
        if (x == 2)
            return dataName2;
        else
            return dataName3;
    }

    public boolean isNegated(int x) {
        if (x == 1)
            return isNegated1;
        if (x == 2)
            return isNegated2;
        else
            return isNegated3;
    }
}
