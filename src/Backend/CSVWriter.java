package Backend;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

import Frontend.TestBenchmark.TestDigitsOfPi;
import Backend.GetInfo;
public class CSVWriter {
    private String csvPath="Test3.csv";
    private FileWriter writer=null;
    private GetInfo info;
    /*public void createCSV(){
        try{
            File file=new File(csvPath);
            writer=new FileWriter(csvPath,true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            try{
                writer.flush();
                writer.close();
            } catch (IOException e) {
               System.out.println("Couldn't close");
            }
        }
    }
*/
    public CSVWriter(){
        /*info=new GetInfo();
       // info.storeInfo();
        String[] information = info.getInfo();
        try (FileWriter writer = new FileWriter(csvPath)) {
                writer.write(Arrays.toString(information));// Write the CSV line to the file
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
