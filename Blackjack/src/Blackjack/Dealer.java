package Blackjack;
import java.util.ArrayList;

/*
 * Crea un dealer contra el que juega el jugador.
 */
class Dealer {
	ArrayList<Carta> mano;	//representa la mano del dealer
	private int valorMano=0;	//valor de la mano del dealer (empieza en 0)
	private Carta[] manoEnArray;	//usado para convertir la mano del dealer en array
	private int contadorAce;	//cuenta los Ace en la mano del dealer
	Dealer(Baraja baraja)
	{
	    mano = new ArrayList<>();
	    manoEnArray = new Carta[]{};
	    int contadorAce=0;
	    for(int i=0; i<2; i++)
	    {
	        mano.add(baraja.robarCarta());
	    }
	    manoEnArray = mano.toArray(manoEnArray);
	    for(int i=0; i<manoEnArray.length; i++)
	    {
	        valorMano += manoEnArray[i].getValor();
	        if(manoEnArray[i].getValor()==11)
	        {
	            contadorAce++;
	        }
	        while(contadorAce>0 && valorMano>21)
	        {
	            valorMano-=10;
	            contadorAce--;
	        }
	    }
	}
	
	/*
	 * Pinta la primera carta del dealer (la carta destapada al comienzo del juego).
	 */
	public void muestraPrimeraCarta()
	{
	    Carta[] primeraCarta = new Carta[]{};
	    primeraCarta = mano.toArray(primeraCarta);
	    System.out.println("["+primeraCarta[0]+"]");
	}
	
	/*
	 * Dá otra carta al dealer y actualiza el valor de su mano. Toma en cuenta el valor de los Aces.
	 */
	public void pideCarta(Baraja baraja)
	{
	    mano.add(baraja.robarCarta());
	    manoEnArray = mano.toArray(manoEnArray);
	    valorMano = 0;
	    for(int i=0; i<manoEnArray.length; i++)
	    {
	        valorMano += manoEnArray[i].getValor();
	        if(manoEnArray[i].getValor()==11)
	        {
	            contadorAce++;
	        }
	        while(contadorAce>0 && valorMano>21)
	        {
	            valorMano-=10;
	            contadorAce--;
	        }
	    }
	}
	
	/*
	 * Determina si el dealer quiere pedir de acuerdo a las reglas del Blackjack clásico.
	 */
	public boolean quierePedir()
	{
	    if(valorMano<17)
	    {
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Devuelve true si el dealer tiene blackjack.
	 */
	public boolean tieneBlackJack()
	{
	    if(mano.size()==2 && valorMano==21)
	    {
	        System.out.println("El dealer tiene blackjack!");
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Pinta la mano del Dealer.
	 */
	public void muestraMano()
	{
	    System.out.println(mano);
	}
	
	/*
	 * Devuelve el valor de la mano del dealer.
	 */
	public int getValorMano()
	{
	    return valorMano;
	}
	
	/*
	 * Determina si el dealer se ha pasado.
	 */
	public boolean pasado(int valorMano)
	{
	    if(valorMano>21)
	    {
	        System.out.println("El dealer se ha pasado!");
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Toma el turno el dealer y devuelve el valor de su mano.
	 */
	public int tomaTurno(Baraja baraja)
	{
	    while(quierePedir())
	    {
	        System.out.println("El dealer pide carta.");
	        pideCarta(baraja);
	        if(pasado(valorMano))
	        {
	            break;
	        }
	    }
	    if(valorMano<=21)
	    {
	        System.out.print("El dealer se planta.");
	    }
	    return valorMano;
	}
}