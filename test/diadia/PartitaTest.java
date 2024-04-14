package diadia;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;

public class PartitaTest {
	private Partita partita = new Partita();
	
	@Before
	public void setUp() {
	}
	
	/* test isFinita */
	@Test
	public void testIsFinita_CfuMaggioriDiZero() {
		this.partita.setFinita();
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonVinta() {
		this.partita.setFinita();
		this.partita.getGiocatore().setCfu(0);
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaCorrente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonFinita() {
		assertFalse(this.partita.isFinita());
	}

}
