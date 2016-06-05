import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksysio on 2016-05-30.
 */
public  class Data implements java.io.Serializable {
    public List<Information> data;


    public Data(){

        this.data = new ArrayList<Information>();

    }

    public void AddToData(Information information){

        this.data.add(information);

    }

    public void RemoveFromData(int id){

        this.data.remove(id);

    }

}
