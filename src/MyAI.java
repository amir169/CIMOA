import client.PlayerAI;
import client.Unit;
import client.UnitType;
import client.WorldModel;
import client.commands.Direction;
import core.math.Vector2D;
import jdk.nashorn.internal.objects.NativeUint16Array;
import server.Settings;

import java.util.Random;

/**
 * Created by Pouya Payandeh on 11/30/2015.
 */
public class MyAI implements PlayerAI
{
    Heuristics heuristics;//=new Heuristics(wm.cloneTerrain(),100);
    String path = "";
    boolean first = true;
    @Override

    public void doTurn(WorldModel wm)
    {
        SearchAlgorithms searchAlgorithms=new SearchAlgorithms(wm.cloneTerrain());
        heuristics=new Heuristics(wm.cloneTerrain(),100);

        Unit c = wm.self.agents.get(0);
        Unit mc=(wm.self.agents.size()>1)?wm.self.agents.get(1) : null;

        if(mc!= null) {
            if(path=="") {
                printheuMat();
                System.out.println();
//                path=searchAlgorithms.BFS(Position.getPos(mc.getPos()),100).toPath(new Position(9,9));
//                System.err.println("BFSNODES: " + searchAlgorithms.bfsNodes);
                path = searchAlgorithms.LRTAStar(heuristics, mc.getPos(), new Vector2D(9, 9)).toPath(new Position(9, 9));
                System.err.println("ASTARNODES: " + searchAlgorithms.AStarNodes);
                System.err.println("path: "+path);
                printheuMat();
            }
        }
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
        if(wm.self.agents.size()<2);
            c.make(Direction.E,UnitType.WORKER);
    }

    private void printheuMat() {
        int m[][]=heuristics.LRTAStarmatrix;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
}
