//Some of the ideas in here are taken from Algorithms book by Sedgewick and Wayne I editted most of there functions to
//work better with the symbol graph rather than a typical graph.
public class Graph {

private final int V;
private int E;
private Bag [] adj;

public Graph(int V) {

	this.V = V; this.E = 0;
	adj = (Bag []) new Bag[V]; // Create array of lists.
	for (int v = 0; v < V; v++) // Initialize all lists
	adj[v] = new Bag (); // to empty.
	}

public int V() { return V; }
public int E() { return E; }

public void addEdge(int v, int w) {
	adj[v].add(w); // Add w to v’s list.
	adj[w].add(v); // Add v to w’s list.
	E++;
	}

public Iterable<Integer> adj(int v)
{ return adj[v]; }

}
