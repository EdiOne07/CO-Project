package Frontend.Graph;
import java.io.*;


public class Scanner_CSV  {
    String line="";
    String splitBy=";";
    String[] computer_data;
    String computer_name;
    String computer_CPU;
    String computer_Score;
    public void scan () throws Exception {
        //parsing a CSV file into Scanner class constructor
        BufferedReader sc = new BufferedReader(new FileReader("D:\\Faculty\\CO-Project\\Test2.csv"));
        while ((line=sc.readLine())!= null)
        {
            computer_data =line.split(splitBy);
            computer_CPU = computer_data[1];
            computer_Score = computer_data[2];
            computer_name=computer_data[0];
          //  System.out.println("Computer: " + computer_data[0] +", CPU model: " + computer_data[1] + ", Score: "+ computer_data[2]);  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner
    }
}
