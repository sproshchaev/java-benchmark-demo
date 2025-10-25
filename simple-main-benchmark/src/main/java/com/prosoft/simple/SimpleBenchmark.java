package com.prosoft.simple;

import com.prosoft.core.DataGenerator;
import com.prosoft.core.SearchService;

import java.util.List;
import java.util.Map;

public class SimpleBenchmark {

    public static void main(String[] args) {
        System.out.println("=== Simple Benchmark Started ===");

        int dataSize = 100000;
        int warmupIterations = 1000;
        int measurementIterations = 10000;

        // Генерация данных
        List<String> list = DataGenerator.generateList(dataSize);
        Map<String, String> map = DataGenerator.generateMap(dataSize);
        List<String> searchTargets = DataGenerator.getSearchTargets(list, 100);

        SearchService searchService = new SearchService();

        // Прогрев JVM
        System.out.println("Warming up...");
        for (int i = 0; i < warmupIterations; i++) {
            for (String target : searchTargets) {
                searchService.searchInList(list, target);
                searchService.searchInMap(map, target);
            }
        }

        // Бенчмарк List
        System.out.println("Benchmarking List search...");
        long listStartTime = System.nanoTime();

        for (int i = 0; i < measurementIterations; i++) {
            for (String target : searchTargets) {
                searchService.searchInList(list, target);
            }
        }

        long listEndTime = System.nanoTime();
        long listTotalTime = listEndTime - listStartTime;
        double listAvgTime = (double) listTotalTime / (measurementIterations * searchTargets.size());

        // Бенчмарк Map
        System.out.println("Benchmarking Map search...");
        long mapStartTime = System.nanoTime();

        for (int i = 0; i < measurementIterations; i++) {
            for (String target : searchTargets) {
                searchService.searchInMap(map, target);
            }
        }

        long mapEndTime = System.nanoTime();
        long mapTotalTime = mapEndTime - mapStartTime;
        double mapAvgTime = (double) mapTotalTime / (measurementIterations * searchTargets.size());

        // Результаты
        System.out.println("\n=== Benchmark Results ===");
        System.out.printf("List search average time: %.2f ns%n", listAvgTime);
        System.out.printf("Map search average time: %.2f ns%n", mapAvgTime);
        System.out.printf("Map is %.2f times faster than List%n", listAvgTime / mapAvgTime);
        System.out.println("Data size: " + dataSize);
        System.out.println("Iterations: " + measurementIterations);
    }
}