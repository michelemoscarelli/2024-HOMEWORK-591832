package diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class LabirintoTest {
	private Labirinto labirinto;
	private Stanza stanza_qualunque;

	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		this.stanza_qualunque = new Stanza("stanza_qualunque");
	}

	/* test getStanzaCorrente */
	@Test
	public void testGetStanzaCorrente_Qualunque() {
		this.labirinto.setStanzaCorrente(stanza_qualunque);
		assertEquals(stanza_qualunque, this.labirinto.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente_Iniziale() {
		// "atrio" è il nome della stanza iniziale del gioco
		assertEquals("Atrio", this.labirinto.getStanzaCorrente().getNome());
	}
	
	/* test getStanzaVincente */
	@Test
	public void testGetStanzaVincente() {
		// "biblioteca" è il nome della stanza vincente del gioco
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome());
	}

}
