/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package PkgTagIT;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

 /*
 * @author Gustavo
 */
public class ConexaoBD {
    private static ConexaoBD instance = null;
    private Connection con;
    private Statement stm;

    private ConexaoBD() throws TagITDAOException {
        try {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("tagitdeveloper");
            ds.setPassword("$enha1");
           // ds.setDatabaseName("317624");
            ds.setServerName("192.168.12.4");
            con = ds.getConnection();
            stm = con.createStatement();

        } catch (Exception e) {
            try {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setUser("tagitdeveloper");
                ds.setPassword("$enha1");
                ds.setDatabaseName("317624");
                ds.setServerName("200.133.238.3");
                con = ds.getConnection();
                stm = con.createStatement();

            } catch (Exception j) {
                try {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setUser("tagitdeveloper");
                ds.setPassword("$enha1");
                ds.setDatabaseName("317624");
                ds.setServerName("shelton.sor.ufscar.br");
                con = ds.getConnection();
                stm = con.createStatement();
                }catch(Exception h){
                    throw new TagITDAOException();
                }
            }
        }
    }

    public static ConexaoBD getInstance() throws TagITDAOException {
        if (instance == null) {
            instance = new ConexaoBD();
        }
        return instance;
    }

}
