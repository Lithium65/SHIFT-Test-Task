package input;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface DataReader {
    List<String> readDirectory (Path directory) throws IOException;
}
