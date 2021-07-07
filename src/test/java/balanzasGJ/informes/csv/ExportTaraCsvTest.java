package balanzasGJ.informes.csv;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.balanzasgj.app.informes.csv.ExportTaraCsv;
import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.services.ReportService;
import com.balanzasgj.app.services.TareService;

public class ExportTaraCsvTest {
    private TareService tareService;
    private ExportTaraCsv exportTaraCsv;
    
    @Before
	public void createConn() {
    	tareService = new TareService();
	}

    //@Test
    public void testExportTara(){
        final String path = "C:\\SistemaDePesaje\\csv\\";
        final String fileName = "prueba.csv";
        
        List<Tare> tarasList =tareService.findAll();
        final ExportTaraCsv exportTaraCsv = ExportTaraCsv
                .newBuild()
                .setTaras(tarasList)
                .setPath(path)
                .setNameFile(fileName)
                .build();

        exportTaraCsv.export();
    }

    @Test
    public  void testReportService(){
    	ReportService reportService = new ReportService();
        reportService.exportCsv("sistema_balanzas_taras.csv");
    }
}
