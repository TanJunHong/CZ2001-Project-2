import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * File Writer.
 */
class ResultWriter {

    private final String fileName;

    ResultWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes k-shortest paths results to file.
     *
     * @param nodes                           Nodes to show path.
     * @param visitedOrder                    Hashmap of the node and its visited order from hospital.
     * @param partialMinDistanceSpanningTrees Hashmap of hospital and its MDST.
     * @throws IOException If there is error writing file.
     */
    void writeKShortestPaths(ArrayList<Integer> nodes, HashMap<Integer, LinkedList<Integer>> visitedOrder,
                             HashMap<Integer, HashMap<Integer, Integer>> partialMinDistanceSpanningTrees)
            throws IOException {
        int counter = 0;
        Instant start = Instant.now();

        BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileName));

        for (int node : nodes) {
            myWriter.write("Node: " + node);

            if (!visitedOrder.containsKey(node)) {
                myWriter.write("\nNo paths!\n\n");
                continue;
            }

            int initialSize = visitedOrder.get(node).size();

            // Find path for each hospitals
            for (int i = 0; i < initialSize; i++) {
                Integer currentHospital = visitedOrder.get(node).poll();
                int currentNode = node;
                int previousNode = partialMinDistanceSpanningTrees.get(currentHospital).get(currentNode);
                int distance = 0;

                StringBuilder path = new StringBuilder();
                path.append(currentNode);

                // Backtrack from node to hospital
                while (currentNode != previousNode) {
                    distance++;
                    path.append("-").append(previousNode);
                    currentNode = previousNode;
                    previousNode = partialMinDistanceSpanningTrees.get(currentHospital).get(previousNode);
                }
                myWriter.write("\nTop " + (i + 1) + " Shortest Distance: " + distance + "\nPath: "
                        + path.toString());

                counter += distance;
            }

            myWriter.write("\n\n");
        }

        myWriter.close();

        Instant end = Instant.now();
        Duration timeTaken = Duration.between(start, end);
        System.out.println("Number of iterations to write modified BFS paths: " + counter);
        System.out.println("Time taken to write modified BFS paths: " + timeTaken.toNanos() + "ns ("
                + timeTaken.toSeconds() + "s)");
    }

    /**
     * Writes shortest path to file.
     *
     * @param nodes         Nodes to show path.
     * @param previousNodes Hashmap of the node and its previous node.
     * @throws IOException If there is error writing file.
     */
    void writeShortestPath(ArrayList<Integer> nodes, HashMap<Integer, Integer> previousNodes) throws IOException {
        int counter = 0;
        Instant start = Instant.now();

        BufferedWriter myWriter = new BufferedWriter(new FileWriter(fileName));

        for (int node : nodes) {

            myWriter.write("Node: " + node);

            if (!previousNodes.containsKey(node)) {
                myWriter.write("\nNo paths!\n\n");
                continue;
            }

            int currentNode = node;
            int previousNode = previousNodes.get(node);
            int distance = 0;

            StringBuilder path = new StringBuilder();
            path.append(currentNode);

            // Backtrack from node to hospital
            while (currentNode != previousNode) {
                distance++;
                path.append("-").append(previousNode);
                currentNode = previousNode;
                previousNode = previousNodes.get(previousNode);
            }

            myWriter.write("\nShortest Distance: " + distance + "\nPath: " + path.toString() + "\n\n");
            counter += distance;
        }

        myWriter.close();

        Instant end = Instant.now();
        Duration timeTaken = Duration.between(start, end);
        System.out.println("Number of iterations to write BFS paths: " + counter);
        System.out.println("Time taken to write BFS paths: " + timeTaken.toNanos() + "ns (" + timeTaken.toSeconds()
                + "s)");
    }
}
