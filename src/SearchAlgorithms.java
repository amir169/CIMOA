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
    int zarib;
    int zarib2;
    int bfsNodes;
    int AStarNodes;
    int[][] worldMatrix;
    int xMoves[] = {0,0,1,-1};
    int yMoves[] = {1,-1,0,0};
    String directions[] = {"S", "N", "E", "W"};

    Vector2D myCastelpos,theirCastlePOs;

    public SearchAlgorithms(int[][] worldMatrix,int zarib,int zarib2,Vector2D myCastelpos,Vector2D theirCastlePOs) {
        this.worldMatrix = worldMatrix;
        this.myCastelpos=myCastelpos;
        this.theirCastlePOs=theirCastlePOs;
        bfsNodes = 0;
        this.zarib=zarib;
        this.zarib2=zarib2;
    }

    private boolean passable(int x,int y)
    {
        if(x < 0 || y < 0 || x >= worldMatrix.length || y >= worldMatrix[0].length)
            return false;

        if(worldMatrix[x][y] != 0 || (x==myCastelpos.x && y==myCastelpos.y))
            return false;

        if(theirCastlePOs!=null)
            if((x==theirCastlePOs.x && y==theirCastlePOs.y))
                return false;

        return true;
    }

    private boolean passable(Vector2D v)
    {
        return passable(v.x,v.y);
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

    public PathData LRTAStar(Heuristics heuristic,Vector2D src,Vector2D dst,int nodeLimit)
    {
        boolean findDest=false;
        AStarNodes = 0;
        PathData pathData= new PathData(worldMatrix,Position.getPos(src));
        Comparator<Vector2D> LRTAStarComp=new Comparator<Vector2D>() {

            int h(Vector2D v){
                int count=0;
                for (int i = 0; i < 4; i++) {
                    if(passable(v.x+xMoves[i],v.y+yMoves[i]) )
                        count+=heuristic.LRTAStarmatrix[v.x+xMoves[i] ][v.y+yMoves[i] ];
                    else
                        count+= heuristic.LRTAStarINF;
                }
                heuristic.LRTAStarmatrix[v.x][v.y]= count / 4;
                if(count<0)return  zarib*v.getDistance(dst);
                return zarib2*(count/4)+ zarib*v.getDistance(dst);
                //return v.getDistance(dst);
            }

            @Override
            public int compare(Vector2D o1, Vector2D o2) {
                int f1=h(o1)+pathData.distance[o1.x][o1.y];
                int f2=h(o2)+pathData.distance[o2.x][o2.y];
                return f1-f2;//must test;
            }
        };
        int minF=LRTAStarH(heuristic,src,dst);
        Vector2D minFV=src;
        PriorityQueue<Vector2D> LRTAStarQ=new PriorityQueue<>(LRTAStarComp);
        LRTAStarQ.add(src);
        while (!LRTAStarQ.isEmpty() && AStarNodes < nodeLimit){

            AStarNodes++;
            Vector2D current=LRTAStarQ.remove();

            if(LRTAStarH(heuristic,current,dst)+pathData.distance[current.x][current.y]<=minF){
                minF=LRTAStarH(heuristic,src,dst)+pathData.distance[current.x][current.y];
                minFV=current;
            }

            if(current.equals(dst)){
                findDest=true; break;}
            for (int i = 0; i < 4; i++) {
                Vector2D child=new Vector2D(current.x+xMoves[i],current.y+yMoves[i]);
                if(passable(child)){
                    int lastF=pathData.distance[child.x][child.y]+LRTAStarH(heuristic,child,dst);
                    int currentF=pathData.distance[current.x][current.y]+LRTAStarH(heuristic,child,dst) + 1;
                    if(currentF<lastF){
                        pathData.distance[child.x][child.y]=pathData.distance[current.x][current.y]+1;
                        pathData.parent[child.x][child.y]=directions[i];
                        LRTAStarQ.add(child);
                    }
                }
            }
        }

        if(findDest)
            pathData.dest=Position.getPos(dst);
        else
        {
            pathData.dest=Position.getPos(minFV);
        }
        return pathData;
    }

    private int LRTAStarH(Heuristics heuristic,Vector2D v,Vector2D dst) {
        int count=0;
        for (int i = 0; i < 4; i++) {
            if(passable(v.x+xMoves[i],v.y+yMoves[i]) )
                count+=heuristic.LRTAStarmatrix[v.x+xMoves[i] ][v.y+yMoves[i] ];
            else
                count+= (0 - heuristic.LRTAStarINF);
        }
        if(count<0)return  zarib*v.getDistance(dst);
        return zarib2*(count/4)+ zarib*v.getDistance(dst);
    }

    private void printheuMat(Heuristics heuristics) {
        int m[][]=heuristics.LRTAStarmatrix;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[j][i]+" ");
            }
            System.out.println();
        }
    }

    public PathData BFS(Position source,int nodesLimit)
    {
        Queue<Position> bfsQ = new LinkedList<Position>();
        PathData pathData = new PathData(worldMatrix,source);
        bfsQ.add(source);
        Position current;
        int x,y;

        while(!bfsQ.isEmpty())
        {
            current = bfsQ.remove();
            bfsNodes++;
            if(bfsNodes > nodesLimit)
                break;

            for(int i=0;i<xMoves.length;i++)
            {

                x = current.x + xMoves[i];
                y = current.y + yMoves[i];

                if (passable(x, y))
                {
                    if(pathData.distance[x][y] == 10000)
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

    public NeighboursData BFS(int[][] contentMatrix, Position source,int nodesLimit)
    {
        Queue<Position> bfsQ = new LinkedList<Position>();
        NeighboursData neighboursData = new NeighboursData();
        PathData pathData = new PathData(worldMatrix,source);
        bfsQ.add(source);
        Position current;
        int x,y;

        while(!bfsQ.isEmpty())
        {
            current = bfsQ.remove();
            bfsNodes++;
            if(bfsNodes > nodesLimit)
                break;

            for(int i=0;i<xMoves.length;i++)
            {

                x = current.x + xMoves[i];
                y = current.y + yMoves[i];

                if (passable(x, y))
                {
                    if(pathData.distance[x][y] == 10000)
                    {
                        if(contentMatrix[x][y]/10 == 1)
                        {
                            if (contentMatrix[x][y] % 10 == 1)
                                neighboursData.myWarriors.add(current);
                            if(contentMatrix[x][y] % 10 == 2)
                                neighboursData.myWorkers.add(current);
                        }
                        if(contentMatrix[x][y] / 10 == 2)
                        {
                            if(contentMatrix[x][y] % 10 == 1)
                                neighboursData.theirWarriors.add(current);
                            if(contentMatrix[x][y] % 10 == 2)
                                neighboursData.theirWorkers.add(current);
                        }
                        bfsQ.add(new Position(x, y));
                        pathData.distance[x][y] = pathData.distance[current.x][current.y] + 1;
                        pathData.parent[x][y] = directions[i];
                    }
                }
            }
        }
        return neighboursData;

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

    public String hillClimbing(Heuristics heuristic,Position source,int turnNumner)
    {
        double resultValue = Double.MAX_VALUE;
        String resultMove = "";
        double badness = 0.0;

        int x,y;
        for(int i=0;i<xMoves.length;i++)
        {
            x = source.x + xMoves[i];
            y = source.y + yMoves[i];
            if (passable(x, y))
            {
                if (heuristic.darknessMatrix[x][y] + 4.0/(double)(new Vector2D(x,y).getDistance(theirCastlePOs)) < resultValue)
                {
                    resultValue = heuristic.darknessMatrix[x][y] + 4.0/(source.getDistance(Position.getPos(theirCastlePOs)));
                    resultMove = directions[i];
                }
            }
            else
                badness += 0.25;            //impassable cells are bad!
        }

        heuristic.darknessMatrix[source.x][source.y] = resultValue + 1 + badness;
        heuristic.lastSeenMatrix[source.x][source.y] = turnNumner;

        return resultMove;
    }
}
