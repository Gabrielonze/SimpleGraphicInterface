
import java.awt.Color;
import java.awt.Graphics;


public class Retangulo2D extends Retangulo {
	Color cor;
	String str;

	Retangulo2D(int x1, int y1, int x2, int y2, Color cor, String str){
		super (x1, y1, x2, y2);
		setCor(cor);
		setStr(str);
	}    

	Retangulo2D(int x1, int y1, int x2, int y2, Color cor){
		super (x1, y1, x2, y2);
		setCor(cor);
		setStr("");
	}   

	Retangulo2D(int x1, int y1, int x2, int y2){
		super (x1, y1, x2, y2);
		setCor(Color.black);
		setStr("");
	}   

	Retangulo2D(Ponto2D p1, Ponto2D p2){
		super(p1, p2);
		setCor(Color.black);
		setStr("");
	}    

	Retangulo2D(Ponto2D p1, Ponto2D p2, Color cor){
		super(p1, p2);
		setCor(cor);
		setStr("");
	}    

	Retangulo2D(Ponto2D p1, Ponto2D p2, Color cor, String str){
		super(p1, p2);
		setCor(cor);
		setStr(str);
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


	public void desenharRetangulo(Graphics g){

		Ponto2D ponto1 = new Ponto2D((int)getVertice1().getX(), (int)getVertice1().getY());
		Ponto2D ponto2 = new Ponto2D((int)getVertice1().getX(), (int)getVertice2().getY());
		Ponto2D ponto3 = new Ponto2D((int)getVertice2().getX(), (int)getVertice1().getY());
		Ponto2D ponto4 = new Ponto2D((int)getVertice2().getX(), (int)getVertice2().getY());

		Reta2D r1 = new Reta2D(ponto1, ponto2, cor);
		Reta2D r2 = new Reta2D(ponto3, ponto4, cor);
		Reta2D r3 = new Reta2D(ponto1, ponto3, cor);
		Reta2D r4 = new Reta2D(ponto2, ponto4, cor);

		r1.desenharReta(g);
		r2.desenharReta(g);

		try {
			r3.desenharReta(g);
		} catch (Exception e) {
			e.printStackTrace();
		}

		r4.desenharReta(g);
	}
}