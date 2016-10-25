import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

 public class DBConnection {
        private final String DBUrl ="jdbc:db2://10.14.128.226:60016/AHNXHIS";
        private final String DBDriver ="com.ibm.db2.jcc.DB2Driver" ;
        private final String username ="hisusr" ;
        private final String password ="hisusr" ;
        private Connection con ;
        public DBConnection(){
            try {
                Class.forName(DBDriver) ;
                con = DriverManager.getConnection(DBUrl,username,password) ;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        public Connection getDB(){
            return con ;
        }
        public void closeDb(ResultSet rs,PreparedStatement ps){
            if(rs!=null){
                try {
                    rs.close() ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close() ;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }