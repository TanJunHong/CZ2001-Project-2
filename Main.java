import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class to test algorithms.
 */
class Main {
    private static final Logger logger = Logger.getLogger("CZ2001 Project 2");

    /**
     * Runs the tests.
     *
     * @param args Default main parameter.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GraphReader graphReader = new GraphReader();
        Graph graph = new Graph();
        int k;

        // Read graph filename
        while (true) {
            try {
                System.out.println("Enter graph filename:");
                String graphFile = scanner.nextLine();
                graphReader.readGraph(graph, graphFile);
                break;
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, ioException.getMessage());
                System.out.println("Error reading file! Please use a valid file!");
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage());
                System.out.println("Problem parsing file! Please use a valid file");
            }
        }

        // Read hospital filename
        while (true) {
            try {
                System.out.println("Enter hospital filename:");
                String hospitalFile = scanner.nextLine();
                graphReader.readHospitals(graph, hospitalFile);
                break;
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, ioException.getMessage());
                System.out.println("Error reading file! Please use a valid file!");
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage());
                System.out.println("Problem parsing file! Please use a valid file");
            }
        }

        // Read node filename
        while (true) {
            try {
                System.out.println("Enter node filename:");
                String nodeFile = scanner.nextLine();
                graphReader.readNodes(graph, nodeFile);
                break;
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, ioException.getMessage());
                System.out.println("Error reading file! Please use a valid file!");
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage());
                System.out.println("Problem parsing file! Please use a valid file");
            }
        }

        // Read k
        while (true) {
            try {
                System.out.println("Enter value of k:");
                k = scanner.nextInt();
                if (k < 1) {
                    throw new Exception("Non-positive integer entered!");
                }
                break;
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage());
                System.out.println("Please enter a valid integer!");
            }
        }

        // Run BFS algorithm and write to file
        try {
            BFS bfs = new BFS(graph);
            logger.log(Level.INFO, "Running BFS...");
            bfs.execute();
            logger.log(Level.INFO, "Writing BFS results...");
            new ResultWriter(bfs.getFileName()).writeShortestPath(graph.getNodes(), bfs.getPreviousNodes());
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, ioException.getMessage());
            System.out.println("Error writing BFS file!");
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            System.out.println("Problem executing BFS algorithm!");
        }

        // Run modified BFS algorithm and write to file
        try {
            ModifiedBFS modifiedBFS = new ModifiedBFS(graph, k);
            logger.log(Level.INFO, "Running modified BFS...");
            modifiedBFS.execute();
            logger.log(Level.INFO, "Writing modified BFS results...");
            new ResultWriter(modifiedBFS.getFileName()).writeKShortestPaths(graph.getNodes(),
                    modifiedBFS.getVisitedOrder(), modifiedBFS.getPartialMinDistanceSpanningTrees());
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, ioException.getMessage());
            System.out.println("Error writing BFS file!");
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            System.out.println("Problem executing BFS algorithm!");
        }
    }
}
