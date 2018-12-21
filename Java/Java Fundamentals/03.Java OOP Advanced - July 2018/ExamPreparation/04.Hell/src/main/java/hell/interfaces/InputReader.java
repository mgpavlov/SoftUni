package hell.interfaces;

import java.io.IOException;

/**
 *  This is the interface for the InputReader
 *  @method String readLine() - a method which is made to return a read line from the corresponding Stream.
 */
public interface InputReader {
    String readLine() throws IOException;
}