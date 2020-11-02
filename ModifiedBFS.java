import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Modified Breadth-First Search.
 * Meets (c) & (d) requirements.
 */
class ModifiedBFS {
    private static final String fileName = "ModifiedBFS.txt";
    private final Graph graph;
    private final int k;

    // Hashmap of Hospital and its partial MDST, which is a Hashmap containing a node and its previous node
    // HashMap<Hospital #, HashMap<Node #, Previous Node #>>
    private final HashMap<Integer, HashMap<Integer, Integer>> partialMinDistanceSpanningTrees;

    // Hashmap of Node and a LinkedLst of hospitals that visited it, in order
    // Hashmap<Node #, Hospitals # Visited in Order>
    private final HashMap<Integer, LinkedList<Integer>> visitedOrder;

    ModifiedBFS(Graph graph, int k) {
        partialMinDistanceSpanningTrees = new HashMap<>();
        visitedOrder = new HashMap<>();
        this.graph = graph;

        // If number of hospitals > k, use number of hospitals as k instead
        this.k = Math.min(k, graph.getHospitals().size());
    }

    /**
     * Runs modified breadth-first search.
     */
    void execute() {
        Instant start = Instant.now();
        int counter = 0;

        // Queue containing the node to visit, and the hospital its path is from
        // i.e. hospital that has this particular node in its partial MDST
        // LinkedList<{Node #, Hospital #}>
        LinkedList<int[]> queue = new LinkedList<>();

        // Add hospitals to the queue
        for (int hospital : graph.getHospitals()) {
            counter++;

            // Add hospitals as current node and the hospital its from (which is itself) to the queue
            queue.add(new int[]{hospital, hospital});

            // Create Hashmap of hospitals to have previous node as current node, and add them to the partial MDST
            HashMap<Integer, Integer> previousNodes = new HashMap<>();
            previousNodes.put(hospital, hospital);
            partialMinDistanceSpanningTrees.put(hospital, previousNodes);

            // Create LinkedList of hospitals visited in order, and add them to the Hashmap
            LinkedList<Integer> visitedHospitals = new LinkedList<>();
            visitedHospitals.add(hospital);
            visitedOrder.put(hospital, visitedHospitals);
        }

        // Start using BFS from queue
        while (queue.size() != 0) {
            int[] currentNodeAndHospital = queue.poll();

            for (int node : graph.getAdjacencyList().get(currentNodeAndHospital[0])) {

                // If not visited, initialise a LinkedList
                if (!visitedOrder.containsKey(node)) {
                    visitedOrder.put(node, new LinkedList<>());
                }

                // If there are less than k nearest hospitals and that particular hospital has not visited the node,
                // add to queue and update visited order and partial MDST Hashmaps
                if (visitedOrder.get(node).size() < k && !partialMinDistanceSpanningTrees.get(currentNodeAndHospital[1])
                        .containsKey(node)) {
                    counter++;
                    queue.add(new int[]{node, currentNodeAndHospital[1]});
                    visitedOrder.get(node).add(currentNodeAndHospital[1]);
                    partialMinDistanceSpanningTrees.get(currentNodeAndHospital[1]).put(node, currentNodeAndHospital[0]);
                }
            }
        }

        Instant end = Instant.now();
        Duration timeTaken = Duration.between(start, end);
        System.out.println("Number of iterations for Modified BFS: " + counter);
        System.out.println("Time taken to run Modified BFS: " + timeTaken.toNanos() + "ns (" + timeTaken.toSeconds()
                + "s)");
    }

    /**
     * Returns partial minimum distance spanning tree calculated.
     *
     * @return Hashmap of each hospital and its partial minimum distance spanning tree.
     */
    HashMap<Integer, HashMap<Integer, Integer>> getPartialMinDistanceSpanningTrees() {
        return partialMinDistanceSpanningTrees;
    }

    /**
     * Returns file name to write results to.
     *
     * @return File name of results.
     */
    String getFileName() {
        return fileName;
    }

    /**
     * Returns visited order of node.
     *
     * @return Hashmap of each node and its hospital visited order.
     */
    HashMap<Integer, LinkedList<Integer>> getVisitedOrder() {
        return visitedOrder;
    }
}
