
package DAO;

import java.sql.*;

public class DaoBd {
    static private String url;
    static private String login;
    static private String password;
    static private String pilote;
    static private Connection connection;

    public DaoBd() {
        System.out.println("ready to connect !!");
    }

    public void seConnecter() {
        try {
            Class.forName(pilote);
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("everything is okk *_*");
        } catch (ClassNotFoundException e) {
            System.err.println("pilote " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @return the connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * @return the login
     */
    public static String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @return the pilote
     */
    public static String getPilote() {
        return pilote;
    }

    /**
     * @return the url
     */
    public static String getUrl() {
        return url;
    }

    /**
     * @param connection the connection to set
     */
    public static void setConnection(Connection connection) {
        DaoBd.connection = connection;
    }

    /**
     * @param login the login to set
     */
    public static void setLogin(String login) {
        DaoBd.login = login;
    }

    /**
     * @param password the password to set
     */
    public static void setPassword(String password) {
        DaoBd.password = password;
    }

    /**
     * @param pilote the pilote to set
     */
    public static void setPilote(String pilote) {
        DaoBd.pilote = pilote;
    }

    /**
     * @param url the url to set
     */
    public static void setUrl(String url) {
        DaoBd.url = url;
    }

}