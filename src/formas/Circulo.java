package formas;


import java.awt.*;

public class Circulo extends Forma {
   Ponto  _centro;
   double _raio;


   //Construtores
   Circulo(int x, int y, double raio){
   	  setCentro(new Ponto(x, y));
   	  setRaio(raio);
   	  this._cor = Color.BLACK;
   }
   Circulo(Ponto c, double raio){
   	  setCentro(new Ponto(c));
   	  setRaio(raio);
       this._cor = Color.BLACK;
   }

    public Circulo(int x, int y, double raio, Color cor){
        setCentro(new Ponto(x, y));
        setRaio(raio);
        this._cor = cor;
    }

    public Circulo(Ponto c, double raio, Color cor){
        setCentro(new Ponto(c));
        setRaio(raio);
        this._cor = cor;
    }

    //Getters and Setters
   void setRaio(double raio){
      _raio = raio;
   }

   void setCentro(Ponto c){
      _centro = c;
   }
   
   public Ponto getCentro(){
      return _centro;
   }
   public double getRaio(){
      return _raio;
   }

    //métodos de todas as formas
    @Override
    public void rotacionar(Ponto p, double angulo) {

    }

    @Override
    public void escalar(double fatorEscala) {

    }

    @Override
    public void transladar(double distanciaX, double distanciaY) {

    }

    @Override
    public void desenhar(Graphics g) {
        int cx, cy, raio;
        cx = (int)getCentro().getX();
        cy = (int)getCentro().getY();
        raio = (int)getRaio();
        if (raio != 0) {

            int x = 0;
            int y = raio;
            Ponto2D p = new Ponto2D(x, y, get_cor());
            for (double alfa=0; alfa <= 45; alfa=alfa+0.2) {
                // Calcula um ponto e desenha os outros 7 por simetria.
                x=(int)(raio*Math.cos((alfa*Math.PI)/180.));
                y=(int)(raio*Math.sin((alfa*Math.PI)/180.));
                p = new Ponto2D(cx+x, cy+y, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx+y, cy+x, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx+y, cy-x, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx+x, cy-y, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx-x, cy-y, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx-y, cy-x, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx-y, cy+x, get_cor());
                p.desenharPonto(g);

                p = new Ponto2D(cx-x, cy+y, get_cor());
                p.desenharPonto(g);
            }
        }
   }

    @Override
    public boolean pontoNaForma(Ponto p) {
        return false;
    }
}