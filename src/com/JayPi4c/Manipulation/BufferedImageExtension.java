package com.JayPi4c.Manipulation;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Ein Klassenerweiterung, welche BufferedImages bearbeitet.
 * 
 * @author JayPi4c
 * @version 1.0
 */
public class BufferedImageExtension extends BufferedImage {

	public BufferedImageExtension(int a, int b, int c) {
		super(a, b, c);
	}

	/**
	 * Bearbeitungskonstanten
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 */
	public static enum Constants {
		vertical, horizontal;
	}

	/**
	 * Anhand eines Bufferedimages und einer Bearbeitungskonstante wird dieses Bild
	 * horizontal oder vertikal gespiegelt
	 * 
	 * @since 1.0
	 * @author JayPi4c
	 * @param img das zu bearbeitende Bild
	 * @param c   die Handlung, die mit dem Bild gemacht werden soll
	 * @return gibt das überarbeitete Bild wieder zurück
	 */
	public static BufferedImage spiegeln(BufferedImage img, Constants c) {
		if (c == Constants.vertical) {
			for (int i = 0; i < img.getHeight(); i++) {
				for (int j = 0; j < img.getWidth() / 2; j++) {
					int temp = img.getRGB(j, i);
					img.setRGB(j, i, img.getRGB(img.getWidth() - 1 - j, i));
					img.setRGB(img.getWidth() - 1 - j, i, temp);
				}
			}
		} else if (c == Constants.horizontal) {
			for (int j = 0; j < img.getWidth(); j++) {
				for (int i = 0; i < img.getHeight() / 2; i++) {
					int temp = img.getRGB(j, i);
					img.setRGB(j, i, img.getRGB(j, img.getHeight() - 1 - i));
					img.setRGB(j, img.getHeight() - 1 - i, temp);
				}
			}
		}
		return img;
	}

	/**
	 * Das übergebene Bild wir in ein Negativ gewandelt
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 * @param img das zu überarbeitende Bild
	 * @return gibt das überarbeitete Bild zurück
	 */
	public static BufferedImage invertieren(BufferedImage img) {
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				c = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
				img.setRGB(i, j, c.getRGB());
			}
		}
		return img;
	}

	public static BufferedImage toGray(BufferedImage img) {
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color c = new Color(img.getRGB(x, y));
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				int avg = (r + g + b) / 3;
				c = new Color(avg, avg, avg);
				img.setRGB(x, y, c.getRGB());
			}

		}

		return img;
	}

}