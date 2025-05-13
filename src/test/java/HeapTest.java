import com.example.Heap;
import com.example.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class HeapTest extends CommonTest {

    @BeforeEach
    public void init() {
        setUp();
    }

    @Test
    void insertNodeToHeapSuccess() {
        Heap<Integer> heap = new Heap<>(inputElementsSize);
        for (Integer value : inputElements) {
            heap.insertNode(new Node<>(value));
        }

        Node<Integer>[] actualHeapElements = heap.getHeapArray();
        Node<Integer>[] expectedHeapElements = buildExpectedHeap().toArray(new Node[0]);

        assertThat(actualHeapElements)
                .usingRecursiveComparison()
                .isEqualTo(expectedHeapElements);
    }

    @Test
    void removeNodeFromHeapSuccess() {
        Heap<Integer> heap = new Heap<>(inputElementsSize);
        for (Integer value : inputElements) {
            heap.insertNode(new Node<>(value));
        }

        Node<Integer> node = heap.removeNode(0); // remove root
        PriorityQueue<Node<Integer>> expectedHeap = buildExpectedHeap();
        expectedHeap.remove(node);

        Node<Integer>[] actualHeapElements = heap.getHeapArray();
        Node<Integer>[] expectedHeapElements = expectedHeap.toArray(new Node[0]);

        assertThat(actualHeapElements)
                .usingRecursiveComparison()
                .isEqualTo(expectedHeapElements);
    }

    private PriorityQueue<Node<Integer>> buildExpectedHeap() {
        PriorityQueue<Node<Integer>> expectedHeap = new PriorityQueue<>((a, b) -> b.compareTo(a)); // Max-heap
        for (Integer element : inputElements) {
            expectedHeap.add(new Node<>(element));
        }
        return expectedHeap;
    }
}