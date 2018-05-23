package lifeform;

/**
 * Keeps track of the information associated with a simple life form. Also
 * provides the functionality related to the life form. Author: Rob Miles
 */
public class LifeForm
{

	protected String myName;
	protected int currentLifePoints;

	/**
	 * Create an instance
	 *
	 * @param name
	 *            the name of the life form
	 * @param points
	 *            the current starting life points of the life form
	 */
	public LifeForm(String name, int points)
	{
		myName = name;
		currentLifePoints = points;
	}

	/**
	 * @return the name of the life form.
	 */
	public String getName()
	{
		return myName;
	}

	/**
	 * @return the amount of current life points the life form has.
	 */
	public int getCurrentLifePoints()
	{
		return currentLifePoints;
	}

	/*
	 * removes damage from life points
	 * 
	 * @return void
	 * 
	 * @param damage - the amount to reduce life points by
	 */
	public void takeHit(int damage)
	{

		currentLifePoints -= damage;
	}
}