
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public Main(){
        Frame frame = new Frame("leetcode solved count finder");
        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setVisible(true);

        Label label = new Label("Enter profile id ");
        label.setBounds(100,170,100,30);
        TextField tf = new TextField();
        tf.setBounds(200,170,220,30);

        Button submit = new Button("Submit");
        submit.setBounds(150,210,100,30);


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxValueFinder bv = new BoxValueFinder();
                bv.formatProfileId(tf.getText());
                System.out.println(bv.getResult());
                Label result = new Label();
                result.setBounds(120,250,250,25);
                frame.add(result);
                result.setText(bv.getResult());
            }
        });


        frame.add(tf);
        frame.add(label);
        frame.add(submit);
    }
    public static void main(String[] args) throws Exception {
        new Main();
        //BoxValueFinder bvf = new BoxValueFinder();

        System.out.println("Process Accomplished");
        System.out.println("check output.txt file for result");
    }
}