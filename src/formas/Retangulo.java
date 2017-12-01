package formas;

import java.awt.*;
import java.util.ArrayList;

public class Retangulo extends Forma {

        //vertice 1 é oposto ao 2
        //vertice 3 é oposto ao 4
        Ponto vertice1, vertice2, vertice3, vertice4;
        Double fatorEscala = 1D;

    //Construtores
    Retangulo(int x1, int y1, int x2, int y2){

        setVertice1(new Ponto(x1, y1));
        setVertice2(new Ponto(x2, y2));

        this.vertice3 = new Ponto(this.getVertice1().getX(), this.getVertice2().getY() );
        this.vertice4 = new Ponto(this.getVertice2().getX(), this.getVertice1().getY());
        this._cor = Color.BLACK;
    }

    Retangulo(Ponto p1, Ponto p2){
        setVertice1(new Ponto(p1));
        setVertice2(new Ponto(p2));
        this.vertice3 = new Ponto(this.getVertice1().getX(), this.getVertice2().getY() );
        this.vertice4 = new Ponto(this.getVertice2().getX(), this.getVertice1().getY());
        this._cor = Color.BLACK;
    }

    //Construtores
    public Retangulo(int x1, int y1, int x2, int y2, Color color){

        setVertice1(new Ponto(x1, y1));
        setVertice2(new Ponto(x2, y2));
        this.vertice3 = new Ponto(this.getVertice1().getX(), this.getVertice2().getY() );
        this.vertice4 = new Ponto(this.getVertice2().getX(), this.getVertice1().getY());
        this._cor =  color;
    }

    public Retangulo(Ponto p1, Ponto p2, Color color){
        setVertice1(new Ponto(p1));
        setVertice2(new Ponto(p2));
        this.vertice3 = new Ponto(this.getVertice1().getX(), this.getVertice2().getY() );
        this.vertice4 = new Ponto(this.getVertice2().getX(), this.getVertice1().getY());
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
        this.vertice1.rotacionar(p, angulo);
        this.vertice2.rotacionar(p, angulo);
        this.vertice3.rotacionar(p, angulo);
        this.vertice4.rotacionar(p, angulo);
    }

    @Override
    public void escalar(double fatorEscala) {
        /*this.vertice1.escalar(fatorEscala);
        this.vertice2.escalar(fatorEscala);
        this.vertice3.escalar(fatorEscala);
        this.vertice4.escalar(fatorEscala);*/
        this.fatorEscala = fatorEscala;
    }

    @Override
    public void transladar(int distanciaX, int distanciaY) {
        this.vertice1.transladar(distanciaX, distanciaY);
        this.vertice2.transladar(distanciaX, distanciaY);
        this.vertice3.transladar(distanciaX, distanciaY);
        this.vertice4.transladar(distanciaX, distanciaY);

    }

    private ArrayList<Reta> calcularRetas() {
        ArrayList<Reta> retas = new ArrayList<>();

        Ponto v1_escala = new Ponto(vertice1.getX(), vertice1.getY());
        v1_escala.escalar(fatorEscala);

        Ponto v2_escala = new Ponto(vertice2.getX(), vertice2.getY());
        v2_escala.escalar(fatorEscala);

        Ponto v3_escala = new Ponto(vertice3.getX(), vertice3.getY());
        v3_escala.escalar(fatorEscala);

        Ponto v4_escala = new Ponto(vertice4.getX(), vertice4.getY());
        v4_escala.escalar(fatorEscala);

        retas.add(new Reta(v1_escala, v3_escala, this._cor));
        retas.add(new Reta(v1_escala, v4_escala, this._cor));
        retas.add(new Reta(v2_escala, v3_escala, this._cor));
        retas.add(new Reta(v2_escala, v4_escala, this._cor));

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
