package MODEL;

import DAO.DaoBd;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionEleves {

    private Connection con;
    private DaoBd dao;
    private Statement st;

    public GestionEleves() {
        DaoBd dao = new DaoBd();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setUrl("jdbc:mysql://localhost/gestion_eleves");
        dao.setLogin("root");
        dao.setPassword("");
        dao.seConnecter();
        con = dao.getConnection();
    }

    public void addEleve(String nom, String prenom, int age, String annee) {
        int id = getId();
        String f="";
        if(annee.contains("ginf")){
            f="ginf";
        }else if(annee.contains("gsea")){
            f="gsea";
        }else if(annee.contains("gind")){
            f="gind";
        }else if(annee.contains("gstr")){
            f="gstr";
        }else if(annee.contains("g3ei")){
            f="g3ei";
        }else if(annee.contains("cp")){
            f="cp";
        }
        String req = "insert into eleve values(" + id + ",'" + nom + "','" + prenom + "'," + age + ",'" + f + "','" + annee + "')";

        try {
            st = con.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteEleve(int id) {
         String req = "delete from eleve where id="+id;
         
        try {
            st=con.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(GestionEleves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEleve(int id,String nom, String prenom, int age, String annee){
        String f="";
        if(annee.contains("ginf")){
            f="ginf";
        }else if(annee.contains("gsea")){
            f="gsea";
        }else if(annee.contains("gind")){
            f="gind";
        }else if(annee.contains("gstr")){
            f="gstr";
        }else if(annee.contains("g3ei")){
            f="g3ei";
        }else if(annee.contains("cp")){
            f="cp";
        }
        String req="update eleve set nom='"+nom+"',prenom='"+prenom+"',age="+age+",filiere='"+f+"',annee='"+annee+"' where id="+id;
        
        try {
            st=con.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(GestionEleves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet readEleves(){
        ResultSet res;
        String req = "select * from eleve";
        
        try {
            st=con.createStatement();
            res=st.executeQuery(req);
            return res;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public ResultSet readEleves(String annee){
        ResultSet res;
        String req = "select * from eleve where annee='"+annee+"'";
        
        try {
            st=con.createStatement();
            res=st.executeQuery(req);
            return res;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int getId() {
        String req = "select max(id) from eleve";
        ResultSet res;

        try {
            st = con.createStatement();
            res = st.executeQuery(req);
            if (res.next()) {
                return res.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return -1;
    }
    
    public ResultSet filieres(){
        ResultSet res;
        String req="select distinct(filiere) from eleves";
        try {
            st=con.createStatement();
            res=st.executeQuery(req);
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(GestionEleves.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet annees(){
        ResultSet res;
        String req="select annee from eleves";
        try {
            st=con.createStatement();
            res=st.executeQuery(req);
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(GestionEleves.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
