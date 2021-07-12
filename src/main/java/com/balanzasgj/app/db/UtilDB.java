package com.balanzasgj.app.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilDB {

    protected void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void closeConnection(Connection conn, Statement st) {
        if(st != null)
            try {
                st.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        closeConnection(conn);
    }
}
