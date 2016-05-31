package Blackjack;

class Carta {
	/*
	 * Crea una carta.
	 */
	private int rank;	//el rank de una carta
	private int palo;	//el palo de la carta
	private int valor;	//el valor de la carta
	private static String[] ranks = {"JOKER","ACE","DOS","TRES","CUATRO","CINCO","SEIS","SIETE","OCHO","NUEVE","DIEZ","JACK","REINA","REY"};
	private static String[] palos = {"TREBOLES","DIAMANTES","CORAZONES","PICAS"};
	
	/*
	 * Creado con un entero que representa una posición en el array de string de ranks y de palos para representar
	 * el rank y palo individual de cada carta.
	 */
	
	Carta(int palo, int valores)	//unica contructora
	{
	    this.rank=valores;
	    this.palo=palo;
	}
	/*
	 * Devuelve la información de la carta en versión string.
	 */
	public String toString()
	{
	    return ranks[rank]+" de "+palos[palo];
	}
	
	/*
	 * Devuelve el rank de una carta
	 */
	public int getRank()
	{
	    return rank;
	}
	
	/*
	 * Returns the suit of a card.
	 */
	public int getPalo()
	{
	    return palo;
	}
	
	/*
	 * Devuelve el valor de la carta. Si es jack, reina, o rey el valor es 10. Los ace son 11 por ahora.
	 */
	public int getValor()
	{
	    if(rank>10)
	    {
	        valor=10;
	    }
	    else if(rank==1)
	    {
	        valor=11;
	    }
	    else
	    {
	    	valor=rank;
	    }
	    return valor;
	}
	
	/*
	 * Establece el valor de una carta.
	 */
	public void setValor(int valor)
	{
		this.valor = valor;
	}
}

