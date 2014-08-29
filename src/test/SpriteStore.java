package test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import data.XY;

public class SpriteStore {
	/** The single instance of this class */
	private static SpriteStore single = new SpriteStore();
	public static int MAP_TILE_SIZE = 16;

	public static String TILE_GRASS = "TILE_GRASS";
	public static String TILE_FOREST = "TILE_FOREST";
	public static String TILE_MOUNTAIN = "TILE_MOUNTAIN";
	public static String TILE_WATER = "TILE_WATER";

	public static String SPRITE_DERECK = "SPRITE_DERECK";
	public static String SPRITE_ALESSA = "SPRITE_ALESSA";
	public static String SPRITE_BAEL = "SPRITE_BAEL";

	/** The cached sprite map, from reference to sprite instance */
	private HashMap<String, BufferedImage> sprites;
	private HashMap<String, XY> coordonnees;
	private BufferedImage tileset;

	public SpriteStore() {
		sprites = new HashMap<String, BufferedImage>();
		coordonnees = new HashMap<String, XY>();

		try {
			tileset = ImageIO.read(new File(this.getClass()
					.getResource("overworld-tileset.png").getFile()));

			BufferedImage spritePerso = ImageIO.read(new File(this.getClass()
					.getResource("dereck.gif").getFile()));
			sprites.put("SPRITE_DERECK", spritePerso);

			spritePerso = ImageIO.read(new File(this.getClass()
					.getResource("alessa.gif").getFile()));
			sprites.put("SPRITE_ALESSA", spritePerso);

			spritePerso = ImageIO.read(new File(this.getClass()
					.getResource("bael.gif").getFile()));
			sprites.put("SPRITE_BAEL", spritePerso);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		coordonnees.put("TILE_GRASS", new XY(16, 16));
		coordonnees.put("TILE_FOREST", new XY(112, 64));
		coordonnees.put("TILE_MOUNTAIN", new XY(224, 96));
		coordonnees.put("TILE_WATER", new XY(64, 128));
	}

	/**
	 * Get the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static SpriteStore get() {
		return single;
	}

	public static BufferedImage getSprite(String ref) {

		if (single.sprites.get(ref) != null) {
			return single.sprites.get(ref);
		}

		if (single.coordonnees.get(ref) != null) {
			XY coord = single.coordonnees.get(ref);
			BufferedImage image = single.tileset.getSubimage(coord.getX(),
					coord.getY(), MAP_TILE_SIZE, MAP_TILE_SIZE);
			single.sprites.put(ref, image);

			return image;
		}
		return null;
	}

	/**
	 * Retrieve a sprite from the store
	 * 
	 * @param ref
	 *            The reference to the image to use for the sprite
	 * @return A sprite instance containing an accelerate image of the request
	 *         reference
	 */
	public BufferedImage getSprite2(String ref) {
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

		for (int x = 1; x < sourceImage.getWidth(); x++) {
			for (int y = 1; y < sourceImage.getHeight(); y++) {
				Color c = new Color(sourceImage.getRGB(x, y));
				sourceImageRougeLeger.setRGB(x, y,
						new Color(Math.min(c.getRed() + 100, 255),
								c.getGreen(), c.getBlue()).getRGB());
				sourceImageRougeLourd.setRGB(x, y,
						new Color(Math.min(c.getRed() + 200, 255),
								c.getGreen(), c.getBlue()).getRGB());
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
