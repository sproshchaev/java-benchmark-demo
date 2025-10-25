# java-benchmark-demo

# Запуск бенчмарков

## 1. Простой бенчмарк:
```bash
cd simple-main-benchmark
mvn compile exec:java -Dexec.mainClass="com.prosoft.simple.SimpleBenchmark"
```

## 2. JMH бенчмарк:
```bash
cd jmh-benchmark
mvn clean package
java -Djmh.ignoreLock=true -jar target/benchmarks.jar
```

## 3. Spring Boot бенчмарк:
```bash
cd spring-boot-benchmark
mvn spring-boot:run
```

# Затем тестируйте через curl:
```bash
curl -X POST "http://localhost:8080/benchmark/compare?iterations=1000"
```

# Или отдельные тесты:
```bash
curl -X POST "http://localhost:8080/benchmark/list?iterations=1000"
```
```bash
curl -X POST "http://localhost:8080/benchmark/map?iterations=1000"
```

## 4. Gatling нагрузочное тестирование:
```bash
cd gatling-benchmark
mvn gatling:test
```

# Для запуска конкретной симуляции:
```bash
mvn gatling:test -Dgatling.simulationClass=com.prosoft.gatling.SpringBootBenchmarkSimulation
```

## Предварительные требования

Перед запуском убедитесь, что:
- Установлен Maven 3.6+
- Установлена Java 17+
- Для Spring Boot и Gatling тестов приложение должно быть запущено на порту 8080
- Все модули собраны: `mvn clean install` из корневой директории проекта
