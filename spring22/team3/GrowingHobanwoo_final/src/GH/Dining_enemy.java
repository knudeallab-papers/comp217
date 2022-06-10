package GH;
import java.awt.Image;

import java.util.Random;

	public class Dining_enemy {
		Image img;
		int x, y;
		int w, h;
		int dy;
		int width, height; 
		boolean isDead = false;

		public Dining_enemy(Image imgEnemy, int width, int height) {

			this.width = width;
			this.height = height;
			img = imgEnemy.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			w = 32;
			h = 32;
			Random rnd = new Random();
			x = rnd.nextInt(width - w * 2) + w;
			y = -h;
			dy =+ rnd.nextInt(15) + 1;

		}

		void move() {
			y += dy;
			if( y > height + h ) {
				isDead = true;
			}
		}

	}