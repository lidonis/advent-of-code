package fr.lidonis.adventofcode;

import picocli.CommandLine;
import picocli.jansi.graalvm.AnsiConsole;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "advent-of-code", mixinStandardHelpOptions = true, description = "Prints Advent of Code result"
)
public class AdventOfCodeMain implements Callable<Integer> {

    @CommandLine.Option(names = {"-y", "--year"}, description = "Advent of Code year", required = true)
    private int year;

    @CommandLine.Option(names = {"-d", "--day"}, description = "Advent of Code day", required = true)
    private int day;

    public static void main(String[] args) {
        int exitCode;
        try (AnsiConsole ignored = AnsiConsole.windowsInstall()) {
            exitCode = new CommandLine(new AdventOfCodeMain()).execute(args);
        }
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            new AdventOfCodeCommand(year, day).call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
    }
}