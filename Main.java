//The main file to run the program
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Main extends JComponent{
private static class Line{
    final int x1;
    final int y1;
    final int x2;
    final int y2;
    final Color color;
    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
}
private final LinkedList<Line> lines = new LinkedList<Line>();
public void addLine(int x1, int x2, int x3, int x4) {
    addLine(x1, x2, x3, x4, Color.black);
}
public void addLine(int x1, int x2, int x3, int x4, Color color) {
    lines.add(new Line(x1,x2,x3,x4, color));
    repaint();
}
/*public void clearLines() {
    lines.clear();
    repaint();
}*/
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Line line : lines) {
        g.setColor(line.color);
        g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }
}
//The main function handles different variations depending on the given input and then call different functions.

public static void main(String[] args) {
	//first case if the user only entered the text file and the command "show" then the map is outputed with java graphics

    if ((args.length == 2) && (args[1].equals("show"))){
    JFrame Frame = new JFrame();
    Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    final LinesComponent comp = new LinesComponent();

   //symbol graph is the calss of type syble graph used to convert the text input into a graph with verticies and edges having specific names.

   SymbolGraph ur = new SymbolGraph(args[0]);
   int e1;
   int e2;
   double x1;
   double x2;
   double y1;
   double y2;

   double minLat = ur.Lats(0);
   double maxLat = ur.Lats(0);
   double minLong= ur.Longs(0);
   double maxLong= ur.Longs(0);

   for (int i = 0; i < ur.vertex; i++){
    if (ur.Lats(i) < minLat)
        minLat = ur.Lats(i);

    if (ur.Lats(i) > maxLat)
        maxLat = ur.Lats(i);

    if (ur.Longs(i) < minLong)
        minLong = ur.Longs(i);

    if (ur.Lats(i) > maxLong)
        maxLong = ur.Longs(i);
   }

  //this for loop is just used to build the graph to draw the graph given each point coordinate.
   for (int i = 0; i < ur.E(); i++){
    e1= ur.e1(i);
    e2= ur.e2(i);
    y1 = ur.Lats(e1);
    y2 = ur.Lats(e2);
    x1 = ur.Longs(e1);
    x2 = ur.Longs(e2);

    Color black = new Color(0,0,0);
    Color red = new Color(255,0,0);
    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , black);

}
    comp.setPreferredSize(new Dimension(1200, 600));
    Frame.getContentPane().add(comp, BorderLayout.CENTER);
    Frame.pack();
    Frame.setVisible(true);
    Frame.setResizable(true);
   Frame.pack();
   Frame.setVisible(true);
   Frame.setResizable(true);

}





//Second case is when the user enter three inputs: text file, show command and Meridinamap command which computes the MST (minimum spanning tree) of the graph and draw it using java graphics
else if(args.length == 3 && args[2].equals("meridianmap") && args[1].equals("show")){
    JFrame Frame = new JFrame();
    Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    final LinesComponent comp = new LinesComponent();
    ArrayList<String> output = new ArrayList<String>();


    SymbolGraph ur = new SymbolGraph(args[0]);
   int e1;
   int e2;
   double x1;
   double x2;
   double y1;
   double y2;

   double minLat = ur.Lats(0);
   double maxLat = ur.Lats(0);
   double minLong=ur.Longs(0);
   double maxLong=ur.Longs(0);
   for (int i = 0; i < ur.vertex; i++){
    if (ur.Lats(i) < minLat)
        minLat = ur.Lats(i);

    if (ur.Lats(i) > maxLat)
        maxLat = ur.Lats(i);

    if (ur.Longs(i) < minLong)
        minLong = ur.Longs(i);

    if (ur.Lats(i) > maxLong)
        maxLong = ur.Longs(i);
   }

   //Lazy prism MST is the algorithm used to build the mst

   LazyPrimMST ur1 = new LazyPrimMST(ur.G());
    Color black = new Color(0,0,0);
    Color red = new Color(255,0,0);
   for (int i = 0; i < ur.E(); i++){
    e1= ur.e1(i);
    e2= ur.e2(i);
    y1 = ur.Lats(e1);
    y2 = ur.Lats(e2);
    x1 = ur.Longs(e1);
    x2 = ur.Longs(e2);


    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , black);

}

    int v1;
    int v2;


    while(!ur1.isEmpty()){
        int i = 0;
        if(ur1.edgep()!= null){
            v1 = ur1.edgep().v1();
            v2 = ur1.edgedq().v2();
            for (int j = 0; j < ur.E(); j++){
                if (v1 == ur.e1(j) && (v2 == ur.e2(j))){
                    output.add(ur.roads(j));

                    e1= ur.e1(j);
                    e2= ur.e2(j);
                    y1 = ur.Lats(e1);
                    y2 = ur.Lats(e2);
                    x1 = ur.Longs(e1);
                    x2 = ur.Longs(e2);
                    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
                    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , red);
                }
            }
        }

    }




    comp.setPreferredSize(new Dimension(1200, 600));
    Frame.getContentPane().add(comp, BorderLayout.CENTER);
    Frame.pack();
    Frame.setVisible(true);
    Frame.setResizable(true);
    Frame.pack();
   Frame.setVisible(true);
    Frame.setResizable(true);

      try {
      FileWriter myWriter = new FileWriter("output.txt");
      for (int i = 0; i < output.size(); i++)
      myWriter.write(output.get(i) + "\n");
      myWriter.close();
      System.out.println("");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}

//Third case when the user inputs textfile and the command meridianmap which just output the edges in the MST in a text file
else if(args.length == 2 && args[1].equals("meridianmap")){


    SymbolGraph ur = new SymbolGraph(args[0]);
   int e1;
   int e2;
   double x1;
   double x2;
   double y1;
   double y2;
   ArrayList<String> output = new ArrayList<String>();
   double minLat = ur.Lats(0);
   double maxLat = ur.Lats(0);
   double minLong=ur.Longs(0);
   double maxLong=ur.Longs(0);
   for (int i = 0; i < ur.vertex; i++){
    if (ur.Lats(i) < minLat)
        minLat = ur.Lats(i);

    if (ur.Lats(i) > maxLat)
        maxLat = ur.Lats(i);

    if (ur.Longs(i) < minLong)
        minLong = ur.Longs(i);

    if (ur.Lats(i) > maxLong)
        maxLong = ur.Longs(i);
   }
   LazyPrimMST ur1 = new LazyPrimMST(ur.G());


    int v1;
    int v2;


    while(!ur1.isEmpty()){
        int i = 0;
        if(ur1.edgep()!= null){
            v1 = ur1.edgep().v1();
            v2 = ur1.edgedq().v2();
            for (int j = 0; j < ur.E(); j++){
                if (v1 == ur.e1(j) && (v2 == ur.e2(j))){
                    output.add(ur.roads(j));


                }
            }
        }
        else {

        }
    }






      try {
      FileWriter myWriter = new FileWriter("output.txt");
      for (int i = 0; i < output.size(); i++)
      myWriter.write(output.get(i) + "\n");
      myWriter.close();
      System.out.println("");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}






//fourth case is one the user uses the command show and directions. Then the program outputs the shortest path direction from
//one points to another and show it using java graphics
else if(args.length == 5 && args[1].equals("show") && args[2].equals("directions")){
    JFrame Frame = new JFrame();
    Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    final LinesComponent comp = new LinesComponent();
    ArrayList<String> output = new ArrayList<String>();

    SymbolGraph2 ur = new SymbolGraph2(args[0]);
   int e1;
   int e2;
   double x1;
   double x2;
   double y1;
   double y2;

   double minLat = ur.Lats(0);
   double maxLat = ur.Lats(0);
   double minLong=ur.Longs(0);
   double maxLong=ur.Longs(0);
   for (int i = 0; i < ur.vertex; i++){
    if (ur.Lats(i) < minLat)
        minLat = ur.Lats(i);

    if (ur.Lats(i) > maxLat)
        maxLat = ur.Lats(i);

    if (ur.Longs(i) < minLong)
        minLong = ur.Longs(i);

    if (ur.Lats(i) > maxLong)
        maxLong = ur.Longs(i);
   }
   DijkstraSP2 ur1 = new DijkstraSP2(ur.G(),ur.index(args[3]));
    Color black = new Color(0,0,0);
    Color red = new Color(255,0,0);
   for (int i = 0; i < ur.E(); i++){
    e1= ur.e1(i);
    e2= ur.e2(i);
    y1 = ur.Lats(e1);
    y2 = ur.Lats(e2);
    x1 = ur.Longs(e1);
    x2 = ur.Longs(e2);


    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , black);

}

    int v1;
    int v2;
    DirectedEdge tempD;

    ur1.computeStack(ur.index(args[4]));

    while(!ur1.pathTo1().isEmpty()){
            tempD = ur1.pathTo1().pop();
            v1 = tempD.to();
            v2 = tempD.from();
            for (int j = 0; j < ur.E(); j++){
                if (v1 == ur.e1(j) && (v2 == ur.e2(j))){
                    output.add(ur.roads(j));

                    e1= ur.e1(j);
                    e2= ur.e2(j);
                    y1 = ur.Lats(e1);
                    y2 = ur.Lats(e2);
                    x1 = ur.Longs(e1);
                    x2 = ur.Longs(e2);
                    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
                    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , red);
                }

                else if (v1 == ur.e2(j) && (v2 == ur.e1(j))){
                    output.add(ur.roads(j));

                    e1= ur.e1(j);
                    e2= ur.e2(j);
                    y1 = ur.Lats(e1);
                    y2 = ur.Lats(e2);
                    x1 = ur.Longs(e1);
                    x2 = ur.Longs(e2);
                    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
                    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , red);
                }
            }


    }




    comp.setPreferredSize(new Dimension(1200, 600));
    Frame.getContentPane().add(comp, BorderLayout.CENTER);
    Frame.pack();
    Frame.setVisible(true);
    Frame.setResizable(true);
    Frame.pack();
   Frame.setVisible(true);
    Frame.setResizable(true);

      try {
      FileWriter myWriter = new FileWriter("output.txt");
      for (int i = 0; i < output.size(); i++)
      myWriter.write(output.get(i) + "\n");
      myWriter.close();
      System.out.println("");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}

//last case if when the user uses the command directions but not show so the directions are only written in the outputfile "output.txt"
else if(args.length == 4 && args[1].equals("directions")){


    SymbolGraph2 ur = new SymbolGraph2(args[0]);
   int e1;
   int e2;
   double x1;
   double x2;
   double y1;
   double y2;

   double minLat = ur.Lats(0);
   double maxLat = ur.Lats(0);
   double minLong=ur.Longs(0);
   double maxLong=ur.Longs(0);
   for (int i = 0; i < ur.vertex; i++){
    if (ur.Lats(i) < minLat)
        minLat = ur.Lats(i);

    if (ur.Lats(i) > maxLat)
        maxLat = ur.Lats(i);

    if (ur.Longs(i) < minLong)
        minLong = ur.Longs(i);

    if (ur.Lats(i) > maxLong)
        maxLong = ur.Longs(i);
   }
  // DijkstraSP ur1 = new DijkstraSP(ur.G(),ur.index(args[2]));
   DijkstraSP2 ur1 = new DijkstraSP2(ur.G(),ur.index(args[2]));
   //System.out.println(ur.index(args[2]));

   ArrayList<String> output = new ArrayList<String>();
    int v1;
    int v2;
    DirectedEdge tempD;

    ur1.computeStack(ur.index(args[3]));

    while(!ur1.pathTo1().isEmpty()){
            tempD = ur1.pathTo1().pop();
            //System.out.println(tempD);
            v1 = tempD.from();
            v2 = tempD.to();
            for (int j = 0; j < ur.E(); j++){
                if (v1 == ur.e1(j) && (v2 == ur.e2(j))){
                    output.add(ur.roads(j));



                }

                else if (v1 == ur.e2(j) && (v2 == ur.e1(j))){
                    output.add(ur.roads(j));



                }

            }




    }






      try {
      FileWriter myWriter = new FileWriter("output.txt");
      for (int i = 0; i < output.size(); i++)
      myWriter.write(output.get(i) + "\n");
      myWriter.close();
      System.out.println("");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
}



}

}
