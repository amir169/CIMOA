import client.Unit;
import client.WorldModel;
import client.commands.Direction;
import core.math.Vector2D;

/**
 * Created by Saleh on 04/12/2015.
 */
public class ChristopherWarrior {
    Unit unit;
    String task;
    Vector2D target;
    WorldModel wm;
    int[][] unitMatrix;
    int[][] goldMatrix;
    SearchAlgorithms searchAlgorithms;
    Heuristics heuristics;
    int turnNumber;

    public ChristopherWarrior(Unit unit, WorldModel wm, int[][] unitMatrix, int[][] goldMatrix, SearchAlgorithms searchAlgorithms, Heuristics heuristics, int turnNumber) {
        this.unit = unit;
        this.wm = wm;
        this.unitMatrix = unitMatrix;
        this.goldMatrix = goldMatrix;
        this.searchAlgorithms = searchAlgorithms;
        this.heuristics = heuristics;
        this.turnNumber = turnNumber;
    }
    public void setTask() {

    }

    public void doItsJob(){

    }
}
