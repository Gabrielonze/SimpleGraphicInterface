package formas;

import java.awt.*;

public abstract class Forma {

    Color _cor;

    public abstract void rotacionar(Ponto p, double angulo);
    public abstract void escalar(double fatorEscala);
    public abstract void transladar(double distanciaX, double distanciaY);
    public abstract void desenhar(Graphics g);
    public abstract boolean pontoNaForma(Ponto p);

    public void set_cor(Color _cor) {
        this._cor = _cor;
    }

    public Color get_cor(){
        return this._cor;
    }
}
