package Blackjack;
import java.util.*;

public class Blackjack {
	private static int efectivo;	//dinero con el que el jugador apuesta
	private static int apuesta;	//cuánto quiere apostar el jugador
	private static int contadorAce;	//cuántos Ace tiene el jugador en la mano
	private static int valorMano;	//el valor de la mano del jugador
	private static String nombre;	//nombre del jugador
	private static Scanner scan;
	private static Scanner siono;
	private static Scanner pideOPasa;
	private static Scanner doblar;
	private static Scanner dinero;
	private static Scanner scan2;
	
	public static void main(String[] args){
	    System.out.println("¡Hola! Elige un nickname:");
	    scan2 = new Scanner(System.in);
	    nombre = scan2.nextLine();
	    System.out.println("Buenas, "+nombre+", vamos a jugar BlackJack!");
	    System.out.println("¿Con cuánto dinero quieres empezar?");
	    dinero = new Scanner(System.in);
	    efectivo = dinero.nextInt();
	    System.out.println("Empiezas con: "+efectivo);
	    while(efectivo>0){
	        Baraja baraja = new Baraja();	//initialize deck, dealer, hands, and set the bet.
	        baraja.mezclar();
	        contadorAce=0;
	        Dealer dealer = new Dealer(baraja);
	        List<Carta> mano = new ArrayList<>();
	        mano.add(baraja.robarCarta());
	        mano.add(baraja.robarCarta());
	        System.out.println("¿Cuánto te gustaría apostar?");
	        apuesta=Apuesta(efectivo);
	        System.out.println("Efectivo:"+(efectivo-apuesta));
	        System.out.println("Dinero en la mesa:"+apuesta);
	        System.out.println("Esta es tu mano: ");
	        System.out.println(mano);
	        int valorMano = calcValorMano(mano);
	        System.out.println("El dealer está enseñando: ");
	        dealer.muestraPrimeraCarta();
	        if(tieneBlackJack(valorMano) && dealer.tieneBlackJack())	//comprueba si el jugador y el dealer tienen blackjack.
	        {
	            Empate();
	        }
	        else if(tieneBlackJack(valorMano))	//comprueba si el jufador tiene blackjack.
	        {
	            System.out.println("Tienes BlackJack!");
	            System.out.println("Ganas el doble!");
	            efectivo=efectivo+apuesta;
	            Gana();
	        }
	        else if(dealer.tieneBlackJack())	//comprueba si el dealer tiene blackjack.
	        {
	            System.out.println("Esta es la mano del dealer:");
	            dealer.muestraMano();
	            Pierde();
	        }
	        else
	        {
	            if(2*apuesta<efectivo)	//comprueba si el jugador puede doblar.
	            {
	                System.out.println("¿Te gustaría doblar?");	//permite al jugador doblar.
	                doblar = new Scanner(System.in);
	                String doblado = doblar.nextLine();
	                while(!esSiONo(doblado))
	                {
	                    System.out.println("Escribe si o no.");
	                    doblado = doblar.nextLine();
	                }
	                if(doblado.equals("si"))
	                {
	                    System.out.println("Has optado por doblar!");
	                    apuesta=2*apuesta;
	                    System.out.println("Efectivo:"+(efectivo-apuesta));
	                    System.out.println("Dinero en el tablero:"+apuesta);
	                }
	            }
	            System.out.println("¿Pides o pasas?");
	            pideOPasa = new Scanner(System.in);
	            String pedido = pideOPasa.nextLine();
	            while(!pideOPasa(pedido))
	            {
	                System.out.println("Escribe 'pido' o 'paso'.");
	                pedido = pideOPasa.nextLine();
	            }
	            while(pedido.equals("pido"))	//pide tantas veces como quiera.
	            {
	                Pide(baraja, mano);
	                System.out.println("Tu mano es:");
	                System.out.println(mano);
	                valorMano = calcValorMano(mano);
	                if(compruebaPasado(valorMano))	//comprueba si el usuario se ha pasado
	                {
	                    Pierde();
	                    break;
	                }
	                if(valorMano<=21 && mano.size()==5)	//comprueba un five card trick.
	                {
	                    fivecardtrick();
	                    break;
	                }
	                System.out.println("¿Pides o pasas?");
	                pedido = pideOPasa.nextLine();
	            }
	            if(pedido.equals("paso"))	//el jugador pasa
	            {
	                int manoDealer = dealer.tomaTurno(baraja);	//toma el turno el dealer.
	                System.out.println("");
	                System.out.println("Esta es la mano del dealer:");
	                dealer.muestraMano();
	                if(manoDealer>21)	//si el dealer se pasa el jugador gana.
	                {
	                    Gana();
	                }
	                else
	                {
	                    int jugador = 21-valorMano;	//comprueba quién está más cerca de 21
	                    int deal = 21-manoDealer;
	                    if(jugador==deal)
	                    {
	                        Empate();
	                    }
	                    if(jugador<deal)
	                    {
	                        Gana();
	                    }
	                    if(deal<jugador)
	                    {
	                        Pierde();
	                    }
	                }
	            }
	        }
	    System.out.println("¿Quieres jugar otra vez?");
	    siono = new Scanner(System.in);
	    String respuesta = siono.nextLine();
	    while(!esSiONo(respuesta))
	            {
	                System.out.println("Escribe si o no.");
	                respuesta = siono.nextLine();
	            }
	    if(respuesta.equals("no"))
	    {
	        break;
	    }
	}
	    System.out.println("Tu efectivo: "+efectivo);
	    if(efectivo==0)
	    {
	        System.out.println("¡Te quedaste sin dinero!");
	    }
	    else
	    {
	        System.out.println("¡Disfruta de tus ganancias, "+nombre+"!");
	    }
	}	//Aqui termina el Main
	
	/*
	 * Comprueba si el jugador tiene blackjack.
	 */
	public static boolean tieneBlackJack(int valorMano)
	{
	    if(valorMano==21)
	    {
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Comprueba el valor de la mano del jugador.
	 */
	public static int calcValorMano(List<Carta> mano)
	{
	    Carta[] arrayCartaMano = new Carta[]{};
	    arrayCartaMano = mano.toArray(arrayCartaMano);
	    int valorMano=0;
	    for(int i=0; i<arrayCartaMano.length; i++)
	    {
	        valorMano += arrayCartaMano[i].getValor();
	        if(arrayCartaMano[i].getValor()==11)
	        {
	            contadorAce++;
	        }
	        while(contadorAce>0 && valorMano>21)
	        {
	            valorMano-=10;
	            contadorAce--;
	        }
	    }
	    return valorMano;
	}
	
	/*
	 * Pregunta al jugador cuando quiere apostar.
	 */
	public static int Apuesta(int efectivo)
	{
	    scan = new Scanner(System.in);
	    int apuesta=scan.nextInt();
	    while(apuesta>efectivo)
	    {
	        System.out.println("¡No puedes apostar más de lo que tienes!");
	        System.out.println("¿Cuánto quieres apostar?");
	        apuesta=scan.nextInt();
	    }
	    return apuesta;
	}
	
	/*
	 * Se llama si el jugador gana.
	 */
	public static void Gana()
	{
	    System.out.println("¡Felicidades, has ganado!");
	    efectivo=efectivo+apuesta;
	    System.out.println("Efectivo: "+efectivo);
	}
	
	/*
	 * Se llama si el jugador pierde.
	 */
	public static void Pierde()
	{
	    System.out.println("Lo siento, has perdido.");
	    efectivo=efectivo-apuesta;
	    System.out.println("Efectivo: "+efectivo);
	}
	
	/*
	 * Se llama si hay empate
	 */
	public static void Empate()
	{
	    System.out.println("¡Es un empate!");
	    System.out.println("Tienes tu dinero de vuelta.");
	    System.out.println("Efectivo: "+efectivo);
	}
	
	/*
	 * Añade una carta a la mano del usuario y calcula el valor de la mano. Los Aces se toman en cuenta.
	 */
	public static void Pide(Baraja baraja, List<Carta> mano)
	{
	    mano.add(baraja.robarCarta());
	    Carta[] arrayCartaMano = new Carta[]{};
	    arrayCartaMano = mano.toArray(arrayCartaMano);
	    valorMano = 0;
	    for(int i=0; i<arrayCartaMano.length; i++)
	    {
	        valorMano += arrayCartaMano[i].getValor();
	        if(arrayCartaMano[i].getValor()==11)
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
	 * Comprueba si el jugador ha escrito pido o paso.
	 */
	public static boolean pideOPasa(String pedido)
	{
	    if(pedido.equals("pido") || pedido.equals("paso"))
	    {
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Comprueba si el jugador se ha pasado.
	 */
	public static boolean compruebaPasado(int valorMano)
	{
	    if(valorMano>21)
	    {
	        System.out.println("¡Te has pasado!");
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Comprueba si el jugador ha escrito si o no.
	 */
	public static boolean esSiONo(String respuesta)
	{
	    if(respuesta.equals("si") || respuesta.equals("no"))
	    {
	        return true;
	    }
	    return false;
	}
	
	/*
	 * Se llama si el jugador tiene un five card trick.
	 */
	public static void fivecardtrick()
	{
	    System.out.println("Has conseguido un five card trick!");
	    Gana();
	}
}