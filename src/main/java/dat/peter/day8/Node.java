package dat.peter.day8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Node {

    private final String label;
    @Setter
    private Node left;
    @Setter
    private Node right;

    public Node(String label) {
        this.label = label;
    }

    public Node move(String direction) {
        return direction.equals("R") ? right : left;
    }
}