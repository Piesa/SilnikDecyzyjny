import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksysio on 2016-05-30.
 */
public  class AllRules implements java.io.Serializable {
    public List<Rule> rulesList;

    public AllRules(){

        this.rulesList = new ArrayList<Rule>();

    }

    public void AddToRules(Rule rule){

        this.rulesList.add(rule);

    }

}
