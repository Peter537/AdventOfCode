package dat.peter.day5;

import dat.peter.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Day5 extends Day {

    @Override
    public int day() {
        return 5;
    }

    @Override
    public String partOne(List<String> input) {
        List<Seed> seeds = createSeedsPart1(input);
        System.out.println(seeds);
        List<DestinationMap> destinationMaps = createMaps(input);
        for (DestinationMap destinationMap : destinationMaps) {
            System.out.println(destinationMap);
        }

        long lowest = getLowest(seeds, destinationMaps);
        return String.valueOf(lowest);
    }

    @Override
    public String partTwo(List<String> input) {
        List<Seed> seeds = createSeedsPart2(input);
        List<DestinationMap> destinationMaps = createMaps(input);
        System.out.println(seeds);
        for (DestinationMap destinationMap : destinationMaps) {
            System.out.println(destinationMap);
        }

        long lowest = getLowest(seeds, destinationMaps);
        return String.valueOf(lowest);
    }

    private long getLowest(List<Seed> seeds, List<DestinationMap> destinationMaps) {
//        long lowest = Long.MAX_VALUE;
//        for (Seed seed : seeds) {
//            System.out.println(seed);
//            for (int i = 0; i < seed.range(); i++) {
//                long curr = seed.start() + i;
//                for (DestinationMap destinationMap : destinationMaps) {
//                    curr = destinationMap.getDestination(curr);
//                }
//
//                lowest = Math.min(lowest, curr);
//            }
//        }
        AtomicLong lowest = new AtomicLong(Long.MAX_VALUE);

        seeds.parallelStream().forEach(seed -> {
            System.out.println(seed);
            for (int i = 0; i < seed.range(); i++) {
                long curr = seed.start() + i;

                for (DestinationMap destinationMap : destinationMaps) {
                    curr = destinationMap.getDestination(curr);
                }

                long finalCurr = curr;
                lowest.updateAndGet(value -> Math.min(value, finalCurr));
            }
            System.out.println("Seed done: " + seed);
        });

        return lowest.get();
//        return lowest;
    }

    private List<DestinationMap> createMaps(List<String> input) {
        List<DestinationMap> destinationMaps = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            if (input.get(i).isEmpty() || i == input.size() - 1) {
                destinationMaps.add(DestinationMap.fromLine(strings));
                strings = new ArrayList<>();
            } else {
                strings.add(input.get(i));
            }
        }

        return destinationMaps;
    }

    private List<Seed> createSeedsPart1(List<String> input) {
        List<Long> seedsLong = createSeeds(input);
        List<Seed> seeds = new ArrayList<>();
        for (long start : seedsLong) {
            seeds.add(new Seed(start, 1));
        }

        return seeds;
    }

    private List<Seed> createSeedsPart2(List<String> input) {
        List<Long> seedsLong = createSeeds(input);
        List<Seed> seeds = new ArrayList<>();
        for (int i = 0; i < seedsLong.size(); i += 2) {
            long start = seedsLong.get(i);
            long range = seedsLong.get(i + 1);
            seeds.add(new Seed(start, range));
        }

        return seeds;
    }

    private List<Long> createSeeds(List<String> input) {
        String[] seedsStr = input.get(0).split(": ")[1].split(" ");
        return Arrays.stream(seedsStr).map(Long::parseLong).toList();
    }
}