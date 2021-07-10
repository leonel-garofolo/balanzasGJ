package balanzasGJ.informes;

import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.model.User;
import com.balanzasgj.app.services.ReportService;
import org.junit.Before;
import org.junit.Test;

public class TicketTest {

    private ReportService reportService;

    @Before
    public void beforeClass() throws Exception {
        this.reportService = new ReportService();
    }

    @Test
    public void testTicketEtiquetadora() throws Exception{
        User.setPerfilLogeado("admin");
        User.setUsuarioLogeado("leonelAdmin");
        this.reportService.ticket(Tare.ACTION.T_NORMAL.label, 2);
        new Thread().sleep(20000);
    }
}
