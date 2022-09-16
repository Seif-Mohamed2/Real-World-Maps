//Some of the ideas in here are taken from Algorithms book by Sedgewick and Wayne I editted most of there functions to
//work better with the symbol graph rather than a typical graph.

public class Edge implements Comparable<Edge> {
private final int v;
private final int w;
private final double weight;
public Edge(int v, int w, double weight) {
this.v = v;
this.w = w;
this.weight = weight;
}
public double weight()
{ return weight; }
public int either()
{ return v; }
public int other(int vertex)
{
if (vertex == v) return w;
else if (vertex == w) return v;
else throw new RuntimeException("Inconsistent edge");
}
public int compareTo(Edge that)
{
if (this.weight() < that.weight()) return -1;
else if (this.weight() > that.weight()) return +1;
else return 0;
}
public String toString()
{ return String.format("%d-%d %.2f", v, w, weight); }

public int v1(){

	return v;
}

public int v2(){
	return w;
}
}
