import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.out;

class Algorithm{


    //algorithm Dijksta's
    static int [] dijkstra(ArrayList<Integer>[] list, ArrayList<Integer>[] wt, int start) {
        ArrayList<Integer>[] graph=new ArrayList[list.length];

        for (int i = 0; i < list.length; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i=0;i<list.length;i++){
            for (int j=0;j<list[i].size();j++){
                if (list[i].get(j)!=null){
                    graph[i].add(list[i].get(j)-1);
                }
            }
        }

        ArrayList<Integer>[] weight=new ArrayList[wt.length];
        for (int i = 0; i < wt.length; i++) {
            weight[i] = new ArrayList<Integer>();
        }

        for (int i=0;i<wt.length;i++){
            for (int j=0;j<wt[i].size();j++){
                if (weight[i].size()<graph[i].size()) {
                    weight[i].add(wt[i].get(j));
                    weight[graph[i].get(j)].add(wt[i].get(j));
                }
            }
        }

        boolean[] visit = new boolean[graph.length];
        int[] dist = new int[graph.length];
        int [] root=new int[graph.length];
        for (int i = 0; i < dist.length; i++) {
            if (i != start) {
                dist[i] = Integer.MAX_VALUE;
            }
            else {
                dist[i] = 0;
            }
        }
        //decision
        for (int iterator = 0; iterator < graph.length; iterator++) {
            int temp=-1;
            int tempresult=Integer.MAX_VALUE;
            for (int i = 0; i < visit.length; i++) {
                if (!visit[i] && dist[i] < tempresult) {
                    temp = i;
                    tempresult = dist[i];
                }
            }

            visit[temp] = true;
            for (int i = 0; i < graph[temp].size(); i++) {
                if ((weight[temp].get(i) + dist[temp]) < dist[graph[temp].get(i)]) {
                    dist[graph[temp].get(i)] = dist[temp] + weight[temp].get(i);
                    root[graph[temp].get(i)]=temp;
                }
            }
        }
        return root;
    }
}