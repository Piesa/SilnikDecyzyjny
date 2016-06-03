/**
 * Created by Ksysio on 2016-05-30.
 */
public class Rule {

    int Howmany; // Przetrzymuje ilość argumentów w danej regule
    int DataId[]; // Przetrzymuje odnośniki do listy danych
    int Relation; // Przetrzymuje relację którą związane są dane w regule ( && albo || )

    public Rule(int howmany, int id[], int relation) {
        this.Howmany = howmany;
        this.Relation = relation;
        int i = 0;
        while (i < howmany){
            this.DataId[i] = id[i];
            i++;
        }
    }

    public int getDataId(int id){

        return DataId[id];

    }

    public int getHowmany(){

        return Howmany;

    }

    public int getRelation(){

        return Relation;

    }

}
