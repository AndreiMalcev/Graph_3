import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DrawDijkstra {
    static void drawDijkstra (ArrayList<Integer>[] list, ArrayList<Integer>[] weight){
        UndirectedSparseMultigraph<Integer, Edge> graph = Input.Graph(list, weight);
        Layout<Integer, Edge> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<Integer, Edge> vv =new BasicVisualizationServer<Integer, Edge>(new FRLayout<Integer, Edge>(graph));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width =400;
        int height =400;
        vv.setPreferredSize(new Dimension(width, height));


        Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
            public Paint transform(Integer i) {
                if (i==1) return Color.GREEN;
                return Color.MAGENTA;
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


        JFrame frame = new JFrame("Алгоритм Дейкстры");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}