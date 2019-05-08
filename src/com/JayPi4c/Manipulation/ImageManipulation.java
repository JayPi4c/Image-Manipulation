package com.JayPi4c.Manipulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * Die Klasse dient dazu, ein Frame zu erstellen, in dem ein Bild geladen werden
 * kann, welches dann bearbeitet werden kann.
 * 
 * @author JayPi4c
 * @version 1.0
 *
 */
public class ImageManipulation extends JFrame {

	/**
	 * @since 1.0
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @since 1.0 das Bild, welches bearbeitet wird.
	 */
	private BufferedImage image;
	/**
	 * @since 1.0 in diesem Panel befinden sich die ganzen Knöpfe, welche zum öffnen
	 *        speichern oder verändern des Bildes genutzt werden
	 */
	private JPanel panel;

	/**
	 * @since 1.0 mit diesem boolean wird gespeichert, ob das Bild nach einer
	 *        Änderung gespeichert wurde oder nicht. Zum Beispiel wird dies beim
	 *        schließen des Programms ohne vorheriges speichern den Nutzer auf
	 *        dieses Hinweisen true -> Image ist gesichert bzw unverändert false ->
	 *        Image muss gesichert werden
	 */
	private boolean saveState = true;

	/**
	 * Konstruktor des der Klasse, welcher das Frame erstellt und die Knöpfe dafür
	 * hinzufügt
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 * @param title Der Titel den das Frame haben wird
	 */
	public ImageManipulation(String title) {
		super(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setSize((int) (width * 0.75), (int) (height * 0.75));
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				if (!saveState) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Ohne speichern fortfahren?", "Warnung",
							JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						System.out.println("ohne speichern fortfahren!");
					} else {
						saveAction();
					}
					saveState = true;
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setVisible(true);

		JButton openImage = new JButton("open Image");
		openImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("saveState: " + saveState);
				if (!saveState) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Ohne speichern fortfahren?", "Warnung",
							JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						System.out.println("ohne speichern fortfahren!");
					} else {
						saveAction();
					}
					saveState = true;
				}
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file", "jpg", "png", "jpeg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						image = ImageIO.read(chooser.getSelectedFile());
						System.out.println("open: " + chooser.getSelectedFile().getAbsolutePath());
						paintPicture(image);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		openImage.setVisible(true);
		panel.add(openImage);

		JButton vertikal = new JButton("spiegeln vertikal");
		vertikal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (image != null) {
					System.out.println("spiegeln vertikal");
					image = BufferedImageExtension.spiegeln(image, BufferedImageExtension.Constants.vertical);
					paintPicture(image);
					saveState = false;
				} else {
					System.out.println("no picture setted!");
				}

			}
		});
		vertikal.setVisible(true);
		panel.add(vertikal);

		JButton horizontal = new JButton("spiegeln horizontal");
		horizontal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (image != null) {
					System.out.println("spiegeln horizontal");
					image = BufferedImageExtension.spiegeln(image, BufferedImageExtension.Constants.horizontal);
					paintPicture(image);
					saveState = false;
				} else {
					System.out.println("no picture setted!");
				}

			}
		});
		horizontal.setVisible(true);
		panel.add(horizontal);

		JButton invertieren = new JButton("invertieren");
		invertieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (image != null) {
					System.out.println("invertieren");
					image = BufferedImageExtension.invertieren(image);
					paintPicture(image);
					saveState = false;
				} else {
					System.out.println("no picture setted!");
				}

			}
		});
		invertieren.setVisible(true);
		panel.add(invertieren);

		JButton toGray = new JButton("in Grau");
		toGray.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (image != null) {
					System.out.println("in Grau umwandeln");
					image = BufferedImageExtension.toGray(image);
					paintPicture(image);
					saveState = false;
				} else {
					System.out.println("no picture setted");
				}
			}
		});
		toGray.setVisible(true);
		panel.add(toGray);

		JButton save = new JButton("speichern");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}
		});
		save.setVisible(true);
		panel.add(save);

		add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void saveAction() {
		if (image != null) {
			System.out.println("save");
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("wähle Ordner zum Speichern");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(getParent());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				String name = "defaultName";
				do {
					name = JOptionPane.showInputDialog(null, "Wie soll das Bild heißen?");
				} while (name == "");

				if (name != null) {
					try {
						File f = new File(path + "/" + name + ".png");
						f.createNewFile();
						ImageIO.write(image, "png", f);
					} catch (IOException e1) {
						System.out.println("error");
						e1.printStackTrace();
					}
					saveState = true;
				} else
					System.out.println("File not saved!");
			}
		}
	}

	/**
	 * Eine Funktion, die ein Bild entgegen nimmt und es dann in einer Scrollpane
	 * zeichnet
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 * @param bi BufferedImage welches gezeichnet werden soll
	 */
	public void paintPicture(BufferedImage bi) {
		final JLabel imageLabel = new JLabel(new ImageIcon(image));
		final JScrollPane scroll = new JScrollPane(imageLabel);
		add(scroll, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	/**
	 * die Main-Methode, die das Programm startet.
	 * 
	 * @author JayPi4c
	 * @since 1.0
	 * @param args
	 */
	public static void main(String args[]) {
		new ImageManipulation("Image Manipulation");
	}

}