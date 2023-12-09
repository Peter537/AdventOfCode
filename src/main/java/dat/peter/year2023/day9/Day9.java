package dat.peter.year2023.day9;

import dat.peter.Day;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day9 extends Day {

    @Override
    public int day() {
        return 9;
    }

    @Override
    public String partOne(List<String> input) {
        List<Sequence> sequences = createSequences(input);
        long sum = 0;
        for (Sequence sequence : sequences) {
            Map<Integer, Sequence> map = createSubSequenceMaps(sequence);
            long nextValue = 0;
            for (int i = map.size() - 1; i > 0; i--) {
                Sequence seq = map.get(i);
                nextValue = seq.sequence()[seq.sequence().length - 1] + nextValue;
            }

            sum += nextValue;
        }
        return String.valueOf(sum);
    }

    @Override
    public String partTwo(List<String> input) {
        List<Sequence> sequences = createSequences(input);
        long sum = 0;
        for (Sequence sequence : sequences) {
            Map<Integer, Sequence> map = createSubSequenceMaps(sequence);
            long nextValue = 0;
            for (int i = map.size() - 1; i > 0; i--) {
                Sequence seq = map.get(i);
                nextValue = seq.sequence()[0] - nextValue;
            }

            sum += nextValue;
        }
        return String.valueOf(sum);
    }

    public Map<Integer, Sequence> createSubSequenceMaps(Sequence sequence) {
        Map<Integer, Sequence> map = new LinkedHashMap<>();
        int iterationIndex = 1;
        map.put(iterationIndex++, sequence);
        Sequence currSequence = sequence;
        boolean running = true;
        while (running) {
            currSequence = currSequence.getDifference();
            map.put(iterationIndex++, currSequence);
            running = false;
            for (long l : currSequence.sequence()) {
                if (l != 0) {
                    running = true;
                    break;
                }
            }
        }

        return map;
    }

    public List<Sequence> createSequences(List<String> input) {
        List<Sequence> sequences = new ArrayList<>();
        for (String s : input) {
            sequences.add(Sequence.fromLine(s));
        }

        return sequences;
    }
}