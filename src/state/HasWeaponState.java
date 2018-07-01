package state;

import java.util.Random;

import exceptions.EnvironmentException;
import exceptions.RException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import ui.command.Attack;
import ui.command.Command;
import weapon.Weapon;

public class HasWeaponState extends ActionState
{
	DeadState dead;
	String randomDirectionPicked;
	int i, j;
	String[] directions = {"North", "South", "East", "West"};
	Boolean searchTurnHalfTheTime;
	int index;
	String currentDirection;
	int[] loc;
	int[] theWorldLoc;
	int x, y;
	LifeForm victim;
	int count = 0;
	public static boolean val;
	HasWeaponState(LifeForm lifeForm, AI ai)
	{
		super(lifeForm, ai);
		// TODO Auto-generated constructor stub
		
		//loc = theWorld.getLifeFormLocation(myLF);
		theWorldLoc = theWorld.getEnvironmentDimensions();
		x = theWorldLoc[0];
		y = theWorldLoc[1];
	}

	/**
	 * Evaluates whether the current active is dead
	 * @throws RException 
	 */
	public void evaluate() throws RException {
		int points = myLF.getLifePoints();
		System.out.println(points);
		
		if(myLF.getLifePoints() != 0) 
		{
			if(myLF.getWeapon().getCurrentAmmo() == 0)
			{
				ai.changeToNoAmmoState();
			}
			else
			{
				search();
			}
		}
		dead();
		
	
	}
	
	/**
	 * Changes to dead state
	 */
	public void dead()
	{
		ai.changeToDeadState();
	}
	
	/**
	 * Changes to Out of Ammo state
	 */
	public void outOfAmmoState()
	{
		
	}
	
	/**
	 * Searched for a new slot for player to move into
	 */
	public void search() throws RException
	{
		Random random = new Random();
	    val = random.nextBoolean();
		currentDirection = myLF.getDirection();
		randomDirectionPicked = getRandomDirectionToSearch();
		loc = theWorld.getLifeFormLocation(myLF);
		//System.out.println("boolean value" + "" + val);
		//System.out.println(i + "cells" + j);
		if (currentDirection == "North")
		{
			
			/**
			 * When direction is North and currentLocation = (5, 6), it goes to search down
			 * the grid at locations (4, 6), (3, 6)...until it reaches the end cell in that
			 * direction
			 * 
			 * the loop searches the entire row or column towards the direction player is in 
			 * 
			 */
			j = loc[1];
			for (i = loc[0] - 1; i > 0;i--) // moving up the grid
			{
				
					victim = theWorld.getLifeForm(i, j);
					
					/**
					 * If there is no target found then he either chooses to stay in that cell or changes direction, 
					 * looks for a victim to attack in that direction, if there are none then moves to a new cell in that direction 
					 *					 * 
					 * If the victim is an instance of lifeform different from the player then 
					 * he attacks
					 * 
					 * count makes sure that a lifeform isn't stuck in one turn forever searching,
					 * changing direction or attacking
					 */
					
					if(victim != null && victim instanceof Human != ((myLF instanceof Human)) || victim instanceof Alien != (myLF instanceof Alien))
					{
						attack();
						break;
					}
					if (i == 1)
					{
						if(victim == null)
						{	
							if(val == true)
							{
								//System.out.println("boolean value" + "" + val);
								theWorld.movePlayer(myLF);
								loc = theWorld.getLifeFormLocation(myLF);
								//System.out.println(loc[0]+ "newloc" +loc[1]);
								theWorld.playerDirection(randomDirectionPicked, myLF);
							
								
							}
						}
					}
					if(theWorld.getLifeForm(i, j) == null)
					{
						continue;
					}
					//If tries to attack on its own kind, throws an exception
					if ((victim instanceof Human == ((myLF instanceof Human)) || victim instanceof Alien == (myLF instanceof Alien)))
					{
						throw new RException("Cannot attack your own kind!");
					}
				}
			}
			
		
		
		if (currentDirection == "South")
		{
			/**
			 * When direction is South and currentLocation = (5, 6), it goes to search down
			 * the grid at locations (6, 6), (7, 6)...until it reaches the end cell in that
			 * direction
			 */

			j = loc[1]; // 7
			
				// i = 6, x = 20
			for (i = loc[0] + 1; i < x;i++) // moving down the grid
			{
				victim = theWorld.getLifeForm(i, j);
				System.out.println(myLF + "..." + victim);
				System.out.println(i + ".cells.." + j);
				
				/**
				 * If there is no target found then he either chooses to stay in that cell or changes direction, 
				 * looks for a victim to attack in that direction, if there are none then moves to a new cell in that direction 
				 *					 * 
				 * If the victim is an instance of lifeform different from the player then 
				 * he attacks
				 * 
				 * count makes sure that a lifeform isn't stuck in one turn forever searching,
				 * changing direction or attacking
				 */
				
				if(victim != null && victim instanceof Human != ((myLF instanceof Human)) || victim instanceof Alien != (myLF instanceof Alien))
				{
					attack();
					break;
				}
				if (i == x)
				{
					if(victim == null)
					{	
						if(val == true)
						{
							System.out.println("boolean value" + "" + val);
							theWorld.movePlayer(myLF);
							loc = theWorld.getLifeFormLocation(myLF);
							System.out.println(loc[0]+ "newloc" +loc[1]);
							theWorld.playerDirection(randomDirectionPicked, myLF);
							
							
						}
					}
				}
				if(theWorld.getLifeForm(i, j) == null)
				{
					continue;
				}
				//If tries to attack on its own kind, throws an exception
				if ((victim instanceof Human == ((myLF instanceof Human)) || victim instanceof Alien == (myLF instanceof Alien)))
				{
					throw new RException("Cannot attack your own kind!");
				}

				}
			

		}
		if (currentDirection == "East")
		{
			/**
			 * When direction is East and currentLocation = (5, 6), it goes to search down
			 * the grid at locations (5, 7), (5, 8)...until it reaches the end cell in that
			 * direction
			 */
			i = loc[0];
			for (j = loc[1] + 1; j < x;j++) // moving down the grid
			{
				victim = theWorld.getLifeForm(i, j);
				System.out.println(myLF + "..." + victim);
				
				
				/**
				 * If there is no target found then he either chooses to stay in that cell or changes direction, 
				 * looks for a victim to attack in that direction, if there are none then moves to a new cell in that direction 
				 *					 * 
				 * If the victim is an instance of lifeform different from the player then 
				 * he attacks
				 * 
				 * count makes sure that a lifeform isn't stuck in one turn forever searching,
				 * changing direction or attacking
				 */
				
				if(victim != null && victim instanceof Human != ((myLF instanceof Human)) || victim instanceof Alien != (myLF instanceof Alien))
				{
					attack();
					break;
				}
				if (j == x )
				{
					if(victim == null)
					{	
						if(val == true)
						{
							//System.out.println("boolean value" + "" + val);
							theWorld.movePlayer(myLF);
							loc = theWorld.getLifeFormLocation(myLF);
							//System.out.println(loc[0]+ "newloc" +loc[1]);
							theWorld.playerDirection(randomDirectionPicked, myLF);
							
							
						}
					}
				}
				if(theWorld.getLifeForm(i, j) == null)
				{
					continue;
				}
				//If tries to attack on its own kind, throws an exception
				if ((victim instanceof Human == ((myLF instanceof Human)) || victim instanceof Alien == (myLF instanceof Alien)))
				{
					throw new RException("Cannot attack your own kind!");
				}				
			}

		}
		if (currentDirection == "West")
		{
			/**
			 * When direction is West and currentLocation = (5, 6), it goes to search down
			 * the grid at locations (5, 5), (5, 4)...until it reaches the end cell in that
			 * direction
			 */
			i = loc[0];
			for (j = loc[1] - 1; j > 0;j--) // moving down the grid
			{
			
				victim = theWorld.getLifeForm(i, j);
				System.out.println(myLF + "..." + victim);
				
				
				/**
				 * If there is no target found then he either chooses to stay in that cell or changes direction, 
				 * looks for a victim to attack in that direction, if there are none then moves to a new cell in that direction 
				 *					 * 
				 * If the victim is an instance of lifeform different from the player then 
				 * he attacks
				 * 
				 * count makes sure that a lifeform isn't stuck in one turn forever searching,
				 * changing direction or attacking
				 */
				
				if(victim != null && victim instanceof Human != ((myLF instanceof Human)) || victim instanceof Alien != (myLF instanceof Alien))
				{
					attack();
					break;
				}
				if (j == 1)
				{
					if(victim == null)
					{	
						if(val == true)
						{
							//System.out.println("boolean value" + "" + val);
							theWorld.movePlayer(myLF);
							theWorld.playerDirection(randomDirectionPicked, myLF);
							loc = theWorld.getLifeFormLocation(myLF);
							//System.out.println(loc[0]+ "newloc" +loc[1]);
							count++;
							search();
							
						}
					}
				}
				
				if(theWorld.getLifeForm(i, j) == null)
				{
					continue;
				}
				//If tries to attack on its own kind, throws an exception
				if ((victim instanceof Human == ((myLF instanceof Human)) || victim instanceof Alien == (myLF instanceof Alien)))
				{
					throw new RException("Cannot attack your own kind!");
				}				
			}
		}
		count = 0;
			
	} 
	
	public void attack()
	{
		//Command attack = new Attack();
			try {
				myLF.mountAttack(victim);
				//attack.execute();
			} catch (EnvironmentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String getRandomDirectionToSearch()
	{
		index = (int) (Math.random() * 3);
		if (directions[index] == currentDirection && index != 3)
		{
			return directions[index + 1];
		}
		if (directions[index] == currentDirection && index == 3)
		{
			return directions[index - 1];
		}
		return directions[index];
	}
	
	


}

