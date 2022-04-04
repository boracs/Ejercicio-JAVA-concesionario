package reto;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {

	public static void exportTo(){
		
		String fileName;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The file name must have 1-45 characters!");
			}
			System.out.println("\nEnter file name (without extension):");
			fileName = Console.readString();
			if(fileName.length() > 45 || fileName.length() < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		ConnectionToDB myConnectionToDB = null;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document documento = implementation.createDocument(null, "concessionaire", null);
			documento.setXmlVersion("1.0");
			
			Element cars = documento.createElement("cars");
			
			try {
				myConnectionToDB = new ConnectionToDB();
				
				ResultSet myResultSetCar;
				myResultSetCar = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, car WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = car.carRegistration");
				while(myResultSetCar.next()){
					
					Element car = documento.createElement("car");
					
					Element brand = documento.createElement("brand");
					Text textBrand = documento.createTextNode(myResultSetCar.getString("brand"));
					brand.appendChild(textBrand);
					car.appendChild(brand); 
					
					Element model = documento.createElement("model");
					Text textModel = documento.createTextNode(myResultSetCar.getString("model"));
					model.appendChild(textModel);
					car.appendChild(model); 
					
					Element year = documento.createElement("year");
					Text textYear = documento.createTextNode(myResultSetCar.getString("year"));
					year.appendChild(textYear);
					car.appendChild(year); 
					
					Element registration = documento.createElement("registration");
					Text textRegistration = documento.createTextNode(myResultSetCar.getString("registration"));
					registration.appendChild(textRegistration);
					car.appendChild(registration); 
					
					Element numFrame = documento.createElement("numFrame");
					Text textNumFrame = documento.createTextNode(myResultSetCar.getString("numFrame"));
					numFrame.appendChild(textNumFrame);
					car.appendChild(numFrame); 
					
					Element colour = documento.createElement("colour");
					Text textColour = documento.createTextNode(myResultSetCar.getString("colour"));
					colour.appendChild(textColour);
					car.appendChild(colour); 
					
					Element numOfSeats = documento.createElement("numOfSeats");
					Text textNumOfSeats = documento.createTextNode(myResultSetCar.getString("numOfSeats"));
					numOfSeats.appendChild(textNumOfSeats);
					car.appendChild(numOfSeats); 
					
					Element price = documento.createElement("price");
					Text textPrice = documento.createTextNode(myResultSetCar.getString("price"));
					price.appendChild(textPrice);
					car.appendChild(price); 
					
					Element numDoors = documento.createElement("numDoors");
					Text textNumDoors = documento.createTextNode(myResultSetCar.getString("numDoors"));
					numDoors.appendChild(textNumDoors);
					car.appendChild(numDoors); 
					
					Element trunkCapacity = documento.createElement("trunkCapacity");
					Text textTrunkCapacity = documento.createTextNode(myResultSetCar.getString("trunkCapacity"));
					trunkCapacity.appendChild(textTrunkCapacity);
					car.appendChild(trunkCapacity); 
	
					cars.appendChild(car);
					
					documento.getDocumentElement().appendChild(cars);
				}
				
				Element trucks = documento.createElement("trucks");
				
				ResultSet myResultSetTruck;
				myResultSetTruck = myConnectionToDB.myQuery("SELECT * FROM series, vehicle, truck WHERE series.serieNum = vehicle.serieNum AND vehicle.registration = truck.truckRegistration");
				while(myResultSetTruck.next()){
					
					Element truck = documento.createElement("truck");
						
					Element brand = documento.createElement("brand");
					Text textBrand = documento.createTextNode(myResultSetTruck.getString("brand"));
					brand.appendChild(textBrand);
					truck.appendChild(brand); 
					
					Element model = documento.createElement("model");
					Text textModel = documento.createTextNode(myResultSetTruck.getString("model"));
					model.appendChild(textModel);
					truck.appendChild(model); 
					
					Element year = documento.createElement("year");
					Text textYear = documento.createTextNode(myResultSetTruck.getString("year"));
					year.appendChild(textYear);
					truck.appendChild(year); 
					
					Element registration = documento.createElement("registration");
					Text textRegistration = documento.createTextNode(myResultSetTruck.getString("registration"));
					registration.appendChild(textRegistration);
					truck.appendChild(registration); 
					
					Element numFrame = documento.createElement("numFrame");
					Text textNumFrame = documento.createTextNode(myResultSetTruck.getString("numFrame"));
					numFrame.appendChild(textNumFrame);
					truck.appendChild(numFrame); 
					
					Element colour = documento.createElement("colour");
					Text textColour = documento.createTextNode(myResultSetTruck.getString("colour"));
					colour.appendChild(textColour);
					truck.appendChild(colour); 
					
					Element numOfSeats = documento.createElement("numOfSeats");
					Text textNumOfSeats = documento.createTextNode(myResultSetTruck.getString("numOfSeats"));
					numOfSeats.appendChild(textNumOfSeats);
					truck.appendChild(numOfSeats); 
					
					Element price = documento.createElement("price");
					Text textPrice = documento.createTextNode(myResultSetTruck.getString("price"));
					price.appendChild(textPrice);
					truck.appendChild(price); 
					
					Element load = documento.createElement("load");
					Text textNumLoad = documento.createTextNode(myResultSetTruck.getString("load"));
					load.appendChild(textNumLoad);
					truck.appendChild(load); 
					
					Element merchandiseType = documento.createElement("merchandiseType");
					Text textMerchandiseType = documento.createTextNode(myResultSetTruck.getString("merchandiseType"));
					merchandiseType.appendChild(textMerchandiseType);
					truck.appendChild(merchandiseType); 
	
					trucks.appendChild(truck);	
						
					documento.getDocumentElement().appendChild(trucks);
						
				}
				
				Source source = new DOMSource(documento);
				Result result = new StreamResult(new File(fileName + ".xml"));
				
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, result);
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				if(myConnectionToDB != null){
	                try{
	                	myConnectionToDB.disconnect();
	                } catch (Exception e){
	                    e.printStackTrace();
	                }
	            }
			}
			
		} catch (ParserConfigurationException  | TransformerException ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println("\nDB succesfully exported to XML!\n\n");
		Concessionaire.menu();
	}

	public static void importFrom() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document documento = builder.parse(new File("concessionaire.xml"));
			
			NodeList listaCoches = documento.getElementsByTagName("car");
			for(int i = 0; i<listaCoches.getLength();i++) {
				Node nodo = listaCoches.item(i);
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nodo;
					NodeList hijos = e.getChildNodes();
					for(int j = 0; j<hijos.getLength(); j++) {
						Node hijo = hijos.item(j);
						if(hijo.getNodeType() == Node.ELEMENT_NODE) {
							System.out.println(Node.ELEMENT_NODE + "Propiedad: " + hijo.getNodeName() + ", Valor: " + hijo.getTextContent());
						}
					}
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
		} 
	}
}
