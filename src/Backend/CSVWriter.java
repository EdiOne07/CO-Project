package Backend;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public HashMap<String, Integer> readCSVToHashMap(String filePath) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the CSV header (optional, skip if not needed)
            //String header = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Split the CSV line into values

                if (data.length >= 2) {
                    String key = data[0].trim();
                    Integer value = Integer.valueOf(data[1].trim());
                    hashMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hashMap;
    }

    public void writeHashMapToCSV(HashMap<String, Integer> hashMap, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            // Write the CSV header
            //writer.write("Key,Value\n");

            // Write each entry in the HashMap to the CSV file
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                // Write the key-value pair as a CSV line
                writer.write(key + "," + value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
