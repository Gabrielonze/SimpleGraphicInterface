import formas.Ponto;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showInputDialog;

class AppGUI extends JFrame {

	private JLabel msg = new JLabel("Msg: ");
	ModosDeTrabalho tipo = ModosDeTrabalho.NENHUM;

	private PainelDesenho areaDesenho = new PainelDesenho(msg, tipo);
	private JToolBar barraComandos = new JToolBar();
	private JToolBar barraComandosPrimitivos = new JToolBar(JToolBar.VERTICAL);

	private JButton jbRetas = new JButton();
	private JButton jbCirculos = new JButton();
	private JButton jbRetangulos = new JButton();
	private JButton jbLinhaPoligonal = new JButton();
	private JButton jbPoligono = new JButton();
	private JButton jbCarregarDesenho = new JButton("Load");
	private JButton jbSalvarDesenho = new JButton("Save");
	private JButton jbCor = new JButton();
	private JButton jbLimparTela = new JButton("Reset Canvas");
	private JButton jbSelecionarForma = new JButton();

	private JButton jbApagar = new JButton();
	private JButton jbMover = new JButton();
	private JButton jbRotacionar = new JButton();
	private JButton jbEscalar = new JButton();


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

		try {
			Image selecaoImg = ImageIO.read(getClass().getResource("img/selecao.png"));
			jbSelecionarForma.setIcon(new ImageIcon(selecaoImg));
			Image retanguloImg = ImageIO.read(getClass().getResource("img/retangulo.png"));
			jbRetangulos.setIcon(new ImageIcon(retanguloImg));
			Image circuloImg = ImageIO.read(getClass().getResource("img/circulo.png"));
			jbCirculos.setIcon(new ImageIcon(circuloImg));
			Image poligonoImg = ImageIO.read(getClass().getResource("img/poligono.png"));
			jbPoligono.setIcon(new ImageIcon(poligonoImg));

			Image retaImg = ImageIO.read(getClass().getResource("img/reta.png"));
			jbRetas.setIcon(new ImageIcon(retaImg));
			Image linhaImg = ImageIO.read(getClass().getResource("img/linha.png"));
			jbLinhaPoligonal.setIcon(new ImageIcon(linhaImg));

			Image escalaImg = ImageIO.read(getClass().getResource("img/escala.png"));
			jbEscalar.setIcon(new ImageIcon(escalaImg));
			Image transladarImg = ImageIO.read(getClass().getResource("img/trasladar.png"));
			jbMover.setIcon(new ImageIcon(transladarImg));
			Image rotacionarImg = ImageIO.read(getClass().getResource("img/rotacao.png"));
			jbRotacionar.setIcon(new ImageIcon(rotacionarImg));
			Image deletarImg = ImageIO.read(getClass().getResource("img/deletar.png"));
			jbApagar.setIcon(new ImageIcon(deletarImg));
			Image rainbowImg = ImageIO.read(getClass().getResource("img/rainbow.jpg"));
			jbCor.setIcon(new ImageIcon(rainbowImg));
		} catch (Exception ex) {
			System.out.println(ex);
		}

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
		barraComandosPrimitivos.add(jbCor);
		barraComandosPrimitivos.setFloatable(false);

		barraComandos.add(jbCarregarDesenho);
		barraComandos.add(jbSalvarDesenho);
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
				transladarForma();
			} else if(event.getSource() == jbRotacionar) {
				rotacionarForma();
			} else if(event.getSource() == jbEscalar) {
				escalarForma();
			}
			
			System.out.println("Botão clicado: " + ( (JButton) event.getSource()).getText());
			
		}
	}

	private void rotacionarForma() {
		buttonStatus(true);
		areaDesenho.setTipo(ModosDeTrabalho.ROTACIONAR);

	}

	private void escalarForma() {
		buttonStatus(true);

		String fatorEscalarString = JOptionPane.showInputDialog("Quantas vezes deseja escalar?");

		try{
			if(fatorEscalarString.matches("[0-9]{1,13}(\\.[0-9]*)?")){
				areaDesenho.escalarFormaSelecionada(Double.parseDouble(fatorEscalarString));
			} else {
				throw new Exception("Valor fora do padrão Double");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Valor Inválido");
		}

	}

	private void transladarForma(){
		buttonStatus(true);
		String moveString = JOptionPane.showInputDialog("Quanto deseja mover?\nInsira: (x, y) Exemplo:\n30, 20 ");

		if(moveString != null){

			String[] split = moveString.split(",");

			try{
				if(split.length == 2){
					String xStr = split[0].trim();
					String yStr = split[0].trim();
					int fatorX = Integer.parseInt(xStr);
					int fatorY = Integer.parseInt(yStr);
					areaDesenho.transladarFormaSelecionada(fatorX, fatorY);
				} else {
					throw new Exception("Valor fora do formato 'x,y'");
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Valor Inválido");
			}
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
