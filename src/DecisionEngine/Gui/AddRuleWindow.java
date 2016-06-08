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
    public Rule rule;
    private MainWindow mainWindow;

    public AddRuleWindow(MainWindow window) {

        mainWindow = window;

        relationBox.addItem("v");
        relationBox.addItem("^");

        int i;
        for (i = 0; i < mainWindow.file.data.dataList.size(); i++) {
            value33.addItem(mainWindow.file.data.dataList.get(i).Name());
            value32.addItem(mainWindow.file.data.dataList.get(i).Name());
            value31.addItem(mainWindow.file.data.dataList.get(i).Name());
            value21.addItem(mainWindow.file.data.dataList.get(i).Name());
            value22.addItem(mainWindow.file.data.dataList.get(i).Name());
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
           if(twoVariablesRadioButton.isSelected())
               rule = new Rule(2, value21.getSelectedItem().toString(), value22.getSelectedItem().toString(), negation21.isSelected(), negation22.isSelected());
           else {
               if (relationBox.getSelectedItem() == "^") {
                   rule = new Rule(3, value31.getSelectedItem().toString(), value32.getSelectedItem().toString(), value33.getSelectedItem().toString(), 1, negation31.isSelected(), negation32.isSelected(), negation33.isSelected());
               }
               else
                   rule = new Rule(3, value31.getSelectedItem().toString(), value32.getSelectedItem().toString(), value33.getSelectedItem().toString(), 2, negation31.isSelected(), negation32.isSelected(), negation33.isSelected());
           }
            mainWindow.file.rules.AddToRules(rule);
            System.out.println(mainWindow.file.rules.rulesList.size());
            mainWindow.updateRulesView();
        });
    }
}
