package Frontend.Logger;



import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements ILog {

    private PrintWriter pw;

    public FileLogger(String fullPath) {

        try {
            BufferedWriter logFile = new BufferedWriter(new FileWriter(
                    fullPath, false));
            pw = new PrintWriter(logFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void write(String string) {
        System.out.println("started writing");

        pw.println(string);
        System.out.println("finished writing");

    }

    @Override
    public void write(long value) {
        pw.println(String.valueOf(value));
    }

    @Override
    public void write(Object... values) {
        System.out.println("started writing");
        String s = "";
        for (Object o : values)
            s += o.toString() + " ";
        pw.println(s);
        System.out.println("finished writing");

    }



    @Override
    public void close() {
        if (pw != null)
            pw.close();
    }
}
