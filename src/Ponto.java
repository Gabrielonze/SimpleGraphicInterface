
/*
* Representa um ponto "matemático" com coordenadas x e y reais
*/
public class Ponto {
    // Atributos x e y do ponto
    private double x;
    private double y;
    
    // Construtores
    Ponto () {
        this.x = 0;
        this.y = 0;
    }

    Ponto (double x, double y) {
        this.x = x;
        this.y = y;
    }

    Ponto (Ponto p) {
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


}