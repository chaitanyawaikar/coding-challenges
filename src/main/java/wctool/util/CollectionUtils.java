package wctool.util;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {

    public static final <T> Set<T> newHashSet(T... objs) {
        return Stream.of(objs)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
