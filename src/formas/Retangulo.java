package formas;

import java.awt.*;
import java.util.ArrayList;

public class Retangulo extends Forma {

        Ponto vertice1, vertice2;

    //Construtores
    Retangulo(int x1, int y1, int x2, int y2){

        setVertice1(new Ponto(x1, y1));
        setVertice2(new Ponto(x2, y2));
        this._cor = Color.BLACK;
    }

    Retangulo(Ponto p1, Ponto p2){
        setVertice1(new Ponto(p1));
        setVertice2(new Ponto(p2));
        this._cor = Color.BLACK;
    }

    //Construtores
    public Retangulo(int x1, int y1, int x2, int y2, Color color){

        setVertice1(new Ponto(x1, y1));
        setVertice2(new Ponto(x2, y2));
        this._cor =  color;
    }

    public Retangulo(Ponto p1, Ponto p2, Color color){
        setVertice1(new Ponto(p1));
        setVertice2(new Ponto(p2));
        this._cor =  color;
    }


    public Ponto getVertice1() {
        return vertice1;
    }

    public void setVertice1(Ponto vertice1) {
        this.vertice1 = vertice1;
    }

    public Ponto getVertice2() {
        return vertice2;
    }

    public void setVertice2(Ponto vertice2) {
        this.vertice2 = vertice2;
    }


    @Override
    public void rotacionar(Ponto p, double angulo) {

    }

    @Override
    public void escalar(double fatorEscala) {

    }

    @Override
    public void transladar(double distanciaX, double distanciaY) {

    }

    private ArrayList<Reta> calcularRetas() {
        ArrayList<Reta> retas = new ArrayList<>();

        Ponto2D ponto1 = new Ponto2D((int)getVertice1().getX(), (int)getVertice1().getY());
        Ponto2D ponto2 = new Ponto2D((int)getVertice1().getX(), (int)getVertice2().getY());
        Ponto2D ponto3 = new Ponto2D((int)getVertice2().getX(), (int)getVertice1().getY());
        Ponto2D ponto4 = new Ponto2D((int)getVertice2().getX(), (int)getVertice2().getY());

        retas.add(new Reta(ponto1, ponto2, this._cor));
        retas.add(new Reta(ponto3, ponto4, this._cor));
        retas.add(new Reta(ponto1, ponto3, this._cor));
        retas.add(new Reta(ponto2, ponto4, this._cor));

        return retas;
    }

    @Override
    public void desenhar(Graphics g) {
        ArrayList<Reta> retas = calcularRetas();
        for(Reta r: retas) {
            r.desenhar(g);
        }
    }

    @Override
    public boolean pontoNaForma(Ponto p, int margemDeErro) {

        boolean encontrouForma = false;

        ArrayList<Reta> retas = calcularRetas();

        for(Reta r: retas) {
            if(r.pontoNaForma(p, margemDeErro)){
                encontrouForma = true;
                break;
            }
        }

        return encontrouForma;
    }


}
