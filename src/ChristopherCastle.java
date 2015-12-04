import client.Unit;
import client.UnitType;
import client.WorldModel;
import client.commands.Direction;
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
    double castleSafety;
    int[][] unitMatrix;
    int[][] goldMatrix;
    SearchAlgorithms searchAlgorithms;
    Heuristics heuristics;
    int turnNumber;
    int myWorkerAmount;
    int goldMineSize;
    boolean inDanger = false;
    int enoughSoldiers = 5;
    int lastDirection = 0;
    int xMoves[] = {0,0,1,-1};
    int yMoves[] = {1,-1,0,0};
    String directions[] = {"S", "N", "E", "W"};

    private boolean passable(int x,int y)
    {
        if(x < 0 || y < 0 || x >= unitMatrix.length || y >= unitMatrix[0].length)
            return false;

        return true;
    }

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
    public String setTask(String status,int castleBFSLimit){
        double total = 1
                ,maxHPOfCastle = 100.0
                ,dangerLimit1 = 0
                ,dangerLimit2 = 0
                ,dangerLimit3 = 0
                ,goldCompare = 0
                ,warriorCompare = 0
                ,workerCompare = 0;

        NeighboursData neighboursData = searchAlgorithms.BFS(unitMatrix, Position.getPos(unit.getPos()), castleBFSLimit);

        total = 1;      //must be set manually

        if(neighboursData.myWarriors.size() != 0)
            dangerAmount = (double)neighboursData.theirWarriors.size()/(double)neighboursData.myWarriors.size();
        else
            dangerAmount = 100;

        dangerAmount *= (maxHPOfCastle - wm.self.agents.get(0).getHP() + 1);

        goldCompare = (double)wm.self.gold / (double)wm.others.get(0).gold;

        if(dangerAmount > dangerLimit1)
        {
            inDanger = true;
            status = "indanger";
            task = "Warrior";
        }
        else
        {
            if(status == "savegold")
                task = "Gold";
            if(status == "gaingold")
                task = "Worker";
            if(status == "none")
                task = "Warrior";
        }
        //worker
        //warrior
        //gold
        if(status != "none")
            return status;

        if(neighboursData.myWarriors.size() >= enoughSoldiers)
            return "attack";
        else
            return "defence";
    }

    public boolean doItsJob()
    {
        int x,y;
        for(int i=0;i<4;i++)
        {
            x = unit.getPos().x + xMoves[i];
            y = unit.getPos().y + yMoves[i];

            if(passable(x , y ))
                if(unitMatrix[x][y] / 10 == 2)
                {
                    unit.attack(Direction.valueOf(directions[i]));
                    System.err.println("castle id : " + unit.getId());
                    System.err.println("be jash flage doshman ro true kardam");
                    return false;
                }
        }

        if(task == "Gold")
        {
            this.unit.nop();
            return true;
        }
        if(task == "Worker")
        {
            x = unit.getPos().x + xMoves[lastDirection];
            y = unit.getPos().y + yMoves[lastDirection];

            if(passable(x,y) && unitMatrix[x][y] == 0)
            {
                unit.make(Direction.valueOf(directions[lastDirection]), UnitType.WORKER);
                lastDirection = (lastDirection + 1)%4;
                return true;
            }

            for(int i=0;i<4;i++)
            {
                x = unit.getPos().x + xMoves[i];
                y = unit.getPos().y + yMoves[i];

                if(passable(x , y ))
                    if(unitMatrix[x][y]  == 0)
                    {
                        unit.make(Direction.valueOf(directions[lastDirection]), UnitType.WORKER);
                        lastDirection = (lastDirection + 1)%4;
                        return true;
                    }
            }
            System.err.println("castle id : " + unit.getId());
            System.err.println("bero gomsho");
            return false;
        }

        if(task == "Warrior")
        {
            x = unit.getPos().x + xMoves[lastDirection];
            y = unit.getPos().y + yMoves[lastDirection];

            if(passable(x,y) && unitMatrix[x][y] == 0)
            {
                unit.make(Direction.valueOf(directions[lastDirection]), UnitType.WARRIOR);
                lastDirection = (lastDirection + 1)%4;
                return true;
            }

            for(int i=0;i<4;i++)
            {
                x = unit.getPos().x + xMoves[i];
                y = unit.getPos().y + yMoves[i];

                if(passable(x , y ))
                    if(unitMatrix[x][y]  == 0)
                    {
                        unit.make(Direction.valueOf(directions[lastDirection]), UnitType.WARRIOR);
                        lastDirection = (lastDirection + 1)%4;
                        return true;
                    }
            }
            System.err.println("castle id : " + unit.getId());
            System.err.println("bero gomsho");
            return false;
        }

        return false;
    }
}