package stoktakipprojesi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class baglanti {
    static Connection myConn; // Veritabanı bağlantısı için kullanılan nesne
    static Statement myStat; // SQL sorgularını yürütmek için kullanılan nesne

    // Veritabanından tüm ürünleri çekmek için kullanılan metot
    static ResultSet yap() {
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stok_takip", "root", "");
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery("select * from urunler");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRs;
    }

    // Veritabanına yeni veri eklemek için kullanılan metot
    static void ekle(String sql_sorgu) {
        try {
            myStat.executeUpdate(sql_sorgu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mevcut bir veriyi güncellemek için kullanılan metot
    static void update(String sql_sorgu) {
        try {
            myStat.executeUpdate(sql_sorgu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Belirtilen bir veriyi veritabanından silmek için kullanılan metot
    static void sil(String sql_sorgu) {
        try {
            myStat.executeUpdate(sql_sorgu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static ResultSet sorgula(String sql_sorgu) {
        ResultSet myRs = null;
        try {
            myRs = myStat.executeQuery(sql_sorgu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myRs;
    }
}
