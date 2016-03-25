import javax.swing.JOptionPane;

public class PrimoApp {
	
	public static void main(String[] args) {
		
		String texto=JOptionPane.showInputDialog("Introduce un numero");
        int numero=Integer.parseInt(texto);//convertimos el String en un entero
        //Ejecutamos la funcion, como devuelve true o false, se puede colocar en un if"
        if (esPrimo(numero)){
        	JOptionPane.showMessageDialog(null,"El numero "+numero+" es primo" );//-->para cuadro de diálogo
            //System.out.println("El numero "+numero+" es primo");
        }else{
        	JOptionPane.showMessageDialog(null,"El numero "+numero+" no es primo" );//-->para cuadro de diálogo
            //System.out.println("El numero "+numero+" no es primo");
        }
		

	}

	private static boolean esPrimo(int numero) {
		
		int divisores=0;
		
		for(int i=1; i<=numero; i++){
			if (numero%i == 0)
				divisores+=1;
		}
		if (divisores == 2)
			return true;
		else
			
		return false;
	}
	
	

}
