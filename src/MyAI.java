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


        Unit myCastle = wm.self.agents.get(0);

        Random r= new Random();
        if(r.nextDouble() > 0.5)
            myCastle.make(Direction.E, UnitType.WORKER);
        else
            myCastle.make(Direction.W,UnitType.WORKER);

        if(r.nextDouble() > 0.5)
            myCastle.attack(Direction.E);
    }
}
