import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DrawColoring {
    static void drawColoring (ArrayList<Integer>[] list, ArrayList<Integer>[] weight){
        UndirectedSparseMultigraph<Integer, Edge> graph = Input.Graph(list, weight);
        Layout<Integer, Edge> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<Integer, Edge> vv =new BasicVisualizationServer<Integer, Edge>(new FRLayout<Integer, Edge>(graph));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =400;
        int height =400;
        vv.setPreferredSize(new Dimension(width, height));
        int [] color=Coloring.coloring(list);
        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            public Paint transform(Integer i) {
                i--;
                if (color[i]%4==0) {
                    return Color.getHSBColor(1 * color[i], 3 * color[i], 9 * color[i]);
                }
                if (color[i]%4==1) {
                    return Color.getHSBColor(4 * color[i], 6 * color[i], 40 * color[i]);
                }
                if (color[i]%4==2) {
                    return Color.getHSBColor(10 * color[i], 20 * color[i], 30 * color[i]);
                }
                if (color[i]%4==3) {
                    return Color.getHSBColor(17 * color[i], 31 * color[i], 53 * color[i]);
                }
                else{
                    return Color.getHSBColor(53 * color[i], 31 * color[i], 17 * color[i]);
                }
            }
        };

        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
            public Stroke transform(Edge s) {
                return edgeStroke;
            }
        };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);//цвет вершин
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);//вывод пунктирного отрезка
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());//вывод названия вершин
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller()); //вывод веса графа
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);


        JFrame frame = new JFrame("Раскраска");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}