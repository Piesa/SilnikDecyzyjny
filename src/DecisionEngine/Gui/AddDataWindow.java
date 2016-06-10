package DecisionEngine.Gui;

import DecisionEngine.Engine.Information;
import DecisionEngine.Engine.File;
import sun.applet.Main;

import javax.swing.*;

/**
 * Created by Ksysio on 2016-06-05.
 */
public class AddDataWindow {
    public JPanel ContentPanel;
    private JLabel Label1;
    private JTextField nameField;
    private JRadioButton falseRadioButton;
    private JRadioButton trueRadioButton;
    private JRadioButton unknownRadioButton;
    private JButton addButton;
    private JCheckBox parameterCheckBox;
    private JTextField parameterField;
    private Information information;
    private MainWindow mainWindow;
    private int value;
    private boolean error;


    public AddDataWindow(MainWindow window) {


        parameterCheckBox.addChangeListener(actionEvent -> {
            if (parameterCheckBox.isSelected())
                parameterField.setEnabled(true);
            else
                parameterField.setEnabled(false
                );
        });
        mainWindow = window;
        addButton.addActionListener(actionEvent -> {
            error = false;
            if (falseRadioButton.isSelected())
                value = 0;
            else if (trueRadioButton.isSelected())
                value = 1;
            else
                value = 2;
            if (parameterCheckBox.isSelected()) {
                if (parameterField.getText().equals("") || nameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "No data to add", "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
                information = new Information(nameField.getText(), value, parameterField.getText());
                for (int i = 0; i < mainWindow.file.data.dataList.size(); i++)
                    if (information.Name().equals(mainWindow.file.data.dataList.get(i).Name())) {
                        JOptionPane.showMessageDialog(null, "Can't add data", "Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
            } else {
                if (nameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "No data to add", "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
                information = new Information(nameField.getText(), value);
                for (int i = 0; i < mainWindow.file.data.dataList.size(); i++)
                    if (information.Name().equals(mainWindow.file.data.dataList.get(i).Name())) {
                        JOptionPane.showMessageDialog(null, "Can't add data", "Error", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
            }
            if (!error)
                mainWindow.file.data.AddToData(information);
            mainWindow.searchedBox.addItem(information.Name());
            mainWindow.updateDataView();
        });

    }
}

