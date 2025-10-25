package com.prosoft.jmh;

import com.prosoft.core.DataGenerator;
import com.prosoft.core.SearchService;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
public class SearchBenchmark {

    @Param({"1000", "10000", "100000"})
    private int dataSize;

    private List<String> list;
    private Map<String, String> map;
    private List<String> searchTargets;
    private SearchService searchService;

    @Setup
    public void setup() {
        list = DataGenerator.generateList(dataSize);
        map = DataGenerator.generateMap(dataSize);
        searchTargets = DataGenerator.getSearchTargets(list, 100);
        searchService = new SearchService();
    }

    @Benchmark
    public void benchmarkListSearch(Blackhole blackhole) {
        for (String target : searchTargets) {
            boolean result = searchService.searchInList(list, target);
            blackhole.consume(result);
        }
    }

    @Benchmark
    public void benchmarkMapSearch(Blackhole blackhole) {
        for (String target : searchTargets) {
            boolean result = searchService.searchInMap(map, target);
            blackhole.consume(result);
        }
    }

    @Benchmark
    public void benchmarkLinearSearch(Blackhole blackhole) {
        for (String target : searchTargets) {
            int result = searchService.linearSearch(list, target);
            blackhole.consume(result);
        }
    }
}
