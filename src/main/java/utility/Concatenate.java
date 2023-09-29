package utility;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A concatenate function I ripped shamelessly from the internet
 */
public interface Concatenate {

    /**
     * Concates 2 lists using streams
     * @param first first list
     * @param second second list
     * @return a new list made of the combined elements of the two given lists
     * @param <T> list type
     */
    static <T> List<T> lists(List<T> first, List<T> second) {
        return Stream.concat(first.stream(), second.stream())
                .collect(Collectors.toList());
    }
}
