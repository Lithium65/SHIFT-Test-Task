package args;

import args.fields.SortOrder;
import args.fields.SortType;
import args.fields.StatsOutputMethod;
import lombok.Getter;
import picocli.CommandLine;

import java.nio.file.Path;

@Getter
public final class ApplicationArgs {

    @CommandLine.Option(names = {"-s", "--sort"}, description = {"Employee sorting type: by name or by salary"})
    private SortType sortType;

    @CommandLine.Option(names = "--order", description = "Sort order, desc/asc")
    private SortOrder sortOrder;

    @CommandLine.Option(names = "--stat", description = "Enables generation of departmental statistics", defaultValue = "false", arity = "0")
    private Boolean isStatsNeeded;

    @CommandLine.Option(names = {"-o", "--output"}, description = "Method for statistics export", defaultValue = "console")
    private StatsOutputMethod statsOutputMethod;

    @CommandLine.Option(names = "--path", description = "Save path of statistics file")
    private Path outputPath;

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    @CommandLine.Command()
    public void validateArgs() {
        if (sortOrder != null && sortType == null) {
            throw new CommandLine.ParameterException(spec.commandLine(), "--sort must be specified to use --order");
        }
        if (!isStatsNeeded && (statsOutputMethod.equals(StatsOutputMethod.file) || outputPath != null)) {
            throw new CommandLine.ParameterException(spec.commandLine(), "met unknown output parameters");
        }
        if ("file".equalsIgnoreCase(statsOutputMethod.toString()) && outputPath == null) {
            throw new CommandLine.ParameterException(spec.commandLine(), "--path is not specified when --output=file is present");
        } else if (outputPath != null && statsOutputMethod.equals(StatsOutputMethod.console)) {
            throw new CommandLine.ParameterException(spec.commandLine(), "--path is specified when --output=file is not present or --output=console");
        }
    }
}
