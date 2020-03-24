import java.util.List;
import java.util.Optional;

/**
 * Author:YANKAI_1101
 * Date:2020/3/24
 * Desc：约简操作，类似reduce
 **/
public class ReduceCollectors {
    public static void main(String[] args) {
        List<Integer> values = List.of(1, 2, 3, 4, 5);
        Optional<Integer> reduce = values.stream().reduce((x, y) -> x + y);
        System.out.println(reduce.get()); // 15

        reduce = values.stream().reduce(Integer::sum);
        System.out.println(reduce.get()); // 15
    }
}
