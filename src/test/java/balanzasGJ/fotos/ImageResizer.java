package balanzasGJ.fotos;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
 
public class ImageResizer {
    //Ancho máximo
    public static int MAX_WIDTH=2592;
    //Alto máximo
    public static int MAX_HEIGHT=1944;
    
    public static String PATH_FROM = "D:\\fotos\\Casamiento\\fotosViejas\\";
    public static String PATH_TO = "D:\\fotos\\Casamiento\\fotosMejoradas4\\";
 
    /*Este método es el de la magia recibe la ruta al archivo original y la ruta donde vamos a guardar la copia
    copyImage("C:\\Users\\IngenioDS\\Desktop\\test.png","C:\\Users\\IngenioDS\\Desktop\\Copia\\test2.png");*/
 
    public static void main(String[] args) {    	
    	File folder = new File(PATH_FROM);
    	File[] listOfFiles = folder.listFiles();
    	for (File file : listOfFiles) {
    	    if (file.isFile()) {
    	        System.out.println(file.getName());
    	        ImageResizer.copyImage(PATH_FROM + file.getName(), PATH_TO + file.getName());
    	    }
    	}
    }    
    
    public static void copyImage(String filePath, String copyPath) {
        BufferedImage bimage = loadImage(filePath);
        if(bimage.getHeight()>bimage.getWidth()){
            int heigt = (bimage.getHeight() * MAX_WIDTH) / bimage.getWidth();
            bimage = resize(bimage, MAX_WIDTH, heigt);
            int width = (bimage.getWidth() * MAX_HEIGHT) / bimage.getHeight();
            bimage = resize(bimage, width, MAX_HEIGHT);
        }else{
            int width = (bimage.getWidth() * MAX_HEIGHT) / bimage.getHeight();
            bimage = resize(bimage, width, MAX_HEIGHT);
            int heigt = (bimage.getHeight() * MAX_WIDTH) / bimage.getWidth();
            bimage = resize(bimage, MAX_WIDTH, heigt);
        }
        saveImage(bimage, copyPath);
    }
     
    /*
    Este método se utiliza para cargar la imagen de disco
    */
    public static BufferedImage loadImage(String pathName) {
        BufferedImage bimage = null;
        try {
            bimage = ImageIO.read(new File(pathName));
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return bimage;
    }
 
    /*
    Este método se utiliza para almacenar la imagen en disco
    */
    public static void saveImage(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file =new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /*
    Este método se utiliza para redimensionar la imagen
    */
    public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();       
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }
}