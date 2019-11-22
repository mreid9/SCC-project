// Java Program to demonstrate adjacency list
// representation of graphs
import java.util.*;
import java.io.File;
import static java.lang.System.*;

public class Driver
{
  static Graph graph;

  public static void initializeFromFile(String fname) {
    ArrayList<String> input = new ArrayList<String>();
    Scanner scanner;
    try {
      // initializing a file can throw a FileNotFoundException,
      // hence the try/catch block
      scanner = new Scanner(new File(fname));
      while (scanner.hasNextLine())
        input.add(scanner.nextLine());
      int vertices = Integer.parseInt(input.get(0));
      graph = new Graph(vertices);

      // remove size of graph from list
      input.remove(0);
      String[] numbers;
      for (String line : input) {
        numbers = line.split(" ");
        graph.addEdge(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
      }
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
    
    out.println("========= GRAPH =======")
    graph.printGraph();
    Graph transpose = graph.transpose();
    out.println("========= GRAPH TRANSPOSE =======");
    transpose.printGraph();
    out.println("========= DFS =======");
    graph.dfs();
  }
}
