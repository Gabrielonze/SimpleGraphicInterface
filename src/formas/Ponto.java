package formas;

/*
* Representa um ponto "matemático" com coordenadas x e y reais
*/
public class Ponto {
    // Atributos x e y do ponto
    private double x;
    private double y;
    
    // Construtores
    Ponto() {
        this.x = 0;
        this.y = 0;
    }

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Ponto(Ponto p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Method setX
     *
     * @param x Valor da coordenada x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Method setY
     *
     * @param y Valor da coordenada y
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * Method getX
     *
     * @return o valor de x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Method getY
     *
     * @return o valor de y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Method calcularDistancia
     *
     * @param p ponto externo
     * @return retorna a distancia ao ponto externo (parametro)
     */
    public double calcularDistancia(Ponto p) {

        return Math.sqrt(Math.pow((p.getY() - getY()),2) +
                         Math.pow((p.getX() - getX()),2));

    }

    public void transladar(int fatorX, int fatorY){
        this.x = this.x + fatorX;
        this.y = this.y + fatorY;
    }

    public void escalar(double fatorDeEscala){
        this.x = this.x * fatorDeEscala;
        this.y = this.y * fatorDeEscala;
    }

    public void rotacionar(Ponto p, double angulo) {
        //transladar -p até a origem
        this.transladar((int)(-1*p.getX()), (int)(-1*p.getY()));

        //rotacionar angulo
        double rad = Math.toRadians(angulo);
        double old_x = this.x;
        double old_y = this.y;
        this.x = old_x * Math.cos(rad) - old_y * Math.sin(rad);
        this.y = old_x * Math.sin(rad) + old_y * Math.cos(rad);

        //desfazer a translacao

        this.transladar((int)(p.getX()), (int)(p.getY()));
    }



}