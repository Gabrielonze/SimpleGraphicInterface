import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JOptionPane;
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
	private List<LinhaPoligonal2D> linhasPoligonais = new ArrayList<LinhaPoligonal2D>();
	private List<Poligono2D> poligonos = new ArrayList<Poligono2D>();

	public boolean readFile(String xmlPath){
		try {
			
			File fXmlFile = new File(xmlPath);

		    if (Conversor.fileExt(fXmlFile.getAbsolutePath()).equals("xml")) {
		    	System.out.println("File exists: " +fXmlFile.exists());
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				buildRetas(doc.getElementsByTagName("Reta"));
				buildRetangulos(doc.getElementsByTagName("Retangulo"));
				buildCirculos(doc.getElementsByTagName("Circulo"));
				buildLinhasPoligonais(doc.getElementsByTagName("LinhaPoligonal"));
				buildPoligonos(doc.getElementsByTagName("Poligono"));
				
		    } else {
		    		JOptionPane.showMessageDialog(null, "Por favor, Selecione um XML");
		    }
			
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
	
	public List<LinhaPoligonal2D> getLinhasPoligonais() {
		return linhasPoligonais;
	}

	public List<Poligono2D> getPoligonos() {
		return poligonos;
	}
	
	private void buildLinhasPoligonais(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				List<Ponto> pontos = new ArrayList<Ponto>();
				
				Element elementCor = (Element) eElement.getElementsByTagName("Cor").item(0);
				String corR = elementCor.getElementsByTagName("R").item(0).getTextContent();
				String corG = elementCor.getElementsByTagName("G").item(0).getTextContent();
				String corB = elementCor.getElementsByTagName("B").item(0).getTextContent();
				
				NodeList nl = eElement.getElementsByTagName("Ponto");
				for(int i = 0; i < nl.getLength();  i++) {
					Element elementPonto = (Element) nl.item(i);
					
					String p1x = elementPonto.getElementsByTagName("x").item(0).getTextContent();
					String p1y = elementPonto.getElementsByTagName("y").item(0).getTextContent();
					pontos.add(new Ponto((double) Conversor.relativeToPixel(p1x), (double) Conversor.relativeToPixel(p1y)));
					
				}
				
				//TODO - COR????
				linhasPoligonais.add(new LinhaPoligonal2D(pontos));
			
			}
		}
	}
	
	private void buildPoligonos(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				List<Ponto> pontos = new ArrayList<Ponto>();
				
				Element elementCor = (Element) eElement.getElementsByTagName("Cor").item(0);
				String corR = elementCor.getElementsByTagName("R").item(0).getTextContent();
				String corG = elementCor.getElementsByTagName("G").item(0).getTextContent();
				String corB = elementCor.getElementsByTagName("B").item(0).getTextContent();
				
				NodeList nl = eElement.getElementsByTagName("Ponto");
				for(int i = 0; i < nl.getLength();  i++) {
					Element elementPonto = (Element) nl.item(i);
					
					String p1x = elementPonto.getElementsByTagName("x").item(0).getTextContent();
					String p1y = elementPonto.getElementsByTagName("y").item(0).getTextContent();
					pontos.add(new Ponto((double) Conversor.relativeToPixel(p1x), (double) Conversor.relativeToPixel(p1y)));
					
				}
				
				//TODO - COR????
				poligonos.add(new Poligono2D(pontos));
			}
		}
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


