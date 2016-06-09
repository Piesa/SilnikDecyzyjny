package DecisionEngine.Engine;

import java.io.*;

/**
 * Created by Ksysio on 2016-06-05.
 */
public class File implements java.io.Serializable {
    public AllRules rules;
    public Data data;

    public File() {
        data = new Data();
        rules = new AllRules();
    }

    public File(Data data, AllRules rules) {
        this.data = data;
        this.rules = rules;
    }

    public void serialize(OutputStream os) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }

    public static File deserialize(InputStream is) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(is);
        File obj = (File) objectInputStream.readObject();
        objectInputStream.close();
        return obj;
    }
}
