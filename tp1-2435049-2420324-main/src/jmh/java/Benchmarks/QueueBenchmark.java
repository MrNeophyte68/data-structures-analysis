package Benchmarks;

import List.*;
import Queue.Queue;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;

@State(Scope.Benchmark)
public class QueueBenchmark {

    @Param({"vector", "linkedlist"})
    String type;

    @Param({"100", "200", "400", "600"})
    int size;

    private Queue enqueueTarget;
    private Queue dequeueTarget;
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
        this.enqueueTarget = new Queue(makeList());
        this.dequeueTarget = new Queue(makeList());

        data = new Random().ints(this.size).toArray();
        Arrays.stream(data).forEach(this.dequeueTarget::enqueue);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int benchmarkEnqueue() {
        for (int i : data) {
            this.enqueueTarget.enqueue(i);
        }

        return this.enqueueTarget.peek();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public int benchmarkDequeue(Blackhole bh) {
        for (int i = 0 ; i < this.size -1; i++) {
            bh.consume(this.dequeueTarget.dequeue());
        }

        return this.dequeueTarget.dequeue();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QueueBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}