package balanzasGJ.printer;
public class PrinterMain {

    public static void main(String[] args) {

        PrinterService printerService = new PrinterService();

        System.out.println(printerService.getPrinters());

        //print some stuff. Change the printer name to your thermal printer name.
        printerService.printString("XP-58", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n");

        // cut that paper!
        byte[] cutP = new byte[] { 0x1d, 'V', 1 };

        printerService.printBytes("XP-58", cutP);

    }

}