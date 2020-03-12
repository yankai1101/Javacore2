import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Author:YANKAI_1101
 * Date:2020/2/28
 * Desc：流的使用：查找长单词功能
 **/
public class CountLongWords {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // 文件路径
        String pathName = CountLongWords.class.getResource("/alice30.txt").getPath();

        String contents = new String(Files.readAllBytes(Paths.get(pathName)), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+")); // \\PL+指的是非字母分隔符

        // 1. 传统方式实现
        long count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        System.out.println(count); // 6

        // 2. 顺序流，查找长单词功能
        count = words.stream().filter(w -> w.length() > 12).count(); // 6
        System.out.println(count);

        // 3. 并行流，查找长单词功能
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count); // 6
    }
}
