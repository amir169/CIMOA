import client.WorldModel;

/**
 * Created by Amir Shams on 11/29/2015.
 */
public class PathData
{
    int [][] distance;
    String [][] parent;
    int width,height;
    Position source;
    public PathData(int[][] matrix,Position source)
    {
        this.source = source;
        this.width = matrix.length;
        this.height = matrix[0].length;

        parent = new String[width][height];
        distance = new int[width][height];
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
            {
                distance[i][j] = 1000; //inf
                parent[i][j] = "";
            }

        distance[source.x][source.y] = 0;
    }
    public String toPath(Position destination)
    {
        String path = "";

        while(!destination.equals(source))
        {
            String move = parent[destination.x][destination.y];
            path += move;
            if(move == "S")
                destination = new Position(destination.x,destination.y - 1);
            else if(move == "N")
                destination = new Position(destination.x,destination.y + 1);
            else if(move == "W")
                destination = new Position(destination.x + 1,destination.y);
            else
                destination = new Position(destination.x - 1,destination.y);
        }

        return new StringBuilder(path).reverse().toString();
    }
}
