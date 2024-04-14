package diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private static final int PESO_BORSA_IMPOSTATO = 6;
	
	private Borsa borsa;
	private Borsa borsa_peso_impostato;
	private Attrezzo attrezzo;
	private Attrezzo attrezzo_pesante;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa();
		this.borsa_peso_impostato = new Borsa(PESO_BORSA_IMPOSTATO);
		this.attrezzo = new Attrezzo("attrezzo", 0);
		this.attrezzo_pesante = new Attrezzo("attrezzo_pesante", 2);
	}

	
	
	/* test addAttrezzo */
	@Test
	public void testAddAttrezzo_BuonFine() {
		assertTrue(this.borsa.addAttrezzo(this.attrezzo));
	}
	
	@Test
	public void testAddAttrezzo_SpazioPieno() {
		for (int i=0; i<10; i++)		// 10 è il numero massimo di attrezzi contenibili in Borsa
			this.borsa.addAttrezzo(this.attrezzo);
		assertFalse(borsa.addAttrezzo(this.attrezzo));
	}
	
	@Test
	public void testAddAttrezzo_PesoMassimoDefault() {
		for (int i=0; i<5; i++)		// 5*2(peso di attrezzo_pesante)=10, peso massimo di Borsa di default
			this.borsa.addAttrezzo(this.attrezzo_pesante);
		assertFalse(borsa.addAttrezzo(this.attrezzo_pesante));
	}
	@Test
	public void testAddAttrezzo_PesoMassimoArbitrario() {
		for (int i=0; i<5; i++)
			this.borsa_peso_impostato.addAttrezzo(this.attrezzo_pesante);
		assertFalse(borsa_peso_impostato.addAttrezzo(this.attrezzo_pesante));
	}
	
	@Test
	public void testAddAttrezzo_Null() {
		assertFalse(borsa.addAttrezzo(null));
	}
	
	
	/* test getAttrezzo */
	@Test
	public void testGetAttrezzo_Null() {
		assertNull(this.borsa.getAttrezzo(null));
	}
	
	@Test
	public void testGetAttrezzo_Inesistente() {
		assertNull(this.borsa.getAttrezzo("attrezzo_inesistente"));	
	}
	
	@Test
	public void testGetAttrezzo_Presente() {
		this.borsa.addAttrezzo(this.attrezzo);
		assertEquals(this.attrezzo, this.borsa.getAttrezzo("attrezzo"));
	}
}
