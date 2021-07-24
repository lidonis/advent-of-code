package fr.lidonis.adventofcode;

import java.util.logging.Logger;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine;
import java.util.logging.Level;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "advent-of-code", mixinStandardHelpOptions = true, description = "Prints Advent of Code result"
)
public class AdventOfCodeMain implements Callable<Integer> {

    private static final Logger LOG = Logger.getLogger(AdventOfCodeMain.class.getName());

    @CommandLine.Option(names = {"-y", "--year"}, description = "Advent of Code year", required = true)
    private int year;

    @CommandLine.Option(names = {"-d", "--day"}, description = "Advent of Code day", required = true)
    private int day;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new AdventOfCodeMain()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            new AdventOfCodeCommand(year, day).call();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error in the command", e);
            return 1;
        }
        return 0;
    }
}
