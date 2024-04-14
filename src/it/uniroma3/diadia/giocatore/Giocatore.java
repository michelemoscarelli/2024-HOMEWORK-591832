package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore(int cfu, Borsa borsa) {
		this.cfu = cfu;
		this.setBorsa(borsa);
	}
	public Giocatore() {
		this(CFU_INIZIALI, new Borsa());
	}
	
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}	
}
