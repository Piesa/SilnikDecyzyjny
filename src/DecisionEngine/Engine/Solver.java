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
        int maxloops;

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
            } else if (rule.relation == 2) {
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
        mainWindow.updateDataView();
    }

    public int forwardChaining(String searched) {
        int index = 0;
        int checker = 0;
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (searched.equals(mainWindow.file.data.dataList.get(i).Name())) {
                index = i;
                if (mainWindow.file.data.dataList.get(i).value != 2) {
                    return mainWindow.file.data.dataList.get(i).value;
                }
                break;
            }
        }
        int maxloops = 1;
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (mainWindow.file.data.dataList.get(i).value == 2) {
                maxloops++;
            }
        }
        int tmp = 0;
        while (mainWindow.file.data.dataList.get(index).value == 2) {
            if (tmp == mainWindow.file.rules.rulesList.size()) {
                checker++;
                tmp = 0;
            }
            solving(mainWindow.file.rules.rulesList.get(tmp));
            mainWindow.textArea1.append("Solving rule number: \t\t" + (tmp+1) + "\n");
            tmp++;
            if (checker > maxloops) {
                mainWindow.textArea1.append("Cannot find value of searched data\n");
                return 666;
            }
        }
        return mainWindow.file.data.dataList.get(index).value;
    }


    public int backwardChaining(String searched) {
        int dataIndex = 0;
        int ruleIndex = 0;
        for (int i = 0; i < mainWindow.file.rules.rulesList.size(); i++) {
            if (searched.equals(mainWindow.file.rules.rulesList.get(i).dataName3)) {
                System.out.println(mainWindow.file.rules.rulesList.get(i).dataName3);
                System.out.println(searched);
                System.out.println(i);
                ruleIndex = i;
                break;
            }
        }
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (searched.equals(mainWindow.file.data.dataList.get(i).Name())) {
                dataIndex = i;
                if (mainWindow.file.data.dataList.get(dataIndex).value != 2) {
                    return mainWindow.file.data.dataList.get(dataIndex).value;
                }
                break;
            }
        }
        System.out.println(mainWindow.file.rules.rulesList.get(ruleIndex).toString());
        int a = 0;
        int b = 0;
        if (mainWindow.file.rules.rulesList.get(ruleIndex).howMany == 3) {
            if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1) == 2) {
                mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                a = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 1));
                mainWindow.textArea1.append("Found value from deeper rules!\n");
                if (a == 666) {
                    return 666;
                }
                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                    if (a == 0)
                        a = 1;
                    else
                        a = 0;
                }
            } else {
                a = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1);
            }

            if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                if (a == 0)
                    a = 1;
                else
                    a = 0;
            }

            if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 2) == 2) {
                mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                b = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 2));
                mainWindow.textArea1.append("Found value from deeper rules!\n");
                if (b == 666) {
                    return 666;
                }
                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(2)) {
                    if (b == 0)
                        b = 1;
                    else
                        b = 0;
                }
            } else {
                b = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 2);
            }

            if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(2)) {
                if (b == 0)
                    b = 1;
                else
                    b = 0;
            }

            if (mainWindow.file.rules.rulesList.get(ruleIndex).relation == 1) {
                if (!mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 1 && b == 1)
                        return 1;
                    else
                        return 666;
                } else {
                    if (a == 0 || b == 0)
                        return 1;
                    else
                        return 0;
                }
            } else {
                if (!mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 1 || b == 1)
                        return 1;
                    else
                        return 666;
                } else {
                    if (a == 0 && b == 0)
                        return 1;
                    else
                        return 0;
                }
            }
        } else {
            if (mainWindow.file.rules.rulesList.get(ruleIndex).dataName1.equals(mainWindow.file.data.dataList.get(dataIndex).Name())) {
                if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 3) == 2) {
                    mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                    a = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 3));
                    mainWindow.textArea1.append("Found value from deeper rules!\n");
                    if (a == 666) {
                        return 666;
                    }
                    if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                        if (a == 0)
                            a = 1;
                        else
                            a = 0;
                    }
                } else {
                    a = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 3);
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 0)
                        a = 1;
                    else
                        a = 0;
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                    if (a == 0)
                        return 1;
                    else
                        return 0;
                } else {
                    if (a == 1)
                        return 1;
                    else
                        return 0;
                }
            } else {
                if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1) == 2) {
                    mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                    a = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 1));
                    mainWindow.textArea1.append("Found value from deeper rules!\n");
                    if (a == 666) {
                        return 666;
                    }
                    if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                        if (a == 0)
                            a = 1;
                        else
                            a = 0;
                    }
                } else {
                    a = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1);
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                    if (a == 0)
                        a = 1;
                    else
                        a = 0;
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 0)
                        return 1;
                    else
                        return 0;
                } else {
                    if (a == 1)
                        return 1;
                    else
                        return 0;
                }
            }
        }
    }

    private int recsolve(Information information) {
        int ruleIndex = 0;
        for (int i = 0; i < mainWindow.file.rules.rulesList.size(); i++) {
            if (information.Name().equals(mainWindow.file.rules.rulesList.get(i).dataName3)) {
                ruleIndex = i;
                break;
            }
        }
        int a = 0;
        int b = 0;
        if (mainWindow.file.rules.rulesList.get(ruleIndex).howMany == 3) {
            if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1) == 2) {
                mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                a = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 1));
                mainWindow.textArea1.append("Found value from deeper rules!\n");
                if (a == 666) {
                    return 666;
                }
                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                    if (a == 0)
                        a = 1;
                    else
                        a = 0;
                }
            } else {
                a = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1);
            }

            if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                if (a == 0)
                    a = 1;
                else
                    a = 0;
            }

            if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 2) == 2) {
                mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                b = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 2));
                mainWindow.textArea1.append("Found value from deeper rules!\n");
                if (b == 666) {
                    return 666;
                }
                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(2)) {
                    if (b == 0)
                        b = 1;
                    else
                        b = 0;
                }
            } else {
                b = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 2);
            }

            if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(2)) {
                if (b == 0)
                    b = 1;
                else
                    b = 0;
            }

            if (mainWindow.file.rules.rulesList.get(ruleIndex).relation == 1) {
                if (!mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 1 && b == 1)
                        return 1;
                    else
                        return 666;
                } else {
                    if (a == 0 || b == 0)
                        return 1;
                    else
                        return 0;
                }
            } else {
                if (!mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 1 || b == 1)
                        return 1;
                    else
                        return 666;
                } else {
                    if (a == 0 && b == 0)
                        return 1;
                    else
                        return 0;
                }
            }
        } else {
            if (mainWindow.file.rules.rulesList.get(ruleIndex).dataName1.equals(information.Name())) {
                if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 3) == 2) {
                    mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                    a = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 3));
                    mainWindow.textArea1.append("Found value from deeper rules!\n");
                    if (a == 666) {
                        return 666;
                    }
                    if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                        if (a == 0)
                            a = 1;
                        else
                            a = 0;
                    }
                } else {
                    a = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 3);
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 0)
                        a = 1;
                    else
                        a = 0;
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                    if (a == 0)
                        return 1;
                    else
                        return 0;
                } else {
                    if (a == 1)
                        return 1;
                    else
                        return 0;
                }
            } else {
                if (getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1) == 2) {
                    mainWindow.textArea1.append("Unknown value, going one step backwards...\n");
                    a = recsolve(getInformation(mainWindow.file.rules.rulesList.get(ruleIndex), 1));
                    mainWindow.textArea1.append("Found value from deeper rules!\n");
                    if (a == 666) {
                        return 666;
                    }
                    if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                        if (a == 0)
                            a = 1;
                        else
                            a = 0;
                    }
                } else {
                    a = getInformationValue(mainWindow.file.rules.rulesList.get(ruleIndex), 1);
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(1)) {
                    if (a == 0)
                        a = 1;
                    else
                        a = 0;
                }

                if (mainWindow.file.rules.rulesList.get(ruleIndex).isNegated(3)) {
                    if (a == 0)
                        return 1;
                    else
                        return 0;
                } else {
                    if (a == 1)
                        return 1;
                    else
                        return 0;
                }
            }
        }
    }

    public Information getInformation(Rule rule, int x) {
        for (int i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            if (rule.getdataName(x).equals(mainWindow.file.data.dataList.get(i).Name()))
                return mainWindow.file.data.dataList.get(i);
        }
        return mainWindow.file.data.dataList.get(0);
    }

    private int getInformationValue(Rule rule, int x) {
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
