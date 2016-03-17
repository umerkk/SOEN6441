package code.game.strategies;

import code.game.models.Critter;
import code.game.models.GameMap;
import code.game.models.TowerModel;

public class WeakestStrategy implements TowerStrategy {

	public String StrategyName="Weakest First";
	Critter weakestCritter=null;
	public boolean ShootCritters(GameMap map, TowerModel tower)
	{
		char[] name_exploded = tower.GetMyLocationOnMap().toCharArray();
		int x = Integer.parseInt(String.valueOf(name_exploded[0]));
		int y = Integer.parseInt(String.valueOf(name_exploded[1]));
		boolean isHit=false;


		for(int k=1;k<=tower.getCurrentLevel();k++)
		{
			try {

				String xRight = String.valueOf(x+k);
				String xLeft = String.valueOf(x-k);
				String yUp = String.valueOf(y+k);
				String yDown = String.valueOf(y-k);

				if(map.CheckCritterExists(xRight+name_exploded[1]))
				{
					if(weakestCritter==null)
					{
						weakestCritter = map.GetCritter(xRight+name_exploded[1]);

					} else if(weakestCritter.GetHealth() > map.GetCritter(xRight+name_exploded[1]).GetHealth())
					{
						weakestCritter = map.GetCritter(xRight+name_exploded[1]);
					}


				} 
				if(map.CheckCritterExists(xLeft+name_exploded[1]))
				{

					if(weakestCritter==null)
					{
						weakestCritter = map.GetCritter(xLeft+name_exploded[1]);

					} else if(weakestCritter.GetHealth() > map.GetCritter(xLeft+name_exploded[1]).GetHealth())
					{
						weakestCritter = map.GetCritter(xLeft+name_exploded[1]);
					}



				} 
				if(map.CheckCritterExists(name_exploded[0]+yUp))
				{
					if(weakestCritter==null)
					{
						weakestCritter = map.GetCritter(name_exploded[0]+yUp);

					} else if(weakestCritter.GetHealth() > map.GetCritter(name_exploded[0]+yUp).GetHealth())
					{
						weakestCritter = map.GetCritter(name_exploded[0]+yUp);
					}



				} 
				if(map.CheckCritterExists(name_exploded[0]+yDown))
				{
					if(weakestCritter==null)
					{
						weakestCritter = map.GetCritter(name_exploded[0]+yDown);

					} else if(weakestCritter.GetHealth() > map.GetCritter(name_exploded[0]+yDown).GetHealth())
					{
						weakestCritter = map.GetCritter(name_exploded[0]+yDown);
					}

				}
			} catch (IndexOutOfBoundsException e)
			{
				continue;
			}
		}
		if(weakestCritter!=null)
		{
			weakestCritter.ReduceHealth((int)tower.getPowerOfBullets());
			switch(tower.getName())
			{
			case "Castle Tower":{
				weakestCritter.SetBackground("red");
				break;
			}
			case "Imperial Tower":{
				weakestCritter.SetBackground("blue");

				break;
			}
			case "Industrial Tower":{
				weakestCritter.SetBackground("black");
				break;
			}
			}
			isHit=true;
			weakestCritter=null;
		}
		return isHit;

	}

	public String GetStrategyName(){
		return StrategyName;
	}
}
