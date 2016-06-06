package DecisionEngine.Gui;

import DecisionEngine.Engine.File;

import javax.swing.*;

/**
 * Created by Ksysio on 2016-06-05.
 */
public class RulesListModel extends AbstractListModel<String> {
    private File file;

    public RulesListModel(File file) {
        this.file = file;
    }

    @Override
    public int getSize() {
        return file.rules.rulesList.size();
    }

    @Override
    public String getElementAt(int i) {
        return file.rules.rulesList.get(i).toString();
    }

    public void updateView() {
        fireContentsChanged(this, 0, getSize());
    }
}
