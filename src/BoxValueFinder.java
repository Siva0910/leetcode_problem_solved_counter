import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BoxValueFinder {
    int sno = 1;

    public void fileIterator(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            FileOutputStream fos = new FileOutputStream("Output.txt");
            String format = String.format("%s:%s:%s:%s:%s:%s:%s","S.no","Name","Dept","Total","Easy","Medium","Hard");
            fos.write(format.getBytes());
            String row = "";
            while((row = br.readLine()) != null){
                int fromIndex = row.indexOf("https://leetcode.com/");
                if(fromIndex == -1){
                    fos.write(("\nInvalid link is provided").getBytes());
                    continue;
                }
                int end = row.length();
                String profileLink = row.substring(fromIndex,end);
                String lineValue = lineFinder(profileLink,fos);

                int []values = valueFinder(lineValue);
                storeValueInFile(row ,fromIndex ,values ,fos);
            }
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void storeValueInFile(String row,int end,int [] value, FileOutputStream fos){
        try{
            String name = row.substring(0,end-3);
            String department = row.substring(end-3,end);
            String format = String.format("\n%s:%s:%s:%s:%s:%s:%s",sno,name,department,value[0],value[1],value[2],value[3]);
            fos.write(format.getBytes());
            sno++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String lineFinder(String urll,FileOutputStream fos){
        try{
            URL url = new URL(urll);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set User-Agent header
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Add cookies if necessary
            // connection.setRequestProperty("Cookie", "name=value");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                BufferedWriter bw = new BufferedWriter(new FileWriter("check.txt"));
                while ((line = reader.readLine()) != null) {
                    //bw.write(line);
                    if(line.contains("{\"acSubmissionNum"))
                    {
                        return line;
                       // break;
                    }
                }
            }
        }catch(Exception e){
            try{
                fos.write(("\nInvalid link is provided").getBytes());
            }catch(Exception ignored){

            }
        }
        return "";
    }
    public int[] valueFinder(String str){
        int index = str.indexOf("{\"acSubmissionNum");
        int start = index;
        Stack <Character> st = new Stack<>();
        if(index == -1) return null;
        st.push(str.charAt(index++));
        //index++;
        while(!st.isEmpty()){
            if(str.charAt(index) == '{')
                st.push(str.charAt(index));
            else if (str.charAt(index) == '}')
                st.pop();
            index++;
        }
        int end = index;
        int []count = new int[4];
        String solvedCount = str.substring(start,end);
        int k=0;
        for(int i=0;i<solvedCount.length();i++){
            if(Character.isDigit(solvedCount.charAt(i))){
                int val=0;
                while(Character.isDigit(solvedCount.charAt(i))){
                    val = val * 10 + Integer.parseInt(String.valueOf(solvedCount.charAt(i)));
                    i++;
                }
                count[k++] = val;
            }
        }
        return count;
    }
}