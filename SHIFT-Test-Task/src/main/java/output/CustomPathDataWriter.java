package output;

import java.nio.file.Path;

public interface CustomPathDataWriter<T> {
    void write (T data, Path outputPath);
}
