package balanzasGJ.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.balanzasgj.app.model.Taras;

public class TarasFactory {

	public static List<Taras> load(){
		List<Taras> taras = new ArrayList<Taras>();
		Taras tara = new Taras();
		tara.setFechaEntrada(new Date());
		tara.setFechaSalida(new Date());
		tara.setBalanza("2561");		
		tara.setTransaccion("1");
		tara.setPesoEntrada(new BigDecimal(5000));
		tara.setPesoSalida(new BigDecimal(7000));		
		taras.add(tara);
		
		tara = new Taras();
		tara.setBalanza("256122");
		tara.setTransaccion("122");
		tara.setPesoEntrada(new BigDecimal(3000));
		tara.setPesoSalida(new BigDecimal(4000));
		taras.add(tara);
		return taras;
	}
}

