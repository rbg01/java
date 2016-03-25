import javax.swing.JOptionPane;

/*1) Crea una aplicación que nos calcule el área de un circulo, cuadrado o triangulo. 
 * Pediremos que figura queremos calcular su área y según lo introducido pedirá los 
 * valores necesarios para calcular el área. Crea un método por cada figura para calcular 
 * cada área, este devolverá un número real. Muestra el resultado por pantalla

Aquí te mostramos que necesita cada figura:

Circulo: (radio^2)*PI
Triangulo: (base * altura) / 2
Cuadrado: lado * lado

*/



public class AreaFigs {
	
	
	final static double PI = 3.1415926535 ;
	
	public static void main(String[] args) {
		
		
		double resultado=0;
		
		String eleccion=JOptionPane.showInputDialog("De qué figura quieeres el Área?\n"
				+ "(circulo, rectangulo, triangulo");
	
		
		
		
	
		switch(eleccion){
		
		case "circulo":	
			String rad=JOptionPane.showInputDialog("Pues dame un radio amigo..");
			int radio=Integer.parseInt(rad);
			resultado=areaCirc(radio);
			JOptionPane.showMessageDialog(null,"El área del circulo de redio:" +radio+" es:" +resultado);
			break;
		
		case "rectangulo":
			String lado_1=JOptionPane.showInputDialog("Pues dame un lado amigo..");
			int lado1=Integer.parseInt(lado_1);
			String lado_2=JOptionPane.showInputDialog("Pues dame otro lado amigo..");
			int lado2=Integer.parseInt(lado_2);
			resultado=areaRectan(lado1, lado2);
			JOptionPane.showMessageDialog(null,"El área del rectangulo de lados:" +lado1+ "y" +lado2+ "es:" +resultado);
			break;
			
		case "triangulo":
			String bases=JOptionPane.showInputDialog("Dime una base:..");
			int base=Integer.parseInt(bases);
			String alturas=JOptionPane.showInputDialog("Dame la altura:..");
			int altura=Integer.parseInt(alturas);
			resultado=areaTriang(base, altura);
			JOptionPane.showMessageDialog(null,"El área del triangulo de base:" +base+ "y altura" +altura+ "es: " +resultado);
			break;
					
				
		
		}
			
		}

	private static double areaTriang(int base, int altura) {
		
		return (base * altura)/2;
	}

	private static double areaRectan(int lado1, int lado2) {
		
		return lado1 * lado2;
	}

	private static double areaCirc(int radio) {
		
		return  (radio^2) * PI;
	}
	

	}


