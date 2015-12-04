import java.util.ArrayList;

/**
 * Created by Amir Shams on 12/4/2015.
 */
public class NeighboursData {
    ArrayList<Position> myWarriors;
    ArrayList<Position> myWorkers;
    ArrayList<Position> theirWarriors;
    ArrayList<Position> theirWorkers;

    public NeighboursData() {
        this.myWarriors = new ArrayList<Position>();
        this.myWorkers = new ArrayList<Position>();
        this.theirWarriors = new ArrayList<Position>();
        this.theirWorkers = new ArrayList<Position>();
    }
}
