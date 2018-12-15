package MODEL;


import DAO.DaoBd;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hamza
 */
public class GestionArticle {

    private Connection con;
    private Statement st;
    private DaoBd dao;

    public GestionArticle() {
        dao = new DaoBd();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost/db19");
        dao.setLogin("root");
        dao.setPassword("");
        dao.seConnecter();
        con = dao.getConnection();
    }

    public void Create(int code, String des, float prix) {
        String req;
        req = "insert into Article values('" + code + "','" + des + "','" + prix + "');";

        try {
            if (con != null) {
                st = con.createStatement();
                st.executeUpdate(req);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur dans la requete Create : " + ex.getMessage());

        }
    }

    public void add(int code, String nom, float prix) {
        try {
            PreparedStatement pst;
            pst = con.prepareStatement("insert into Article values (?,?,?)");
            pst.setInt(1, code);
            pst.setString(1, nom);
            pst.setFloat(1, prix);
            pst.executeUpdate();
            System.err.println("ajout effectue avec succes");
        } catch (SQLException ex) {
            System.err.println("erreur dans la requete add" + ex.getMessage());
        }
    }
    
    public void All_Articles(){
        ResultSet res;
        try {
            st= con.createStatement();
            res=st.executeQuery("select * from Article");
            while(res.next()){
                System.out.println(res.getInt(1)+"  "+res.getString("designation")+" "+res.getFloat(3));
            }
        } catch (SQLException ex) {
            System.err.println("erreur dans la requete select ou st " + ex.getMessage());
        }
    }
    
    
    public ResultSet LesArticles(){
        ResultSet res=null;
        try {
            st= con.createStatement();
            res=st.executeQuery("select * from Article");

        } catch (SQLException ex) {
            System.err.println("erreur dans la requete select ou st " + ex.getMessage());
        }
        return res;
    }
    /*
    public void update(int code,String des,float prix){
        try {
            PreparedStatement pst;
            pst = con.prepareStatement("update Article set prix="+prix+" , designation="+des+" where code="+code);
            pst.executeUpdate();
            System.err.println("update effectue avec succes");
        } catch (SQLException ex) {
            System.err.println("erreur dans la requete add" + ex.getMessage());
        }
    }
    */
    public void update(int code,String Des,float Prix){
        String Req;
        Req =   "update Article set designation=?,prix=? where code=?;";
        try{
            PreparedStatement Pst=con.prepareStatement(Req);
            Pst.setInt(3, code);
            Pst.setString(1,Des);
            Pst.setFloat(2, Prix);
            
            int nb=Pst.executeUpdate();
            System.out.println("MAJ Réussi");
            
        }catch(SQLException ex){
            System.err.println("Erreur de la req Update "+ex.getMessage());
        }
    }
}


