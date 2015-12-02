import core.math.Vector2D;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Amir Shams on 11/29/2015.
 */
public class SearchAlgorithms
{
    int[][] worldMatrix;
    int xMoves[] = {0,0,1,-1};
    int yMoves[] = {1,-1,0,0};
    String directions[] = {"S", "N", "E", "W"};
    private Queue<Position> bfsQ = new LinkedList<Position>();

    public SearchAlgorithms(int[][] worldMatrix) {
        this.worldMatrix = worldMatrix;
    }

    private boolean passable(int x,int y)
    {
        if(x < 0 || y < 0 || x >= worldMatrix.length || y >= worldMatrix[0].length)
            return false;

        if(worldMatrix[x][y] != 0)
            return false;

        return true;
    }

    public PathData Dijkstra(int[][] matrix,Position pos)
    {
        //needs implementations
        return  null;
    }

    public PathData AStar(int[][] matrix,int sourceX,int sourceY)
    {
        //needs implementations
        return null;
    }

    public PathData LRTAStar(Heuristics heuristic,Vector2D src,Vector2D dst)
    {
        PathData pathData= new PathData(worldMatrix,Position.getPos(src));

        Comparator<Vector2D> LRTAStarComp=new Comparator<Vector2D>() {

            int h(Vector2D v){
                int count=0;
                for (int i = 0; i < 4; i++) {
                    count+=heuristic.LRTAStarmatrix[v.x+xMoves[i] ][v.y+yMoves[i] ];
                }
                heuristic.LRTAStarmatrix[v.x][v.y]= count/4;
                return (count/4)+v.getDistance(dst);
            }

            @Override
            public int compare(Vector2D o1, Vector2D o2) {
                int f1=h(o1)+pathData.distance[o1.x][o1.y];
                int f2=h(o2)+pathData.distance[o2.x][o2.y];
                return f2-f1;//must test;
            }
        };
        PriorityQueue<Vector2D> LRTAStarQ=new PriorityQueue<>(LRTAStarComp);
        return null;
    }


    public PathData BFS(Position source,int depthLimit)
    {

        PathData pathData = new PathData(worldMatrix,source);
        bfsQ.add(source);
        Position current;
        int x,y;

        while(!bfsQ.isEmpty())
        {
            current = bfsQ.remove();

            if(pathData.distance[current.x][current.y] > depthLimit)
                break;

            for(int i=0;i<xMoves.length;i++)
            {

                x = current.x + xMoves[i];
                y = current.y + yMoves[i];

                if (passable(x, y))
                {
                    if(pathData.distance[x][y] == 1000)
                    {
                        bfsQ.add(new Position(x, y));
                        pathData.distance[x][y] = pathData.distance[current.x][current.y] + 1;
                        pathData.parent[x][y] = directions[i];
                    }
                }
            }
        }
        return pathData;

    }

    public PathData DFS(int[][] matrix,int sourceX,int sourceY)
    {
        //needs implementations
        return null;
    }

    public PathData IterativeDeepening(int[][] matrix,int sourceX,int sourceY)
    {
        //needs implementations
        return null;
    }

}
