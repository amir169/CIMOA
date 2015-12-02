import core.math.Vector2D;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Amir Shams on 11/29/2015.
 */
public class SearchAlgorithms
{
    int xMoves[] = {0,0,1,-1};
    int yMoves[] = {1,-1,0,0};
    String directions[] = {"S", "N", "E", "W"};
    private Queue<Position> Q = new LinkedList<Position>();

    private boolean passable(int[][] matrix,int x,int y)
    {
        if(x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length)
            return false;

        if(matrix[x][y] != 0)
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

    public PathData BFS(int[][] matrix,Position source)
    {

        PathData pathData = new PathData(matrix,source);
        Q.add(source);
        Position current;
        int x,y;

        while(!Q.isEmpty())
        {
            current = Q.remove();

            for(int i=0;i<xMoves.length;i++)
            {

                x = current.x + xMoves[i];
                y = current.y + yMoves[i];

                if (passable(matrix, x, y))
                {
                    if(pathData.distance[x][y] == 1000)
                    {
                        Q.add(new Position(x, y));
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
