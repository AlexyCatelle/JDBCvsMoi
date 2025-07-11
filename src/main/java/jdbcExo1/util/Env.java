package jdbcExo1.util;

public class Env {
    // Variables de configuration (statiques pour un accès rapide sans instanciation)
    public static final String HOST = "localhost";
    public static final String DB_NAME = "demo_jdbc";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "bq2PQaoyZH6&L3hE"; // Remplace par ton mot de passe réel

    // Optionnel : port JDBC si différent du défaut (3306 pour MySQL)
    public static final String PORT = "3306";

    // Génère l’URL de connexion JDBC
    public static String getJdbcUrl() {
        return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
    }
}

