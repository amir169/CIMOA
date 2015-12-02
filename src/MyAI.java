import client.PlayerAI;
import client.Unit;
import client.UnitType;
import client.WorldModel;
import client.commands.Direction;
import jdk.nashorn.internal.objects.NativeUint16Array;
import server.Settings;

import java.util.Random;

/**
 * Created by Pouya Payandeh on 11/30/2015.
 */
public class MyAI implements PlayerAI
{

    String path = "";
    boolean first = true;
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


        Unit mc = null;

        if(wm.self.agents.size() == 2)
            mc =  wm.self.agents.get(1);
        if(wm.self.agents.size() == 2 && path.equals("") && first)
        {
            mc = wm.self.agents.get(1);
            PathData pathData = searchAlgorithms.BFS(wm.cloneTerrain(), new Position(c.getPos().x, c.getPos().y),20);
            path = pathData.toPath(new Position(7,0));
            first = false;
        }

//        if(!path.equals("") && mc!=null)
//            mc.move(Direction.valueOf(path.substring(0,1)));


//        for(int i = 1 ; i < wm.self.agents.size() ; i++)
//        {
//            mc = wm.self.agents.get(i);
//            double chance = r.nextDouble();
//            if(chance < 0.25)
//                mc.move(Direction.N);
//            else if(chance < 0.5)
//                mc.move(Direction.S);
//            else if(chance < 0.75)
//            mc.move(Direction.E);
//            else
//                mc.move(Direction.W);
//        }

//        System.out.println(path);
//        System.out.println(wm.self.agents.size());

//        System.out.println(path.length());
//        if(path.length() > 0)
//            System.out.println(path.charAt(0));
//        System.out.println(path.charAt(0));

//        if(wm.self.agents.size() > 1)
//            System.err.println("mc: " + mc.toString());


        if(!path.equals(""))
        {
            if(path.charAt(0) == 'S' && mc != null)
            {

                mc.move(Direction.N);

//                path = path.substring(1);
            }
            else if(path.charAt(0) == 'N' && mc != null)
            {

                mc.move(Direction.S);
//                mc.move(Direction.valueOf(path.substring(0,1)));
//                path = path.substring(1);
            }
            else if(path.charAt(0) == 'W' && mc != null)
            {
                mc.move(Direction.W);

//                path = path.substring(1);
            }
            else if(path.charAt(0) == 'E' && mc != null)
            {
                mc.move(Direction.E);

//                path = path.substring(1);
            }
        }

        if(!path.equals("")) {
            System.out.println(path.substring(1));

            path = path.substring(1);
            System.out.println("path: " + path);
        }
        if(r.nextDouble() > 0.5 && wm.self.agents.size() < 2)
            c.make(Direction.E, UnitType.WORKER);
        else if( wm.self.agents.size() < 2)
            c.make(Direction.W,UnitType.WORKER);

        if(r.nextDouble() > 0.5)
            c.attack(Direction.E);
    }
}
