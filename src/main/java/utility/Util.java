package utility;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Util {

    static <T> List<T> listConcat(List<T> first, List<T> second) {
        return Stream.concat(first.stream(), second.stream())
                .collect(Collectors.toList());
    }
}