


public class Retangulo{
    
	Ponto vertice1, vertice2;
    
    //Construtores
    Retangulo(int x1, int y1, int x2, int y2){
        
        setVertice1(new Ponto(x1, y1));
        setVertice2(new Ponto(x2, y2));
    }    
    
    Retangulo(Ponto p1, Ponto p2){
    	setVertice1(new Ponto(p1));
    	setVertice2(new Ponto(p2));
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

    
}