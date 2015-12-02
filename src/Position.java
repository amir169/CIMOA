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
}
