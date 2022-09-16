//Some of the ideas in here are taken from Algorithms book by Sedgewick and Wayne I editted most of there functions to
//work better with the symbol graph rather than a typical graph.
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SymbolGraph2 {


private ST<String, Integer> st;
private ArrayList<String> Keys;
public int vertex = 0;
public ArrayList<Double> Lats;
public ArrayList<Double> Longs;
public int E = 0;
private ArrayList<String> roads;
public ArrayList<Integer> e1;
public ArrayList<Integer> e2;
private double maxLat;
private double maxLong;
private double minLat;
private double minLong;
private EdgeWeightedDigraph G;
public SymbolGraph2(String fileName1) {

	st = new ST<String, Integer>();

	  String temp = "";
      double Lat;
      double Long;
      Keys = new ArrayList<String>();
      Lats =  new ArrayList<Double>();
      Longs =  new ArrayList<Double>();
      String temp2 = "";
      int tempInt1;
      int tempInt2;

      e1 =  new ArrayList<Integer>();
      e2 =  new ArrayList<Integer>();

	 try {
      File myObj = new File(fileName1);
      Scanner scnr = new Scanner(myObj);


      while (scnr.hasNext()) {
      	if (scnr.next().equals("i")){
      		temp = scnr.next();
      		//System.out.println(temp);
      		st.put(temp, st.size());
      		Lat = scnr.nextDouble();
      		Long = scnr.nextDouble();
      		Keys.add(temp);
      		Lats.add(Lat);
      		Longs.add(Long);
      		vertex++;
      	}

      else {
      	scnr.next();
      	scnr.next();
      	scnr.next();
      }
      }


       scnr.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
	}


	G = new EdgeWeightedDigraph(st.size());
	DirectedEdge tempe;
	double dis;
	roads = new ArrayList<String>();
	try {
      File myObj = new File(fileName1);
      Scanner scnr = new Scanner(myObj);

      while (scnr.hasNext()) {
      	if (scnr.next().equals("r")){
      		temp = scnr.next();

      		roads.add(temp);

      		temp = scnr.next();
      		temp2 = scnr.next();
      		tempInt1 = st.get(temp);
      		tempInt2 = st.get(temp2);

      		dis = Math.sqrt(Math.pow(Lats(tempInt1)-Lats(tempInt2), 2))+
      		(Math.pow(Longs(tempInt1)-Longs(tempInt2),2)) ;
      		tempe = new DirectedEdge (tempInt1, tempInt2,  dis);

      		G.addEdge(tempe);
          tempe = new DirectedEdge(tempInt2, tempInt1,  dis);
          G.addEdge(tempe);

      		e1.add(tempInt1);
      		e2.add(tempInt2);

      		E++;


      	}

      else {
      	scnr.next();
      	scnr.next();
      	scnr.next();
      }
      }


       scnr.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
	}
	minLat = Lats(0);
   maxLat = Lats(0);
   minLong= Longs(0);
   maxLong= Longs(0);
for (int i = 0; i < vertex; i++){
    if (Lats(i) < minLat)
        minLat = Lats(i);

    if (Lats(i) > maxLat)
        maxLat = Lats(i);

    if (Longs(i) < minLong)
        minLong = Longs(i);

    if (Lats(i) > maxLong)
        maxLong = Longs(i);
   }



}
public boolean contains(String s) { return st.contains(s); }
public int index(String s) { return st.get(s); }
public String name(int v) { return Keys.get(v); }
public EdgeWeightedDigraph G() { return G; }

public int e1(int s){
	return e1.get(s);
}
public int e2(int s){
	return e2.get(s);
}

public int E (){ return E;}


public double Longs(int s){
	return Longs.get(s);
}

public double Lats(int s){
	return Lats.get(s);
}

public double maxLat(){return maxLat;}
public double maxLong(){return maxLong;}
public double minLat(){return minLat;}
public double minLong(){return minLong;}

public String roads(int s){
	return roads.get(s);
}
}
