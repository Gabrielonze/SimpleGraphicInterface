import java.util.ArrayList;
import java.util.List;

public class LinhaPoligonal{
    
	
	List<Ponto> pontos = new ArrayList<Ponto>();
    
    //Construtores
    LinhaPoligonal(List<Ponto> pontos){
        this.pontos = pontos;
    }    

    public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	
	public void addPonto(Ponto ponto) {
		this.pontos.add(ponto);
	}



    
}