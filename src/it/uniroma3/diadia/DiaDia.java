package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private IOConsole ioConsole;
	private Partita partita;

	public DiaDia(IOConsole ioconsole) {
		this.partita = new Partita();
		this.ioConsole = ioconsole;
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		this.ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do		
			istruzione = this.ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		/* se l'istruzione è vuota, quindi null */
		if (comandoDaEseguire.getNome()==null) return false;
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("borsa"))
			this.borsa();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.ioConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   



	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.ioConsole.mostraMessaggio(elencoComandi[i]+" ");
		this.ioConsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		this.ioConsole.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	private void borsa() {
		this.ioConsole.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString());
	}
	
	
	private void prendi(String nomeAttrezzo) {	
		
		/* il nome dell'attrezzo è null, stampa messaggio di errore e ritorna */
		if (nomeAttrezzo==null) {
			this.ioConsole.mostraMessaggio("Parametro non valido");
			return;
		}
		
		
		
		/* il nome dell'attrezzo non è null, cercalo tra gli attrezzi della stanza */
		Attrezzo attrezzo_da_prendere;
		// se non esiste, vale null
		attrezzo_da_prendere = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		/* esiste l'attrezzo: mettilo in borsa se si può, toglilo dalla stanza e ritorna */
		if (attrezzo_da_prendere!=null) {
			/* mettilo in borsa */
			boolean preso_in_borsa = this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo_da_prendere);
			
			/* borsa è piena */
			if ( !(preso_in_borsa) ) {
				this.ioConsole.mostraMessaggio("Impossibile prendere l'attrezzo"+" "+nomeAttrezzo+":"+" "+"borsa piena o già troppo pesante.");
				return;
			}
			/* borsa non è piena: attrezzo preso */
			this.ioConsole.mostraMessaggio("Attrezzo"+" "+nomeAttrezzo+" "+"preso.");
			
			/* toglilo dalla stanza */
			/* se removeAttrezzo restituisce false anziché true, stampa messaggio di errore,
			 * altrimenti l'elemento è rimosso da stanza */
			if ( !(this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo_da_prendere)) ) {
				this.ioConsole.mostraMessaggio("Errore: rimozione di"+" "+nomeAttrezzo+" "+"da stanza non riuscita.");
			}
			return;
		}
		/* non esiste, stampa messaggio di errore e ritorna */
		this.ioConsole.mostraMessaggio("Attrezzo non trovato nella stanza.");
		return;
	}
	
	private void posa(String nomeAttrezzo) {
		
		/* il nome dell'attrezzo è null, stampa messaggio di errore e ritorna */
		if (nomeAttrezzo==null) {
			this.ioConsole.mostraMessaggio("Parametro non valido");
			return;
		}
		
		
		
		/* il nome dell'attrezzo non è null, cercalo tra gli attrezzi della borsa*/
		Attrezzo attrezzo_da_posare;
		// se non esiste, vale null
		attrezzo_da_posare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		/* esiste l'attrezzo, viene aggiunto nella stanza se non è piena, viene rimosso dalla borsa e ritorna */
		if (attrezzo_da_posare!=null) {
			/* mettilo nella stanza */
			boolean posato_in_stanza = this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo_da_posare);
			
			/* stanza è piena */
			if ( !(posato_in_stanza) ) {
				this.ioConsole.mostraMessaggio("Impossibile posare l'attrezzo"+" "+nomeAttrezzo+":"+" "+"stanza piena.");
				return;
			}
			
			/* stanza non è piena: attrezzo posato */
			this.ioConsole.mostraMessaggio("Attrezzo"+" "+nomeAttrezzo+" "+"posato.");
			
			/* toglilo dalla borsa */
			/* se removeAttrezzo non restituisce l'elemento eliminato, stampa messaggio di errore,
			 * altrimenti l'elemento è rimosso da borsa */
			if ( this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo_da_posare.getNome())==null ) {
				this.ioConsole.mostraMessaggio("Errore: rimozione di"+" "+nomeAttrezzo+" "+" da borsa non riuscita.");
			}
			return;
		}
		/* non esiste, stampa messaggio di errore e ritorna */
		this.ioConsole.mostraMessaggio("Attrezzo non trovato in borsa.");
		return;
	}
	

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole ioconsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioconsole);
		gioco.gioca();
	}
}