package com.prosoft.gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class SpringBootBenchmarkSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    private final ScenarioBuilder scn = scenario("Spring Boot Benchmark")
            .exec(
                    http("List Search Benchmark")
                            .post("/benchmark/list?iterations=1000")
            )
            .pause(1)
            .exec(
                    http("Map Search Benchmark")
                            .post("/benchmark/map?iterations=1000")
            )
            .pause(1)
            .exec(
                    http("Compare Benchmarks")
                            .get("/benchmark/compare?iterations=100")
            );

    {
        setUp(
                scn.injectOpen(
                        constantUsersPerSec(10).during(60) // 10 пользователей в секунду в течение 60 секунд
                )
        ).protocols(httpProtocol);
    }
}