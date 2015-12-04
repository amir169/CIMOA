import core.math.Vector2D;

/**
 * Created by Amir Shams on 12/1/2015.
 */
public class Position
{
    public Position(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    int x;
    int y;
    public boolean equals(Position that)
    {
        if(this.x == that.x && this.y == that.y)
            return true;
        return false;
    }

    public static Position getPos(Vector2D v){
        Position p=new Position(v.x,v.y);
        return p;
    }
    public double getDistance(Position that)
    {
        return Math.sqrt((this.x - that.x)*(this.x - that.x) + (this.y - that.y)*(this.y - that.y));
    }
}
