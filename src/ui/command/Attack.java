package ui.command;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RException;
import lifeform.LifeForm;

public class Attack implements Command{
	Environment theWorld;
	LifeForm life, life2;    // I don't think we need to keep track of "life" here since that should be itsMyTurn.  
							//  but..  interesting problem about how we are supposed to get life2 (aka victim)
							//  I say that for now we just leave it hard coded as you have in line 17.  then just have to make it dynamically generated later
	@Override
	public void execute() throws RException, EnvironmentException { 
		
		theWorld.itsMyTurn.mountAttack(life2);
	}
}