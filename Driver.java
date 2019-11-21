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
      // get each edge
      for(Integer edge: graph.adjList.get(v)) {
        out.print(v + " -> " + edge + "  ");
      }
      out.println("\n");
    }
  }

  public static ArrayList<String> readFile(String fname) {
    ArrayList<String> input = new ArrayList<String>();
    Scanner scanner;
    try {
      scanner = new Scanner(new File(fname));
      while (scanner.hasNextLine()) input.add(scanner.nextLine());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return input;
  }


  public static void main(String args[])
  {
    if (args.length < 1) {
      out.println("Incorrect number of args: java Driver <FileName>.txt");
      return;
    }

    ArrayList<String> lines = readFile(args[0]);
    int vertices = Integer.parseInt(lines.get(0));

    out.println("number of lines: " + lines.size());
    Graph graph = new Graph(vertices);
    lines.remove(0);

    String[] numbers;
    for (String line : lines) {
      numbers = line.split(" ");
      if (numbers.length == 2)
        addEdge(graph, Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
    }

    printGraph(graph);
  }
}
