import client.PlayerAI;
import client.Unit;
import client.UnitType;
import client.WorldModel;
import client.commands.Direction;
import server.Settings;

import java.util.Random;

/**
 * Created by Pouya Payandeh on 11/30/2015.
 */
public class MyAI implements PlayerAI
{

    @Override
    public void doTurn(WorldModel wm)
    {


        Unit c = wm.self.agents.get(0);
//        wm.self.agents.get(0).move();
        Random r= new Random();
//        int k = 1; //<< r.nextInt(32);
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();


//        for(int i=0;i<wm.getWidth();i++)
//            for(int j=0;j<wm.getHeight();j++)
//                System.out.println(wm.cloneTerrain()[i][j]);


        String path = "";
        if(wm.self.agents.size() == 2 && path == "")
        {
            c = wm.self.agents.get(1);
            PathData pathData = searchAlgorithms.BFS(wm.cloneTerrain(), new Position(c.getPos().x, c.getPos().y));
            path = pathData.toPath(new Position(4,4));
        }


        Unit mc = null;

//        for(int i = 1 ; i < wm.self.agents.size() ; i++)
//        {
//            mc = wm.self.agents.get(i);
//            double chance = r.nextDouble();
//            if(chance < 0.25)
//                mc.move(Direction.N);
//            else if(chance < 0.5)
//                mc.move(Direction.S);
//            else if(chance < 0.75)
//                mc.move(Direction.E);
//            else
//                mc.move(Direction.W);
//        }

//        System.out.println(path);
//        System.out.println(wm.self.agents.size());

        if(path.length() !=0 && path.charAt(0) == 'S' && mc != null)
        {
            mc.move(Direction.S);
            path = path.substring(1);
        }
        else if(path.length() !=0 && path.charAt(0) == 'N' && mc != null)
        {
            mc.move(Direction.N);
            path = path.substring(1);
        }
        else if(path.length() !=0 && path.charAt(0) == 'W' && mc != null)
        {
            mc.move(Direction.W);
            path = path.substring(1);
        }
        else if(path.length() !=0 && path.charAt(0) == 'E' && mc != null)
        {
            mc.move(Direction.E);
            path = path.substring(1);
        }
        System.out.println(path);
        if(r.nextDouble() > 0.5)
            c.make(Direction.E, UnitType.WORKER);
        else
            c.make(Direction.W,UnitType.WORKER);

        if(r.nextDouble() > 0.5)
            c.attack(Direction.E);
    }
}
