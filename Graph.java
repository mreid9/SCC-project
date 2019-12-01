import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

import static java.lang.System.*;

public class Graph
{
  int V;
  ArrayList<List<Integer>> adjList;
  ArrayList<List<Integer>> tAdjs;

  Graph(int V)
  {
    this.V = V;
    adjList = new ArrayList<List<Integer>>();
    // for(int i = 0; i < V ; i++)
    //   adjList.add(new ArrayList<Integer>());
    tAdjs = new ArrayList<List<Integer>>();
    for(int i = 0; i < V ; i++) {
      adjList.add(new ArrayList<Integer>());
      tAdjs.add(new ArrayList<Integer>());
    }
  }

  // add edge to directed graph
  public void addEdge(int src, int dest) {
    adjList.get(src).add(dest);
  }

  public void tAddEdge(int s, int d) {
    tAdjs.get(s).add(d);
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

  // iterates over vertices and prints outgoing edges
  public void tPrintGraph() {
    for(int v = 0; v < V; v++)
    {
      out.println("Outgoing edges on vertex "+ v);
      for(Integer edge: tAdjs.get(v))
      out.print(v + "->" + edge + "  ");
      out.println();
    }
  }

  // computes and returns transpose from original Graph
  public Graph transpose() {
    Graph transpose = new Graph(V);
    for (int v = 0; v < V; v++) {
      for (Integer u : adjList.get(v)) {
        tAddEdge(u, v);
        transpose.addEdge(u, v);
      }
    }
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
          if (!visited[u]) s.push(u);
      }
    }

    String seen;
    for (int i=0; i<visited.length; i++) {
      seen = (visited[i]) ? "visited" : "not visited";
      out.println("Node " + i + " " + seen);
    }
  }

  public void kosarajus() {
    Stack<Integer> kosarajuStack = new Stack<Integer>();
    boolean[] kosarajuVisited = new boolean[V];

    // Sets that all vertices have not been visited yet
    for(int v = 0; v < V; v++) kosarajuVisited[v] = false;

    // Uses the helper method to make the stack have the correct order of vertices
    // to be popped
    for(int v = 0; v < V; v++) {
      if(kosarajuVisited[v] == false) kosarajuOrder(v, kosarajuVisited, kosarajuStack);
    }

    // Calls our transpose function to get the transposed graph
    Graph transposed = transpose();

    // Resets the visited array to ensure all vertices have not been checked yet since we are now dealing with the transposed graph
    for(int v = 0; v < V; v++) kosarajuVisited[v] = false;

    // for every vertex in the ordered stack
    while(!kosarajuStack.empty()) {
      int currVertex = (int)kosarajuStack.pop();

      // if the current vertex has not been visited
      if(kosarajuVisited[currVertex] == false) {
        kosarajuDFSPrinter(currVertex, kosarajuVisited);  // calls DFS helper with the current vertex and the transposed graph
        System.out.println();
      }
    }
  }

  // Helper method for kosarajus algorithm to properly order vertices based on each vertex's finishing time in a DFS
  public void kosarajuOrder(int vertexNumber, boolean[] visited, Stack s) {
    visited[vertexNumber] = true;     // This vertex has now been visited

    // For all of the current vertex's adjacents
    for(Integer adj : adjList.get(vertexNumber)) {
      // If the vertex has not been visited, visit it (DFS)
      if(visited[adj] == false) kosarajuOrder(adj, visited, s);
    }

    // After all the vertices that are reachable from the current vertex are visited (DFS)
    // Add the current vertex to the stack
    s.push(new Integer(vertexNumber));
  }

  public void kosarajuDFSPrinter(int vertex, boolean[] visited) {
    visited[vertex] = true;               // This vertex has now been visited
    System.out.print(vertex + " ");       // Add this vertex to a SCC

    // For all of the current vertex's adjacents
    for(Integer adj : tAdjs.get(vertex)) {
      // If the vertex has not been visited, visit it (DFS)
      if(visited[adj] == false) kosarajuDFSPrinter(adj, visited);
    }
  }

  public void bfs(int vertex, boolean[] visited) {
    // BFS queue
    Queue<Integer> queue = new LinkedList<>();
    List<Integer> currentVertex;
    int v;

    visited[vertex] = true;
    queue.add(vertex);

    while (queue.size() != 0)
    {
      // Dequeue current vertex
      v = queue.poll();

      // visit each of its adjacents
      currentVertex = adjList.get(v);
      for (Integer u : currentVertex) {
        if (!visited[u])
        {
          visited[u] = true;
          queue.add(u);
        }
      }
    }

    String seen;
    for (int i=0; i<visited.length; i++) {
      seen = (visited[i]) ? "visited" : "not visited";
      out.println("Node " + i + " " + seen);
    }
  }

  public String isGraphSC() {
    String notSC = new String("Graph is not strongly connected");
    String SC = new String("Graph is strongly connected");
    boolean[] visited = new boolean[V];

    // call BFS from vertex 0
    bfs(0, visited);

    for (boolean v : visited)
      if (!v) return notSC;


    // create transpose
    Graph transposed = transpose();
    // unvisit verts for transpose
    for(int i = 0; i < V; i++)
      visited[i] = false;

    // BFS on transpose, still vertex 0
    transposed.bfs(0, visited);

    for (boolean v : visited)
      if (!v) return notSC;

    return SC;
  }

  private int tarjanId;
  private int[] tarjanIds;
  private int[] tarjanLow;
  private Stack<Integer> tarjanStack;

  public void tarjan() {
    tarjanId = 0;
    tarjanIds = new int[V];
    tarjanLow = new int[V];
    tarjanStack = new Stack<Integer>();

    // Set all node id's to be unvisited
    for (int i = 0; i < V; i++) tarjanIds[i] = -1;

    // Iterate and run tarjanDFS on each node
    for (int i = 0; i < V; i++)
    if (tarjanIds[i] == -1) tarjanDFS(i);
  }

  // Recursive helper function for the tarjan algorithm
  private void tarjanDFS(int at) {
    tarjanStack.push(at);
    tarjanIds[at] = tarjanLow[at] = tarjanId++;

    // Loop through all adjacent nodes connected to this one
    for (Integer to: adjList.get(at)) {
      // If an adjacent node is unvisited, recursively run the DFS function on it
      if (tarjanIds[to] == -1) tarjanDFS(to);

      // When backtrack, if the new node is on the stack, set the current node's low to the minimum of the two lows
      if (tarjanStack.contains(to)) tarjanLow[at] = (tarjanLow[at] <= tarjanLow[to]) ? tarjanLow[at] : tarjanLow[to];
    }

    // Indicates the closing of an SCC, pop values of the SCC off the stack and print out the SCC
    if (tarjanIds[at] == tarjanLow[at]) {
      for (Integer node = tarjanStack.pop();; node = tarjanStack.pop()) {
        out.print(node + " ");
        tarjanLow[node] = tarjanIds[at];
        if (node == at) break;
      }
      out.println();
    }
  }
}
