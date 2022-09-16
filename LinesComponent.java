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

public class LinesComponent extends JComponent{

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

public void clearLines() {
    lines.clear();
    repaint();
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Line line : lines) {
        g.setColor(line.color);
        g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }
}

public static void main(String[] args) {
    JFrame testFrame = new JFrame();
    testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    final LinesComponent comp = new LinesComponent();

    comp.setPreferredSize(new Dimension(1200, 600));
    testFrame.getContentPane().add(comp, BorderLayout.CENTER);
    testFrame.pack();
    testFrame.setVisible(true);
    testFrame.setResizable(true);

    SymbolGraph ur = new SymbolGraph("nys.txt");
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
   System.out.print(maxLong);

   for (int i = 0; i < ur.E(); i++){
    e1= ur.e1(i);
    e2= ur.e2(i);
    y1 = ur.Lats(e1);
    y2 = ur.Lats(e2);
    x1 = ur.Longs(e1);
    x2 = ur.Longs(e2);




    Color black = new Color(0,0,0);
    comp.addLine(((int)((x1-minLong)*300/(maxLong-minLong))), (int)((y1-maxLat)*300/(minLat-maxLat)),
    (int)((x2-minLong)*300/(maxLong-minLong)), (int)((y2-maxLat)*300/(minLat-maxLat)) , black);



    System.out.println(((x1-minLong)*700/(maxLong-minLong)));
    //comp.addLine(0, 0, 700,700 , black);
}


   /*  newLineButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int x1 = (int) (Math.random()*320);
            int x2 = (int) (Math.random()*320);
            int y1 = (int) (Math.random()*200);
            int y2 = (int) (Math.random()*200);
            Color randomColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
            comp.addLine(x1, y1, x2, y2, randomColor);
        }
    });
    clearButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            comp.clearLines();
        }
    }); */
    testFrame.pack();
    testFrame.setVisible(true);
    testFrame.setResizable(true);


}

}
