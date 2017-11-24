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
	Ponto2D p1;
	Ponto2D p2, oldP2;
	List<Reta2D> retas = new ArrayList<Reta2D>();
	List<Circulo2D> circulos = new ArrayList<Circulo2D>();
	List<Retangulo2D> retangulos = new ArrayList<Retangulo2D>();
	List<LinhaPoligonal2D> linhasPoligonais = new ArrayList<LinhaPoligonal2D>();
	List<Poligono2D> poligonos = new ArrayList<Poligono2D>();
	Color currentColor = Color.BLACK;
	LinhaPoligonal2D lp = null;
	Poligono2D po = null;
	
	private Image offScreenImage = null;
	private Graphics offScreenGraphics = null;

	public void setCor(Color cor) {
		this.currentColor = cor;
		
		if(po != null) {
			po.setCor(cor);
		}
		if(lp != null) {
			lp.setCor(cor);
		}
				
	}
	
	public void setRetas(List<Reta2D> retas) {
		this.retas = retas;
	}

	public void setCirculos(List<Circulo2D> circulos) {
		this.circulos = circulos;
	}
	
	public void setRetangulos(List<Retangulo2D> retangulos) {
		this.retangulos = retangulos;
	}
	
	public void setLinhasPoligonais(List<LinhaPoligonal2D> linhasPoligonais) {
		this.linhasPoligonais = linhasPoligonais;
	}
	
	public void setPoligonos(List<Poligono2D> poligonos) {
		this.poligonos = poligonos;
	}

	public List<Reta2D> getRetas() {
		return retas;
	}

	public List<Circulo2D> getCirculos() {
		return circulos;
	}

	public List<Retangulo2D> getRetangulos() {
		return retangulos;
	}
	
	public List<LinhaPoligonal2D> getLinhasPoligonais() {
		return linhasPoligonais;
	}

	public List<Poligono2D> getPoligonos() {
		return poligonos;
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
		
		if(this.tipo == ModosDeTrabalho.LINHA_POLIGONAL) {
			lp = new LinhaPoligonal2D(new ArrayList<Ponto>(), currentColor);
		} else if(tipo == ModosDeTrabalho.POLIGONO) {
			po = new Poligono2D(new ArrayList<Ponto>(), currentColor);		
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
		if(e.getButton() == 1 && tipo == ModosDeTrabalho.LINHA_POLIGONAL) {
			Ponto po = new Ponto((double)e.getX(), (double)e.getY());
			lp.addPonto(po);
			
			p1 = new Ponto2D(e.getX(), e.getY());
			repaint();
			
		} else if(e.getButton() == 1 && tipo == ModosDeTrabalho.POLIGONO) {
			po.addPonto(new Ponto( (double)e.getX(), (double)e.getY()));
			
			p1 = new Ponto2D(e.getX(), e.getY());
			repaint();
			
		} else if (e.getButton() == 1 && tipo != ModosDeTrabalho.SELECIONAR) {
			p1 = new Ponto2D(e.getX(), e.getY());
			p2 = null;
		} else if (e.getButton() == 3 && tipo == ModosDeTrabalho.LINHA_POLIGONAL) {
			p1 = null;
			p2 = null;
			linhasPoligonais.add(lp);
			lp = new LinhaPoligonal2D(new ArrayList<Ponto>(), currentColor);
			repaint();
		} else if (e.getButton() == 3 && tipo == ModosDeTrabalho.POLIGONO) {
			p1 = null;
			p2 = null;
			poligonos.add(po);
			po = new Poligono2D(new ArrayList<Ponto>(), currentColor);
			repaint();
		} else if (e.getButton() == 1 && tipo == ModosDeTrabalho.SELECIONAR) {
			selecionarForma(e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (tipo == ModosDeTrabalho.RETAS && p1 != null && p2 != null) {
			retas.add(new Reta2D(p1, p2, currentColor));
		}
		else if (tipo == ModosDeTrabalho.CIRCULOS && p1 != null && p2 != null) {
			circulos.add(new Circulo2D(p1, p2.calcularDistancia(p1), currentColor));
		} else if (tipo == ModosDeTrabalho.RETANGULOS && p1 != null && p2 != null) {
			retangulos.add(new Retangulo2D(p1, p2, currentColor));
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
				p2 = new Ponto2D(e.getX(), e.getY());
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
			p2 = new Ponto2D(e.getX(), e.getY());
			repaint();
		}
	}

	public void repaintAll(Graphics g) {
		for(Reta2D r : retas) {
			r.desenharReta(g);
		}
		for(Circulo2D c : circulos) {
			c.desenharCirculoPolar(g);
		}
		for(Retangulo2D c : retangulos) {
			c.desenharRetangulo(g);
		}
		for(LinhaPoligonal2D c : linhasPoligonais) {
			c.desenharLinhaPoligonal(g);
		}
		for(Poligono2D c : poligonos) {
			c.desenharPoligono(g, true);
		}
		
		if(lp != null) {
			lp.desenharLinhaPoligonal(g);
		}
		if(po != null) {
			po.desenharPoligono(g, false);
		}
		
		setBackground(java.awt.Color.white);
	}
	
	public void selecionarForma(int x, int y) {
		System.out.println("Selected: FORM in ("+ x + ", " + y + ")");
		boolean encontrouForma = false;
		double margemDeErro = 5;
		Object forma;
		
		for(Reta2D r : retas) {
			if ( pontoNaReta(r, new Ponto(x,y)) ) {
				encontrouForma = true;
				forma = r;
				((Reta2D)forma).setCor(Color.RED);
				repaint();
				break;
			}
			
		}
		
		System.out.println(encontrouForma);
		/*for(Circulo2D c : circulos) {
			
		}
		for(Retangulo2D c : retangulos) {
			
		}
		for(LinhaPoligonal2D c : linhasPoligonais) {

		}
		for(Poligono2D c : poligonos) {

		}*/
		
	}
	  

	public void message(MouseEvent e){
		msg.setText("Primitivo: "+tipo.name().replace("_", " ")+" (" + e.getX() + ", " + e.getY() + ")");
	}

	public void desenharPrimitivos(Graphics g) {

		if ((tipo == ModosDeTrabalho.RETAS || tipo == ModosDeTrabalho.LINHA_POLIGONAL || tipo == ModosDeTrabalho.POLIGONO) && (p1 != null) && (p2 != null)) {

			Reta2D rOld = null;
			if(oldP2 != null) {
				rOld = new Reta2D(p1, oldP2, Color.WHITE);
				rOld.desenharReta(g);
			}

			Reta2D r = new Reta2D(p1, p2, currentColor);
			r.desenharReta(g);
		}

		if ((tipo == ModosDeTrabalho.CIRCULOS) && (p1 != null) && (p2 != null)) {
			Circulo2D r = new Circulo2D(p1, p2.calcularDistancia(p1), currentColor);

			Circulo2D rOld = null; 
			if(oldP2 != null) {
				rOld = new Circulo2D(p1, oldP2.calcularDistancia(p1), Color.WHITE);
				rOld.desenharCirculoPolar(g);
			}
			
			r.desenharCirculoPolar(g);
		}
		
		if ((tipo == ModosDeTrabalho.RETANGULOS) && (p1 != null) && (p2 != null)) {
			Retangulo2D r = new Retangulo2D(p1, p2, currentColor);

			Retangulo2D rOld = null; 
			if(oldP2 != null) {
				rOld = new Retangulo2D(p1, oldP2, Color.WHITE);
				rOld.desenharRetangulo(g);
			}
			
			try {
				r.desenharRetangulo(g);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		repaintAll(g);

	}

	private boolean pontoNaReta(Reta r, Ponto p){
		int margemDeErro = 10;
		return (r.calculaDistanciaEntreRetaEPonto(p) <= margemDeErro)
				&& ((p.getX() >= r.p1.getX() && p.getX() <= r.p2.getX()) || (p.getX() >= r.p2.getX() && p.getX() <= r.p1.getX()))
				&& ((p.getY() >= r.p1.getY() && p.getY() <= r.p2.getY()) || (p.getY() >= r.p2.getY() && p.getY() <= r.p1.getY()));
	}

}
