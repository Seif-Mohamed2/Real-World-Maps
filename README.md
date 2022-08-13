# Real-World-Maps

<h2> Introduction </h2>

Real World maps is a project designed to take an input of coordinates and roads then convert them to an electronic map that can be displayed using java graphics. Moreover the program provides the two features:
1- Get the Minimum spanning tree of the graph and display it.
2-Tell the user the shortest path from one point to another.
<br>
<h2>Files in the program</h2>
ur.txt : example input file.<br>
Monroe.txt: example input file.<br>
Most files provided with this program are classes that are used to create objects in the main file.<br>
<strong>Main </strong>: contain main algorithm and it’s called to perform the other function <br>
<h2>Simple explanation </h2>
The program uses java graphics to represent real life maps of real locations. The vertices of the graph are represented with a symbol graph. The program also has two features: 
1- Finding the minimum spanning tree using Lazy Prim MST algorithm.
2- Find the shortest distance between two points using Dijkstra algorithm.


<h2>Examples/h2>
Input file: <br>
Example 1: 
```powershell
Java Main monroe.txt show
```
Monroe.txt file which contains the real coordinates of locations and roads in monroe county area.
<br>
The input file is represented as follows:<br>
Each line starts with either i for coordinate of point or r for road between two points. 
<img width="437" alt="image" src="https://user-images.githubusercontent.com/105813102/184509055-300d7f57-46bd-4393-a0df-83225875498b.png">
If you run this file with the command show you get this map drawn with java graphics:
—--------------- <br>
==========<br>
==========<br>
==========<br>

Example 2:
```powershell
Java Main ur.txt show meridinamap
```
Another simple input file example is ur.txt which represents the locations and roads in university of rochester. <br>
If the command meridinamap is used, the program returns an output.txt file in the same folder which includes the main file. The output file has only the name of the roads needed in the minimum spanning tree. The program also output the map with the minimum spanning tree colored in red as shown:
—--------------- <br>
==========<br>
==========<br>
==========<br>

<br>
Example 3:<br>

If the command directions is called with start point and target points, the program return a text file: output.txt in the same folder as main.java with which roads the user should follow to reach their destination in the least distance traveled possible.

```powershell
 Java Main ur.txt show directions WILDER LATTIMORE
```
 —--------------- <br>
==========<br>
==========<br>
==========<br>



<h2>How to run </h2>
In command line or terminal window go to the directory where you have your files, run:<br>

if you are on mac or linux 
```powershell
find "${dir:-.}" -name "*.java" > sources.txt
Javac @sources.txt
```

if you are on windows: <br>
```powershell
dir /s /B *.java > sources.txt
Javac @sources.txt
```

That compiles all files in the project.
Then run
```powershell
Java Main inputfile.txt [set of options]
```
For example: 
```powershell
Java Main ur.txt show
```
<strong>Or any of the examples mentioned above</strong><br>

Then you find the output in the output.txt file.

<h2>Detailed explanation</h2>

The Symbol graph is the most important thing in the program 
the symbol graph creates a simple graph but I attached many arraylists to it
to keep track of each variable

For instance, there is arraylist with the Strings related to each vertex
and two arraylists to the vericies attached to each edge 
and arraylist of strings that corrsponds to each edge

these data were very important to help convert the given data from strings
to integers then make operations and output strings again

So for Show:

the program creates an array of lines 
each line is created using the coordinates given by
starting and ending point of each edge
To make the graph with a decent size the minimum and maximum longitude and latitude
are computed and the graph is scaled

then using paint of JFrame to draw all lines

in case of MST and Shortest path a red lines were created


MST and Dijkstra's basically works in the same way
they are computed by creating a tree and this tree
is adding the shortest possible edge to to reach each 
vertex

The two algorithms are very well-known the only thing I added is
the manpulations with strings because as I mentioned before the algorithms
uses numbers to represent vertex and edge but the project requires using Strings 


For MST I used the lazyPRIMMST algorithm from the textbook but with Major edits
The edits basically are because my data is very different from what books assume
The data we have for the project are strings of names not just vertex with integers

Same goes for DijkstraSP2 is from the book but I editted it to be able to use it.

Most of the work lies in the symbol graph code which I almost created in my own 
the ideas are from the textbook but my implementation is very different (explained in details in OUTPUT file)

RUNTIME:

For show only the runtime is O(E+v) and that's simple
The algorithm just iterate for edges and verticies to scan them and then iterates again through edges
to determine the string attached to the found edge so it's simply O(E+E+V) = O(E+V)

For LazyPrim MST time is O(E logE) as the algorithm depends on puting the edges in priority queue
and for priority queue in the cost of insertion is logE and most and its done for E edges so a total of ElogE

For Dijkstra's algorithm the runtime is O(Elog(v)) because the algorithm depends on creating a tree of verticies
adding vertex to the tree costs log(v) and its done E time so a runtime of  O(Elog(v))




