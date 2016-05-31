package Blackjack;
import java.util.ArrayList;
import java.util.Random;

/*
 * Crea y mezcla una baraja de 52 cartas.
 */
class Baraja {
	private ArrayList<Carta> baraja;	//representa una baraja de cartas
	Baraja()	//constructora por defecto
	{
	    baraja = new ArrayList<Carta>();
	    for(int i=0; i<4; i++)	//4 palos
	    {
	        for(int j=1; j<=13; j++)	//13 cartas de cada palo
	        {
	            baraja.add(new Carta(i,j));
	        }
	    }
	}
	
	/*
	 * Mezcla la baraja
	 */
	public void mezclar()
	{
		Random random = new Random();
	    Carta aux;
	    for(int i=0; i<200; i++)
	    {
	        int carta1 = random.nextInt(baraja.size()-1);
	        int carta2 = random.nextInt(baraja.size()-1);
	        aux = baraja.get(carta2);
	        baraja.set(carta2, baraja.get(carta1));
	        baraja.set(carta1, aux);
	    }
	}
	
	/*
	 * Roba una carta de la baraja
	 */
	public Carta robarCarta()
	{
	    return baraja.remove(0);
	}
}