


import java.awt.Color;
import java.awt.Graphics;


public class Reta2D extends Reta{
    Color cor;
    String str;
    Reta2D(int x1, int y1, int x2, int y2, Color cor, String str){
        super (x1, y1, x2, y2);
        setCor(cor);
        setStr(str);
    }    

    Reta2D(int x1, int y1, int x2, int y2, Color cor){
        super (x1, y1, x2, y2);
        setCor(cor);
        setStr("");
    }   

    Reta2D(int x1, int y1, int x2, int y2){
        super (x1, y1, x2, y2);
        setCor(Color.black);
        setStr("");
    }   

    Reta2D(Ponto2D p1, Ponto2D p2){
        super(p1, p2);
        setCor(Color.black);
        setStr("");
    }    

    Reta2D(Ponto2D p1, Ponto2D p2, Color cor){
        super(p1, p2);
        setCor(cor);
        setStr("");
    }    

    Reta2D(Ponto2D p1, Ponto2D p2, Color cor, String str){
        super(p1, p2);
        setCor(cor);
        setStr(str);
    }    

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Color getCor() {
        return cor;
    }

    public String getStr() {
        return str;
    }
//    public void desenharReta(Graphics g){
   public void desenharReta(Graphics g){

        double b=calculaB();
        double m=calculaM();

        //Caso 1) Caso em que o intervalo em y � maior
        if(Math.abs(p2.getY()-p1.getY())>Math.abs(p2.getX()-p1.getX())){

            //Caso 1.1)Caso em que y1 > y2
            if(p1.getY()>p2.getY()){

                //System.out.println("Intervalo em Y eh maior com y1 > y2 .");
                if (p1.getX()==p2.getX()){
                    Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),cor,str);
                    ponto.desenharPonto(g);
                    for(double y=p2.getY();y<p1.getY();++y){
                        ponto=new Ponto2D((int)(p1.getX()),(int)y,cor);
                        ponto.desenharPonto(g);
                    }
                }
                else{
                    Ponto2D ponto=new Ponto2D((int)p2.getX(),(int)p2.getY(),cor,str);
                    ponto.desenharPonto(g);
                    for(double y=p2.getY();y<p1.getY();++y){
                        ponto=new Ponto2D((int)((y-b)/m),(int)y,cor);
                        ponto.desenharPonto(g);
                    }
                }

                //Caso 1.2)Caso em que x1 = x2
            }else if(p1.getX()==p2.getX()){

                //System.out.println("Intervalo em Y eh maior com Reta vertical .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),cor,str);
                ponto.desenharPonto(g);
                for(double y=p1.getY();y<p2.getY();++y){
                    ponto=new Ponto2D((int)(p1.getX()),(int)y,cor);
                    ponto.desenharPonto(g);
                }
                //Caso 1.3)Caso em que x1 < x2  
            }else{

                //System.out.println("Intervalo em Y eh maior com x1 < x2 .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),cor,str);
                ponto.desenharPonto(g);
                for(double y=p1.getY();y<p2.getY();++y){
                    ponto=new Ponto2D((int)((y-b)/m),(int)y,cor);
                    ponto.desenharPonto(g);
                }

            }

            //Caso 2)Caso em que o intervalo em x � maior
        }else{

            //Caso 2.1)Caso em que x1 > x2
            if(p1.getX()>p2.getX()){

                //System.out.println("Intervalo em X eh maior com x1 > x2 .");
                Ponto2D ponto=new Ponto2D((int)p2.getX(),(int)p2.getY(),cor,str);
                ponto.desenharPonto(g);
                for(double x=p2.getX();x<p1.getX();++x){
                    ponto=new Ponto2D((int)x,(int)(b+m*x),cor);
                    ponto.desenharPonto(g);
                }

                //Caso 2.2)Caso em que x1 = x2  
            }else if(p1.getX()==p2.getX()){

                //System.out.println("Intervalo em X eh maior com Reta Vertical .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),cor,str);
                ponto.desenharPonto(g);
                for(double x=p1.getX();x<p2.getX();++x){
                    ponto=new Ponto2D((int)(p1.getX()),(int)(b+m*x),cor);
                    ponto.desenharPonto(g);
                }

                //Caso 2.3)Caso em que x1 < x2
            }else{

                //System.out.println("Intervalo em X eh maior com x1 < x2 .");
                Ponto2D ponto=new Ponto2D((int)p1.getX(),(int)p1.getY(),cor,str);
                ponto.desenharPonto(g);
                for(double x=p1.getX();x<p2.getX();++x){
                    ponto=new Ponto2D((int)x,(int)(b+m*x),cor);
                    ponto.desenharPonto(g);
                }
            }
        }

    }

 //   public void desenharRetaDDA(Graphics g){
    public void desenharRetaNAOUSAR(Graphics g){
		Ponto2D p0 = new Ponto2D(0, 0, cor);
		
    	int x1 = (int) getP1().getX(), x2 = (int) getP2().getX();
		int y1 = (int) getP1().getY(), y2 = (int) getP2().getY();
		
		int d = 0;
		 
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
 
        int dy2 = (dy << 1); // slope scaling factors to avoid floating point
        int dx2 = (dx << 1);
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        if (dy <= dx) {
              do {  
            	p0.setX(x1);
				p0.setY(y1);
				p0.desenharPonto(g);
                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            } while (x1 != x2);
        } else {
              do {
            	p0.setX(x1);
				p0.setY(y1);
				p0.desenharPonto(g);
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            } while (y1 != y2);
        }

	}

    //public void desenharReta(Graphics g){
   public void desenharRetaMidPoint(Graphics g){
        //         dx = x_end - x_start;
        //         dy = y_end - y_start;
        //         d = 2*dy – dx;
        //         x = x_start;
        //         y = y_start;
        //         while (x < x_end) { 
        //             if (d <= 0){
        //                 d = d + (2*dy);
        //                 x = x + 1;
        //             }
        //             else {
        //                 d = d + 2*(dy - dx);
        //                 x = x + 1;
        //                 y = y + 1;
        //             }
        //             desenharPonto(x, y, cor);
        //         }

        int dx,dy,d,incry,incre,incrne,slopegt1=0;
        int x1 = (int)p1.getX(), y1 = (int)p1.getY(), x2 = (int)p2.getX(), y2 = (int)p2.getY();
        dx = (int)Math.abs(x2-x1);
        dy = (int)Math.abs(y2-y1);
        int t;
        if(dy>dx)
        {
            //swap(x1,y1);
            t=x1;
            x1=y1;
            y1=t;

            
            //swap(x2,y2);
            t=x2;
            x2=y2;
            y2=t;

            //swap(dx,dy);
            t=dx;
            dx=dy;
            dy=t;

            slopegt1=1;
        }
        if(x1>x2)
        {
            //swap(x1,x2);
            t=x1;
            x1=x2;
            x2=t;
            
            //swap(y1,y2);
            t=y1;
            y1=y2;
            y2=t;
            
        }
        if(y1>y2){
            incry=-1;
        }
        else {
            incry=1;
        }
        d=2*dy-dx;
        incre=2*dy;
        incrne=2*(dy-dx);
        while(x1<x2)
        {
            if(d<=0){
                d+=incre;
            }
            else
            {
                d+=incrne;
                y1+=incry;
            }
            x1++;
            //if(slopegt1 == 1){
            //    Ponto2D ponto=new Ponto2D(x1,y1,cor,str);
            //    ponto.desenharPonto(g);
           // }
            //else{
                Ponto2D ponto=new Ponto2D(x1,y1,cor,str);
                ponto.desenharPonto(g);
            //}
        }
    }

}
