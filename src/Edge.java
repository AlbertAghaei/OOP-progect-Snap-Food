import java.util.ArrayList;

public class Edge
{
    static ArrayList<Edge> edges = new ArrayList<>();
    Node node1;// salam hoora
    Node node2;
    int weight;
    int ID;
    Edge(int ID,Node first,Node second, int weight)
    {
        this.ID=ID;
        this.node1=first;
        this.node2=second;
        this.weight=weight;
        ///read from database the last ID and give the next one to this comment
    }
    public static Edge getEdgeByID(int ID)
    {
        for(int i=0; i<edges.size(); i++)
            if(edges.get(i).ID==ID)
                return edges.get(i);
        return null;
    }
}
