package Frontend.Logger;

public interface ILog {

    /**
     * Writes a given string to implementing output stream
     *
     * @param string
     */
    public void write(String string);

    /**
     * Writes a given long value to implementing output stream
     *
     * @param value
     */
    public void write(long value);

    /**
     * Writes an array of objects to the implementing output stream. <br>
     * Calls each object's toString method and delimits them with " ".
     *
     * @param values
     */
    public void write(Object... values);



    public void close();

}
