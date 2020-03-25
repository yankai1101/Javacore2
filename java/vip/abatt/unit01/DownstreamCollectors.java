package vip.abatt.unit01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.summarizingInt;

import java.util.*;
import java.util.stream.Stream;

/**
 * Author:YANKAI_1101
 * Date:2020/3/24
 * Desc： 下游收集器
 **/
public class DownstreamCollectors {
    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", state='" + state + '\'' +
                    ", population=" + population +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(","))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        String path = DownstreamCollectors.class.getResource("/cities.txt").getPath();

        // 获取 getName-City 的map集合
        Map<String, Set<City>> nameToSet = readCities(path).collect(groupingBy(City::getName, toSet()));
        System.out.println(nameToSet); // {上海=[City{name='上海', state='一线城市', population=9}], 马鞍山=[City{name='马鞍山', state='三线城市', population=3}], 合肥=[City{name='合肥', state='二线城市', population=5}], 乌鲁木齐=[City{name='乌鲁木齐', state='三线城市', population=4}], 北京=[City{name='北京', state='一线城市', population=10}]}

        // 获取 getState-stateCount 的map集合
        Map<String, Long> stateCounting = readCities(path).collect(groupingBy(City::getState, counting()));
        System.out.println(stateCounting); // {三线城市=2, 一线城市=2, 二线城市=1}

        // 获取 getState-stateSum 的map集合
        Map<String, Integer> stateSummingInt = readCities(path).collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        System.out.println(stateSummingInt); // {三线城市=7, 一线城市=19, 二线城市=5}

        // 获取 getState-maxName 的map集合
        Map<String, Optional<String>> collect = readCities(path).collect(groupingBy(City::getState, mapping(City::getName, maxBy(Comparator.comparing(String::length)))));
        System.out.println(collect); // {三线城市=Optional[乌鲁木齐], 一线城市=Optional[北京], 二线城市=Optional[合肥]}

        // 获取 getState-IntSummaryStatistics 的map集合
        Map<String, IntSummaryStatistics> summarizingInt = readCities(path).collect(groupingBy(City::getState, summarizingInt(City::getPopulation)));
        System.out.println(summarizingInt); // {三线城市=IntSummaryStatistics{count=2, sum=7, min=3, average=3.500000, max=4}, 一线城市=IntSummaryStatistics{count=2, sum=19, min=9, average=9.500000, max=10}, 二线城市=IntSummaryStatistics{count=1, sum=5, min=5, average=5.000000, max=5}}

        // 获取 getState-Names 的map集合
        Map<String, String> reduceName = readCities(path).collect(groupingBy(City::getState, reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));
        System.out.println(reduceName); // {三线城市=乌鲁木齐, 马鞍山, 一线城市=北京, 上海, 二线城市=合肥}

        // 获取 getState-Names 的map集合
        Map<String, String> joinName = readCities(path).collect(groupingBy(City::getState, mapping(City::getName, joining(", "))));
        System.out.println(joinName); // {三线城市=乌鲁木齐, 马鞍山, 一线城市=北京, 上海, 二线城市=合肥}

    }
}
