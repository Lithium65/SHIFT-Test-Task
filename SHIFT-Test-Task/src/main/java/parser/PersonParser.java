package parser;

import repo.RawDataRepo;

import java.util.List;

public interface PersonParser {
    void parsePerson (List<String> lines, RawDataRepo rawDataRepo);
}
