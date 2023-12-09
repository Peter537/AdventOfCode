package dat.peter.year2023.day5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public record DestinationMap(String name, long[] sources, long[] destination, long[] ranges) {

    public long getDestination(long source) {
        for (int i = 0; i < sources.length; i++) {
            long s = sources[i];
            if (source >= s && source <= (s + ranges[i] - 1)) {
                return destination[i] + (source - sources[i]);
            }
        }

        return source;
    }

    public static DestinationMap fromLine(List<String> lines) {
        String name = lines.get(0).split(" ")[0];
        List<Long> sources = new LinkedList<>();
        List<Long> destination = new LinkedList<>();
        List<Long> ranges = new LinkedList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            List<Long> numbers = Arrays.stream(split).map(Long::parseLong).toList();
            sources.add(numbers.get(1));
            destination.add(numbers.get(0));
            ranges.add(numbers.get(2));
        }

        long[] sourcesArr = sources.stream().mapToLong(Long::longValue).toArray();
        long[] destinationArr = destination.stream().mapToLong(Long::longValue).toArray();
        long[] rangesArr = ranges.stream().mapToLong(Long::longValue).toArray();
        return new DestinationMap(name, sourcesArr, destinationArr, rangesArr);
    }
}