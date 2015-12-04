import client.Unit;
import client.WorldModel;
import core.math.Vector2D;

/**
 * Created by Saleh on 04/12/2015.
 */
public class ChristopherCastle {
    Unit unit;
    String task;
    Vector2D target;
    WorldModel wm;
    double dangerAmount;
    int[][] unitMatrix;
    int[][] goldMatrix;
    SearchAlgorithms searchAlgorithms;
    Heuristics heuristics;
    int turnNumber;
    int myWorkerAmount;
    int goldMineSize;

    public ChristopherCastle(Unit unit, WorldModel wm, int[][] unitMatrix, int[][] goldMatrix, SearchAlgorithms searchAlgorithms, Heuristics heuristics, int turnNumber,int myWorkerAmount,int goldMineSize) {
        this.myWorkerAmount=myWorkerAmount;
        this.goldMineSize=goldMineSize;
        this.unit = unit;
        this.wm = wm;
        this.unitMatrix = unitMatrix;
        this.goldMatrix = goldMatrix;
        this.searchAlgorithms = searchAlgorithms;
        this.heuristics = heuristics;
        this.turnNumber = turnNumber;
    }
    public void setTask(int castleBFSLimit){
        NeighboursData neighboursData = searchAlgorithms.BFS(unitMatrix, Position.getPos(unit.getPos()), castleBFSLimit);
        //worker
        //warrior
        //gold

    }

    public void doItsJob(){

    }
}
