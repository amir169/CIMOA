import client.PlayerAI;
import client.Unit;
import client.UnitType;
import client.WorldModel;
import client.commands.Direction;
import core.math.Vector2D;
import jdk.nashorn.internal.objects.NativeUint16Array;
import server.Settings;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pouya Payandeh on 11/30/2015.
 */
public class MyAI implements PlayerAI
{
    Heuristics heuristics;//=new Heuristics(wm.cloneTerrain(),100);
    boolean first = true;
    int turnNumber = 0;
    int zaribGetDistanceLRTAstar=7;
    int zaribMatrixCellLRTAstar=1;
    int LRAStarINF=50;

    int[][] goldMatrix;
    int[][] unitMatrix;


    @Override

    public void doTurn(WorldModel wm)
    {
        // ********************** //
        // we chan
        wm.cloneTerrain()[wm.self.agents.get(0).getPos().x][wm.self.agents.get(0).getPos().y]=3;
        wm.cloneTerrain()[wm.others.get(0).agents.get(0).getPos().x][wm.others.get(0).agents.get(0).getPos().y]=3;
        SearchAlgorithms searchAlgorithms=new SearchAlgorithms(wm.cloneTerrain(),zaribGetDistanceLRTAstar,zaribMatrixCellLRTAstar);//a
        turnNumber++;

        if(first){
            //peyda kardan path behine ta castle harif
            heuristics=new Heuristics(wm.cloneTerrain(),LRAStarINF);
            first = false;
        }
        ChristopherCastle myCastle = new ChristopherCastle(wm.self.agents.get(0),wm,unitMatrix,goldMatrix,searchAlgorithms,heuristics,turnNumber);


        if(turnNumber%2==0){

            myCastle.unit.make(Direction.E,UnitType.WORKER);
        }


//        initilaUnitMatrixAndData(wm);
        initialGoldMatrix(wm);
//        determineLimits();
//        turnDesicion();


        ArrayList<ChristopherWorker> workers=new ArrayList<>();
        for (int i = 1; i < wm.self.agents.size(); i++) {
            if(wm.self.agents.get(i).getType()==UnitType.WORKER)
                workers.add(new ChristopherWorker(wm.self.agents.get(i),wm,null,goldMatrix,searchAlgorithms,heuristics,turnNumber));

        }

        for (int i = 0; i < workers.size(); i++) {
            workers.get(i).setTask();
            workers.get(i).doItsJob();
        }
/*
        if(!path.equals("")){
            if(path.charAt(0) == 'S' && mc != null)
                mc.move(Direction.N);
            else if(path.charAt(0) == 'N' && mc != null)
                mc.move(Direction.S);
            else if(path.charAt(0) == 'W' && mc != null)
                mc.move(Direction.W);
            else if(path.charAt(0) == 'E' && mc != null)
                mc.move(Direction.E);
        }
        if(!path.equals("")) {
            path = path.substring(1);
        }
*/


    }

    private void initialGoldMatrix(WorldModel wm) {
        goldMatrix=new int[wm.cloneTerrain().length][wm.cloneTerrain()[0].length];
        for (int i = 0; i < wm.cloneTerrain().length; i++) {
            for (int j = 0; j < wm.cloneTerrain()[0].length; j++) {
                goldMatrix[i][j]=0;
            }
        }
        for (int i = 0; i < wm.goldMines.size(); i++) {
            if(wm.goldMines.get(i).goldAmount>0)
                goldMatrix[wm.goldMines.get(i).pos.x][wm.goldMines.get(i).pos.y]=1;
        }
    }

    private void printheuMat() {
        int m[][]=heuristics.LRTAStarmatrix;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[j][i]+" ");
            }
            System.out.println();
        }
    }
    private void printhillMat()
    {
        for (int i = 0; i < heuristics.darknessMatrix.length; i++) {
            for (int j = 0; j < heuristics.darknessMatrix[0].length; j++) {
                System.out.print(heuristics.lastSeenMatrix[j][i]+" ");
            }
            System.out.println();
        }
    }
}
