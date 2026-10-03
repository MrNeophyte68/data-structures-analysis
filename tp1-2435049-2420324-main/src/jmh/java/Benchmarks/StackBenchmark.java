package Benchmarks;

import Queue.Queue;
import Stack.Stack;
import List.*;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.Random;

@State(Scope.Benchmark)
public class StackBenchmark {

    @Param({"100", "200", "400", "600"})
    int size;

    @Param({"vector", "linkedlist"})
    String type;

    private Stack pushTarget;
    private Stack popTarget;
    private int[] data;

    private List makeList() {
        return switch (this.type) {
            case "vector" -> new Vector();
            case "linkedlist" -> new LinkedList();
            default -> throw new IllegalStateException("Unexpected value: " + this.type);
        };
    }

    @Setup(Level.Invocation)
    public void setup() {
        this.pushTarget = new Stack(makeList());
        this.popTarget = new Stack(makeList());

        data = new Random().ints(this.size).toArray();
        Arrays.stream(data).forEach(this.popTarget::push);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int benchmarkPush() {
        for (int i : data) {
            this.pushTarget.push(i);
        }

        return this.pushTarget.peek();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int benchmarkPop(Blackhole bh) {
        for (int i = 0 ; i < this.size -1; i++) {
            bh.consume(this.popTarget.pop());
        }

        return this.popTarget.pop();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StackBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}