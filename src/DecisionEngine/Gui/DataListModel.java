package DecisionEngine.Gui;

import DecisionEngine.Engine.File;

import javax.swing.*;

/**
 * Created by Ksysio on 2016-06-05.
 */
public class DataListModel extends AbstractListModel<String> {

    private File file;

    public DataListModel(File file) {
        this.file = file;
    }

    @Override
    public int getSize() {
        return file.data.dataList.size();
    }

    @Override
    public String getElementAt(int i) {
        return file.data.dataList.get(i).toString();
    }

    public void updateView() {
        fireContentsChanged(this, 0, getSize());
    }
}
