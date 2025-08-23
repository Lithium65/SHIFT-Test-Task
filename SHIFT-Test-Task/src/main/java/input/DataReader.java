package input;

import exception.StaffIsNotInitialized;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<String> readDirectory () throws IOException, StaffIsNotInitialized;
}
