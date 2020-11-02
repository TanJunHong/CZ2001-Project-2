import java.util.ArrayList;
import java.util.HashMap;

/**
 * Graph represented by adjacency list with hospitals.
 */
class Graph {
    private final HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();
    private final ArrayList<Integer> hospitals = new ArrayList<>();
    private final ArrayList<Integer> nodes = new ArrayList<>();

    /**
     * Adds edge to adjacency list.
     *
     * @param firstNode  Node 1.
     * @param secondNode Node 2.
     */
    void addEdge(String firstNode, String secondNode) {
        int node1 = Integer.parseInt(firstNode);
        int node2 = Integer.parseInt(secondNode);

        adjacencyList.computeIfAbsent(node1, k -> new ArrayList<>());
        adjacencyList.get(node1).add(node2);
    }

    /**
     * Adds hospital to hospitals list.
     *
     * @param hospital Hospital to add.
     */
    void addHospital(int hospital) {
        if (adjacencyList.containsKey(hospital)) {
            hospitals.add(hospital);
        } else {
            System.out.println("Hospital " + hospital + " is omitted as it is not in the graph!");
        }
    }

    /**
     * Returns hospital list.
     *
     * @return ArrayList of hospitals.
     */
    ArrayList<Integer> getHospitals() {
        return hospitals;
    }

    /**
     * Returns adjacency list.
     *
     * @return HashMap of node and its adjacency list.
     */
    HashMap<Integer, ArrayList<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Adds node to nodes list.
     *
     * @param node Node to add.
     */
    void addNodes(int node) {
        if (adjacencyList.containsKey(node)) {
            nodes.add(node);
        } else {
            System.out.println("Node " + node + " is omitted as it is not in the graph!");
        }
    }

    /**
     * Returns nodes list.
     *
     * @return ArrayList of nodes.
     */
    ArrayList<Integer> getNodes() {
        return nodes;
    }
}
