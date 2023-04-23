package Backend;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class CSVWriter {
    public static void main(String[] args){
        String csvfile="test.csv";
        FileWriter writer=null;
        try{
            File file=new File(csvfile);
            writer=new FileWriter(csvfile,true);
            if(file.length()==0){
                    writer.append("Hazt");
                    writer.append(",");
                    writer.append("John");
            }
            writer.append("\n");
            for(int i=0;i<10;i++){
                writer.append("Dorian");
                writer.append(",");
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
}
