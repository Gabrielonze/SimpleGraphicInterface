


public class Circulo{
   Ponto _centro;
   double _raio;
   
   Circulo(int x, int y, double raio){
   	  setCentro(new Ponto(x, y));
   	  setRaio(raio);  
   }
   Circulo(Ponto c, double raio){
   	  setCentro(new Ponto(c));
   	  setRaio(raio);  
   }

   Circulo(){
   	  setCentro(new Ponto(0,0));
   	  setRaio(0.0);  
   }
   
   void setRaio(double raio){
      _raio = raio;
   }

   void setCentro(Ponto c){
      _centro = c;
   }
   
   Ponto getCentro(){
      return _centro;
   }
   double getRaio(){
      return _raio;
   }
}