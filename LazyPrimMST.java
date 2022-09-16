//Some of the ideas in here are taken from Algorithms book by Sedgewick and Wayne I editted most of there functions to
//work better with the symbol graph rather than a typical graph.
public class LazyPrimMST
{
private boolean[] marked; // MST vertices
private Queue<Edge> mst; // MST edges
private MinPQ<Edge> pq; // crossing (and ineligible) edges
public LazyPrimMST(EdgeWeightedGraph G)
{
pq = new MinPQ<Edge>();
marked = new boolean[G.V()];
mst = new Queue<Edge>();
visit(G, 0);
while (!pq.isEmpty())
{
Edge e = pq.delMin(); // Get lowest-weight
int v = e.either(), w = e.other(v); // edge from pq.
if (marked[v] && marked[w]) continue; // Skip if ineligible.
mst.enqueue(e); // Add edge to tree.
if (!marked[v]) visit(G, v); // Add vertex to tree
if (!marked[w]) visit(G, w); // (either v or w).
}
}
private void visit(EdgeWeightedGraph G, int v)
{ // Mark v and add to pq all edges from v to unmarked vertices.
marked[v] = true;
for (Edge e : G.adj(v))
if (!marked[e.other(v)]) pq.insert(e);
}
public Iterable<Edge> edges()
{ return mst; }

public Edge edgedq (){
	return mst.dequeue();
}

public Edge edgep (){
	return mst.peek();
}

public int edgeL (){
	return mst.size();
}

public boolean isEmpty(){
	return mst.isEmpty();
}
}
