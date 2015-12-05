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
    int xMoves[] = {0,0,1,-1};
    int yMoves[] = {1,-1,0,0};
    String directions[] = {"S", "N", "E", "W"};
    private boolean passable(int x,int y)
    {
        if(x < 0 || y < 0 || x >= unitMatrix.length || y >= unitMatrix[0].length)
            return false;

        return true;
    }

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

        int x,y;
        for(int i=0;i<4;i++)
        {
            x = unit.getPos().x + xMoves[i];
            y = unit.getPos().y + yMoves[i];

            if(passable(x , y ))
                if(unitMatrix[x][y] / 10 == 2)
                {
                    unit.attack(Direction.valueOf(directions[i]));
                    System.err.println("worker id : " + unit.getId());
                    System.err.println("be jash flage doshman ro true kardam");
                    return;
                }
        }

        if(task.equals("gain")){
            unit.nop();
        }
        else {
            String path=searchAlgorithms.hillClimbing(heuristics, Position.getPos(unit.getPos()), turnNumber);
            if(!path.equals("")){
                unit.move(Direction.valueOf(path.substring(0, 1)));
            }

        }
    }
}
