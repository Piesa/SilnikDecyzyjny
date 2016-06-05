import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksysio on 2016-05-30.
 */
public class AllRules {
    public List<Rule> rules;

    public AllRules(){

        this.rules = new ArrayList<Rule>();

    }

    public void AddToRules(Rule rule){

        this.rules.add(rule);

    }

}
