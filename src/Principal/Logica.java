package Principal;

import javax.swing.JButton;

/**
 * @author César
 * @author Cristo
 */

public class Logica{
    
    static int x;
    private final int vacio = 0,blanca = 1,negra = 2,blancaR = 3,negraR = 4;
    //String aux = "ficha";
    //Estado 0 es casilla vacia
    static int tablero[][] = new int[8][8];
    Interfaz graph = new Interfaz();   
    
    public Logica(){
        
        this.graphToLogic(graph.tablero);
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println("");
        }
    }
    
     

    
    public void graphToLogic(JButton[][] tablerog){
        
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8; j++) {
               if(tablerog[i][j] != null && tablerog[i][j].getIcon() == (graph.negra)){
                  this.tablero[i][j] = negra; 
               }
               if(tablerog[i][j] != null && tablerog[i][j].getIcon() == (graph.blanca)){
                   this.tablero[i][j] = blanca;
               }
               if(tablerog[i][j] != null && tablerog[i][j].getIcon() == (graph.blancaR)){
                   this.tablero[i][j] = blancaR;
               }
               if(tablerog[i][j] != null && tablerog[i][j].getIcon() == (graph.negraR)){
                   this.tablero[i][j] = negraR;
               }
               if(tablerog[i][j].getIcon() == (null)){
                   this.tablero[i][j] = vacio;
               }
               
            }
        }
        
        for (int i = 0; i < 8; i++) {
            
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println("");
        }
        
    }
           
    public static int notacionAlberto(int y){
        int interno1 = y;
        return (interno1*-1+8);
    }
    
}
