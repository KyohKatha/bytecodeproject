/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PkgTagIT;

import aaTag.User;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                } catch (Exception h) {
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
    public String[] insereParticipante(User p) throws TagITDAOException {
        CallableStatement cstm = null;
        String[] i = null;

        try {
            cstm = con.prepareCall("{call sp_inserir_participante(?, ?, ?, ?)}");
            cstm.setString(1, p.getEmail());
            cstm.setString(2, p.getNome());
            cstm.setString(3, p.getCPF());
            cstm.registerOutParameter(4, java.sql.Types.VARCHAR);
            cstm.execute();
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

        User p = null;

        try {
            cstm = con.prepareCall("{call sp_retorna_dados_participante(?, ?)}");

            cstm.setString(1, aEmail);
            cstm.registerOutParameter(2, java.sql.Types.VARCHAR);

            ResultSet rs = cstm.executeQuery();

            if (rs.next()) {
                String email = rs.getString(1);
                String nome = rs.getString(2);

                p = new User(email, nome);

            }

            i = cstm.getString(2).split(";");
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        if (i[0].compareTo("1") == 0) {
            return p;
        }

        return i;

    }

    public String[] inscreverParticipanteEvento(Evento evento, User participante) throws TagITDAOException {

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

    public Object buscarEventos(String parametro) throws TagITDAOException {
        CallableStatement cstm = null;

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        ArrayList<Categoria> lstCat = null;
        try {

            cstm = con.prepareCall("{call sp_retornar_evento(?)}");
            cstm.setString(1, parametro);

            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                Evento aux = new Evento(rs.getString("nome"), rs.getDouble("vagasPrincipal"), rs.getDate("inscInicio").toString(), rs.getDate("inscTermino").toString(), rs.getString("rua"), rs.getString("cidade"), rs.getDate("dataEvento").toString(), rs.getString("contato"));
                eventos.add(aux);
            }
            cstm.close();

            for(int i = 0; i < eventos.size(); i++) {
                cstm = con.prepareCall("{call sp_retornar_categorias_do_evento(?)}");
                cstm.setString(1, eventos.get(i).getNome());
                rs = cstm.executeQuery();
                lstCat = new ArrayList<Categoria>();
                while(rs.next()) {
                    Categoria cat = new Categoria(rs.getDouble("id"), rs.getString("nome"));
                    lstCat.add(cat);
                }
                cstm.close();
                eventos.get(i).setCategoria(lstCat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new TagITDAOException();
        }

        return eventos;


    }

    public ArrayList<Evento> buscarEventosParticipante(User participante) throws TagITDAOException {
        CallableStatement cstm = null;

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {

            System.out.println("aaaaaaaaaa");

            cstm = con.prepareCall("{call sp_retornar_eventosParticipante(?)}");
            cstm.setString(1, participante.getEmail());

            System.out.println("aaaaaaaaaa");

            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                Evento aux = new Evento(rs.getString("nome"), rs.getDouble("vagasPrincipal"), rs.getDate("inscInicio").toString(), rs.getDate("inscTermino").toString(), rs.getString("rua"), rs.getString("cidade"), rs.getDate("dataEvento").toString(), rs.getString("contato"));
                eventos.add(aux);
            }
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return eventos;

    }

    /* Entrada no evento - Início */
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
            if (retorno.trim().compareTo("5") == 0) {
                throw new TagITDAOException("Participante nao cadastrado no evento!");
            } else if (retorno.trim().compareTo("2") == 0) {
                throw new TagITDAOException("Participante ja entrou no evento");
            }


        } catch (SQLException e) {
            throw new TagITDAOException("ARAIRAIRAIRIARIAIRAIR");
        }
    }
    private HashMap<String, String> tokens = new HashMap<String, String>();

    public void guardaToken(String tag, String token, String verififer) {
        String tokenVerifier = token + ";" + verififer;

        if (tokens.containsKey(tag.trim())) {
            tokens.remove(tag.trim());
        }

        tokens.put(tag.trim(), tokenVerifier);
    }

    public String retornaToken(String tag) {

        if (tokens.containsKey(tag.trim())) {
            return tokens.get(tag);
        }
        return null;
    }

    /* Entrada no evento - fim */
    public void insereListaInteresseParticipante(ArrayList<Interesse> lInteresse, String email, ArrayList<ContaCategoriaFacebook> lContaCateg) throws TagITDAOException {
        ArrayList<ContaCategoriaFacebook> lAuxCategoria;
        lAuxCategoria = new ArrayList<ContaCategoriaFacebook>();
        for (int i = 0; i < lContaCateg.size(); i++) {
            ContaCategoriaFacebook c;
            c = new ContaCategoriaFacebook();
            c.setCategoria(lContaCateg.get(i).getCategoria());
            c.setContador(lContaCateg.get(i).getContador());
            lAuxCategoria.add(c);
        }
        try {

            for (int i = 0; i < lInteresse.size(); i++) {
                CallableStatement cstm = null;
                cstm = con.prepareCall("{call sp_inserir_participanteInteresse(?, ?, ?, ?, ?)}");
                cstm.setString(1, email);
                cstm.setString(2, lInteresse.get(i).getCategoria());
                cstm.setString(3, lInteresse.get(i).getNome());
                int k = 0;
                for (; k < lAuxCategoria.size(); k++) {
                    if (lInteresse.get(i).getCategoria().equals(lAuxCategoria.get(k).getCategoria())) {
                        break;
                    }
                }
                cstm.setDouble(4, (double) ((1.0 / lContaCateg.get(k).getContador()) * lAuxCategoria.get(k).getContador()));
                lAuxCategoria.get(k).setContador(lAuxCategoria.get(k).getContador() - 1);

                cstm.registerOutParameter(5, java.sql.Types.VARCHAR);
                cstm.execute();
                String retorno = cstm.getString(5);
                if (retorno.trim().compareTo("0") == 0) {
                    throw new TagITDAOException("Os interesses não puderam ser recuperados");
                }
                cstm.close();
            }
        } catch (SQLException e) {
            throw new TagITDAOException();
        }
    }

    public String[] insereEvento(Evento evento) throws TagITDAOException, ParseException,
            Exception {
        CallableStatement cstm = null;
        String[] sRetorno = null;

        String data = evento.getDataEvento();
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6, 10);
        String horaEvento = evento.getHora();
        String hora = horaEvento.substring(0, 2);
        String minuto = horaEvento.substring(3, 5);

        java.sql.Timestamp dataEvento = java.sql.Timestamp.valueOf(ano + "-" + mes + "-"
                + dia + " " + hora + ":" + minuto + ":00");

        try {
            cstm = con.prepareCall("{call sp_inserir_evento(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            cstm.setString(1, evento.getNome());
            cstm.setDouble(2, evento.getVagasPrincipal());
            cstm.setDate(3, converterData(evento.getInscInicio()));
            cstm.setDate(4, converterData(evento.getInscTermino()));
            cstm.setString(5, evento.getRua());
            cstm.setString(6, evento.getCidade());
            cstm.setTimestamp(7, dataEvento);
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

    public String[] insereCategoriaNoEvento(Categoria categoria, Evento evento) throws TagITDAOException {
        CallableStatement cstm = null;
        String[] i = null;

        System.out.println("EVENTOOOOOOOOOO >> " + evento.getNome());
        System.out.println("EVENTOOOOOOOOOO >> " + categoria.getNome());

        try {
            cstm = con.prepareCall("{call sp_inserir_eventoCategoria(?, ?, ?)}");
            cstm.setString(1, evento.getNome());
            cstm.setString(2, categoria.getNome());
            cstm.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstm.execute();

            i = cstm.getString(3).split(";");

            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return i;

    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,
     * retorna null - para facilitar em casos onde formulários podem ter campos
     * de datas vazios.
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception Caso a String esteja no formato errado
     */
    public static java.sql.Date converterData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        java.sql.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    public List<String> retornarTodasCategorias() {
        CallableStatement cstm = null;
        List<String> retorno = new ArrayList();

        ResultSet rs = null;
        int i = 0;

        try {
            cstm = con.prepareCall("{call sp_retornar_todas_categorias}");
            rs = cstm.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(2));
                retorno.add(rs.getString(2));
            }

            cstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retorno;

    }

    public ArrayList<Evento> buscarUltimosEventos() throws TagITDAOException {
        CallableStatement cstm = null;

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {

            System.out.println("aaaaaaaaaa");
            int num = 2;
            cstm = con.prepareCall("{call sp_retornar_topEventos(?)}");
            cstm.setInt(1, num);

            System.out.println("aaaaaaaaaa");

            ResultSet rs = cstm.executeQuery();
            while (rs.next()) {
                Evento aux = new Evento(rs.getString("nome"), rs.getDouble("vagasPrincipal"), rs.getDate("inscInicio").toString(), rs.getDate("inscTermino").toString(), rs.getString("rua"), rs.getString("cidade"), rs.getDate("dataEvento").toString(), rs.getString("contato"));
                System.out.println(aux.getNome());
                eventos.add(aux);
            }
            cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return eventos;
    }

    public ArrayList buscarCategoriasRanqueadas(String evento, String categoria) throws TagITDAOException {
        CallableStatement cstm = null;

        ArrayList categorias = new ArrayList();
        try {
            
                cstm = con.prepareCall("{call sp_retornar_categorias(?,?)}");
                cstm.setString(1, evento);
                cstm.setString(1, evento);
                ResultSet rs = cstm.executeQuery();
                while (rs.next()) {
                    String aux = rs.getString(1);
                    categorias.add(aux);
                }

                cstm.close();

        } catch (SQLException e) {
            throw new TagITDAOException();
        }

        return categorias;
    }
}
