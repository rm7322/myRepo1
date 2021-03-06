/**
 * Tests for Power booster attachment
 * 
 * @author Chandana G, Rob Miles
 */
package weapon;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestPowerBooster
{
	/**
	 * Test for weapon with one attachment
	 */
	@Test
	public void testInitialize()
	{
		Weapon chaingun = new MockChainGun();
		PowerBooster pb = new PowerBooster(chaingun);
		assertEquals(195, pb.damage(2));
	}

	/**
	 * Test for weapon with two attachments
	 */
	@Test
	public void test2Attachments()
	{
		Weapon chaingun = new MockChainGun();
		PowerBooster pb = new PowerBooster(chaingun);
		assertEquals(195, pb.damage(2));
		PowerBooster pb2 = new PowerBooster(pb);
		assertEquals(381, pb2.damage(2));
	}
	
	@Test
	public void test2AttachmentCombinations()
	{
		/**
		 * Power Booster + Scope
		 */
		Weapon chaingun = new MockChainGun();
		PowerBooster pb = new PowerBooster(chaingun); 
		assertEquals(195, pb.damage(2));
		//Range.distance = 0;
		Scope s = new Scope(pb);		
		assertEquals(388, s.damage(0));
		
		/**
		 * PowerBooster + Stabilizer
		 */
		Weapon chainGun = new MockChainGun();
		Stabilizer sz = new Stabilizer(chainGun);
		assertEquals(5, sz.damage(2));
		PowerBooster sb = new PowerBooster(sz);
		assertEquals(9, sb.damage(2));
	}
}

