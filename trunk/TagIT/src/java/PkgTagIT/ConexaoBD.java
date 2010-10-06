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

    public void insereEvento(Evento evento) throws TagITDAOException {
        int i;
        CallableStatement cstm = null;
        ResultSet rs = null;


        try {
            cstm = con.prepareCall("{call sp_inserir_evento"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            cstm.setString(1, evento.getNome());
            cstm.setDouble(2, evento.getVagasPrincipal());
            cstm.setDouble(3, evento.getVagasEspera());
            cstm.setString(4, evento.getInscInicio());
            cstm.setString(5, evento.getInscTermino());
            cstm.setString(6, evento.getRua());
            cstm.setString(7, evento.getCidade());
            cstm.setString(8, evento.getDataEvento());
            cstm.setString(9, evento.getContato());

            rs = cstm.executeQuery();
            if(rs.next()) {
                throw new TagITDAOException("Já existe um evento com esse nome!");
            }
            cstm.close();


            for(i = 0; i < evento.getCategoria().size(); i++) {
                insereCategoriaNoEvento(evento.getCategoria().get(i), evento);
            }
        } catch (SQLException e) {
            throw new TagITDAOException();
        }
    }

    public void insereCategoriaNoEvento(Categoria categoria, Evento evento) throws TagITDAOException {
        CallableStatement cstm = null;


        try {
            cstm = con.prepareCall("{call sp_inserir_eventoCategoria(?, ?)}");
            cstm.setString(1, evento.getNome());
            cstm.setString(2, categoria.getNome());
            cstm.execute();
            cstm.close();
        } catch (SQLException e) {
            throw new TagITDAOException();
        }

    }

    /*
    public Participante validarLogin(String email, String senha) throws TagITDAOException {
        CallableStatement cstm = null;
        ResultSet rs = null;

        try {
            cstm = con.prepareCall("{call sp_retorna_dados_participante(" + email + ")}");
            rs = cstm.executeQuery();

            if (rs.next()) {
                if (!rs.getString(3).equals(senha)) {
                    return null;
                } else {
                    Participante part = new Participante(rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5));
                    return part;
                }
            }

            cstm.close();
        } catch (SQLException e) {
            throw new TagITDAOException();
        }
        return null;
    }
    */
        public boolean validarLogin(String email, String senha) throws TagITDAOException {
        CallableStatement cstm = null;
        ResultSet rs = null;

        try {
            cstm = con.prepareCall("{call sp_retorna_dados_participante(" + email + ")}");
            rs = cstm.executeQuery();
            
            if (rs.next()) {
                System.out.println("VOLTOU ALGO VÁLIDO DO BD");
            }else{
                System.out.println("NÃO VOLTOU PORRA NENHUMA");
            }
            
            cstm.close();
        } catch (SQLException e) {
            throw new TagITDAOException();
        }
        
        return true;
    }


}
