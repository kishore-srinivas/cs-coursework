

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingMovie {

	private Movie movie;
	private PImage coverArt;
	
	public DrawingMovie(Movie m) {
		this.movie = m;
		coverArt = null;
	}
	
	public void draw(PApplet drawer, float x, float y, float width, float height) {
//		coverArt = drawer.loadImage("cover.jpg");
		if (movie != null) {
			if (coverArt != null) {
				drawer.image(coverArt, x, y,width,height);
			}
		}
		drawer.stroke(0);
		drawer.noFill();
		drawer.rect(x, y, width, height);
	}
	

	public void downloadArt(PApplet drawer) {
		
		Thread downloader = new Thread(new Runnable() {

			@Override
			public void run() {
 
                // Find the cover art using IMDB links
                // Initialize coverArt
 
                Scanner scan = null;
                 
                try {
					String link = movie.getIMDB() + "";
					if (!link.contains("null")) {

						String pageURLString = "http://www.imdb.com/title/tt0" + link + "/";

						URL pageURL = new URL(pageURLString);
						InputStream is = pageURL.openStream();
						scan = new Scanner(is);

						String fileData = "";
						while (scan.hasNextLine()) {
							String line = scan.nextLine();
							fileData += line + FileIO.DELIMITER;
						}

						// System.out.println(fileData);

						String cut = fileData.substring(fileData.indexOf("<div class=\"poster\""));
						// look for <div class="poster">, look for src=", look for "

						String imageURL = cut.substring(cut.indexOf("src=\"") + 5,
								cut.indexOf("\"", cut.indexOf("src=\"") + 5));

						coverArt = drawer.loadImage(imageURL);
					}
                     
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (scan != null)
                        scan.close();
                }
            }			
		});
		
		downloader.start();

	}

	
}
