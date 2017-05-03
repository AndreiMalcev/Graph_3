import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.out;

class Input{

    static  ArrayList<Integer>[] inputVertex() {
        Scanner in = new Scanner(System.in);
        out.println("Введите кол-во вершин графа:");
        int n = in.nextInt();
        String number1 = in.nextLine();
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        out.println("Введите список смежности графа");
        for (int i = 0; i < n; i++) {
            out.print(i+1+":");
            String number = in.nextLine();
            if (number != "") {
                for (String s : number.split(" ")) {
                    graph[i].add(Integer.parseInt(s));
                }
            }
        }
        return graph;
    }

    static ArrayList<Integer>[] inputEdges(ArrayList<Integer>[] list) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer>[] weight = new ArrayList[list.length];
        for (int i = 0; i < list.length; i++) {
            weight[i] = new ArrayList<Integer>();
        }
        out.println("Введите вес ребер:");
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                if (i < list[i].get(j)){
                    out.print(i+1+"-"+list[i].get(j)+":");
                    weight[i].add(in.nextInt());
                }
                else{
                    weight[i].add(null);
                }
            }
            if (weight[i].size()==0){
                weight[i]=null;
            }
        }
        return weight;
    }

    static UndirectedSparseMultigraph<Integer, Edge> Graph(ArrayList<Integer> [] list, ArrayList[] weight) {
        UndirectedSparseMultigraph<Integer, Edge> graph = new UndirectedSparseMultigraph<Integer, Edge>();
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                if (weight[i]!=null && weight[i].get(j)!=null)
                    graph.addEdge(new Edge(weight[i].get(j).toString()), i+1, list[i].get(j));
            }
        }
        return graph;
    }
}
