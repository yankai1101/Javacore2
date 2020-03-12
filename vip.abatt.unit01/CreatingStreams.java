import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Author:YANKAI_1101
 * Date:2020/2/28
 * Desc：流的创建
 **/
public class CreatingStreams {
    public static void main(String[] args) throws IOException {
        // 文件路径
        String pathName = CountLongWords.class.getResource("/alice30.txt").getPath();

        String contents = new String(Files.readAllBytes(Paths.get(pathName)), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+"));// \\PL+指的是非字母分隔符
        show("words", words); // Get,customized,notifications,and,news,Get,customized,notifications,and,news,...

        Stream<String> song = Stream.of("g", "o", "o", "d");
        show("song", song); // g,o,o,d

        Stream<String> silence = Stream.empty();
        show("silence", silence); //

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos); // Echo,Echo,Echo,Echo,Echo,Echo,Echo,Echo,Echo,Echo,...

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms); // 0.5050294775398693,0.961559866780883,0.4345429935388113,0.6738163204031445,0.48842932575724896,0.7029536200875018,0.6730925224061385,0.5415821060940917,0.807007397435007,0.4485066537525805,...

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers); // 1,2,3,4,5,6,7,8,9,10,...

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay); // Get,customized,notifications,and,news,Get,customized,notifications,and,news,...

        Stream<String> lines = Files.lines(Paths.get(pathName), StandardCharsets.UTF_8);
        show("lines", lines); // Get customized notifications and news.,Get customized notifications and news.,Get customized notifications and news.,Get customized notifications and news.,Get customized notifications and news.,Get customized notifications and news.

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectors = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectors", rootDirectors); // /

        Iterator<Path> iterator = Paths.get(pathName).iterator();
        Stream<Path> pathStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathStream", pathStream); // Users,yankai,IdeaProjects,javacore2,out,production,javacore2,alice30.txt
    }

    public static <T> void show(String title, Stream<T> stream) {
        System.out.println(title + ":");
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());

        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(",");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }
}
