import java.util.ArrayList;

class Coloring {

    private static boolean neighbors(ArrayList<Integer>[] graph, int u1, int u2){
        for (int i=0;i<graph[u1].size();i++){
            if (graph[u1].get(i)==(u2+1)){
                return true;
            }
        }
        return false;
    }


    public static int [] coloring (ArrayList<Integer>[] graph){
        int [] color= new int[graph.length];
        int i=0;
        boolean bool=false;
        int colors=1;
        while (i<graph.length){
            if (color[i]==0) {
                int j = 0;
                ArrayList<Integer> plurality= new ArrayList<Integer>();
                plurality.add(i);
                color[i] = colors;
                while (j < graph.length) {
                    for (int temp=0;temp<plurality.size();temp++){
                        if (neighbors(graph,j,plurality.get(temp)) || j==plurality.get(temp)){
                            bool=true;
                        }
                    }
                    if (!bool && color[j] == 0) {
                        color[j] = colors;
                        plurality.add(j);
                    }
                    j++;
                    bool=false;
                }
                colors++;
            }
            i++;
        }
        return color;
    }
}