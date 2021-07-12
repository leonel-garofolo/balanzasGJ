package com.balanzasgj.app.db;

import com.balanzasgj.app.persistence.impl.jdbc.commons.DataSourceProvider;
import com.ibm.icu.text.SimpleDateFormat;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ProcessDB extends UtilDB{
    final static Logger logger = Logger.getLogger(ProcessDB.class);
    public static String PATH_MYSQL_INSTALLER = "C:\\SistemaDePesaje\\mysql\\bin\\";
    private String query;

    public boolean cleanDB(){
        Connection conn = null;
        Statement st = null;
        try {
            conn = DataSourceProvider.getConnection();
            st = conn.createStatement();
            query = "delete from reports";
            st.execute(query);
            query = "delete from taras";
            st.execute(query);

            //reset index
            query = "ALTER TABLE taras AUTO_INCREMENT = 1";
            st.execute(query);
            query = "ALTER TABLE reports AUTO_INCREMENT = 1";
            st.execute(query);
        }catch (SQLException e){
            logger.error(e);
            return false;
        }finally {
            super.closeConnection(conn, st);
        }
        return true;
    }

    public static void generarBackup(String path) throws IOException {
        SimpleDateFormat formatDate = new SimpleDateFormat("ddmmyyyy_HHmm");
        String command = PATH_MYSQL_INSTALLER + "mysqldump -u root sist_pesada -p1234";
        logger.info(command);
        Process p = Runtime.getRuntime().exec(command);
        InputStream is = p.getInputStream();
        FileOutputStream fos = new FileOutputStream(path + "\\backup_" + formatDate.format(new Date()) + ".sql");
        byte[] buffer = new byte[1000];

        int leido = is.read(buffer);
        while (leido > 0) {
            fos.write(buffer, 0, leido);
            leido = is.read(buffer);
        }

        fos.close();
    }

    public static void restaurarBackup(String path) throws IOException {
        String command = PATH_MYSQL_INSTALLER + "mysql -u root -p1234 sist_pesada < " + path;
        logger.info(command);
        Runtime.getRuntime().exec(command);
    }

    public static boolean restoreDB(String path) {
        String[] executeCmd = new String[] { PATH_MYSQL_INSTALLER + "mysql", "--user=root", "--password=1234", "sist_pesada", "-e",
                " source " + path };
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
