import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * File Reader.
 */
class GraphReader {

    /**
     * Reads graph from file.
     *
     * @param graph    Graph to pass edges to.
     * @param fileName Filename to read.
     * @throws IOException If error reading file.
     */
    void readGraph(Graph graph, String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            // Ignore lines starting with #
            if (line.charAt(0) == '#') {
                continue;
            }
            String[] nodesString = line.split("\\s+", 2);
            graph.addEdge(nodesString[0], nodesString[1]);
        }

        bufferedReader.close();
    }

    /**
     * Reads hospitals from file.
     *
     * @param graph    Graph to pass hospitals to.
     * @param fileName Filename to read.
     * @throws IOException If error reading file.
     */
    void readHospitals(Graph graph, String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        int num = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        for (int i = 0; i < num; i++) {
            graph.addHospital(Integer.parseInt(bufferedReader.readLine()));
        }

        bufferedReader.close();
    }

    /**
     * Reads nodes from file.
     * Nodes to show path are stored identically as hospitals.
     *
     * @param graph    Graph to pass hospitals to.
     * @param fileName Filename to read.
     * @throws IOException If error reading file.
     */
    void readNodes(Graph graph, String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        String numString = bufferedReader.readLine();

        if (numString.trim().equalsIgnoreCase("all")) {
            for (int node : graph.getAdjacencyList().keySet()) {
                graph.addNodes(node);
            }
        } else {
            int num = Integer.parseInt(numString.split(" ")[1]);
            for (int i = 0; i < num; i++) {
                graph.addNodes(Integer.parseInt(bufferedReader.readLine()));
            }
        }

        bufferedReader.close();
    }
}
