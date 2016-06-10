package DecisionEngine.Gui;

import DecisionEngine.Engine.File;
import DecisionEngine.Engine.Information;
import DecisionEngine.Engine.Rule;
import DecisionEngine.Engine.Solver;
import DecisionEngine.Gui.AddDataWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * Created by Ksysio on 2016-05-31.
 */
public class MainWindow {
    private DataListModel dataListModel;
    private RulesListModel rulesListModel;
    public JPanel ContentPanel;
    private JList DataList;
    private JList RulesList;
    public JTextArea textArea1;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JButton addDataButton;
    private JButton addRuleButton;
    private JButton solveButton;
    private JRadioButton backwardRadioButton;
    private JRadioButton forwardRadioButton;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JButton removeSelectedButton;
    private JPanel DataTab;
    private JTabbedPane Tab;
    private JPanel RulesTab;
    private JPanel ChainingTab;
    public JComboBox searchedBox;
    private JRadioButton unknownRadioButton;
    private JButton saveRulesButton;
    private JButton saveRulesAsButton;
    public File file;
    private String currentFileName = null;
    final JFileChooser fileChooser;
    public Solver solver;

    public MainWindow() {

        file = new File();

        solver = new Solver(this);

        dataListModel = new DataListModel(file);
        DataList.setModel(dataListModel);
        updateDataView();

        rulesListModel = new RulesListModel(file);
        RulesList.setModel(rulesListModel);
        updateRulesView();

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Logical Engine File", "lef"));

        newButton.addActionListener(actionEvent -> {
            file = new File();

            dataListModel = new DataListModel(file);
            DataList.setModel(dataListModel);
            updateDataView();

            rulesListModel = new RulesListModel(file);
            RulesList.setModel(rulesListModel);
            updateRulesView();

            currentFileName = null;

            searchedBox.removeAllItems();
        });


        addDataButton.addActionListener(actionEvent -> {
            JFrame buttonFrame = new JFrame("Forward and Backward Chaining Logical Engine ");
            buttonFrame.setContentPane(new AddDataWindow(this).ContentPanel);
            buttonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            buttonFrame.pack();
            buttonFrame.setVisible(true);
            updateDataView();
        });

        addRuleButton.addActionListener(actionEvent -> {
            JFrame rulesFrame = new JFrame("Forward and Backward Chaining Logical Engine ");
            rulesFrame.setContentPane(new AddRuleWindow(this).ContentPanel);
            rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rulesFrame.pack();
            rulesFrame.setVisible(true);
            updateRulesView();

        });

        openButton.addActionListener(actionEvent -> {
            try {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!filename.endsWith(".lef"))
                        filename += ".lef";

                    setCurrentFileName(filename);
                } else {
                    return;
                }

                FileInputStream is = new FileInputStream(currentFileName);
                file = File.deserialize(is);

                updateDataView();
                updateRulesView();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Can't load file: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateDataView();
            updateRulesView();
            dataListModel = new DataListModel(file);
            DataList.setModel(dataListModel);
            updateDataView();

            rulesListModel = new RulesListModel(file);
            RulesList.setModel(rulesListModel);
            for (int j = 0; j < file.data.dataList.size(); j++) {
                searchedBox.addItem(file.data.dataList.get(j).Name());
            }
            updateRulesView();
        });

        saveButton.addActionListener(actionEvent -> {
            try {
                save(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Can't save file: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveAsButton.addActionListener(actionEvent -> {
            try {
                save(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Can't save file: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        solveButton.addActionListener(actionEvent -> {
            int value;
            if (forwardRadioButton.isSelected()) {
                value = solver.forwardChaining(searchedBox.getSelectedItem().toString());
                if (value == 1) {
                    trueRadioButton.setSelected(true);
                }
                if (value == 0) {
                    falseRadioButton.setSelected(true);
                }
                if (value == 666) {
                    unknownRadioButton.setSelected(true);
                }
            }
        });


        removeSelectedButton.addActionListener(actionEvent -> {
            if (DataTab.isVisible()) {
                try {
                    Information information = file.data.dataList.get(DataList.getSelectedIndex());
                    file.data.dataList.remove(information);
                    updateDataView();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Can't remove data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                searchedBox.removeItemAt(DataList.getSelectedIndex());
            }
            if (RulesTab.isVisible()) {
                try {
                    Rule rule = file.rules.rulesList.get(RulesList.getSelectedIndex());
                    file.rules.rulesList.remove(rule);
                    updateRulesView();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Can't remove rule.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void save(boolean saveAs) throws IOException {
        if (currentFileName == null || saveAs) {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();

                if (!filename.endsWith(".lef"))
                    filename += ".lef";

                setCurrentFileName(filename);
            } else {
                return;
            }
        }

        FileOutputStream is = new FileOutputStream(currentFileName);
        file.serialize(is);
    }


    private void setCurrentFileName(String filename) {
        currentFileName = filename;
    }

    public void updateDataView() {
        dataListModel.updateView();
    }

    public void updateRulesView() {
        rulesListModel.updateView();
    }

}

