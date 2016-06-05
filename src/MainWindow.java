import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Ksysio on 2016-05-31.
 */
public class MainWindow {
    public JPanel ContentPanel;
        private JList DataList;
    private JList RulesList;
    private JTextArea textArea1;
    private JButton newButton;
    private JButton openButton;
    private JButton saveButton;
    private JButton saveAsButton;
    private JButton addDataButton;
    private JButton addRuleButton;
    private JButton solveButton;
    private JRadioButton backwardRadioButton;
    private JRadioButton forwardRadioButton;
    private JTextField textField1;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JButton removeSelectedButton;
    private JButton saveRulesButton;
    private JButton saveRulesAsButton;
    public File file;
    private String currentFileName = null;
    final JFileChooser fileChooser;

    public MainWindow() {

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Logical Engine File", "lef"));

        newButton.addActionListener(actionEvent -> {

            file = new File();

        });


        addDataButton.addActionListener(actionEvent ->{
            JFrame buttonFrame = new JFrame("Forward and Backward Chaining Logical Engine ");
            buttonFrame.setContentPane(new AddDataWindow().ContentPanel);
            buttonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            buttonFrame.pack();
            buttonFrame.setVisible(true);
        });

        addRuleButton.addActionListener(actionEvent ->{
            JFrame rulesFrame = new JFrame("Forward and Backward Chaining Logical Engine ");
            rulesFrame.setContentPane(new AddRuleWindow().ContentPanel);
            rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            rulesFrame.pack();
            rulesFrame.setVisible(true);
        });

        openButton.addActionListener(actionEvent -> {
            try {
                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getAbsolutePath();

                    if(!filename.endsWith(".lef"))
                        filename += ".lef";

                    setCurrentFileName(filename);
                } else {
                    return;
                }

                FileInputStream is = new FileInputStream(currentFileName);
                file = File.deserialize(is);

                //updateView();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Can't load file: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    }
    private void save(boolean saveAs) throws IOException {
        if (currentFileName == null || saveAs) {
            if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filename = fileChooser.getSelectedFile().getAbsolutePath();

                if(!filename.endsWith(".lef"))
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


}

