package vip.abatt.unit01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Author:YANKAI_1101
 * Date:2020/3/24
 * Desc：并行流测试
 * 只有面对海量内存数据和运算密集处理，并行流才能工作最佳
 **/
public class ParallelStream {
    public static void main(String[] args) throws IOException {
        String path = ParallelStream.class.getResource("/alice30.txt").getPath();
        String contents = new String(Files.readAllBytes(Path.of(path)), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));

        int[] shortWords = new int[10];
        words.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords)); // [0, 0, 0, 12, 6, 0, 0, 0, 0, 0]

        Arrays.fill(shortWords, 0);

        words.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords)); // [0, 0, 0, 12, 6, 0, 0, 0, 0, 0]

        // 产生 单词长度小于10 ， 单词长度-单词个数的 Map
        Map<Integer, Long> showWordCount = words.parallelStream().filter(s -> s.length() < 10).collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(showWordCount); // {3=12, 4=6} 表示长度为3的有12个，长度为4的有6个

        // 并发映射表，产生 单词长度-单词数组 的map集合
        ConcurrentMap<Integer, List<String>> result = words.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result); // {3=[Get, and, and, and, Get, and, and, Get, Get, Get, Get, and], 4=[news, news, news, news, news, news], 10=[customized, customized, customized, customized, customized, customized], 13=[notifications, notifications, notifications, notifications, notifications, notifications]}

        // 并发映射表，产生 单词长度-单词个数 的map集合
        ConcurrentMap<Integer, Long> wordCounts = words.parallelStream().collect(Collectors.groupingByConcurrent(String::length, Collectors.counting()));
        System.out.println(wordCounts); // {3=12, 4=6, 10=6, 13=6}

    }
}
