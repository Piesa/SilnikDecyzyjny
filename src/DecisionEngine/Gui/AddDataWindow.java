package DecisionEngine.Gui;

import DecisionEngine.Engine.Information;
import DecisionEngine.Engine.File;
import sun.applet.Main;

import javax.swing.*;

/**
 * Created by Ksysio on 2016-06-05.
 */
public class AddDataWindow  {
    public JPanel ContentPanel;
    private JLabel Label1;
    private JTextField nameField;
    private JRadioButton falseRadioButton;
    private JRadioButton trueRadioButton;
    private JRadioButton unknownRadioButton;
    private JButton addButton;
    private JCheckBox parameterCheckBox;
    private JTextField parameterField;
    public Information information;
    private MainWindow mainWindow;
    public int value;

    public AddDataWindow(MainWindow window){

        parameterCheckBox.addChangeListener(actionEvent ->{
            if(parameterCheckBox.isSelected())
                parameterField.setEnabled(true);
            else
                parameterField.setEnabled(false
                );
        });

        mainWindow = window;
        addButton.addActionListener(actionEvent ->{
            if(falseRadioButton.isSelected())
                value = 0;
            else if(trueRadioButton.isSelected())
                value = 1;
            else
                value = 2;
            if(parameterCheckBox.isSelected())
                information = new Information(nameField.getText(), value, parameterField.getText());
            else
                information = new Information(nameField.getText(), value);

            mainWindow.file.data.AddToData(information);
            System.out.println(mainWindow.file.data.dataList.size());
            mainWindow.updateDataView();
        });

    }
}

