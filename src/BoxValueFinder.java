
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BoxValueFinder {
    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void formatProfileId(String profileLink){
        try{
            int[] value;
                int index = profileLink.indexOf("https://leetcode.com/");
                if(index == -1){
                    String url = "https://leetcode.com/" + profileLink + "/";
                    String lineValue = lineFinder(url);
                    value = valueFinder(lineValue);
                }
                else{
                    String lineValue = lineFinder(profileLink);
                    value = valueFinder(lineValue);
                }
            displayValue(value);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String lineFinder(String urll){
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

                while ((line = reader.readLine()) != null) {
                    //bw.write(line);
                    if(line.contains("{\"acSubmissionNum"))
                    {
                        return line;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public void displayValue(int [] value){
        try{
            String format = String.format("Total : %s  Easy : %s  Medium : %s  Hard : %s",value[0],value[1],value[2],value[3]);

            setResult(format);
        }catch(Exception e){
            e.printStackTrace();
        }
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
        int daa = 100;
        System.out.println(daa);
        return count;
    }
}