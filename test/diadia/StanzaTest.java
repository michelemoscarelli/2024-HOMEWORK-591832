package diadia;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	/* fixture */
	private Stanza stanza;
	private Attrezzo attrezzo;
	private Stanza stanza_adiacente;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza("stanzaProva");
		this.attrezzo = new Attrezzo("attrezzo_qualunque", 0);
	}

	
	
	/* test addAttrezzo: modificato leggermente il metodo, cambiato if */
	@Test
	public void testAddAttrezzo_BuonFine() {
		assertTrue(this.stanza.addAttrezzo(this.attrezzo));
	}
	
	@Test
	public void testAddAttrezzo_SpazioPieno() {
		for (int i=0; i<10; i++)		// 10 è il numero massimo di attrezzi contenibili in Stanza
			this.stanza.addAttrezzo(this.attrezzo);
		assertFalse(stanza.addAttrezzo(this.attrezzo));
	}
	
	@Test
	public void testAddAttrezzo_Null() {
		assertFalse(stanza.addAttrezzo(null));
	}
	
	
	/* test getAttrezzo: modificato il for */
	@Test
	public void testGetAttrezzo_Null() {
		assertNull(this.stanza.getAttrezzo(null));
	}
	
	@Test
	public void testGetAttrezzo_Inesistente() {
		assertNull(this.stanza.getAttrezzo("attrezzo_inesistente"));	
	}
	
	@Test
	public void testGetAttrezzo_Presente() {
		this.stanza.addAttrezzo(this.attrezzo);
		assertEquals(this.attrezzo, this.stanza.getAttrezzo("attrezzo_qualunque"));
	}
	
	
	/* test getStanzaAdiacente */
	@Test
	public void testGetStanzaAdiacente_Null() {
		assertNull(this.stanza.getStanzaAdiacente(null));
	}
	
	@Test
	public void testGetStanzaAdiacente_Inesistente() {
		assertNull(this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacente_Presente() {
		this.stanza.impostaStanzaAdiacente("sud", stanza_adiacente);
		assertEquals(stanza_adiacente, this.stanza.getStanzaAdiacente("sud"));
	}
	
}
