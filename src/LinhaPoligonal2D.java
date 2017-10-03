
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;


public class LinhaPoligonal2D extends LinhaPoligonal {
	Color cor;
	String str;

	LinhaPoligonal2D(List<Ponto> pontos, Color cor, String str){
		super (pontos);
		setCor(cor);
		setStr(str);
	}    

	LinhaPoligonal2D(List<Ponto> pontos, Color cor){
		super (pontos);
		setCor(cor);
		setStr("");
	}   

	LinhaPoligonal2D(List<Ponto> pontos){
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
	
	public void desenharLinhaPoligonal(Graphics g){
		
		Ponto2D lastPoint = null;
		
		for(Ponto p : getPontos()) {
		
			if(lastPoint == null) {
				lastPoint = new Ponto2D((int)p.getX(), (int)p.getY());
			} else {
				Ponto2D currentPoint = new Ponto2D((int)p.getX(), (int)p.getY());
				Reta2D reta = new Reta2D(lastPoint, currentPoint, cor);
				reta.desenharReta(g);
				lastPoint = currentPoint;
			}			
		}
	}
}