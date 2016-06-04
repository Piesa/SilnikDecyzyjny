/**
 * Created by Ksysio on 2016-05-30.
 */
public class Rule {

    int howMany; // Przetrzymuje ilość argumentów w danej regule
    int dataId[]; // Przetrzymuje odnośniki do listy danych
    int relation; // Przetrzymuje relację którą związane są dane w regule ( && albo || )

    public Rule(int howmany, int id[], int relation) {
        this.howMany = howmany;
        this.relation = relation;
        int i = 0;
        while (i < howmany){
            this.dataId[i] = id[i];
            i++;
        }
    }

    public int getDataId(int id){

        return dataId[id];

    }

    public int getHowMany(){

        return howMany;

    }

    public int getRelation(){

        return relation;

    }

}
