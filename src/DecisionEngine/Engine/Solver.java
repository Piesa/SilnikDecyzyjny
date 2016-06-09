package DecisionEngine.Engine;

import DecisionEngine.Gui.MainWindow;
/**
 * Created by Ksysio on 2016-05-30.
 */
public class Solver {

    private MainWindow mainWindow;

    public Solver(MainWindow mainWindow) {

        this.mainWindow = mainWindow;

    }

    public void solving(Rule rule) {
        if(rule.howMany == 2) {

        }

    }

    public boolean forwardChaining() {
        return true;
    }

    public boolean backwardChaining() {
        return true;
    }

    public Information getInformation(Rule rule, int x) {
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (rule.getdataName(x).equals(mainWindow.file.data.dataList.get(i).Name()))
                return mainWindow.file.data.dataList.get(i);
        }
        return mainWindow.file.data.dataList.get(0);
    }

    public int getInformationValue(Rule rule, int x) {
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (rule.getdataName(x).equals(mainWindow.file.data.dataList.get(i).Name())) {
                if (rule.isNegated(x)) {
                    if (mainWindow.file.data.dataList.get(x).value == 1)
                        return 0;
                    else if (mainWindow.file.data.dataList.get(x).value == 0)
                        return 1;
                    else /*if(mainWindow.file.data.dataList.get(x).value == 2)
                        return -2;
                    else if(mainWindow.file.data.dataList.get(x).value == -2)*/
                        return 2;
                } else
                    return mainWindow.file.data.dataList.get(x).value;
            }
        }
        return 800;
    }

    public void setValue(Rule rule, int x, int value) {
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (rule.getdataName(x).equals(mainWindow.file.data.dataList.get(i).Name())) {
                mainWindow.file.data.dataList.get(i).setValue(value);
                break;
            }
        }

    }
}
