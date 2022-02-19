package balanzasGJ.informes;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class FileCopyTest {
	
	@Test
	public void copyFileNetwork() {
		try {
			URI uri = new URI("file://aa:a@192.168.1.137/d/pesadaCSV");
			
			File f = new File(uri);
			System.out.println("Exist:" + f.exists());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void copy() {
		File f = new File("file://aa:a@192.168.1.137/d/pesadaCSV");
		System.out.println("Exist:" + f.exists());
	}

}
