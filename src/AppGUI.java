import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AppGUI extends JFrame {

    private JLabel msg = new JLabel("Msg: ");
    TiposPrimitivos tipo = TiposPrimitivos.NENHUM;

    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipo);
    
    // barra de 
    private JToolBar barraComandos = new JToolBar();
    private JButton jbRetas = new JButton("Retas");
    private JButton jbCirculos = new JButton("Circulos");
    private JButton jbRetangulos = new JButton("Retangulos");
    

    public AppGUI(int larg, int alt) {
        /**
         * Definicoes de janela
         */
        super("Testa Primitivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        getContentPane().setBackground(java.awt.Color.white);
        

        // Adicionando os componentes
        barraComandos.add(jbRetas);
        barraComandos.add(jbCirculos);
        barraComandos.add(jbRetangulos);
 
        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);

        Eventos eventos = new Eventos();
        jbRetas.addActionListener(eventos);
        jbCirculos.addActionListener(eventos);
        jbRetangulos.addActionListener(eventos);
 
    }

    private class Eventos implements ActionListener{

        TiposPrimitivos tipo = TiposPrimitivos.RETAS;

        public void actionPerformed(ActionEvent event) {            
        
        	
            if (event.getSource() == jbRetas){
                tipo = TiposPrimitivos.RETAS;
            } else if (event.getSource() == jbCirculos){
                tipo = TiposPrimitivos.CIRCULOS;
            } else if (event.getSource() == jbRetangulos){
                tipo = TiposPrimitivos.RETANGULOS;
            }
 
            // Enviando a Forma a ser desenhada e a cor da linha
            areaDesenho.setTipo( tipo );
 
        }
    } 
}
