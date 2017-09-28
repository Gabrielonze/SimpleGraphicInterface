
public class Conversor {

	public static int relativeToPixel(String relative) {
		//TODO - CONVERTER
		return (int) (Float.parseFloat(relative) * 1000);
	}
	
	public static String fileExt(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
		    extension = fileName.substring(i+1);
		}
		
		return extension;
	}
	
}
