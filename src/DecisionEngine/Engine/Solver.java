package DecisionEngine.Engine;

import DecisionEngine.Gui.MainWindow;

import javax.swing.*;

/**
 * Created by Ksysio on 2016-05-30.
 */
public class Solver {

    private MainWindow mainWindow;

    public Solver(MainWindow mainWindow) {

        this.mainWindow = mainWindow;

    }

    public void solving(Rule rule) {
        if (rule.howMany == 2) {
            if ((getInformationValue(rule, 3) != 2) && (getInformationValue(rule, 1) == 2)) {
                setValue(rule, 1, getInformationValue(rule, 3));
            } else if ((getInformationValue(rule, 1) != 2) && (getInformationValue(rule, 3) == 2)) {
                setValue(rule, 3, getInformationValue(rule, 1));
            }
        } else {
            if (rule.relation == 1) {
                if ((getInformationValue(rule, 2) != 2) && (getInformationValue(rule, 1) != 2) && (getInformationValue(rule, 3) == 2)) {
                    if ((getInformationValue(rule, 2) == 1) && (getInformationValue(rule, 1) == 1)) {
                        setValue(rule, 3, 1);
                    }
                } else if ((getInformationValue(rule, 2) == 1) && (getInformationValue(rule, 1) != 2) && (getInformationValue(rule, 3) == 0)) {
                    setValue(rule, 2, 0);
                } else if ((getInformationValue(rule, 2) != 2) && (getInformationValue(rule, 1) == 1) && (getInformationValue(rule, 3) == 0)) {
                    setValue(rule, 1, 0);
                }
            } else if(rule.relation == 2) {
                if ((getInformationValue(rule, 2) != 2) && (getInformationValue(rule, 1) != 2) && (getInformationValue(rule, 3) == 2)) {
                    if ((getInformationValue(rule, 2) == 1) || (getInformationValue(rule, 1) == 1)) {
                        setValue(rule, 3, 1);
                    }
                } else if ((getInformationValue(rule, 2) == 0) && (getInformationValue(rule, 1) != 2) && (getInformationValue(rule, 3) == 0)) {
                    setValue(rule, 2, 0);
                } else if ((getInformationValue(rule, 2) != 2) && (getInformationValue(rule, 1) == 0) && (getInformationValue(rule, 3) == 0)) {
                    setValue(rule, 1, 0);
                }
            }
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
                    if (mainWindow.file.data.dataList.get(i).value == 1)
                        return 0;
                    else if (mainWindow.file.data.dataList.get(i).value == 0)
                        return 1;
                    else /*if(mainWindow.file.data.dataList.get(i).value == 2)
                        return -2;
                    else if(mainWindow.file.data.dataList.get(i).value == -2)*/
                        return 2;
                } else
                    return mainWindow.file.data.dataList.get(i).value;
            }
        }
        return 666;
    }

    public void setValue(Rule rule, int x, int value) {
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (rule.getdataName(x).equals(mainWindow.file.data.dataList.get(i).Name())) {
                if (!rule.isNegated(x)) {
                    mainWindow.file.data.dataList.get(i).setValue(value);
                } else {
                    if (value == 1)
                        mainWindow.file.data.dataList.get(i).setValue(0);
                    else
                        mainWindow.file.data.dataList.get(i).setValue(1);
                }
                mainWindow.updateDataView();
                break;
            }
        }

    }
}
