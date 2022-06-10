package TeamProject;

public class Promotion {
	protected String Barcode;
	protected String Name;
	protected int PromotionCode;
	
	
	public Promotion(String theBarcode, String theName, int thePromotionCode) {
		Barcode = theBarcode;
		Name = theName;
		PromotionCode = thePromotionCode;
	}
	
	public String toString() {
		return (Barcode + "/" + Name + "/" + Integer.toString(PromotionCode));
	}
}
