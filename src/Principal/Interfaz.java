package Principal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author cesar
 * @author cristo
 */
public class Interfaz extends JFrame implements ActionListener {

    static Logica log;
    int turno = 0;
    int click = -1;
    String entrada = "", salida = "";
    //Declaración de imagenes
    ImageIcon blanca = new ImageIcon("roja.png");
    ImageIcon negra = new ImageIcon("negra.png");
    ImageIcon blancaR = new ImageIcon("roja_reina.png");
    ImageIcon negraR = new ImageIcon("negra_reina.png");

    static JButton tablero[][] = new JButton[8][8];

    public Interfaz() {

        this.setLayout(new BorderLayout());
        this.setTitle("Damas");
        this.setSize(660, 680); // cristo, no muevas este valor.
        this.setResizable(false);//voy a moverlo mucho, cesar
        // no, cristo, por favor no.
        //si cesar, lo haré
        JPanel panel = new JPanel();
        panel.setSize(650, 650);
        panel.setLayout(new GridLayout(8, 8));

        //Crear matriz
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = new JButton();
                panel.add(tablero[i][j]);
                tablero[i][j].addActionListener(this);
                tablero[i][j].setActionCommand("" + i + j);
            }
        }
        //Pintar matriz
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 1 || i % 2 == 1 && j % 2 == 0) {
                    tablero[i][j].setBackground(Color.BLACK);
                } else {
                    tablero[i][j].setBackground(Color.WHITE);

                }
            }
        }
        //Poner fichas negras
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 1 || i % 2 == 1 && j % 2 == 0) {
                    tablero[i][j].setIcon(negra);
                }
            }
        }
        //Poner fichas blancas
        for (int i = 7; i >= 5; i--) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0 && j % 2 == 1 || i % 2 == 1 && j % 2 == 0) {
                    tablero[i][j].setIcon(blanca);
                }
            }
        }

        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override//Se obtiene la acción de casa botón
    public void actionPerformed(ActionEvent e) {
        click++;
        if (click % 2 == 0) {
            entrada = e.getActionCommand();
        } else {
            salida = e.getActionCommand();
            validar(entrada, salida);
        }
    }

    public void validar(String entrada, String salida) {

        int filaE = Integer.parseInt(entrada.charAt(0) + "");
        int columnaE = Integer.parseInt(entrada.charAt(1) + "");
        int filaS = Integer.parseInt(salida.charAt(0) + "");
        int columnaS = Integer.parseInt(salida.charAt(1) + "");
        System.out.println("Entrada: " + filaE + "," + columnaE);
        System.out.println("Salida: " + filaS + "," + columnaS);

        if (tablero[filaE][columnaE].getIcon() == blanca && turno % 2 == 0) {

            while ((filaE >= 2 && columnaE >= 2 && tablero[filaE - 1][columnaE - 1].getIcon() == negra && tablero[filaE - 2][columnaE - 2].getIcon() == null
                    && filaS == filaE - 2 && columnaS == columnaE - 2) || (filaE >= 2 && columnaE <= 5 && tablero[filaE - 1][columnaE + 1].getIcon() == negra && tablero[filaE - 2][columnaE + 2].getIcon() == null
                    && filaS == filaE - 2 && columnaS == columnaE + 2)) {
                boolean comerI = true;
                boolean comerD = true;

                /**
                 * Comer para blancas hacia arriba izquierda
                 */
                if (filaE >= 2 && columnaE >= 2 && tablero[filaE - 1][columnaE - 1].getIcon() == negra && tablero[filaE - 2][columnaE - 2].getIcon() == null
                        && filaS == filaE - 2 && columnaS == columnaE - 2) {
                    comer(filaE - 1, columnaE - 1);
                    mover(entrada, salida, 1);
                    if (filaS < 2 && columnaS < 2 || filaS -1 < 0 || columnaS - 1 < 0 || filaS -2 < 0 || columnaS - 2 < 0 || tablero[filaS -1][columnaS -1].getIcon() != negra || tablero[filaS - 2][columnaS - 2].getIcon() != null) {
                        comerI = false;
                    }
                    if (filaS < 2 && columnaS > 5 || filaS -1 < 0 || columnaS + 1 > 7 || filaS -2 < 0 || columnaS + 2 > 7||tablero[filaS - 1][columnaS + 1].getIcon() != negra || tablero[filaS - 2][columnaS + 2].getIcon() != null ) {
                        comerD = false;
                    }
                    if (filaS == 0) {
                        coronar(filaS, columnaS, 1);
                    }
                    if (comerI == false && comerD == false) {
                        turno++;
                        break;
                    }
                    
                }

                /**
                 * Comer para blancas hacia arriba derecha
                 */
                if (filaE >= 2 && columnaE <= 5 && tablero[filaE - 1][columnaE + 1].getIcon() == negra && tablero[filaE - 2][columnaE + 2].getIcon() == null
                        && filaS == filaE - 2 && columnaS == columnaE + 2) {
                    comer(filaE - 1, columnaE + 1);
                    mover(entrada, salida, 1);
                    if (filaS < 2 && columnaS < 2 || filaS -1 < 0 || columnaS - 1 < 0 || filaS -2 < 0 || columnaS - 2 < 0 || tablero[filaS -1][columnaS -1].getIcon() != negra || tablero[filaS - 2][columnaS - 2].getIcon() != null) {
                        comerI = false;
                    }
                    if (filaS < 2 && columnaS > 5 || filaS -1 < 0 || columnaS + 1 > 7 || filaS -2 < 0 || columnaS + 2 > 7 || tablero[filaS - 1][columnaS + 1].getIcon() != negra || tablero[filaS - 2][columnaS + 2].getIcon() != null ) {
                        comerD = false;
                    }
                    if (filaS == 0) {
                        coronar(filaS, columnaS, 1);
                    }
                    if (comerI == false && comerD == false) {
                        turno++;
                        break;
                    }

                }

            }
            /**
             * Mover blancas hacia arriba izquierda
             */
            if (filaE >= 1 && columnaE >= 1 && filaS == filaE - 1 && columnaS == columnaE - 1) {
                mover(entrada, salida, 1);

                if (filaS == 0) {
                    coronar(filaS, columnaS, 1);

                }
                turno++;
                
            } /**
             * Mover blancas hacia arriba derechas
             */
            else {
                if (filaE >= 1 && columnaE <= 6 && filaS == filaE - 1 && columnaS == columnaE + 1) {
                    mover(entrada, salida, 1);

                    if (filaS == 0) {
                        coronar(filaS, columnaS, 1);

                    }
                    turno++;
                }

            }
        }

        /**
         * Programar reina blanca mover
         */
        if (tablero[filaE][columnaE].getIcon() == blancaR && turno % 2 == 0) {
            System.out.println("es reina blanca");
            if (Math.abs(filaE - columnaE) % 2 != 0 && Math.abs(filaS - columnaS) % 2 != 0) {
                System.out.println("Se mueve");
                mover(entrada, salida, 3);
                turno++;
            }
            /**
             * Comer blanca reina hacia abajo izquierda
             */
            if(columnaE < columnaS && filaE > filaS){
                int fichaF = 0;
                int fichaC = 0;
                int cont = 0;
                for (int i = filaE; i <= filaS; i++) {
                    for (int j = columnaE; j >= columnaS; j--) {
                        if (tablero[i][j].getIcon() != null) {
                            cont++;
                            fichaF = i;
                            fichaC = j;
                            break;
                        }
                    }
                }
                if(cont == 1 && tablero[filaS][columnaS].getIcon() == null){
                    comer(fichaF,fichaC);
                }
            }
            
        }
        
        

        if (tablero[filaE][columnaE].getIcon() == negra && turno % 2 != 0) {
            
            while ((filaE <= 5 && columnaE >= 2 && (tablero[filaE + 1][columnaE - 1].getIcon() == blanca || tablero[filaE + 1][columnaE - 1].getIcon() == blancaR) && tablero[filaE + 2][columnaE - 2].getIcon() == null
                    && filaS == filaE + 2 && columnaS == columnaE - 2) || (filaE <= 5 && columnaE <= 5 && (tablero[filaE + 1][columnaE + 1].getIcon() == blanca || tablero[filaE + 1][columnaE + 1].getIcon() == blancaR) && tablero[filaE + 2][columnaE + 2].getIcon() == null
                    && filaS == filaE + 2 && columnaS == columnaE + 2)) {
                boolean comerI = true;
                boolean comerD = true;
                /**
                * Comer para negras hacia abajo izquierda
                */
                if (filaE <= 5 && columnaE >= 2 && (tablero[filaE + 1][columnaE - 1].getIcon() == blanca || tablero[filaE + 1][columnaE - 1].getIcon() == blancaR) && tablero[filaE + 2][columnaE - 2].getIcon() == null
                        && filaS == filaE + 2 && columnaS == columnaE - 2) {
                    comer(filaE + 1, columnaE - 1);
                    mover(entrada, salida, 2);
                    if (filaS > 5 && columnaS < 2 || filaS + 1 > 7 || columnaS - 1 < 0 || filaS + 2 > 7 || columnaS - 2 < 0 || tablero[filaS + 1][columnaS - 1].getIcon() != blanca || tablero[filaS + 2][columnaS - 2].getIcon() != null) {
                        comerI = false;
                    }
                    if (filaS > 5 && columnaS > 5 || filaS + 1 > 7 || columnaS + 1 < 0 || filaS + 2 > 7 || columnaS + 2 > 7 || tablero[filaS + 1][columnaS + 1].getIcon() != blanca || tablero[filaS + 2][columnaS + 2].getIcon() != null) {
                        comerD = false;
                    }
                    if (filaS == 7) {
                        coronar(filaS, columnaS, 2);
                    }
                    if (comerI == false && comerD == false) {
                        turno++;
                        break;
                    }
                }
                /**
                 * Comer para negras hacia abajo derecha
                 */

                if (filaE <= 5 && columnaE <= 5 && (tablero[filaE + 1][columnaE + 1].getIcon() == blanca || tablero[filaE + 1][columnaE + 1].getIcon() == blancaR) && tablero[filaE + 2][columnaE + 2].getIcon() == null
                        && filaS == filaE + 2 && columnaS == columnaE + 2) {
                    comer(filaE + 1, columnaE + 1);
                    mover(entrada, salida, 2);
                    if (filaS > 5 && columnaS < 2 || filaS + 1 > 7 || columnaS - 1 < 0 || filaS + 2 > 7 || columnaS - 2 < 0 || tablero[filaS + 1][columnaS - 1].getIcon() != blanca || tablero[filaS + 2][columnaS - 2].getIcon() != null) {
                        comerI = false;
                    }
                    if (filaS > 5 && columnaS > 5 || filaS + 1 > 7 || columnaS + 1 < 0 || filaS + 2 > 7 || columnaS + 2 > 7 || tablero[filaS + 1][columnaS + 1].getIcon() != blanca || tablero[filaS + 2][columnaS + 2].getIcon() != null) {
                        comerD = false;
                    }
                    if (filaS == 7) {
                        coronar(filaS, columnaS, 2);
                    }
                    if (comerI == false && comerD == false) {
                        turno++;
                        break;
                    }
                } 

            }

            /**
             * Mover negras hacia abajo izquierda
             */
            if (filaE <= 6 && columnaE >= 1 && filaS == filaE + 1 && columnaS == columnaE - 1) {
                mover(entrada, salida, 2);

                if (filaS == 7) {
                    coronar(filaS, columnaS, 2);
                }
                turno++;
            } /**
             * Mover negras hacia abajo derecha
             */
            else {
                if (filaE <= 6 && columnaE <= 6 && filaS == filaE + 1 && columnaS == columnaE + 1) {
                    mover(entrada, salida, 2);

                    if (filaS == 7) {
                        coronar(filaS, columnaS, 2);
                    }
                    turno++;
                }

            }
        }

        /**
         * Programas reina negra
         */
        if (tablero[filaE][columnaE].getIcon() == negraR && turno % 2 != 0) {
            System.out.println("es reina negra");
            if (Math.abs(filaE - columnaE) % 2 != 0 && Math.abs(filaS - columnaS) % 2 != 0) {

                System.out.println("Se mueve");
                mover(entrada, salida, 4);
                turno++;

            }
        }

    }

    public void mover(String entrada, String salida, int tipo) {
        int filaE = Integer.parseInt(entrada.charAt(0) + "");
        int columnaE = Integer.parseInt(entrada.charAt(1) + "");
        int filaS = Integer.parseInt(salida.charAt(0) + "");
        int columnaS = Integer.parseInt(salida.charAt(1) + "");

        if (tipo == 1) {
            tablero[filaE][columnaE].setIcon(null);
            tablero[filaS][columnaS].setIcon(blanca);
        }
        if (tipo == 2) {
            tablero[filaE][columnaE].setIcon(null);
            tablero[filaS][columnaS].setIcon(negra);
        }
        if (tipo == 3) {
            tablero[filaE][columnaE].setIcon(null);
            tablero[filaS][columnaS].setIcon(blancaR);
        }
        if (tipo == 4) {
            tablero[filaE][columnaE].setIcon(null);
            tablero[filaS][columnaS].setIcon(negraR);
        }
        log.graphToLogic(tablero);
    }

    public void comer(int a, int b) {

        tablero[a][b].setIcon(null);

    }

    public void coronar(int a, int b, int tipo) {
        if (tipo == 1) {
            tablero[a][b].setIcon(blancaR);

        }
        if (tipo == 2) {
            tablero[a][b].setIcon(negraR);

        }
    }

    public void logicToGraph(int mtz[][]) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mtz[i][j] == 1) {
                    tablero[i][j].setIcon(blanca);
                }
                if (mtz[i][j] == 2) {
                    tablero[i][j].setIcon(negra);
                }
                if (mtz[i][j] == 3) {
                    tablero[i][j].setIcon(negraR);
                }
                if (mtz[i][j] == 4) {
                    tablero[i][j].setIcon(blancaR);
                }
                if (mtz[i][j] == 0) {
                    tablero[i][j].setIcon(null);
                }
            }
        }
    }

    public static void main(String[] args) {
        log = new Logica();

    }

}
