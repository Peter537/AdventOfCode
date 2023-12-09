package dat.peter.year2023.day8;

import dat.peter.Day;
import dat.peter.utils.MathUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 extends Day {

    @Override
    public int day() {
        return 8;
    }

    @Override
    public String partOne(List<String> input) {
        Map<String, Node> nodeMap = createNodeMap(input);
        String[] instructions = input.get(0).split("");
        return String.valueOf(getSteps(nodeMap.get("AAA"), instructions, "ZZZ"));
    }

    @Override
    public String partTwo(List<String> input) {
        Map<String, Node> nodeMap = createNodeMap(input);
        String[] instructions = input.get(0).split("");
        List<Node> nodes = nodeMap.values().stream().filter(node -> node.getLabel().endsWith("A")).toList();
        List<Long> steps = new ArrayList<>();
        for (Node node : nodes) {
            steps.add(getSteps(node, instructions, "Z"));
        }

        return steps.stream().reduce(1L, MathUtils::lcm).toString();
    }

    public long getSteps(Node startNode, String[] instructions, String ending) {
        long steps = 0;
        Node currentNode = startNode;
        while (!currentNode.getLabel().endsWith(ending)) {
            String direction = instructions[(int) (steps++ % instructions.length)];
            currentNode = currentNode.move(direction);
        }

        return steps;
    }

    public Map<String, Node> createNodeMap(List<String> input) {
        Map<String, Node> nodeMap = new HashMap<>();
        for (int i = 2; i < input.size(); i++) {
            String line = input.get(i);
            String label = line.split(" = \\(")[0];
            String leftLabel = line.split(" = \\(")[1].split(",")[0];
            String rightLabel = line.split(", ")[1].replace(")", "");
            Node leftNode = nodeMap.getOrDefault(leftLabel, new Node(leftLabel));
            nodeMap.putIfAbsent(leftLabel, leftNode);
            Node rightNode = nodeMap.getOrDefault(rightLabel, new Node(rightLabel));
            nodeMap.putIfAbsent(rightLabel, rightNode);
            Node node = nodeMap.getOrDefault(label, new Node(label, leftNode, rightNode));
            nodeMap.putIfAbsent(label, node);
            node.setLeft(leftNode);
            node.setRight(rightNode);
        }

        return nodeMap;
    }
}