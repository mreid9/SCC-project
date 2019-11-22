import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.System.*;

public class Graph
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

  // add edge to directed graph
  public void addEdge(int src, int dest) {
    adjList.get(src).add(dest);
  }

  // iterates over vertices and prints outgoing edges
  public void printGraph() {
    for(int v = 0; v < V; v++)
    {
      out.println("Outgoing edges on vertex "+ v);
      for(Integer edge: adjList.get(v))
        out.print(v + "->" + edge + "  ");
      out.println();
    }
  }

  // computes and returns transpose from original Graph
  public Graph transpose() {
    Graph transpose = new Graph(V);
    for (int v = 0; v < V; v++)
        for (Integer u : adjList.get(v))
            transpose.addEdge(u, v);
    return transpose;
  }

  public boolean isEmpty() {
    return (this.V < 1);
  }

  public void dfs() {
      if (isEmpty()) return;
      Stack s = new Stack<Integer>();
      boolean[] visited = new boolean[V];
      int index = 0;
      s.push(index);

      while (!s.empty()) {
        index = Integer.valueOf(""+s.pop());

        if (!visited[index]) {
          visited[index] = true;
          for (Integer u : adjList.get(index))
            if (!visited[u])
              s.push(u);
        }
      }

      String seen;
      for (int i=0; i<visited.length; i++) {
        seen = (visited[i]) ? "visited" : "not visited";
        out.println("Node " + i + " " + seen);
      }
  }

  public void kosarajus() {
    // call DFS
    // compute transpose
    // call DFS on transpose
    // add an SCC each time a dead end is reached
  }
}
