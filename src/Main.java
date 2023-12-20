
import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public Main(){
        Frame frame = new Frame("leetcode solved count finder");
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setVisible(true);

        Label label = new Label("Enter profile id ");
        label.setBounds(100,100,100,30);
        TextField tf = new TextField();
        tf.setBounds(200,100,100,30);

        frame.add(tf);
        frame.add(label);
    }
    public static void main(String[] args) throws Exception {
        new Main();
        BoxValueFinder bvf = new BoxValueFinder();
        bvf.fileIterator();
        System.out.println("Process Accomplished");
        System.out.println("check output.txt file for result");
    }
}