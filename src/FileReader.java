import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FileReader{
	
	private List<Reta2D> retas = new ArrayList<Reta2D>();
	private List<Circulo2D> circulos = new ArrayList<Circulo2D>();
	private List<Retangulo2D> retangulos = new ArrayList<Retangulo2D>();

	public boolean readFile(){
		try {
			
			File fXmlFile = new File("exemplo.xml");
			
			System.out.println("File exists: " +fXmlFile.exists());
		
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			buildRetas(doc.getElementsByTagName("Reta"));
			buildRetangulos(doc.getElementsByTagName("Retangulo"));
			buildCirculos(doc.getElementsByTagName("Circulo"));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Reta2D> getRetas() {
		return retas;
	}

	public List<Circulo2D> getCirculos() {
		return circulos;
	}

	public List<Retangulo2D> getRetangulos() {
		return retangulos;
	}
	
	private void buildRetas(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				Element elementCor = (Element) eElement.getElementsByTagName("Cor").item(0);
				String corR = elementCor.getElementsByTagName("R").item(0).getTextContent();
				String corG = elementCor.getElementsByTagName("G").item(0).getTextContent();
				String corB = elementCor.getElementsByTagName("B").item(0).getTextContent();
				
				Element elementPonto1 = (Element) eElement.getElementsByTagName("Ponto").item(0);
				String p1x = elementPonto1.getElementsByTagName("x").item(0).getTextContent();
				String p1y = elementPonto1.getElementsByTagName("y").item(0).getTextContent();
				
				Element elementPonto2 = (Element) eElement.getElementsByTagName("Ponto").item(1);
				String p2x = elementPonto2.getElementsByTagName("x").item(0).getTextContent();
				String p2y = elementPonto2.getElementsByTagName("y").item(0).getTextContent();
				
				createReta(p1x, p1y, p2x, p2y, corR, corG, corB);
				
				/*System.out.println("corR : " + corR);
				System.out.println("corG : " + corG);
				System.out.println("corB : " + corB);
				
				System.out.println("p1x : " + p1x);
				System.out.println("p1y : " + p1y);
				
				System.out.println("p2x : " + p2x);
				System.out.println("p2y : " + p2y);*/
			
			}
		}
	}
	
	private void buildRetangulos(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				Element elementCor = (Element) eElement.getElementsByTagName("Cor").item(0);
				String corR = elementCor.getElementsByTagName("R").item(0).getTextContent();
				String corG = elementCor.getElementsByTagName("G").item(0).getTextContent();
				String corB = elementCor.getElementsByTagName("B").item(0).getTextContent();
				
				Element elementPonto1 = (Element) eElement.getElementsByTagName("Ponto").item(0);
				String p1x = elementPonto1.getElementsByTagName("x").item(0).getTextContent();
				String p1y = elementPonto1.getElementsByTagName("y").item(0).getTextContent();
				
				Element elementPonto2 = (Element) eElement.getElementsByTagName("Ponto").item(1);
				String p2x = elementPonto2.getElementsByTagName("x").item(0).getTextContent();
				String p2y = elementPonto2.getElementsByTagName("y").item(0).getTextContent();
				
				createRetangulo(p1x, p1y, p2x, p2y, corR, corG, corB);
			}
		}
	}
	
	private void buildCirculos(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				Element elementCor = (Element) eElement.getElementsByTagName("Cor").item(0);
				String corR = elementCor.getElementsByTagName("R").item(0).getTextContent();
				String corG = elementCor.getElementsByTagName("G").item(0).getTextContent();
				String corB = elementCor.getElementsByTagName("B").item(0).getTextContent();
				
				Element elementPonto1 = (Element) eElement.getElementsByTagName("Ponto").item(0);
				String p1x = elementPonto1.getElementsByTagName("x").item(0).getTextContent();
				String p1y = elementPonto1.getElementsByTagName("y").item(0).getTextContent();
				
				String raio = eElement.getElementsByTagName("Raio").item(0).getTextContent();
				
				createCirculo(p1x, p1y, raio, corR, corG, corB);
			}
		}
	}

	private void createReta(String p1x, String p1y, String p2x, String p2y, String corR, String corG, String corB) {
		
		int ponto1X = Conversor.relativeToPixel(p1x);
		int ponto1Y = Conversor.relativeToPixel(p1y);
		int ponto2X = Conversor.relativeToPixel(p2x);
		int ponto2Y = Conversor.relativeToPixel(p2y);
		
		//TODO - COR??????
		retas.add(new Reta2D(ponto1X, ponto1Y, ponto2X, ponto2Y));
	}
	
	private void createRetangulo(String p1x, String p1y, String p2x, String p2y, String corR, String corG, String corB) {
		
		int ponto1X = Conversor.relativeToPixel(p1x);
		int ponto1Y = Conversor.relativeToPixel(p1y);
		int ponto2X = Conversor.relativeToPixel(p2x);
		int ponto2Y = Conversor.relativeToPixel(p2y);
		
		//TODO - COR??????
		retangulos.add(new Retangulo2D(ponto1X, ponto1Y, ponto2X, ponto2Y));
	}
	
	
	private void createCirculo(String p1x, String p1y, String raioStr, String corR, String corG, String corB) {
		int ponto1X = Conversor.relativeToPixel(p1x);
		int ponto1Y = Conversor.relativeToPixel(p1y);
		int raio = Integer.parseInt(raioStr);
		
		//TODO - COR??????
		circulos.add(new Circulo2D(ponto1X, ponto1Y, raio));
	}
}


