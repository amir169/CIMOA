import client.Unit;
import client.WorldModel;
import client.commands.Direction;
import core.math.Vector2D;
import sun.management.Agent;

/**
 * Created by Saleh on 02/12/2015.
 */
public class ChristopherWorker {
    Unit unit;
    String task;
    Vector2D target;
    WorldModel wm;
    int[][] unitMatrix;
    int[][] goldMatrix;
    SearchAlgorithms searchAlgorithms;
    Heuristics heuristics;
    int turnNumber;

    public ChristopherWorker(Unit unit, WorldModel wm, int[][] unitMatrix, int[][] goldMatrix, SearchAlgorithms searchAlgorithms, Heuristics heuristics, int turnNumber) {
        this.unit = unit;
        this.wm = wm;
        this.unitMatrix = unitMatrix;
        this.goldMatrix = goldMatrix;
        this.searchAlgorithms = searchAlgorithms;
        this.heuristics = heuristics;
        this.turnNumber = turnNumber;
    }

    public void setTask() {
        if(goldMatrix[unit.getPos().x][unit.getPos().y]==1)
            task="gain";
        else
            task="explore";
    }

    public void doItsJob(){
        if(task.equals("gain")){
            unit.nop();
        }
        else {
            String path=searchAlgorithms.hillClimbing(heuristics, Position.getPos(unit.getPos()), turnNumber);
            if(!path.equals("")){
                unit.move(Direction.valueOf(path.substring(0, 1)));
                path = path.substring(1);
            }

        }
    }
}
