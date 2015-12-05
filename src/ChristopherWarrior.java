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
    String pathS;
    PathData path;

    static int lastDir=0;

    int xMoves[] = {0,0,1,-1};
    int yMoves[] = {1,-1,0,0};
    String directions[] = {"S", "N", "E", "W"};

    private boolean passable(int x,int y)
    {
        if(x < 0 || y < 0 || x >= unitMatrix.length || y >= unitMatrix[0].length)
            return false;

        return true;
    }

    public ChristopherWarrior(Unit unit, WorldModel wm, int[][] unitMatrix, int[][] goldMatrix, SearchAlgorithms searchAlgorithms, Heuristics heuristics, int turnNumber) {
        this.unit = unit;
        this.wm = wm;
        this.unitMatrix = unitMatrix;
        this.goldMatrix = goldMatrix;
        this.searchAlgorithms = searchAlgorithms;
        this.heuristics = heuristics;
        this.turnNumber = turnNumber;
    }
    public void setTask(String turnDec,int LAlimit) {
        if(turnDec=="indanger")
            pathS=searchAlgorithms.LRTAStar(heuristics,unit.getPos(),wm.self.agents.get(0).getPos(),LAlimit).toPath();
        else{
            lastDir=(lastDir+1)%4;
            switch (lastDir){
                case 1: pathS=searchAlgorithms.LRTAStar(heuristics,unit.getPos(),new Vector2D(wm.others.get(0).agents.get(0).getPos().x,wm.others.get(0).agents.get(0).getPos().y+1),LAlimit).toPath();
                    break;
                case 2: pathS=searchAlgorithms.LRTAStar(heuristics,unit.getPos(),new Vector2D(wm.others.get(0).agents.get(0).getPos().x,wm.others.get(0).agents.get(0).getPos().y-1),LAlimit).toPath();
                    break;
                case 3: pathS=searchAlgorithms.LRTAStar(heuristics,unit.getPos(),new Vector2D(wm.others.get(0).agents.get(0).getPos().x+1,wm.others.get(0).agents.get(0).getPos().y),LAlimit).toPath();
                    break;
                default: pathS=searchAlgorithms.LRTAStar(heuristics,unit.getPos(),new Vector2D(wm.others.get(0).agents.get(0).getPos().x-1,wm.others.get(0).agents.get(0).getPos().y+1),LAlimit).toPath();
                    break;
            }
            System.err.println(":"+pathS);
        }
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
                    System.err.println("warrior id : " + unit.getId());
                    System.err.println("be jash flage doshman ro true kardam");
                    return;
                }
        }
        if(!pathS.equals("")) {
            if(pathS.length()==1)
                unit.move(Direction.valueOf(pathS));
            else {
                System.err.println(pathS + ":" + pathS.length());
                unit.move(Direction.valueOf(pathS.substring(0, 1)));

            }
        }}
}
