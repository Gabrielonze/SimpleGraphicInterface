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
			
			System.out.println(fXmlFile.exists());
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Reta");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
					Element eElement1 = (Element) eElement.getElementsByTagName("Cor").item(0);
					
					System.out.println("Staff id : " + eElement1.getElementsByTagName("B").item(0).getTextContent());
					/*System.out.println("Staff id : " + eElement.getAttribute("id"));
					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());*/
				}
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
}


