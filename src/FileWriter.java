import java.awt.Color;
import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileWriter {

	public static void write(File file, List<Reta2D> retas, List<Circulo2D> circulos, List<Retangulo2D> retangulos, List<LinhaPoligonal2D> linhasPoligonais, List<Poligono2D> poligonos) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element figuraElement = doc.createElement("Figura");
		doc.appendChild(figuraElement);

		for (Reta2D reta : retas) {
			createReta(doc, figuraElement, reta);	
		}
		
		for (Circulo2D circulo : circulos) {
			createCirculo(doc, figuraElement, circulo);	
		}
		
		for (Retangulo2D retangulo : retangulos) {
			createRetangulo(doc, figuraElement, retangulo);	
		}
		
		for(LinhaPoligonal2D linhaPoligonal : linhasPoligonais) {
			createLinhaPoligonal(doc, figuraElement, linhaPoligonal);
		}
		
		for(Poligono2D poligono : poligonos) {
			createPoligono(doc, figuraElement, poligono);
		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}


	private static void createPoligono(Document doc, Element figuraElement, Poligono2D poligono) {
		Element poligonoElement = doc.createElement("Poligono");
		figuraElement.appendChild(poligonoElement);
		
		for(Ponto p : poligono.getPontos()) {
			poligonoElement.appendChild(createPonto(p, doc));
		}
		poligonoElement.appendChild(createCor(doc, poligono.getCor()));
	}


	private static void createLinhaPoligonal(Document doc, Element figuraElement, LinhaPoligonal2D linhaPoligonal) {
		
		Element linhaPoligonalElement = doc.createElement("LinhaPoligonal");
		figuraElement.appendChild(linhaPoligonalElement);
		
		for(Ponto p : linhaPoligonal.getPontos()) {
			linhaPoligonalElement.appendChild(createPonto(p, doc));
		}
		linhaPoligonalElement.appendChild(createCor(doc, linhaPoligonal.getCor()));
	}

	private static void createRetangulo(Document doc, Element figuraElement, Retangulo2D retangulo) {
		Element retanguloElement = doc.createElement("Retangulo");
		figuraElement.appendChild(retanguloElement);
		
		retanguloElement.appendChild(createPonto(retangulo.vertice1, doc));
		retanguloElement.appendChild(createPonto(retangulo.vertice2, doc));	
		retanguloElement.appendChild(createCor(doc, retangulo.getCor()));
	}


	private static void createCirculo(Document doc, Element figuraElement, Circulo2D circulo) {
		Element circuloElement = doc.createElement("Circulo");
		figuraElement.appendChild(circuloElement);
		
		circuloElement.appendChild(createPonto(circulo.getCentro(), doc));
		Element raio = doc.createElement("Raio");
		raio.appendChild(doc.createTextNode(((int) circulo.getRaio())+""));
		circuloElement.appendChild(raio);
		circuloElement.appendChild(createCor(doc, circulo.getCor()));
	}


	private static void createReta(Document doc, Element figuraElement, Reta2D reta) {
		Element retaElement = doc.createElement("Reta");
		figuraElement.appendChild(retaElement);
		
		retaElement.appendChild(createPonto(reta.p1, doc));
		retaElement.appendChild(createPonto(reta.p2, doc));	
		retaElement.appendChild(createCor(doc, reta.getCor()));
	}
	
	
	private static Element createPonto(Ponto ponto, Document doc) {
		Element dot = doc.createElement("Ponto");
		Element px = doc.createElement("x");
		Element py = doc.createElement("y");
		
		px.appendChild(doc.createTextNode(Conversor.pixelToRelative(ponto.getX())));
		py.appendChild(doc.createTextNode(Conversor.pixelToRelative(ponto.getY())));
		
		dot.appendChild(px);
		dot.appendChild(py);
		return dot;
	}
	
	private static Element createCor(Document doc, Color corForm) {
		Element cor = doc.createElement("Cor");
		Element r = doc.createElement("R");
		Element g = doc.createElement("G");
		Element b = doc.createElement("B");
		
		r.appendChild(doc.createTextNode(corForm.getRed()+""));
		g.appendChild(doc.createTextNode(corForm.getGreen()+""));
		b.appendChild(doc.createTextNode(corForm.getBlue()+""));
		
		cor.appendChild(r);
		cor.appendChild(g);
		cor.appendChild(b);
		return cor;
	}
	
}