import core.math.Vector2D;

import java.util.LinkedList;
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
