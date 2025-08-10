package reader;

import repo.DataRepoImpl;

import java.io.IOException;
import java.nio.file.Path;

public interface ReaderImpl {
    void readDirectory (Path directory, DataRepoImpl staffRepo) throws IOException;
}
