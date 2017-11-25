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

import formas.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileWriter {

	public static void write(File file, List<Reta> retas, List<Circulo> circulos, List<Retangulo> retangulos, List<LinhaPoligonal> linhasPoligonais) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element figuraElement = doc.createElement("Figura");
		doc.appendChild(figuraElement);

		for (Reta reta : retas) {
			createReta(doc, figuraElement, reta);	
		}
		
		for (Circulo circulo : circulos) {
			createCirculo(doc, figuraElement, circulo);
		}
		
		for (Retangulo retangulo : retangulos) {
			createRetangulo(doc, figuraElement, retangulo);	
		}
		
		for(LinhaPoligonal linhaPoligonal : linhasPoligonais) {
			createLinhaPoligonal(doc, figuraElement, linhaPoligonal);
		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);

	  } catch (Exception tfe) {
		tfe.printStackTrace();
	  }
	}

	private static void createLinhaPoligonal(Document doc, Element figuraElement, LinhaPoligonal linhaPoligonal) {
		String tagName;

		if(linhaPoligonal.getPoligonoFechado()){
            tagName = "Poligono";
        } else {
		    tagName = "LinhaPoligonal";
        }


		Element linhaPoligonalElement = doc.createElement(tagName);
		figuraElement.appendChild(linhaPoligonalElement);
		
		for(Ponto p : linhaPoligonal.getPontos()) {
			linhaPoligonalElement.appendChild(createPonto(p, doc));
		}
		linhaPoligonalElement.appendChild(createCor(doc, linhaPoligonal.get_cor()));
	}

	private static void createRetangulo(Document doc, Element figuraElement, Retangulo retangulo) {
		Element retanguloElement = doc.createElement("Retangulo");
		figuraElement.appendChild(retanguloElement);
		
		retanguloElement.appendChild(createPonto(retangulo.getVertice1(), doc));
		retanguloElement.appendChild(createPonto(retangulo.getVertice2(), doc));
		retanguloElement.appendChild(createCor(doc, retangulo.get_cor()));
	}


	private static void createCirculo(Document doc, Element figuraElement, Circulo circulo) {
		Element circuloElement = doc.createElement("Circulo");
		figuraElement.appendChild(circuloElement);
		
		circuloElement.appendChild(createPonto(circulo.getCentro(), doc));
		Element raio = doc.createElement("Raio");
		raio.appendChild(doc.createTextNode((Conversor.pixelToRelative(circulo.getRaio()))+""));
		circuloElement.appendChild(raio);
		circuloElement.appendChild(createCor(doc, circulo.get_cor()));
	}


	private static void createReta(Document doc, Element figuraElement, Reta reta) {
		Element retaElement = doc.createElement("Reta");
		figuraElement.appendChild(retaElement);
		
		retaElement.appendChild(createPonto(reta.getP1(), doc));
		retaElement.appendChild(createPonto(reta.getP2(), doc));
		retaElement.appendChild(createCor(doc, reta.get_cor()));
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
