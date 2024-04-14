package diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	private Giocatore giocatore_qualunque;
	private Giocatore giocatore_max;
	private Borsa borsa;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa();
		this.giocatore_qualunque = new Giocatore(7, this.borsa);
		this.giocatore_max = new Giocatore();	// avrà il massimo numero di cfu
	}

	/* test getCfu */
	@Test
	public void testGetCfu_GiocatoreQualunque() {
		assertEquals(7, this.giocatore_qualunque.getCfu());
	}
	@Test
	public void testGetCfu_GiocatoreMax() {
		assertEquals(20, this.giocatore_max.getCfu());	// 20 è il numero massimo di cfu
	}

	/* test getBorsa */
	@Test
	public void testGetBorsa() {
		assertEquals(this.borsa, this.giocatore_qualunque.getBorsa());
	}
}
