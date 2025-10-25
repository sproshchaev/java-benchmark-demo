package com.prosoft.springboot;

import com.prosoft.core.DataGenerator;
import com.prosoft.core.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/benchmark")
public class BenchmarkController {

    @Autowired
    private SearchService searchService;

    private final List<String> list = DataGenerator.generateList(100000);
    private final Map<String, String> map = DataGenerator.generateMap(100000);
    private final List<String> searchTargets = DataGenerator.getSearchTargets(list, 100);

    @PostMapping("/list")
    public BenchmarkResult benchmarkList(@RequestParam("iterations") int iterations) {
        long startTime = System.nanoTime();

        for (int i = 0; i < iterations; i++) {
            for (String target : searchTargets) {
                searchService.searchInList(list, target);
            }
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double avgTime = (double) totalTime / (iterations * searchTargets.size());

        return new BenchmarkResult("List Search", avgTime, "ns", iterations);
    }

    @PostMapping("/map")
    public BenchmarkResult benchmarkMap(@RequestParam("iterations") int iterations) {
        long startTime = System.nanoTime();

        for (int i = 0; i < iterations; i++) {
            for (String target : searchTargets) {
                searchService.searchInMap(map, target);
            }
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double avgTime = (double) totalTime / (iterations * searchTargets.size());

        return new BenchmarkResult("Map Search", avgTime, "ns", iterations);
    }

    @PostMapping("/compare")
    public ComparisonResult compare(@RequestParam("iterations") int iterations) {
        BenchmarkResult listResult = benchmarkList(iterations);
        BenchmarkResult mapResult = benchmarkMap(iterations);

        return new ComparisonResult(listResult, mapResult);
    }

    public static record BenchmarkResult(
            String operation,
            double averageTime,
            String timeUnit,
            int iterations
    ) {}

    public static record ComparisonResult(
            BenchmarkResult listResult,
            BenchmarkResult mapResult,
            double speedupFactor
    ) {
        public ComparisonResult(BenchmarkResult listResult, BenchmarkResult mapResult) {
            this(listResult, mapResult, listResult.averageTime() / mapResult.averageTime());
        }
    }
}