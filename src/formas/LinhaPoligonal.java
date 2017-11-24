package formas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class LinhaPoligonal extends Forma{

    List<Ponto> pontos = new ArrayList<Ponto>();
    boolean poligono_fechado;

    //Construtores

    LinhaPoligonal(List<Ponto> pontos){
        this.pontos = pontos;
        this._cor = Color.BLACK;
        this.poligono_fechado = false;
    }

    LinhaPoligonal(List<Ponto> pontos, Color color){
        this.pontos = pontos;
        this._cor = color;
        this.poligono_fechado = false;
    }

    LinhaPoligonal(List<Ponto> pontos, Color color, boolean fechado){
        this.pontos = pontos;
        this._cor = color;
        this.poligono_fechado = fechado;
    }

    public List<Ponto> getPontos() {
        return pontos;
    }

    //Getters e Setters

    public void setPontos(List<Ponto> pontos) {
        this.pontos = pontos;
    }

    public void addPonto(Ponto ponto) {
        this.pontos.add(ponto);
    }

    public boolean getPoligonoFechado(){
        return this.poligono_fechado;
    }

    public void setPoligono_fechado(boolean fechado) {
        this.poligono_fechado = fechado;
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

    @Override
    public void desenhar(Graphics g) {

        for(int i = 0; i < getPontos().size() - 1 ; i++){
            Ponto p1 = getPontos().get(i);
            Ponto p2 = getPontos().get(i+1);
            Reta reta = new Reta(p1, p2, this._cor);
            reta.desenhar(g);
        }

        if (this.poligono_fechado){
            Ponto primeiroPonto  = getPontos().get(0);
            Ponto ultimoPonto = getPontos().get(getPontos().size()-1);
            Reta reta = new Reta(primeiroPonto, ultimoPonto, this._cor);
            reta.desenhar(g);
        }
    }
}
