import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

class AppGUI extends JFrame {

	private JLabel msg = new JLabel("Msg: ");
	TiposPrimitivos tipo = TiposPrimitivos.NENHUM;

	private PainelDesenho areaDesenho = new PainelDesenho(msg, tipo);

	// barra de 
	private JToolBar barraComandos = new JToolBar();
	private JButton jbRetas = new JButton("Retas");
	private JButton jbCirculos = new JButton("Circulos");
	private JButton jbRetangulos = new JButton("Retangulos");
	private JButton jbLinhaPoligonal = new JButton("Linha Poligonal");
	private JButton jbCarregarDesenho = new JButton("Recarregar Desenho");
	private JButton jbSalvarDesenho = new JButton("Salvar Desenho");

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
		barraComandos.add(jbSalvarDesenho);
		barraComandos.add(jbLinhaPoligonal);

		add(barraComandos, BorderLayout.NORTH);                
		add(areaDesenho, BorderLayout.CENTER);                
		add(msg, BorderLayout.SOUTH);

		Eventos eventos = new Eventos();
		jbRetas.addActionListener(eventos);
		jbCirculos.addActionListener(eventos);
		jbRetangulos.addActionListener(eventos);
		jbCarregarDesenho.addActionListener(eventos);
		jbSalvarDesenho.addActionListener(eventos);
		jbLinhaPoligonal.addActionListener(eventos);

	}
	
	private class Eventos implements ActionListener{

		public void actionPerformed(ActionEvent event) {            

			if (event.getSource() == jbRetas){
				areaDesenho.setTipo(TiposPrimitivos.RETAS);
			} else if (event.getSource() == jbCirculos){
				areaDesenho.setTipo(TiposPrimitivos.CIRCULOS);
			} else if (event.getSource() == jbRetangulos){
				areaDesenho.setTipo(TiposPrimitivos.RETANGULOS);
			} else if (event.getSource() == jbLinhaPoligonal){
				areaDesenho.setTipo(TiposPrimitivos.LINHA_POLIGONAL);
			} else if( event.getSource() == jbCarregarDesenho) {
				loadFile();
			} else if( event.getSource() == jbSalvarDesenho) {
				saveFile();
			}
			
			System.out.println("Botão clicado: " + ( (JButton) event.getSource()).getText());
			
		}
	}

	
	private void loadFile() {
		
		JFileChooser openFile = new JFileChooser();
		openFile.setFileFilter(new FileNameExtensionFilter("xml file","xml"));
        openFile.showOpenDialog(null);
        
		FileReader fr = new FileReader();
		File f = openFile.getSelectedFile();
		
		if(f != null) {
			boolean fullLoad = fr.readFile(f.getPath());
			
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
	
	private void saveFile() {
		
		JFileChooser result = new JFileChooser();
		result.setFileFilter(new FileNameExtensionFilter("xml file","xml"));
		result.showSaveDialog(null);
            
        File targetFile = result.getSelectedFile();

        if (! Conversor.fileExt(targetFile.getName()).equalsIgnoreCase("xml")) {
        		targetFile = new File(targetFile.toString() + ".xml");
        }
        
        try {
			targetFile.createNewFile();
			FileWriter.write(targetFile, areaDesenho.getRetas(), areaDesenho.getCirculos(), areaDesenho.getRetangulos());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
