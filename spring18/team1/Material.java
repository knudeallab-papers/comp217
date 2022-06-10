import java.io.Serializable;

public class Material  implements Serializable{
		private String name;
		private int price;
		
		public Material() {
			name = null;
			price = 0;
		}
		
		public Material(String n, int p) {
			name = new String(n);
			price = p;
		}
		
		public Material(Material other) {
			name = new String(other.name);
			price = other.price;
		}
		
		public int getPrice() {
			return price;
		}
		
		public String getName() {
			return new String(name);
		}
	}
	