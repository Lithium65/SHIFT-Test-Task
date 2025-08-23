package parser;

import repo.RawDataRepo;

import java.util.List;

public interface PersonParser {
    void parsePersons(List<String> lines, RawDataRepo rawDataRepo);
}
