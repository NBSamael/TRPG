package test;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * A resource manager for sprites in the game. Its often quite important how and
 * where you get your game resources from. In most cases it makes sense to have
 * a central resource loader that goes away, gets your resources and caches them
 * for future use.
 * <p>
 * [singleton]
 * <p>
 * 
 * @author Kevin Glass
 */
public class SpriteStore {
	/** The single instance of this class */
	private static SpriteStore single = new SpriteStore();

	/**
	 * Get the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static SpriteStore get() {
		return single;
	}

	/** The cached sprite map, from reference to sprite instance */
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();

	/**
	 * Retrieve a sprite from the store
	 * 
	 * @param ref
	 *            The reference to the image to use for the sprite
	 * @return A sprite instance containing an accelerate image of the request
	 *         reference
	 */
	public BufferedImage getSprite(String ref) {
		// if we've already got the sprite in the cache
		// then just return the existing version
		if (sprites.get(ref) != null) {
			return sprites.get(ref);
		}

		// otherwise, go away and grab the sprite from the resource
		// loader
		BufferedImage sourceImage = null;

		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.
			URL url = this.getClass().getClassLoader().getResource(ref);

			if (url == null) {
				fail("Can't find ref: " + ref);
			}

			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			fail("Failed to load: " + ref);
		}

		sprites.put(ref, sourceImage);

		BufferedImage sourceImageRougeLeger = Copie(sourceImage);
		BufferedImage sourceImageRougeLourd = Copie(sourceImage);
		
		for(int x=1; x < sourceImage.getWidth(); x++) {
			for(int y=1; y < sourceImage.getHeight(); y++) {
				Color c = new Color(sourceImage.getRGB(x, y));
				sourceImageRougeLeger.setRGB(x, y, new Color(Math.min(c.getRed() + 100, 255), c.getGreen(), c.getBlue()).getRGB());
				sourceImageRougeLourd.setRGB(x, y, new Color(Math.min(c.getRed() + 200, 255), c.getGreen(), c.getBlue()).getRGB());
			}
		}

		sprites.put(ref + "_rg_lg", sourceImageRougeLeger);

		sprites.put(ref + "_rg_lr", sourceImageRougeLourd);
		
		return sourceImage;
	}

	/**
	 * Utility method to handle resource loading failure
	 * 
	 * @param message
	 *            The message to display on failure
	 */
	private void fail(String message) {
		// we're pretty dramatic here, if a resource isn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}

	static BufferedImage Copie(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}