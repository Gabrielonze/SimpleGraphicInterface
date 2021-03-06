package formas;

import java.awt.*;

public class Reta extends Forma {

    Ponto p1, p2;
    EquacaoDaReta eq;

    //Construtores
    public Reta(int x1, int y1, int x2, int y2){
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        this.eq = new EquacaoDaReta(this.p1, this.p2);
        this._cor = Color.BLACK;

    }

    Reta(Ponto p1, Ponto p2){
        setP1(new Ponto(p1));
        setP2(new Ponto(p2));
        eq = new EquacaoDaReta(this.p1, this.p2);
        this._cor = Color.BLACK;
    }

    public Reta(int x1, int y1, int x2, int y2, Color color){
        setP1(new Ponto(x1, y1));
        setP2(new Ponto(x2, y2));
        this.eq = new EquacaoDaReta(this.p1, this.p2);
        this._cor = color;

    }

    public Reta(Ponto p1, Ponto p2, Color color){
        setP1(new Ponto(p1));
        setP2(new Ponto(p2));
        eq = new EquacaoDaReta(this.p1, this.p2);
        this._cor = color;
    }

    //Sets
    public void setP1(Ponto p1) {
        this.p1 = p1;
    }

    public void setP2(Ponto p2) {
        this.p2 = p2;
    }


    //Gets
    public Ponto getP1() {
        return p1;
    }

    public Ponto getP2() {
        return p2;
    }


    //calculos especificos da Reta

    public double calculaM(){

        double m=(p2.getY()-p1.getY())/(p2.getX()-p1.getX());
        return m;
    }

    //Calcula b da equa��o da reta (y = m*x + b)
    public double calculaB(){

        double m=calculaM();
        double b=p2.getY()-(m*p2.getX());
        return b;
    }

    public double calculaDistanciaEntreRetaEPonto(Ponto p) {
        double distancia;
        double a = this.eq.getA();
        double b = this.eq.getB();
        double c = this.eq.getC();
        if (Double.isInfinite(a)){
            distancia = Math.abs(this.p1.getX() - p.getX());
        } else {
            distancia = Math.abs((a * p.getX() + b * p.getY() + c)) / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        }
        return distancia;
    }

    //métodos de todas as formas

    @Override
    public void rotacionar(Ponto p, double angulo) {
        this.p1.rotacionar(p, angulo);
        this.p2.rotacionar(p, angulo);;
        this.eq = new EquacaoDaReta(this.p1, this.p2);

    }

    @Override
    public void escalar(double fatorEscala) {
        this.p1.escalar(fatorEscala);
        this.p2.escalar(fatorEscala);
        this.eq = new EquacaoDaReta(this.p1, this.p2);
    }

    @Override
    public void transladar(int distanciaX, int distanciaY) {
        this.p1.transladar(distanciaX, distanciaY);
        this.p2.transladar(distanciaX, distanciaY);
        this.eq = new EquacaoDaReta(this.p1, this.p2);
    }

    @Override
    public void desenhar(Graphics g) {
        double b=calculaB();
        double m=calculaM();

        //Caso 1) Caso em que o intervalo em y � maior
        if(Math.abs(p2.getY()-p1.getY())>Math.abs(p2.getX()-p1.getX())){

            //Caso 1.1)Caso em que y1 > y2
            if(p1.getY()>p2.getY()){

                //System.out.println("Intervalo em Y eh maior com y1 > y2 .");
                if (p1.getX()==p2.getX()){
                    Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),_cor);
                    ponto.desenharPonto(g);
                    for(double y=p2.getY();y<p1.getY();++y){
                        ponto=new Ponto2D((int)(p1.getX()),(int)y,_cor);
                        ponto.desenharPonto(g);
                    }
                }
                else{
                    Ponto2D ponto=new Ponto2D((int)p2.getX(),(int)p2.getY(),_cor);
                    ponto.desenharPonto(g);
                    for(double y=p2.getY();y<p1.getY();++y){
                        ponto=new Ponto2D((int)((y-b)/m),(int)y,_cor);
                        ponto.desenharPonto(g);
                    }
                }

                //Caso 1.2)Caso em que x1 = x2
            }else if(p1.getX()==p2.getX()){

                //System.out.println("Intervalo em Y eh maior com Reta vertical .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),_cor);
                ponto.desenharPonto(g);
                for(double y=p1.getY();y<p2.getY();++y){
                    ponto=new Ponto2D((int)(p1.getX()),(int)y,_cor);
                    ponto.desenharPonto(g);
                }
                //Caso 1.3)Caso em que x1 < x2
            }else{

                //System.out.println("Intervalo em Y eh maior com x1 < x2 .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),_cor);
                ponto.desenharPonto(g);
                for(double y=p1.getY();y<p2.getY();++y){
                    ponto=new Ponto2D((int)((y-b)/m),(int)y,_cor);
                    ponto.desenharPonto(g);
                }

            }

            //Caso 2)Caso em que o intervalo em x � maior
        }else{

            //Caso 2.1)Caso em que x1 > x2
            if(p1.getX()>p2.getX()){

                //System.out.println("Intervalo em X eh maior com x1 > x2 .");
                Ponto2D ponto=new Ponto2D((int)p2.getX(),(int)p2.getY(),_cor);
                ponto.desenharPonto(g);
                for(double x=p2.getX();x<p1.getX();++x){
                    ponto=new Ponto2D((int)x,(int)(b+m*x),_cor);
                    ponto.desenharPonto(g);
                }

                //Caso 2.2)Caso em que x1 = x2
            }else if(p1.getX()==p2.getX()){

                //System.out.println("Intervalo em X eh maior com Reta Vertical .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),_cor);
                ponto.desenharPonto(g);
                for(double x=p1.getX();x<p2.getX();++x){
                    ponto=new Ponto2D((int)(p1.getX()),(int)(b+m*x),_cor);
                    ponto.desenharPonto(g);
                }

                //Caso 2.3)Caso em que x1 < x2
            }else{

                //System.out.println("Intervalo em X eh maior com x1 < x2 .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),_cor);
                ponto.desenharPonto(g);
                for(double x=p1.getX();x<p2.getX();++x){
                    ponto=new Ponto2D((int)x,(int)(b+m*x),_cor);
                    ponto.desenharPonto(g);
                }
            }
        }

    }

    public boolean pontoNaForma(Ponto p, int margemDeErro){
        boolean pontoNaReta = (calculaDistanciaEntreRetaEPonto(p) <= margemDeErro);
        boolean dentroDoLimiteDeX = (p.getX() >= (this.getP1().getX() - margemDeErro) && p.getX() <= (this.getP2().getX() + margemDeErro)) ||
                (p.getX() >= (this.getP2().getX() - margemDeErro) && p.getX() <= (this.getP1().getX() + margemDeErro));
        boolean dentroDoLimiteDeY = (p.getY() >= (this.getP1().getY() - margemDeErro) && p.getY() <= (this.getP2().getY() + margemDeErro)) ||
                                    (p.getY() >= (this.getP2().getY() - margemDeErro) && p.getY() <= (this.getP1().getY() + margemDeErro));
        return (pontoNaReta && dentroDoLimiteDeY && dentroDoLimiteDeX);
    }
}
