package stoktakipprojesi;
//Gerekli Java sınıflarını içe aktarıyoruz
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

//Giriş penceresi için ana sınıf
public class yetkili_giriş extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_id;
    private JLabel yetkili_id;
    private JLabel parola;
    private JPasswordField txt_parola;
    private JButton girisyap_butonu;
    private JButton iptal_butonu;
    static String no;
    static String sifre;
    private JLabel lblNewLabel_2;
    
 // Uygulamayı başlatmak için ana metot
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	// Giriş penceresi oluşturuluyor ve görünür yapılıyor
                    yetkili_giriş frame = new yetkili_giriş();
                    frame.setVisible(true);
                } catch (Exception e) {
                	// Hata durumunda hata izleme çıktısını yazdırma
                    e.printStackTrace();
                }
            }
        });
    }
 // Giriş penceresi için kurucu metod
    public yetkili_giriş() {
    	// Pencere başlığını ve özelliklerini belirleme
        setTitle("YETKİLİ GİRİŞ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 925, 525);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 160));
        contentPane.setForeground(new Color(0, 0, 160));
        contentPane.setBorder(new LineBorder(new Color(255, 128, 0), 10, true));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Kullanıcı girişi için etiket ve metin alanları oluşturma
        JLabel yetkili_giris = new JLabel("YETKİLİ GİRİŞ");
        yetkili_giris.setHorizontalAlignment(SwingConstants.CENTER);
        yetkili_giris.setBackground(new Color(255, 255, 255));
        yetkili_giris.setForeground(new Color(255, 128, 0));
        yetkili_giris.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
        yetkili_giris.setBounds(477, 64, 410, 102);
        contentPane.add(yetkili_giris);

        txt_id = new JTextField();
        txt_id.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
        txt_id.setBackground(new Color(192, 192, 192));
        txt_id.setBounds(685, 194, 202, 38);
        contentPane.add(txt_id);
        txt_id.setColumns(10);

        yetkili_id = new JLabel("YETKiLi ID");
        yetkili_id.setForeground(new Color(192, 192, 192));
        yetkili_id.setBackground(new Color(0, 0, 128));
        yetkili_id.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
        yetkili_id.setBounds(499, 176, 211, 68);
        contentPane.add(yetkili_id);

        parola = new JLabel("PAROLA");
        parola.setForeground(new Color(192, 192, 192));
        parola.setFont(new Font("Bookman Old Style", Font.PLAIN, 30));
        parola.setBounds(499, 268, 186, 38);
        contentPane.add(parola);

        txt_parola = new JPasswordField();
        txt_parola.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 25));
        txt_parola.setEchoChar('*');
        txt_parola.setBackground(new Color(192, 192, 192));
        txt_parola.setBounds(685, 271, 202, 37);
        contentPane.add(txt_parola);

        // Giriş ve İptal butonları oluşturma
        girisyap_butonu = new JButton("GİRİŞ");
        girisyap_butonu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseExited(MouseEvent e) {
        		girisyap_butonu.setBackground(getBackground());
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
          		girisyap_butonu.setBackground(Color.ORANGE);
        	}
        });
        girisyap_butonu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 // Veritabanında yetkilinin doğruluğunu kontrol etme
                no = txt_id.getText();
                sifre = txt_parola.getText();
                String sql_sorgu = "SELECT count(yetkili_no) as giris FROM yetkililer where yetkili_no='" + no
                        + "' and yetkili_parola='" + sifre + "'";

                ResultSet myRs = baglanti.yap();
                myRs = baglanti.sorgula(sql_sorgu);

                try {
                    while (myRs.next()) {
                        if (myRs.getInt("giris") == 1) {
                        	// Giriş başarılı ise stok_sorgu penceresini aç
                            stok_sorgu srg = new stok_sorgu();
                            srg.setVisible(true);
                            setVisible(false);
                        } else {
                        	// Hatalı giriş durumu
                            JOptionPane.showMessageDialog(null, "Hatalı Giriş! Yetkili ID veya Şifre hatalı.",
                                    "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        girisyap_butonu.setForeground(new Color(0, 0, 0));
        girisyap_butonu.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
        girisyap_butonu.setBackground(new Color(172, 172, 172));
        girisyap_butonu.setBounds(527, 362, 169, 53);
        contentPane.add(girisyap_butonu);

        iptal_butonu = new JButton("İPTAL");
        iptal_butonu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseExited(MouseEvent e) {
        		iptal_butonu.setBackground(getBackground());
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		iptal_butonu.setBackground(Color.red);
        	}
        });
        iptal_butonu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Metin alanlarını temizleme
                txt_id.setText(""); 
                txt_parola.setText("");
            }
        });
        iptal_butonu.setForeground(new Color(0, 0, 0));
        iptal_butonu.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
        iptal_butonu.setBackground(girisyap_butonu.getBackground()); 
        iptal_butonu.setBounds(706, 362, 169, 53); 
        contentPane.add(iptal_butonu);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(192, 192, 192), 4, true));
        panel.setBackground(new Color(255, 128, 0));
        panel.setBounds(46, 47, 421, 404);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("NERCŞ \r\nSTOK \r\n");
        lblNewLabel_1.setToolTipText("");
        lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
        lblNewLabel_1.setBounds(123, 299, 198, 63);
        panel.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("TAKİP");
        lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
        lblNewLabel_2.setBounds(171, 343, 134, 42);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\080808\\Desktop\\stoktakipprojesi\\image\\stok_anasayfa.png"));
        lblNewLabel.setBounds(59, 27, 317, 292);
        panel.add(lblNewLabel);
    }
}