package reader;

import repo.RawDataRepo;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {
    void readDirectory (Path directory, RawDataRepo staffRepo) throws IOException;
}
