package vip.abatt.unit01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Author:YANKAI_1101
 * Date:2020/3/19
 * Desc：测试Optional
 **/
public class OptionalTest {
    public static void main(String[] args) throws IOException {
        String pathName = OptionalTest.class.getResource("/alice30.txt").getPath();

        String contents = new String(Files.readAllBytes(Paths.get(pathName)), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));

        // 查找第一个 news
        Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("news"))
                .findFirst();
        // 输出 optionalValue，若optionalValue 为空，则输出 NO word
        System.out.println(optionalValue.orElse("NO word")); // news

        Optional<String> optionalString = Optional.empty();

        // optional.orElse,若当前optional值为空的时候，产生一些other
        String result = optionalString.orElse("N/A");
        System.out.println("result：" + result); // result：N/A

        // optional.orElseGet,若当前optional值为空的时候，产生一些效果
        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result：" + result); // result：中文 (简体，中国)

        // optional.orElseThrow,若当前optional值为空的时候，抛出异常；否则，什么都不做。
        try {
            result = optionalString.orElseThrow(IllegalAccessError::new);
            System.out.println("result：" + result); // Optional.empty
        } catch (IllegalAccessError illegalAccessError) {
            // do something...
        }

        // optional.ifPresent,若当前optional值为空的时候，什么都不做，否则，执行ifPresent方法中的谓词条件
        optionalValue = wordList.stream()
                .filter(s -> s.contains("customized"))
                .findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains customized")); // customized contains customized


        // optional.map,若当前optional值为空的时候，什么都不做，否则，执行map方法中的谓词条件
        HashSet<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added); // Optional[false]

        // optional.flatMap,当当前Optional值为空的时候，返回一个空的OPtional
        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot)); // Optional[0.5]
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot)); // Optional.empty
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot)); // Optional.empty

        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(OptionalTest::inverse)
                .flatMap(OptionalTest::squareRoot); // Optional.empty
        System.out.println(result2);
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
