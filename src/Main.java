import edu.uci.ics.jung.graph.*;


import java.awt.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer>[] list = Input.inputVertex();
        ArrayList<Integer>[] weight = Input.inputEdges(list);
     /* int [] root=Algorithm.dijkstra(list,weight,0);
      for (int i=0;i<root.length;i++){
          out.print(root[i]+" ");
      }*/
        Coloring.coloring(list);
        DrawGraph.drawGraph(list, weight);
        //DrawDijkstra.drawDijkstra(list,weight);
        DrawColoring.drawColoring(list,weight);
    }
}
