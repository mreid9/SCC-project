// Java Program to demonstrate adjacency list
// representation of graphs
import java.util.ArrayList;
import java.util.List;

public class Driver
{
    static class Graph
    {
        int V;
        ArrayList<List<Integer>> adjList;

        Graph(int V)
        {
            this.V = V;
            adjList = new ArrayList<List<Integer>>();
            for(int i = 0; i < V ; i++)
                adjList.add(new ArrayList<Integer>());
        }
    }

    // Adds an edge to an undirected graph
    static void addEdge(Graph graph, int src, int dest)
    {
        // Add an edge from src to dest.
        graph.adjList.get(src).add(dest);
    }

    // A utility function to print the adjacency list
    // representation of graph
    static void printGraph(Graph graph)
    {
        for(int v = 0; v < graph.V; v++)
        {
            System.out.println("Outgoing edges of vertex "+ v);
            for(Integer edge: graph.adjList.get(v)) {
                System.out.print(v + " -> " + edge + "  ");
            }
            System.out.println("\n");
        }
    }

    // Driver program to test above functions
    public static void main(String args[])
    {
        // create the graph given in above figure
        int V = 5;
        Graph graph = new Graph(V);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 4);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);

        printGraph(graph);
    }
}
