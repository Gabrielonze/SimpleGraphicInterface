import formas.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
	JLabel msg;
	ModosDeTrabalho tipo;
	Ponto p1;
	Ponto p2, oldP2;
	List<Reta> retas = new ArrayList<Reta>();
	List<Circulo> circulos = new ArrayList<Circulo>();
	List<Retangulo> retangulos = new ArrayList<Retangulo>();
	List<LinhaPoligonal> linhasPoligonais = new ArrayList<LinhaPoligonal>();
	Color currentColor = Color.BLACK;
	LinhaPoligonal lp = null;
	
	private Image offScreenImage = null;
	private Graphics offScreenGraphics = null;

	public void setCor(Color cor) {
		this.currentColor = cor;

		if(lp != null) {
			lp.set_cor(cor);
		}
				
	}
	
	public void setRetas(List<Reta> retas) {
		this.retas = retas;
	}

	public void setCirculos(List<Circulo> circulos) {
		this.circulos = circulos;
	}
	
	public void setRetangulos(List<Retangulo> retangulos) {
		this.retangulos = retangulos;
	}
	
	public void setLinhasPoligonais(List<LinhaPoligonal> linhasPoligonais) {
		this.linhasPoligonais = linhasPoligonais;
	}

	public List<Reta> getRetas() {
		return retas;
	}

	public List<Circulo> getCirculos() {
		return circulos;
	}

	public List<Retangulo> getRetangulos() {
		return retangulos;
	}
	
	public List<LinhaPoligonal> getLinhasPoligonais() {
		return linhasPoligonais;
	}

	public PainelDesenho(JLabel msg, ModosDeTrabalho tipo) {
		this.tipo = tipo;
		this.msg = msg;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setTipo(ModosDeTrabalho tipo) {
		this.tipo = tipo;
		p1 = null;
		p2 = null;
		
		msg.setText("Primitivo: "+tipo.name().replace("_", " "));
		
		if(this.tipo == ModosDeTrabalho.LINHA_POLIGONAL || this.tipo == ModosDeTrabalho.POLIGONO) {
			lp = new LinhaPoligonal(new ArrayList<Ponto>(), currentColor);
		}

	}

	public ModosDeTrabalho getTipo() {
		return tipo;
	}


	public void paintComponent(Graphics g) {
		
		final Dimension d = getSize();

		if(offScreenImage == null) {
			offScreenImage = createImage(d.width, d.height);
			
		}
		offScreenGraphics = offScreenImage.getGraphics();
		offScreenGraphics.setColor(Color.WHITE);
		offScreenGraphics.fillRect(0, 0, d.width, d.height);
	
		desenharPrimitivos(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1 && ( tipo == ModosDeTrabalho.LINHA_POLIGONAL ||  tipo == ModosDeTrabalho.POLIGONO)) {
			Ponto po = new Ponto((double)e.getX(), (double)e.getY());
			lp.addPonto(po);
			
			p1 = new Ponto(e.getX(), e.getY());
			repaint();
			
		} else if (e.getButton() == 1 && tipo != ModosDeTrabalho.SELECIONAR) {
			p1 = new Ponto(e.getX(), e.getY());
			p2 = null;
		} else if (e.getButton() == 3 && tipo == ModosDeTrabalho.LINHA_POLIGONAL) {
			p1 = null;
			p2 = null;
			linhasPoligonais.add(lp);
			lp = new LinhaPoligonal(new ArrayList<Ponto>(), currentColor);
			repaint();
		} else if (e.getButton() == 3 && tipo == ModosDeTrabalho.POLIGONO) {
			p1 = null;
			p2 = null;
			lp.setPoligono_fechado(true);
			linhasPoligonais.add(lp);
			lp = new LinhaPoligonal(new ArrayList<Ponto>(), currentColor);
			repaint();
		} else if (e.getButton() == 1 && tipo == ModosDeTrabalho.SELECIONAR) {
			selecionarForma(e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (tipo == ModosDeTrabalho.RETAS && p1 != null && p2 != null) {
			retas.add(new Reta(p1, p2, currentColor));
		}
		else if (tipo == ModosDeTrabalho.CIRCULOS && p1 != null && p2 != null) {
			circulos.add(new Circulo(p1, p2.calcularDistancia(p1), currentColor));
		} else if (tipo == ModosDeTrabalho.RETANGULOS && p1 != null && p2 != null) {
			retangulos.add(new Retangulo(p1, p2, currentColor));
		}
		
		if(tipo != ModosDeTrabalho.LINHA_POLIGONAL && tipo != ModosDeTrabalho.POLIGONO) {
			p1 = null;
			p2 = null;
		}
	}

	public void mouseMoved(MouseEvent e) {
		message(e);
		if(tipo == ModosDeTrabalho.LINHA_POLIGONAL || tipo == ModosDeTrabalho.POLIGONO) {
			if(p1 != null) {
				oldP2 = p2;
				p2 = new Ponto(e.getX(), e.getY());
				repaint();
			}
			
		}    
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}


	public void mouseDragged(MouseEvent e) {

		message(e);
		if(tipo != ModosDeTrabalho.LINHA_POLIGONAL && tipo != ModosDeTrabalho.POLIGONO) {
			oldP2 = p2;
			p2 = new Ponto(e.getX(), e.getY());
			repaint();
		}
	}

	public void repaintAll(Graphics g) {
		for(Reta r : retas) {
			r.desenhar(g);
		}
		for(Circulo c : circulos) {
			c.desenhar(g);
		}
		for(Retangulo c : retangulos) {
			c.desenhar(g);
		}
		for(LinhaPoligonal c : linhasPoligonais) {
			c.desenhar(g);
		}

		if(lp != null) {
			lp.desenhar(g);
		}
		
		setBackground(java.awt.Color.white);
	}
	
	public void selecionarForma(int x, int y) {
		System.out.println("Selected: FORM in ("+ x + ", " + y + ")");
		boolean encontrouForma = false;
		double margemDeErro = 5;
		Object forma;
		
		for(Reta r : retas) {
			if ( pontoNaReta(r, new Ponto(x,y)) ) {
				encontrouForma = true;
				forma = r;
				((Reta)forma).set_cor(Color.RED);
				repaint();
				break;
			}
			
		}
		
		System.out.println(encontrouForma);
		/*for(Circulo c : circulos) {
			
		}
		for(Retangulo c : retangulos) {
			
		}
		for(LinhaPoligonal c : linhasPoligonais) {

		}
		for(Poligono c : poligonos) {

		}*/
		
	}
	  

	public void message(MouseEvent e){
		msg.setText("Primitivo: "+tipo.name().replace("_", " ")+" (" + e.getX() + ", " + e.getY() + ")");
	}

	public void desenharPrimitivos(Graphics g) {

		if ((tipo == ModosDeTrabalho.RETAS || tipo == ModosDeTrabalho.LINHA_POLIGONAL || tipo == ModosDeTrabalho.POLIGONO) && (p1 != null) && (p2 != null)) {

			Reta rOld = null;
			if(oldP2 != null) {
				rOld = new Reta(p1, oldP2, Color.WHITE);
				rOld.desenhar(g);
			}

			Reta r = new Reta(p1, p2, currentColor);
			r.desenhar(g);
		}

		if ((tipo == ModosDeTrabalho.CIRCULOS) && (p1 != null) && (p2 != null)) {
			Circulo r = new Circulo(p1, p2.calcularDistancia(p1), currentColor);

			Circulo rOld = null;
			if(oldP2 != null) {
				rOld = new Circulo(p1, oldP2.calcularDistancia(p1), Color.WHITE);
				rOld.desenhar(g);
			}
			
			r.desenhar(g);
		}
		
		if ((tipo == ModosDeTrabalho.RETANGULOS) && (p1 != null) && (p2 != null)) {
			Retangulo r = new Retangulo(p1, p2, currentColor);

			Retangulo rOld = null;
			if(oldP2 != null) {
				rOld = new Retangulo(p1, oldP2, Color.WHITE);
				rOld.desenhar(g);
			}
			
			try {
				r.desenhar(g);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		repaintAll(g);

	}

	private boolean pontoNaReta(Reta r, Ponto p){
		int margemDeErro = 10;
		return (r.calculaDistanciaEntreRetaEPonto(p) <= margemDeErro)
				&& ((p.getX() >= r.getP1().getX() && p.getX() <= r.getP2().getX()) || (p.getX() >= r.getP2().getX() && p.getX() <= r.getP1().getX()))
				&& ((p.getY() >= r.getP1().getY() && p.getY() <= r.getP2().getY()) || (p.getY() >= r.getP2().getY() && p.getY() <= r.getP1().getY()));
	}

}
