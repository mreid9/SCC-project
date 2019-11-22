// Java Program to demonstrate adjacency list
// representation of graphs
import java.util.*;
import java.io.File;
import static java.lang.System.*;

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

  static Graph graph;



  static void addEdge(Graph graph, int src, int dest)
  {
    graph.adjList.get(src).add(dest);
  }

  static void printGraph(Graph graph)
  {
    // iterate over vertices
    for(int v = 0; v < graph.V; v++)
    {
      out.println("Outgoing edges of vertex "+ v);
      // get each edge of v
      for(Integer edge: graph.adjList.get(v)) {
        out.print(v + " -> " + edge + "  ");
      }
      out.println("\n");
    }
  }

  //
  public static void initializeFromFile(String fname) {
    ArrayList<String> input = new ArrayList<String>();
    Scanner scanner;
    try {
      scanner = new Scanner(new File(fname));
      while (scanner.hasNextLine())
        input.add(scanner.nextLine());
      int vertices = Integer.parseInt(input.get(0));
      graph = new Graph(vertices);
      // remove 0th element - size of graph
      input.remove(0);
      // adds vertices
      String[] numbers;
      for (String line : input) {
        numbers = line.split(" ");
        addEdge(graph, Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
      }
      input.remove(0);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void main(String args[])
  {
    if (args.length < 1) {
      out.println("Incorrect number of args: java Driver <FileName>.txt");
      return;
    }

    initializeFromFile(args[0]);
    printGraph(graph);
  }
}
