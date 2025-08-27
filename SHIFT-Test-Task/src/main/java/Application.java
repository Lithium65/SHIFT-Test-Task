import args.ApplicationArgs;
import com.google.inject.Guice;
import com.google.inject.Injector;
import module.ApplicationModule;
import picocli.CommandLine;
import runner.ApplicationRunner;

public class Application {

    public static void main(String[] args) {
        ApplicationArgs applicationArgs = new ApplicationArgs();
        CommandLine commandline = new CommandLine(applicationArgs);
        try {
            commandline.parseArgs(args);
            applicationArgs.validateArgs();
            Injector injector = Guice.createInjector(new ApplicationModule());
            ApplicationRunner runner = injector.getInstance(ApplicationRunner.class);
            runner.run(applicationArgs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
