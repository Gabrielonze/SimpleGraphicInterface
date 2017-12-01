package formas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LinhaPoligonal extends Forma {

    List<Ponto> pontos = new ArrayList<Ponto>();
    boolean poligono_fechado;

    //Construtores

    LinhaPoligonal(List<Ponto> pontos){
        this.pontos = pontos;
        this._cor = Color.BLACK;
        this.poligono_fechado = false;
    }

    public LinhaPoligonal(List<Ponto> pontos, Color color){
        this.pontos = pontos;
        this._cor = color;
        this.poligono_fechado = false;
    }

    public LinhaPoligonal(List<Ponto> pontos, Color color, boolean fechado){
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
        for(Ponto vertice : this.pontos){
            vertice.rotacionar(p, angulo);
        }
    }

    @Override
    public void escalar(double fatorEscala) {
        for(Ponto p : this.pontos){
            p.escalar(fatorEscala);
        }
    }

    @Override
    public void transladar(int distanciaX, int distanciaY) {
        for(Ponto p : this.pontos){
            p.transladar(distanciaX, distanciaY);
        }

    }

    private ArrayList<Reta> calcularRetas() {
        ArrayList<Reta> retas = new ArrayList<>();


        for(int i = 0; i < getPontos().size() - 1 ; i++){
            Ponto p1 = getPontos().get(i);
            Ponto p2 = getPontos().get(i+1);
            Reta reta = new Reta(p1, p2, this._cor);
            retas.add(reta);
        }

        if (this.poligono_fechado){
            Ponto primeiroPonto  = getPontos().get(0);
            Ponto ultimoPonto = getPontos().get(getPontos().size()-1);
            Reta reta = new Reta(primeiroPonto, ultimoPonto, this._cor);
            retas.add(reta);
        }

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
