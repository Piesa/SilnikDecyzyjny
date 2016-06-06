package DecisionEngine.Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksysio on 2016-05-30.
 */
public  class Data implements java.io.Serializable {
    public List<Information> dataList;


    public Data(){
        this.dataList = new ArrayList<Information>();
    }

    public void AddToData(Information information){
        this.dataList.add(information);
    }

    public void RemoveFromData(int id){
        this.dataList.remove(id);
    }

}
