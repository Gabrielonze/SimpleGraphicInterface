import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class AppGUI extends JFrame {

	private JLabel msg = new JLabel("Msg: ");
	ModosDeTrabalho tipo = ModosDeTrabalho.NENHUM;

	private PainelDesenho areaDesenho = new PainelDesenho(msg, tipo);
	private JToolBar barraComandos = new JToolBar();
	private JToolBar barraComandosPrimitivos = new JToolBar(JToolBar.VERTICAL);

	private JButton jbRetas = new JButton("\u2571");
	private JButton jbCirculos = new JButton("\u2d54");
	private JButton jbRetangulos = new JButton("\u25fb");
	private JButton jbLinhaPoligonal = new JButton("\u29d9");
	private JButton jbPoligono = new JButton("\u2394");
	private JButton jbCarregarDesenho = new JButton("Recarregar Desenho");
	private JButton jbSalvarDesenho = new JButton("Salvar Desenho");
	private JButton jbCor = new JButton("Trocar Cor");
	private JButton jbLimparTela = new JButton("Limpar Tela");
	private JButton jbSelecionarForma = new JButton("\u2316");

	private JButton jbApagar = new JButton("\u232B");
	private JButton jbMover = new JButton("\u2932");
	private JButton jbRotacionar = new JButton("\u27f2");
	private JButton jbEscalar = new JButton("\u219B");


	public AppGUI(int larg, int alt) {
		/**
		 * Definicoes de janela
		 */
		super("Testa Primitivos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(larg, alt);
		setVisible(true);
		getContentPane().setBackground(java.awt.Color.white);
		setResizable(false);

		buttonStatus(false);
		
		jbSelecionarForma.setFont(new Font("Arial", Font.BOLD, 20));
		jbRetas.setFont(new Font("Arial", Font.BOLD, 20));
		jbCirculos.setFont(new Font("Arial", Font.BOLD, 20));
		jbRetangulos.setFont(new Font("Arial", Font.BOLD, 20));
		jbLinhaPoligonal.setFont(new Font("Arial", Font.BOLD, 20));
		jbPoligono.setFont(new Font("Arial", Font.BOLD, 20));
		jbApagar.setFont(new Font("Arial", Font.BOLD, 20));
		jbMover.setFont(new Font("Arial", Font.BOLD, 20));
		jbRotacionar.setFont(new Font("Arial", Font.BOLD, 20));
		jbEscalar.setFont(new Font("Arial", Font.BOLD, 20));
		
		// Adicionando os componentes

		barraComandosPrimitivos.add(jbSelecionarForma);
		barraComandosPrimitivos.add(jbRetas);
		barraComandosPrimitivos.add(jbCirculos);
		barraComandosPrimitivos.add(jbRetangulos);
		barraComandosPrimitivos.add(jbLinhaPoligonal);
		barraComandosPrimitivos.add(jbPoligono);
		barraComandosPrimitivos.add(jbApagar);
		barraComandosPrimitivos.add(jbMover);
		barraComandosPrimitivos.add(jbRotacionar);
		barraComandosPrimitivos.add(jbEscalar);
		barraComandosPrimitivos.setFloatable(false);
		
		barraComandos.add(jbCarregarDesenho);
		barraComandos.add(jbSalvarDesenho);
		barraComandos.add(jbCor);
		barraComandos.add(jbLimparTela);
		barraComandos.setFloatable(false);
		jbRetas.setSize(300, 300);

		add(barraComandos, BorderLayout.NORTH);
		add(barraComandosPrimitivos, BorderLayout.WEST);
		add(areaDesenho, BorderLayout.CENTER);                
		add(msg, BorderLayout.SOUTH);

		
		Eventos eventos = new Eventos();
		jbRetas.addActionListener(eventos);
		jbCirculos.addActionListener(eventos);
		jbRetangulos.addActionListener(eventos);
		jbLinhaPoligonal.addActionListener(eventos);
		jbPoligono.addActionListener(eventos);
		jbCarregarDesenho.addActionListener(eventos);
		jbSalvarDesenho.addActionListener(eventos);
		jbCor.addActionListener(eventos);
		jbLimparTela.addActionListener(eventos);
		jbSelecionarForma.addActionListener(eventos);

		jbApagar.addActionListener(eventos);
		jbMover.addActionListener(eventos);
		jbRotacionar.addActionListener(eventos);
		jbEscalar.addActionListener(eventos);
		
	}
	
	private class Eventos implements ActionListener{

		public void actionPerformed(ActionEvent event) {

			buttonStatus(false);

			if (event.getSource() == jbRetas){
				areaDesenho.setTipo(ModosDeTrabalho.RETAS);
			} else if (event.getSource() == jbCirculos){
				areaDesenho.setTipo(ModosDeTrabalho.CIRCULOS);
			} else if (event.getSource() == jbRetangulos){
				areaDesenho.setTipo(ModosDeTrabalho.RETANGULOS);
			} else if (event.getSource() == jbLinhaPoligonal){
				areaDesenho.setTipo(ModosDeTrabalho.LINHA_POLIGONAL);
			} else if (event.getSource() == jbPoligono){
				areaDesenho.setTipo(ModosDeTrabalho.POLIGONO);
			} else if(event.getSource() == jbCarregarDesenho) {
				loadFile();
			} else if(event.getSource() == jbSalvarDesenho) {
				saveFile();
			} else if(event.getSource() == jbCor) {
				changeColor();
			} else if(event.getSource() == jbLimparTela) {
				limparTela();
			} else if(event.getSource() == jbSelecionarForma) {
				selecionarForma();
			} else if(event.getSource() == jbApagar) {
				apagarForma();
			} else if(event.getSource() == jbMover) {

			} else if(event.getSource() == jbRotacionar) {

			} else if(event.getSource() == jbEscalar) {

			}
			
			System.out.println("Botão clicado: " + ( (JButton) event.getSource()).getText());
			
		}
	}

	private void apagarForma() {
		buttonStatus(true);
		areaDesenho.apagarFormaSelecionada();
	}

	private void selecionarForma() {
		buttonStatus(true);
		areaDesenho.setTipo(ModosDeTrabalho.SELECIONAR);
	}
	
	private void limparTela() {
		areaDesenho.setFormas(new ArrayList<>());
		areaDesenho.repaint();
	}
	
	private void changeColor() {
		Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
		areaDesenho.setCor(newColor);
	}

	private void buttonStatus(boolean status) {
		jbApagar.setEnabled(status);
		jbMover.setEnabled(status);
		jbRotacionar.setEnabled(status);
		jbEscalar.setEnabled(status);
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
				areaDesenho.setFormas(fr.getFormas());
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
        if(targetFile != null) {
		    if (! Conversor.fileExt(targetFile.getName()).equalsIgnoreCase("xml")) {
		    		targetFile = new File(targetFile.toString() + ".xml");
		    }
		    
		    try {
				targetFile.createNewFile();
				FileWriter.write(targetFile, areaDesenho.getFormas());
			} catch (IOException e) {
				e.printStackTrace();
			}    
        }
	}
	
}
