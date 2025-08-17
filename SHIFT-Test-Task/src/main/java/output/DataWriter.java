package output;

import java.io.IOException;

public interface DataWriter<T> {
    void write (T data) throws IOException;
}
