import args.ApplicationArgs;
import factory.ApplicationFactory;
import picocli.CommandLine;
import runner.ApplicationRunner;

public class Application {

    public static void main(String[] args) {
        ApplicationArgs applicationArgs = new ApplicationArgs();
        CommandLine commandline = new CommandLine(applicationArgs);
        try {
            commandline.parseArgs(args);
            applicationArgs.validateArgs();
            ApplicationRunner runner = ApplicationFactory.createAppRunner();
            runner.run(applicationArgs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
