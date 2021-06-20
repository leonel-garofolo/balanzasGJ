package balanzasGJ.informes.csv;

import com.balanzasgj.app.informes.csv.ExportTaraCsv;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;
import com.balanzasgj.app.services.ReportService;
import org.junit.Test;

import java.util.List;

public class ExportTaraCsvTest {
    private TarasPersistence tarasPersistence;
    private ExportTaraCsv exportTaraCsv;

    //@Test
    public void testExportTara(){
        final String path = "C:\\SistemaDePesaje\\csv\\";
        final String fileName = "prueba.csv";
        tarasPersistence = new TarasPersistenceJdbc();
        List<Taras> tarasList =tarasPersistence.findAll();
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
        ReportService.exportCsv();
    }
}
