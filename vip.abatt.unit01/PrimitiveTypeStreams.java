import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Author:YANKAI_1101
 * Date:2020/3/24
 * Desc：基本类型流
 **/
public class PrimitiveTypeStreams {

    public static void show(String title, IntStream stream) {
        final int SIZE = 10;
        int[] firstElements = stream.limit(SIZE + 1).toArray();
        System.out.println(title + ": " + Arrays.toString(firstElements));
    }

    public static void main(String[] args) throws IOException {
        // 创建随机数流
        IntStream generate = IntStream.generate(() -> (int) (Math.random() * 1000));
        show("generate", generate); // generate: [281, 624, 866, 379, 948, 387, 512, 486, 832, 486, 589]

        IntStream range = IntStream.range(5, 10);
        show("range", range); // range: [5, 6, 7, 8, 9]

        IntStream rangeClosed = IntStream.rangeClosed(5, 10);
        show("rangeClosed", rangeClosed); // rangeClosed: [5, 6, 7, 8, 9, 10]

        String path = PrimitiveTypeStreams.class.getResource("/alice30.txt").getPath();
        String contents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream lengthArray = words.mapToInt(String::length);
        show("lengthArray", lengthArray); // lengthArray: [3, 10, 13, 3, 4, 3, 10, 13, 3, 4, 3]

        String sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence); // 𝕆 is the set of octonions.
        // 产生由当前字符串的所有Unitcode码点构成的流
        IntStream codePoints = sentence.codePoints(); //codePoints: [120134, 32, 105, 115, 32, 116, 104, 101, 32, 115, 101]

        System.out.println(codePoints.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining())); // 1D546 20 69 73 20 74 68 65 20 73 65 74 20 6F 66 20 6F 63 74 6F 6E 69 6F 6E 73 2E


        Stream<Integer> boxed = IntStream.range(0, 100).boxed();
        IntStream mapToInt = boxed.mapToInt(Integer::intValue);
        show("mapToInt", mapToInt); // mapToInt: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
}
