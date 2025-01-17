import java.util.*;

public class aimllab2 {

    public static class Edge {
        int neighbor;
        int weight;

        public Edge(int neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

    public static class Graph {
        Map<Integer, List<Edge>> adjList = new HashMap<>();

        public void addEdge(int u, int v, int weight) {
            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.get(u).add(new Edge(v, weight));

            adjList.putIfAbsent(v, new ArrayList<>());
            adjList.get(v).add(new Edge(u, weight)); // For undirected graph
        }
    }

    public static class Result {
        int minCost;
        List<Integer> bestPath;

        public Result(int minCost, List<Integer> bestPath) {
            this.minCost = minCost;
            this.bestPath = bestPath;
        }
    }

    public static Result tspDfs(Graph graph, int startNode) {
        List<Integer> nodes = new ArrayList<>(graph.adjList.keySet());
        nodes.remove((Integer) startNode);

        int minCost = Integer.MAX_VALUE;
        List<Integer> bestPath = new ArrayList<>();

        List<List<Integer>> permutations = generatePermutations(nodes);

        for (List<Integer> perm : permutations) {
            List<Integer> currentPath = new ArrayList<>();
            currentPath.add(startNode);
            currentPath.addAll(perm);
            currentPath.add(startNode);

            int currentCost = calculatePathCost(graph, currentPath);

            if (currentCost != Integer.MAX_VALUE && currentCost < minCost) {
                minCost = currentCost;
                bestPath = new ArrayList<>(currentPath);
            }
        }

        return new Result(minCost, bestPath);
    }

    private static List<List<Integer>> generatePermutations(List<Integer> nodes) {
        List<List<Integer>> permutations = new ArrayList<>();
        permute(nodes, 0, permutations);
        return permutations;
    }

    private static void permute(List<Integer> nodes, int index, List<List<Integer>> permutations) {
        if (index == nodes.size()) {
            permutations.add(new ArrayList<>(nodes));
            return;
        }
        for (int i = index; i < nodes.size(); i++) {
            Collections.swap(nodes, i, index);
            permute(nodes, index + 1, permutations);
            Collections.swap(nodes, i, index);
        }
    }

    private static int calculatePathCost(Graph graph, List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            int v = path.get(i + 1);

            boolean edgeFound = false;
            for (Edge edge : graph.adjList.get(u)) {
                if (edge.neighbor == v) {
                    cost += edge.weight;
                    edgeFound = true;
                    break;
                }
            }
            if (!edgeFound) {
                return Integer.MAX_VALUE; // No valid edge
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        System.out.println("Enter the number of edges:");
        int edges = scanner.nextInt();

        System.out.println("Enter edges in the format: <node1> <node2> <weight>");
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(u, v, weight);
        }

        System.out.println("Enter the starting node:");
        int startNode = scanner.nextInt();

        Result result = tspDfs(graph, startNode);

        if (result.minCost == Integer.MAX_VALUE) {
            System.out.println("No valid TSP path exists.");
        } else {
            System.out.println("Minimum cost: " + result.minCost);
            System.out.println("Best path: " + result.bestPath);
        }
    }
}
