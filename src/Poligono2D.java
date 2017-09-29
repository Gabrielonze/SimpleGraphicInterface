
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;


public class Poligono2D extends Poligono {
	Color cor;
	String str;

	Poligono2D(List<Ponto> pontos, Color cor, String str){
		super (pontos);
		setCor(cor);
		setStr(str);
	}    

	Poligono2D(List<Ponto> pontos, Color cor){
		super (pontos);
		setCor(cor);
		setStr("");
	}   

	Poligono2D(List<Ponto> pontos){
		super (pontos);
		setCor(Color.black);
		setStr("");
	}   
	
	public void setCor(Color cor) {
		this.cor = cor;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Color getCor() {
		return cor;
	}

	public String getStr() {
		return str;
	}	
	
	public void desenharPoligono(Graphics g, boolean complete){
		
		Ponto2D lastPoint = null;
		Ponto2D firstPoint = null;
		
		for(Ponto p : getPontos()) {
			
			if(lastPoint == null) {
				lastPoint = new Ponto2D((int)p.getX(), (int)p.getY());
				firstPoint = lastPoint;
			} else {
				Ponto2D currentPoint = new Ponto2D((int)p.getX(), (int)p.getY());
				Reta2D reta = new Reta2D(lastPoint, currentPoint);
				reta.desenharReta(g);
				lastPoint = currentPoint;
			}			
		}
		
		if(complete) {
			Reta2D reta = new Reta2D(firstPoint, lastPoint);
			reta.desenharReta(g);
		}
	}
}