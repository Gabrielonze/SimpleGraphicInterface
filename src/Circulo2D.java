

import java.awt.*;

class Circulo2D extends Circulo{
    Color _cor;
    String _str;

    Circulo2D(int x, int y, double raio, Color cor, String str){
        super(x, y, raio);
        setCor(cor);
        setStr(str);
    }
    
    public Circulo2D(int x, int y, int raio) {
    		super(x, y, raio);
    		setCor(Color.black);
        setStr("");
	}
    
    Circulo2D(Ponto2D c, double raio, Color cor, String str){
        super(c, raio);
        setCor(cor);
        setStr(str);
    }
    
    Circulo2D(Ponto2D c, double raio){
        super(c, raio);
        setCor(Color.black);
        setStr("");
    } 
    
    Circulo2D(Ponto2D c, double raio, Color cor){
        super(c, raio);
        setCor(cor);
        setStr("");
    }
    
    Circulo2D(){
        super(new Ponto2D(0, 0), 0.0);
        setCor(Color.black);
    }

	void setCor(Color cor){
        _cor = cor;
    }

    Color getCor(){
        return _cor;
    }
    private String getStr(){
        return _str;
    }

    private void setStr(String str){
        _str = str;
    }

    void desenharCirculoPolar(Graphics g){
        g.setColor(Color.black);
        g.drawString(getStr(),(int)getCentro().getX()+5,(int)getCentro().getY());
        g.setColor(getCor());
        desenharCirculo((int)getCentro().getX(),(int)getCentro().getY(),(int)getRaio(),g);
    }


    void desenharCirculo(int cx, int cy, int raio, Graphics g){
        if (raio != 0) {

            int x = 0;
            int y = raio;
            Ponto2D p = new Ponto2D(x, y, getCor());
            for (double alfa=0; alfa <= 45; alfa=alfa+0.2) {
                // Calcula um ponto e desenha os outros 7 por simetria.
                x=(int)(raio*Math.cos((alfa*Math.PI)/180.));
                y=(int)(raio*Math.sin((alfa*Math.PI)/180.));
                p = new Ponto2D(cx+x, cy+y, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx+y, cy+x, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx+y, cy-x, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx+x, cy-y, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx-x, cy-y, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx-y, cy-x, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx-y, cy+x, getCor());
                p.desenharPonto(g);
                
                p = new Ponto2D(cx-x, cy+y, getCor());
                p.desenharPonto(g);
            }
        }

    }

    void desenharCirculoMidPoint(Graphics g){
        g.setColor(Color.black);
        g.drawString(getStr(),(int)getCentro().getX(), (int)getCentro().getY());
        g.setColor(getCor());
        desenharCirculoMidPoint((int)getCentro().getX(),(int)getCentro().getY(),(int)getRaio(),g);
    }
    void desenharCirculoMidPoint(int cx, int cy, int raio, Graphics g) {

        if (raio != 0) {

            double x = 0;
            double y = raio;
            double d = 5 / 4 - raio;

            desenharPontos (cx, cy, (int)x, (int)y, g); // passa os pontos para serem desenhados

            while (y > x) {
                if (d < 0) {
                    d = d + 2 * x + 3;
                    x++;
                }
                
                else {
                    d = d + 2 * (x - y) + 5;
                    x++;
                    y--;
                }
                desenharPontos (cx, cy, (int)x, (int)y, g);
            }
        }       
    }

    //desenha os pontos passados pelo Bresenham para cada 1/8 do circulo
    private void desenharPontos(int x0, int y0, int x, int y, Graphics g) {
        Ponto2D p = new Ponto2D(x0+x, y0+y, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0+y, y0+x, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0+y, y0-x, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0+x, y0-y, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0-x, y0-y, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0-y, y0-x, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0-y, y0+x, getCor());
        p.desenharPonto(g);
        p = new Ponto2D(x0-x, y0+y, getCor());
        p.desenharPonto(g);
    }
}
