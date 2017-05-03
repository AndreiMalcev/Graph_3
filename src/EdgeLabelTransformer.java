import org.apache.commons.collections15.Transformer;

class EdgeLabelTransformer implements Transformer<Edge, String>
{
    @Override
    public String transform(Edge edge)
    {
        return edge.toString();
    }
}
