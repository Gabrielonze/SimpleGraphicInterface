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
	private JButton jbCarregarDesenho = new JButton("Recarregar Desenho");

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
		barraComandos.add(jbCarregarDesenho);

		add(barraComandos, BorderLayout.NORTH);                
		add(areaDesenho, BorderLayout.CENTER);                
		add(msg, BorderLayout.SOUTH);

		Eventos eventos = new Eventos();
		jbRetas.addActionListener(eventos);
		jbCirculos.addActionListener(eventos);
		jbRetangulos.addActionListener(eventos);
		jbCarregarDesenho.addActionListener(eventos);

	}
	
	private class Eventos implements ActionListener{

		public void actionPerformed(ActionEvent event) {            

			if (event.getSource() == jbRetas){
				areaDesenho.setTipo(TiposPrimitivos.RETAS);
			} else if (event.getSource() == jbCirculos){
				areaDesenho.setTipo(TiposPrimitivos.CIRCULOS);
			} else if (event.getSource() == jbRetangulos){
				areaDesenho.setTipo(TiposPrimitivos.RETANGULOS);
			} else if( event.getSource() == jbCarregarDesenho) {
				loadFile();
			}
			
			System.out.println("Botão clicado: " + ( (JButton) event.getSource()).getText());
			
		}
	}

	
	private void loadFile() {
		
		JFileChooser openFile = new JFileChooser();
        openFile.showOpenDialog(null);
        
		FileReader fr = new FileReader();
		boolean fullLoad = fr.readFile(openFile.getSelectedFile().getPath());
		
		if(fullLoad) {
			System.out.println("Só imprimo depois de ler tudo");
			areaDesenho.apagarTudo();
			areaDesenho.setRetas(fr.getRetas());
			areaDesenho.setRetangulos(fr.getRetangulos());
			areaDesenho.setCirculos(fr.getCirculos());
			
			//TODO -> Checar se Windows consegue usar o repaint, se sim, apagar "apagarTudo"
			areaDesenho.repaint();
			
		} else {
			System.out.println("Arquivo não pode ser lido");
		}
		
	}
	
}
