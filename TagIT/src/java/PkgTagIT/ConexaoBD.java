/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package PkgTagIT;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;

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
            ds.setDatabaseName("317624");
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

    public String[] insereEvento(Evento evento) throws TagITDAOException {
        CallableStatement cstm = null;
        String[] sRetorno = null;

        try {
            cstm = con.prepareCall("{call sp_inserir_evento(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            cstm.setString(1, evento.getNome());
            cstm.setDouble(2, evento.getVagasPrincipal());
            cstm.setDate(3,  converterData(evento.getInscInicio()));
            cstm.setDate(4,  converterData(evento.getInscTermino()));
            cstm.setString(5, evento.getRua());
            cstm.setString(6, evento.getCidade());
            cstm.setDate(7,  converterData(evento.getDataEvento()));
            cstm.setString(8, evento.getContato());
            cstm.registerOutParameter(9, Types.VARCHAR);

            cstm.execute();
            sRetorno = cstm.getString(9).split(";");

            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return sRetorno;
    }

    public Date converterData(String data){
        String sDia = data.substring(0, 2);
        String sMes = data.substring(3, 5);
        String sAno = data.substring(6, 10);

        Date date = new Date(Integer.parseInt(sAno) - 1900, Integer.parseInt(sMes) - 1, Integer.parseInt(sDia));

        return date;
    }

    public String[] insereCategoriaNoEvento(Categoria categoria, Evento evento) throws TagITDAOException {
        CallableStatement cstm = null;
        String[] i = null;

        try {
            cstm = con.prepareCall("{call sp_inserir_eventoCategoria(?, ?, ?)}");
            cstm.setString(1, evento.getNome());
            cstm.setString(2, categoria.getNome());
            cstm.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstm.execute();
            cstm.close();

            i = cstm.getString(3).split(";");
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return i;

    }

    /*public Participante validarLogin(String email, String senha) throws TagITDAOException {
        CallableStatement cstm = null;
        ResultSet rs = null;
        Participante part = null;
        int tipoParticipante;
        String emailRecebido = null;
        String nome = null;
        String senhaRecebida = null;
        String cpf = null;
        int tentativasUpgrade = 0;
        boolean upgrade = false;
        double id = 0.0;

        try {
            cstm = con.prepareCall("{call sp_retorna_dados_participante( ?, ? )}");
            cstm.registerOutParameter(2, java.sql.Types.INTEGER);
            cstm.setString(1, email);
            rs = cstm.executeQuery();
            if (rs.next()) {
                emailRecebido = rs.getString(2);
                nome = rs.getString(4);
                senhaRecebida = rs.getString(3);
                cpf = rs.getString(5);
                tentativasUpgrade = rs.getInt(7);
                id = rs.getDouble(1);
                upgrade = rs.getBoolean(6);

                tipoParticipante = cstm.getInt(2);

                if (senhaRecebida.equals(senha)) {
                    part = new Participante(id, emailRecebido, nome, senhaRecebida, cpf, upgrade, tentativasUpgrade, null, null);
                }
            }

            cstm.close();
            return part;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TagITDAOException();
        }
    } 


    public ArrayList<Evento> buscaEventosdoOrganizador(Participante organizador) throws TagITDAOException {
        CallableStatement cstm = null;
        CallableStatement cstm2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ArrayList<Evento> lstEventos = new ArrayList<Evento>();
        Evento evento = null;
        Categoria categoria = null;
        double id;
        String nome;
        double vagasPrincipal;
        double vagasEspera;
        String inscInicio;
        String inscTermino;
        String rua;
        String cidade;
        String dataEvento;
        String contato;
        //Organizador organizador;

        try {
            cstm = con.prepareCall("{call sp_retornar_eventos_organizador(?)}");
            cstm.setString(1, organizador.getEmail());
            rs = cstm.executeQuery();

            while(rs.next()) {
                id = rs.getDouble(1);
                nome = rs.getString(2);
                vagasPrincipal = rs.getDouble(3);
                vagasEspera = rs.getDouble(4);
                inscInicio = rs.getString(5);
                inscTermino = rs.getString(6);
                rua = rs.getString(7);
                cidade = rs.getString(8);
                dataEvento = rs.getString(9);
                contato = rs.getString(10);
                evento = new Evento(id, nome, vagasPrincipal, vagasEspera, inscInicio, inscTermino, rua, cidade, dataEvento, contato, organizador, (new ArrayList<Categoria>()));
                lstEventos.add(evento);
            }



                /*cstm2 = con.prepareCall("{call sp_retorna_categorias_do_evento(?)}");
                cstm2.setString(1, evento.getNome());
                rs2 = cstm2.executeQuery();
                while(rs2.next()) {
                    id = rs2.getDouble(1);
                    nome = rs2.getString(2);
                    categoria = new Categoria(id, nome);
                    evento.getCategoria().add(categoria);
                }
            return lstEventos;


        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
*/


    /**
     *
     * @param p Objeto do tipo participante que sera inserido no BD
     */
    public String[] insereParticipante(Participante p) throws TagITDAOException {
        CallableStatement cstm = null;
        String[] i = null;

        try {
            cstm = con.prepareCall("{call sp_inserir_participante(?, ?, ?, ?)}");
            cstm.setString(1, p.getEmail());
            cstm.setString(2, p.getNome());
            cstm.setString(3, p.getCPF());
            cstm.registerOutParameter(4, java.sql.Types.VARCHAR);
            cstm.execute();
            cstm.close();
            
            i = cstm.getString(4).split(";");
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return i;

    }


    /**
     *
     * @param p Objeto que contem dados de um participante que sera alterado no BD
     *
     * Acho que não vai ser usado!!
     */
    /*public void alteraParticipante(Participante p) throws TagITDAOException {
        CallableStatement cstm = null;

        try {
            cstm = con.prepareCall("{call sp_atualizar_participante(?, ?, ?)}");
            cstm.setString(1, p.getEmail());
            cstm.setString(3, p.getNome());
            cstm.setString(4, p.getCPF());
            cstm.execute();
            cstm.close();
        } catch (SQLException e) {
            throw new TagITDAOException();
        }

    }*/

    /**
     *
     * @param aEmail email do participante que tera seus dados recuperados
     * @return dados do participante
     */
    public Object retornaDadosParticipante(String aEmail) throws TagITDAOException {
        CallableStatement cstm = null;
        String[] i = null;

        Participante p = null;

        try {
            cstm = con.prepareCall("{call sp_retorna_dados_participante(?, ?)}");

            cstm.setString(1, aEmail);
            cstm.registerOutParameter(2, java.sql.Types.VARCHAR);

            ResultSet rs = cstm.executeQuery();

            if (rs.next()) {
                String email = rs.getString(1);
                String nome = rs.getString(2);

                p = new Participante( email, nome, null, null);
                
            }

            i = cstm.getString(2).split(";");
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        if (i[0].compareTo("1") == 0)
            return p;

        return i;

    }

    public String[] inscreverParticipanteEvento(Evento evento, Participante participante) throws TagITDAOException{
        
        CallableStatement cstm = null;
        String[] i;
         try {
            cstm = con.prepareCall("{call sp_inserir_participanteEvento(?, ?, ?)}");
            cstm.setString(1, participante.getEmail());
            cstm.setString(2, evento.getNome());
            cstm.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            cstm.execute();

            i = cstm.getString(3).split(";");

            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return i;
    }

    public Object buscarEventos(String parametro) throws TagITDAOException{
        CallableStatement cstm = null;
        
        ArrayList<Evento> eventos = new ArrayList<Evento>();
         try {
             
            cstm = con.prepareCall("{call sp_retornar_evento(?)}");
            cstm.setString(1, parametro);
            
            ResultSet rs = cstm.executeQuery();
            while(rs.next()){
                Evento aux = new Evento(rs.getString("nome"),rs.getDouble("vagasPrincipal"),rs.getDate("inscInicio").toString(), rs.getDate("inscTermino").toString(), rs.getString("rua"), rs.getString("cidade"), rs.getDate("dataEvento").toString(), rs.getString("contato"));
                eventos.add(aux);
            }
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }
        
            return eventos;

        
    }

    public ArrayList<Evento> buscarEventosParticipante(Participante participante) throws TagITDAOException{
        CallableStatement cstm = null;
        
        ArrayList<Evento> eventos = new ArrayList<Evento>();
         try {
             
            cstm = con.prepareCall("{call sp_retornar_eventosParticipante(?)}");
            cstm.setString(1, participante.getEmail());
            
            ResultSet rs = cstm.executeQuery();
            while(rs.next()){
                Evento aux = new Evento(rs.getString("nome"),rs.getDouble("vagasPrincipal"),rs.getDate("inscInicio").toString(), rs.getDate("inscTermino").toString(), rs.getString("rua"), rs.getString("cidade"), rs.getDate("dataEvento").toString(), rs.getString("contato"));
                eventos.add(aux);
            }
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }
        
            return eventos;

    }
    public void entradaEvento(String email, String evento) throws TagITDAOException {
        String stored = "{call sp_registrar_participanteEvento(?, ?, ?)}";
        String retorno = null;
        CallableStatement cstm = null;
        

        try {
            cstm = con.prepareCall(stored);
            cstm.setString(1, email);
            cstm.setString(2, evento);
            cstm.registerOutParameter(3, java.sql.Types.VARCHAR);

            cstm.execute();
            retorno = cstm.getString(3);
            if(retorno.trim().compareTo("5") == 0) {
                throw new TagITDAOException("Participante nao cadastrado no evento!");
            }
            else if(retorno.trim().compareTo("2") == 0) {
                throw new TagITDAOException("Participante ja entrou no evento");
            }


        } catch (SQLException e) {
            throw new TagITDAOException("BUNDA");
        }
    }
}