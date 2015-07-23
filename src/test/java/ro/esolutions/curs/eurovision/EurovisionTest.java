/**
 * 
 */
package ro.esolutions.curs.eurovision;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EurovisionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@Test
	public void dummyComparison() {
		assertEquals(26, 26);
	}
	
	@Test
	public void testAddPointsMethod() {
		Song s = new Song();
		CountryRating c = new CountryRating("Elvetia", 12);
		ContestEntry entry = new ContestEntry(s, c);
		entry.addPointsToFinalScore(12);
		assertEquals(12, entry.getFinalScore());		
	}
	
	@Test
	public void testPermissionToVote() {
		VotingCountry v = new VotingCountry("Sweden");
		Song s = new Song();
		CountryRating c = new CountryRating("Sweden", 44);
		ContestEntry entry = new ContestEntry(s,c);
		v.setPermissionToVote(entry);
		assertFalse("mesaj de eroare" ,v.getPermissionToVote());
	}
	
	@Test
	public void testIntermediateScores(){
		Song s = new Song();
		CountryRating c = new CountryRating("Elvetia", 12);
		ContestEntry entry = new ContestEntry(s, c);
		entry.setIntermediateJuryScore(32);
		assertEquals(32, entry.getIntermediateJuryScore(), 0.012);	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
