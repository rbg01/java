import javax.swing.JOptionPane;
public class Factorial{
	
    public static void main(String[] args) {
    	
        String texto=JOptionPane.showInputDialog("Introduce un numero");
        int numero=Integer.parseInt(texto);
        JOptionPane.showMessageDialog(null, "El factorial de "+numero+ " es " +factorial(numero));
        
    }
    
   public static int factorial (int numero){
	   
        int res=1;
        //Se inicia con el numero anterior al que introducimos
        for(int i=1;i<=numero;i++){
            //Acumulamos el resultado
            res*=i;
        }
        return res;
    }
}