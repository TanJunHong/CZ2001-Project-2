import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Breadth-First Search.
 * Meets (a) & (b) requirements.
 */
class BFS {
    private static final String fileName = "BFS.txt";
    private final Graph graph;

    // Hashmap of current node and its previous node
    private final HashMap<Integer, Integer> previousNodes;

    BFS(Graph graph) {
        previousNodes = new HashMap<>(graph.getAdjacencyList().size() + 1, 1);
        this.graph = graph;
    }

    /**
     * Runs breadth-first search.
     */
    void execute() {
        Instant start = Instant.now();
        int counter = 0;

        LinkedList<Integer> queue = new LinkedList<>();

        for (int hospital : graph.getHospitals()) {
            counter++;

            // Update Hashmap of hospitals to have previous node as current node
            previousNodes.put(hospital, hospital);

            // Add hospitals to the queue
            queue.add(hospital);
        }

        // Start using BFS from queue
        while (queue.size() != 0) {
            int currentNode = queue.poll();

            for (int node : graph.getAdjacencyList().get(currentNode)) {

                // If not visited, add nodes to queue and update previous nodes Hashmap
                if (!previousNodes.containsKey(node)) {
                    counter++;
                    previousNodes.put(node, currentNode);
                    queue.add(node);
                }
            }
        }

        Instant end = Instant.now();
        Duration timeTaken = Duration.between(start, end);
        System.out.println("Number of iterations for BFS: " + counter);
        System.out.println("Time taken to run BFS: " + timeTaken.toNanos() + "ns (" + timeTaken.toSeconds() + "s)");
    }

    /**
     * Returns previous node calculated.
     *
     * @return Hashmap of node and its previous node.
     */
    HashMap<Integer, Integer> getPreviousNodes() {
        return previousNodes;
    }

    /**
     * Returns file name to write results to.
     *
     * @return File name of results.
     */
    String getFileName() {
        return fileName;
    }
}
