package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference")
public final class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format", defaultValue = "stylish")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }
}
