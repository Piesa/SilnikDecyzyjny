import javax.swing.*;


/**
 * Created by Ksysio on 2016-05-30.
 */
public class Main {

    public static void main(String[] args){

        JFrame frame = new JFrame("Forward and Backward Chaining Logical Engine ");
        frame.setContentPane(new MainWindow().ContentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
