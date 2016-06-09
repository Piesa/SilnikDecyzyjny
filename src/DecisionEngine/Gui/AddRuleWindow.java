package DecisionEngine.Gui;

import DecisionEngine.Engine.Rule;

import javax.swing.*;

/**
 * Created by Ksysio on 2016-06-05.
 */
public class AddRuleWindow {
    public JPanel ContentPanel;
    private JComboBox value33;
    private JComboBox value32;
    private JComboBox value31;
    private JCheckBox negation31;
    private JCheckBox negation32;
    private JCheckBox negation33;
    private JButton addButton;
    private JComboBox relationBox;
    private JComboBox value21;
    private JComboBox value22;
    private JCheckBox negation21;
    private JCheckBox negation22;
    private JRadioButton threeVariablesRadioButton;
    private JRadioButton twoVariablesRadioButton;
    private Rule rule;
    private Rule rule2;
    private MainWindow mainWindow;
    private boolean error;

    public AddRuleWindow(MainWindow window) {

        mainWindow = window;

        relationBox.addItem("v");
        relationBox.addItem("^");

        int j;
        for (j = 0; j < mainWindow.file.data.dataList.size(); j++) {
            value33.addItem(mainWindow.file.data.dataList.get(j).Name());
            value32.addItem(mainWindow.file.data.dataList.get(j).Name());
            value31.addItem(mainWindow.file.data.dataList.get(j).Name());
            value21.addItem(mainWindow.file.data.dataList.get(j).Name());
            value22.addItem(mainWindow.file.data.dataList.get(j).Name());
        }

        twoVariablesRadioButton.addActionListener(actionEvent -> {
            value31.setEnabled(false);
            value32.setEnabled(false);
            value33.setEnabled(false);
            value21.setEnabled(true);
            value22.setEnabled(true);
            relationBox.setEnabled(false);
            negation31.setEnabled(false);
            negation32.setEnabled(false);
            negation33.setEnabled(false);
            negation21.setEnabled(true);
            negation22.setEnabled(true);
        });


        threeVariablesRadioButton.addActionListener(actionEvent -> {
            value31.setEnabled(true);
            value32.setEnabled(true);
            value33.setEnabled(true);
            value21.setEnabled(false);
            value22.setEnabled(false);
            relationBox.setEnabled(true);
            negation31.setEnabled(true);
            negation32.setEnabled(true);
            negation33.setEnabled(true);
            negation21.setEnabled(false);
            negation22.setEnabled(false);
        });



        addButton.addActionListener(AddEvent ->{
            error = false;
            if(twoVariablesRadioButton.isSelected()) {
                rule = new Rule(2, value21.getSelectedItem().toString(), value22.getSelectedItem().toString(), negation21.isSelected(), negation22.isSelected());
                rule2 = new Rule(2, value22.getSelectedItem().toString(), value21.getSelectedItem().toString(), negation22.isSelected(), negation21.isSelected());
                if (value21.getSelectedItem().toString().equals(value22.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Can't add rule", "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
                for(int i = 0; i < mainWindow.file.rules.rulesList.size(); i++) {
                    if (rule.toString().equals(mainWindow.file.rules.rulesList.get(i).toString()) || rule2.toString().equals(mainWindow.file.rules.rulesList.get(i).toString())) {
                       JOptionPane.showMessageDialog(null, "Rule already exists", "Error", JOptionPane.ERROR_MESSAGE);
                       error = true;
                       break;
                    }
                }
            }
            else {
                if (relationBox.getSelectedItem() == "^") {
                    rule = new Rule(3, value31.getSelectedItem().toString(), value32.getSelectedItem().toString(), value33.getSelectedItem().toString(), 1, negation31.isSelected(), negation32.isSelected(), negation33.isSelected());
                    rule2 = new Rule(3, value32.getSelectedItem().toString(), value31.getSelectedItem().toString(), value33.getSelectedItem().toString(), 1, negation32.isSelected(), negation31.isSelected(), negation33.isSelected());

                    if (value33.getSelectedItem().toString().equals(value32.getSelectedItem().toString()) || value32.getSelectedItem().toString().equals(value31.getSelectedItem().toString()) || value33.getSelectedItem().toString().equals(value31.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "Can't add rule", "Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }

                    for(int i = 0; i < mainWindow.file.rules.rulesList.size(); i++) {
                        if (rule.toString().equals(mainWindow.file.rules.rulesList.get(i).toString()) || rule2.toString().equals(mainWindow.file.rules.rulesList.get(i).toString())) {
                            JOptionPane.showMessageDialog(null, "Rule already exists", "Error", JOptionPane.ERROR_MESSAGE);
                            error = true;
                            break;
                        }
                    }
                }
                else {
                    rule = new Rule(3, value31.getSelectedItem().toString(), value32.getSelectedItem().toString(), value33.getSelectedItem().toString(), 2, negation31.isSelected(), negation32.isSelected(), negation33.isSelected());
                    rule2 = new Rule(3, value32.getSelectedItem().toString(), value31.getSelectedItem().toString(), value33.getSelectedItem().toString(), 2, negation32.isSelected(), negation31.isSelected(), negation33.isSelected());

                    if (value33.getSelectedItem().toString().equals(value32.getSelectedItem().toString()) || value32.getSelectedItem().toString().equals(value31.getSelectedItem().toString()) || value33.getSelectedItem().toString().equals(value31.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(null, "Can't add rule", "Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }

                    for (int i = 0; i < mainWindow.file.rules.rulesList.size(); i++) {
                        if (rule.toString().equals(mainWindow.file.rules.rulesList.get(i).toString()) || rule2.toString().equals(mainWindow.file.rules.rulesList.get(i).toString())) {
                            JOptionPane.showMessageDialog(null, "Rule already exists", "Error", JOptionPane.ERROR_MESSAGE);
                            error = true;
                            break;
                        }
                    }
                }
            }
            System.out.print(error);
            if(!error)
            mainWindow.file.rules.AddToRules(rule);
            mainWindow.updateRulesView();
        });
    }
}
