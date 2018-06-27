/**
 * the implementation of the state that the Lifeform goes into when it's dead.  This happens when the lifepoints have reached 0.
 * 
 * @author Rob Miles
 */

package state;

import exceptions.RException;
import lifeform.LifeForm;
import weapon.Weapon;

public class DeadState extends ActionState
{

	DeadState(LifeForm lifeForm)
	{
		super(lifeForm);

	}

	public void evalutat()
	{
		respawn();
	}

	public void respawn()
	{

		// reset lifepoints
		myLF.resetLifePointsToMax();

		// drop your weapon
		int[] myLFLoc = theWorld.getLifeFormLocation(myLF);

		try
		{
			theWorld.addWeapon(myLFLoc[0], myLFLoc[1], myLF.getWeapon());
		} 
		catch (RException e)
		{

			e.printStackTrace();
		}
		myLF.dropWeapon();

		int row = (int) (Math.random()*theWorld.getEnvironmentDimensions()[0]);
		int col = (int) (Math.random()*theWorld.getEnvironmentDimensions()[1]);
		
		theWorld.removeLifeForm(myLFLoc[0], myLFLoc[1]);
		theWorld.addLifeForm(row, col, myLF);
		// change the active state that the AI has to no weapon state
		// ai.changeCurrentState(myStates[3]);
	}

}
