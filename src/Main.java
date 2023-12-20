// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public Main(){

    }
    public static void main(String[] args) throws Exception {
        BoxValueFinder bvf = new BoxValueFinder();
        bvf.fileIterator();
        System.out.println("Process Accomplished");
        System.out.println("check output.txt file for result");
    }
}