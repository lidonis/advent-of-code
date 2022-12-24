package fr.lidonis.adventofcode.y2022.day23;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Day23Benchmark {

    private Day23 day23;
 
    @Setup
    public void setUp() {
       day23 =  Day23.INSTANCE;
    }
 
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 3, time = 3)
    @Measurement(iterations = 5, time = 5)
    @Fork(1)
    public String part2() {
        return day23.part2().toString();
    }
 
}