import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
	JLabel msg;
	TiposPrimitivos tipo;
	Ponto2D p1;
	Ponto2D p2, oldP2;
	List<Reta2D> retas = new ArrayList<Reta2D>();
	List<Circulo2D> circulos = new ArrayList<Circulo2D>();
	List<Retangulo2D> retangulos = new ArrayList<Retangulo2D>();
	List<LinhaPoligonal2D> linhasPoligonais = new ArrayList<LinhaPoligonal2D>();
	List<Poligono2D> poligonos = new ArrayList<Poligono2D>();
	
	LinhaPoligonal2D lp = null;
	Poligono2D po = null;

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

	public PainelDesenho(JLabel msg, TiposPrimitivos tipo) {
		this.tipo = tipo;
		this.msg = msg;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setTipo(TiposPrimitivos tipo) {
		this.tipo = tipo;
		p1 = null;
		p2 = null;
		if (tipo == TiposPrimitivos.RETAS) {
			msg.setText("Primitivo: RETA ");
		}
		else if (tipo == TiposPrimitivos.CIRCULOS) {
			msg.setText("Primitivo: CIRCULO ");
		} else if (tipo == TiposPrimitivos.RETANGULOS) {
			msg.setText("Primitivo: RETANGULO ");
		} else if(tipo == TiposPrimitivos.LINHA_POLIGONAL) {
			msg.setText("Primitivo: LINHA POLIGONAL ");
			lp = new LinhaPoligonal2D(new ArrayList<Ponto>());
		} else if(tipo == TiposPrimitivos.POLIGONO) {
			msg.setText("Primitivo: POLIGONO ");
			po = new Poligono2D(new ArrayList<Ponto>());
		}
		else {
			msg.setText("Primitivo: NENHUM ");
		}
	}

	public TiposPrimitivos getTipo() {
		return tipo;
	}


	public void paintComponent(Graphics g) {
		desenharPrimitivos(g);
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1 && tipo == TiposPrimitivos.LINHA_POLIGONAL) {
			lp.addPonto(new Ponto( (double)e.getX(), (double)e.getY()));
			
			p1 = new Ponto2D(e.getX(), e.getY());
			repaint();
			
		} else if(e.getButton() == 1 && tipo == TiposPrimitivos.POLIGONO) {
			po.addPonto(new Ponto( (double)e.getX(), (double)e.getY()));
			
			p1 = new Ponto2D(e.getX(), e.getY());
			repaint();
			
		} else if (e.getButton() == 1) {
			p1 = new Ponto2D(e.getX(), e.getY());
			p2 = null;
		} else if (e.getButton() == 3 && tipo == TiposPrimitivos.LINHA_POLIGONAL) {
			p1 = null;
			p2 = null;
			linhasPoligonais.add(lp);
			lp = new LinhaPoligonal2D(new ArrayList<Ponto>());
			repaint();
		} else if (e.getButton() == 3 && tipo == TiposPrimitivos.POLIGONO) {
			p1 = null;
			p2 = null;
			poligonos.add(po);
			po = new Poligono2D(new ArrayList<Ponto>());
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (tipo == TiposPrimitivos.RETAS && p1 != null && p2 != null) {
			retas.add(new Reta2D(p1, p2));
		}
		else if (tipo == TiposPrimitivos.CIRCULOS && p1 != null && p2 != null) {
			circulos.add(new Circulo2D(p1, p2.calcularDistancia(p1)));
		} else if (tipo == TiposPrimitivos.RETANGULOS && p1 != null && p2 != null) {
			retangulos.add(new Retangulo2D(p1, p2));
		}
		
		if(tipo != TiposPrimitivos.LINHA_POLIGONAL && tipo != TiposPrimitivos.POLIGONO) {
			p1 = null;
			p2 = null;
		}
	}

	public void mouseMoved(MouseEvent e) {
		message(e);
		if(tipo == TiposPrimitivos.LINHA_POLIGONAL || tipo == TiposPrimitivos.POLIGONO) {
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
		if(tipo != TiposPrimitivos.LINHA_POLIGONAL && tipo != TiposPrimitivos.POLIGONO) {
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
	}

	public void message(MouseEvent e){
		if (tipo == TiposPrimitivos.RETAS) {
			msg.setText("Primitivo: RETA (" + e.getX() + ", " + e.getY() + ")");
		} else if (tipo == TiposPrimitivos.CIRCULOS) {
			msg.setText("Primitivo: CIRCULO (" + e.getX() + ", " + e.getY() + ")");
		} else if (tipo == TiposPrimitivos.RETANGULOS) {
			msg.setText("Primitivo: RETANGULO (" + e.getX() + ", " + e.getY() + ")");
		} else if(tipo == TiposPrimitivos.LINHA_POLIGONAL) {
			msg.setText("Primitivo: LINHA POLIGONAL (" + e.getX() + ", " + e.getY() + ")");
		} else if(tipo == TiposPrimitivos.POLIGONO) {
			msg.setText("Primitivo: POLIGONO (" + e.getX() + ", " + e.getY() + ")");
		} else {
			msg.setText("Primitivo: NENHUM (" + e.getX() + ", " + e.getY() + ")");
		}
	}

	public void desenharPrimitivos(Graphics g) {

		if ((tipo == TiposPrimitivos.RETAS || tipo == TiposPrimitivos.LINHA_POLIGONAL || tipo == TiposPrimitivos.POLIGONO) && (p1 != null) && (p2 != null)) {

			Reta2D rOld = null;
			if(oldP2 != null) {
				rOld = new Reta2D(p1, oldP2, Color.WHITE);
				rOld.desenharReta(g);
			}

			Reta2D r = new Reta2D(p1, p2);
			r.desenharReta(g);
		}

		if ((tipo == TiposPrimitivos.CIRCULOS) && (p1 != null) && (p2 != null)) {
			Circulo2D r = new Circulo2D(p1, p2.calcularDistancia(p1));

			Circulo2D rOld = null; 
			if(oldP2 != null) {
				rOld = new Circulo2D(p1, oldP2.calcularDistancia(p1), Color.WHITE);
				rOld.desenharCirculoPolar(g);
			}
			
			r.desenharCirculoPolar(g);
		}
		
		if ((tipo == TiposPrimitivos.RETANGULOS) && (p1 != null) && (p2 != null)) {
			Retangulo2D r = new Retangulo2D(p1, p2);

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
	
	public void apagarTudo() {
		//TODO -> DO
	}

}
