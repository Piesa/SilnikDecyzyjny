import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Ksysio on 2016-05-31.
 */
public class MainWindow {
    public JPanel ContentPanel;
    private JList list1;
    private JList list2;
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

    final JFileChooser fileChooserData;
    final JFileChooser fileChooserRules;

    public MainWindow(){

        fileChooserData = new JFileChooser();
        fileChooserData.setFileFilter(new FileNameExtensionFilter("Logical Engine Data File", "led"));

        fileChooserRules = new JFileChooser();
        fileChooserRules.setFileFilter(new FileNameExtensionFilter("Logical Engine Rules File", "ler"));

        newButton.addActionListener(actionEvent -> {
            JFrame frame = new JFrame("Logical Engine Adding Rules and Data");
            frame.setContentPane(new AddWindow().AddPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });





    }

}
