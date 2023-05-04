package Backend;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVWriter {
    public void CreateCSV(){
        String csvfile="test.csv";
        FileWriter writer=null;
        try{
            File file=new File(csvfile);
            writer=new FileWriter(csvfile,true);
            if(file.length()==0){
                    writer.append("Hazt");
                    writer.append(",");
                    writer.append("John");
                    writer.append(",");
                    writer.append("Chel");
                    writer.append(",");
                    writer.append("1");
                    writer.append(",");
            }
            writer.append("\n");
            for(int i=0;i<10;i++){
                writer.append("Dorian");
                writer.append(",");
                writer.append("John");
                writer.append(",");
                writer.append("Chel");
                writer.append((char) i);
            }
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
    public List<Elements> readCSV(String filename) {
        BufferedReader reader = null;
        List<Elements> info=new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String cpu = parts[0];
                String hdd = parts[1];
                String pc=parts[2];
                int score= Integer.parseInt(parts[3]);
                System.out.println("CPU : " + cpu);
                System.out.println("Hdd : " + hdd);
                Elements element=new Elements(cpu,hdd,pc,score);
                info.add(element);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Couldn't close");
            }
        }
        return info;
    }
}
