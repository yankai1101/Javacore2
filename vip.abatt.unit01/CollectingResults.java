import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author:YANKAI_1101
 * Date:2020/3/23
 * Desc：收集结果测试
 **/
public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        String path = CollectingResults.class.getResource("/alice30.txt").getPath();
        String contents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        // 替换 contents 中的所有 aeiouAEIOU
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String lable, Set<T> set) {
        System.out.print(lable + ":" + set.getClass().getName());
        System.out.println("[" + set.stream().limit(10).map(Objects::toString).collect(Collectors.joining(", ")) + "]");
    }

    public static void main(String[] args) throws IOException {
        // 获取 0-9之间的迭代器
        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " "); // 0 1 2 3 4 5 6 7 8 9
        System.out.println();

        // 获取 0-9之间的Object数组
        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array:" + Arrays.toString(numbers)); // Object array:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        Integer number = (Integer) numbers[0];
        System.out.println(number); // 0

        // 获取 0-9之间的Integer数组
        Integer[] integers = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array: " + Arrays.toString(numbers)); // Integer array: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // 把 alice30.txt 内容转换成 Set<String>
        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet: ", noVowelSet); // noVowelSet: :java.util.HashSet[nd, cstmzd, nws, Gt, ntfctns]

        // 把 alice30.txt 内容转换成 TreeSet<String>
        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet: ", noVowelTreeSet); // noVowelTreeSet: :java.util.TreeSet[Gt, cstmzd, nd, ntfctns, nws]

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining: " + result); // Joining: GtcstmzdntfctnsndnwsGtcstmzdntfctnsndnws


        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result); // Joining with commas: Gt, cstmzd, ntfctns, nd, nws, Gt, cstmzd, ntfctns, nd, nws

        // 根据字符串长度，创建 IntSummaryStatistics，求平均数，最大值，最小值
        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double average = summary.getAverage();
        int max = summary.getMax();
        int min = summary.getMin();

        System.out.println("average: "+average); // 4.0
        System.out.println("max: "+max); // 7
        System.out.println("min: "+min); // 2

        noVowels().limit(10).forEach(System.out::print); // GtcstmzdntfctnsndnwsGtcstmzdntfctnsndnws

    }
}
