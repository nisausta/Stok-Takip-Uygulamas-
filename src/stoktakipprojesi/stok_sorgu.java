
package stoktakipprojesi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class stok_sorgu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_no;
	private JTextField txt_adi;
	private JTextField txt_fiyat;
	private JLabel urun_no;
	private JTable stoktakiler_table;
	private JTextField txt_kategori;
	private JTextField txt_beden;
	private JTextField txt_renk;
	private JTextField txt_adet;
	
	// Tablo modeli
	DefaultTableModel modelim= new DefaultTableModel();//tabloyu dinamik hale getiriyoruz
	Object[] kolonlar={"Ürün No","Ürün Adı","Ürün Adedi","Ürün Kategori","Ürün Bedeni","Ürün Rengi","Ürün Fiyat"};
	Object[] satirlar= new Object[7];
	private JTextField txt_adsorgu;
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stok_sorgu frame = new stok_sorgu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		// Swing uygulamasının ana penceresi
	public stok_sorgu() {
		setTitle("STOK SORGU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 545);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setForeground(new Color(0, 0, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel.setBounds(22, 41, 334, 457);
		panel.setBackground(new Color(255, 128, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		txt_fiyat = new JTextField();
		txt_fiyat.setHorizontalAlignment(SwingConstants.CENTER);
		txt_fiyat.setForeground(new Color(0, 0, 160));
		txt_fiyat.setBackground(new Color(208, 208, 208));
		txt_fiyat.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_fiyat.setColumns(10);
		txt_fiyat.setBounds(162, 273, 146, 27);
		panel.add(txt_fiyat);
		
		urun_no = new JLabel("Ürün No");
		urun_no.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_no.setBounds(24, 15, 109, 37);
		panel.add(urun_no);
		
		JLabel urun_adi = new JLabel("Ürün Adı");
		urun_adi.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_adi.setBounds(24, 62, 100, 27);
		panel.add(urun_adi);
		
		JLabel urun_kategori = new JLabel("Ürün Kategori");
		urun_kategori.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_kategori.setBounds(24, 142, 130, 39);
		panel.add(urun_kategori);
		
		JLabel urun_fiyati = new JLabel("Ürün Fiyat");
		urun_fiyati.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_fiyati.setBounds(24, 273, 100, 25);
		panel.add(urun_fiyati);
		
		JLabel urun_bedeni = new JLabel("Ürün Bedeni");
		urun_bedeni.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_bedeni.setBounds(24, 191, 109, 32);
		panel.add(urun_bedeni);
		
		JLabel urun_adedi = new JLabel("Ürün Adedi");
		urun_adedi.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_adedi.setBounds(24, 105, 109, 27);
		panel.add(urun_adedi);
		
		JLabel urun_rengi = new JLabel("Ürün Rengi");
		urun_rengi.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		urun_rengi.setBounds(24, 227, 109, 36);
		panel.add(urun_rengi);
		
		
		
		txt_no = new JTextField();
		txt_no.setHorizontalAlignment(SwingConstants.CENTER);
		txt_no.setForeground(new Color(0, 0, 160));
		txt_no.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_no.setBackground(new Color(208, 208, 208));
		txt_no.setBounds(162, 21, 146, 27);
		panel.add(txt_no);
		txt_no.setColumns(10);
		
		txt_adi = new JTextField();
		txt_adi.setHorizontalAlignment(SwingConstants.CENTER);
		txt_adi.setForeground(new Color(0, 0, 160));
		txt_adi.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_adi.setBackground(new Color(208, 208, 208));
		txt_adi.setColumns(10);
		txt_adi.setBounds(162, 63, 146, 27);
		panel.add(txt_adi);
		
		
		
		JButton kaydet_butonu = new JButton("KAYDET");
		// "KAYDET" butonu için action listener
		kaydet_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Metin alanlarından giriş değerlerini al
				String no,adi,adet,beden,kategori,renk,fiyat,sql_sorgu;
				 no = txt_no.getText();
                 adi = txt_adi.getText();
                 adet = txt_adet.getText();
                 kategori = txt_kategori.getText();
                 beden = txt_beden.getText();
                 renk = txt_renk.getText();
                 fiyat = txt_fiyat.getText();
              // Veritabanına ekleme işlemini buraya ekleyebilirsiniz
                 sql_sorgu = "INSERT INTO urunler(urun_no, urun_adi, urun_adet, urun_kategori, urun_beden, urun_renk, urun_fiyat) VALUES (" + no + ", '" + adi + "' , " + adet + ", '" + kategori + "', '" + beden + "', '" + renk + "' , " + fiyat + ")";               //  sql_sorgu = "INSERT INTO urunler(urun_no, urun_adi, urun_adet, urun_kategori, urun_beden, urun_renk, urun_fiyat) VALUES (" + no + ", '" + adi + "' , " + adet + ", '" + kategori + "', '" + beden + "', '" + renk + "' , " + fiyat + ")";
             //  System.out.println(sql_sorgu);
                 baglanti.ekle(sql_sorgu);	
			}
		});
		kaydet_butonu.setBackground(new Color(192, 192, 192));
		kaydet_butonu.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		kaydet_butonu.setBounds(24, 331, 130, 50);
		panel.add(kaydet_butonu);
		
		
		
		
		txt_kategori = new JTextField();
		txt_kategori.setHorizontalAlignment(SwingConstants.CENTER);
		txt_kategori.setForeground(new Color(0, 0, 160));
		txt_kategori.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_kategori.setColumns(10);
		txt_kategori.setBackground(new Color(208, 208, 208));
		txt_kategori.setBounds(162, 149, 146, 27);
		panel.add(txt_kategori);
		
		txt_beden = new JTextField();
		txt_beden.setHorizontalAlignment(SwingConstants.CENTER);
		txt_beden.setForeground(new Color(0, 0, 160));
		txt_beden.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_beden.setColumns(10);
		txt_beden.setBackground(new Color(208, 208, 208));
		txt_beden.setBounds(162, 191, 146, 27);
		panel.add(txt_beden);
		
		txt_renk = new JTextField();
		txt_renk.setHorizontalAlignment(SwingConstants.CENTER);
		txt_renk.setForeground(new Color(0, 0, 160));
		txt_renk.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_renk.setColumns(10);
		txt_renk.setBackground(new Color(208, 208, 208));
		txt_renk.setBounds(162, 233, 146, 27);
		panel.add(txt_renk);
		
		txt_adet = new JTextField();
		txt_adet.setHorizontalAlignment(SwingConstants.CENTER);
		txt_adet.setForeground(new Color(0, 0, 160));
		txt_adet.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_adet.setColumns(10);
		txt_adet.setBackground(new Color(208, 208, 208));
		txt_adet.setBounds(162, 106, 146, 27);
		panel.add(txt_adet);
		
		// (Ürün bilgilerini al, SQL sorgusu oluştur, güncelleme işlemi yap)
		JButton guncelle_buttonu = new JButton("GÜNCELLE");
		guncelle_buttonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Kullanıcının girdiği ürün bilgilerini al
				String no,adi,adet,beden,kategori,renk,fiyat,sql_sorgu;
				 no = txt_no.getText();
                adi = txt_adi.getText();
                adet = txt_adet.getText();
                kategori = txt_kategori.getText();
                beden = txt_beden.getText();
                renk = txt_renk.getText();
                fiyat = txt_fiyat.getText();
             // Güncelleme için SQL sorgusunu oluştur
				sql_sorgu="UPDATE urunler SET urun_no="+no+", "+
				"urun_adi='"+adi+"', urun_adet="+adet+", urun_kategori='"+kategori+"', "
						+ "urun_beden='"+beden+"', urun_renk='"+renk+"', urun_fiyat="+fiyat+" WHERE urun_no="+no;
			//System.out.println(sql_sorgu);
			baglanti.update(sql_sorgu);// Veritabanında güncelleme yap
			}
		});
		
		guncelle_buttonu.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		guncelle_buttonu.setBackground(new Color(192, 192, 192));
		guncelle_buttonu.setBounds(162, 331, 146, 50);
		panel.add(guncelle_buttonu);
		
		
		//  (Ürün numarasını al, SQL sorgusu oluştur, silme işlemi yap)
		JButton sil_buttonu = new JButton("SiL");
		sil_buttonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			String no,sql_sorgu;// Kullanıcının girdiği ürün numarasını al
			no= txt_no.getText();
			// Silme için SQL sorgusunu oluştur
				sql_sorgu="DELETE FROM urunler WHERE urun_no="+no;
				baglanti.sil(sql_sorgu); // Veritabanından ürünü sil
			}
		});
		sil_buttonu.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		sil_buttonu.setBackground(new Color(192, 192, 192));
		sil_buttonu.setBounds(102, 391, 117, 50);
		panel.add(sil_buttonu);
		
		JLabel stok_sorgu_label = new JLabel("STOK SORGU");
		stok_sorgu_label.setBounds(320, 3, 219, 28);
		stok_sorgu_label.setForeground(new Color(192, 192, 192));
		stok_sorgu_label.setFont(new Font("Bookman Old Style", Font.BOLD, 25));
		contentPane.add(stok_sorgu_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 128, 0));
		panel_1.setBounds(379, 41, 605, 273);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 3, true));

		scrollPane.setBounds(10, 60, 585, 203);
		panel_1.add(scrollPane);
		
		stoktakiler_table = new JTable();
		stoktakiler_table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		stoktakiler_table.setBackground(new Color(208, 208, 208));
		stoktakiler_table.setFont(new Font("Bookman Old Style", Font.PLAIN, 12));
		stoktakiler_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		stoktakiler_table.setForeground(new Color(0, 0, 0));
		stoktakiler_table.addMouseListener(new MouseAdapter() {
			
				@Override
				public void mouseClicked(MouseEvent e) {
					txt_no.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),0));
					txt_adi.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),1));
					txt_adet.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),2));
					txt_kategori.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),3));
					txt_beden.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),4));
					txt_renk.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),5));
					txt_fiyat.setText((String) modelim.getValueAt(stoktakiler_table.getSelectedRow(),6));
				}
			});
		modelim.setColumnIdentifiers(kolonlar);

		scrollPane.setViewportView(stoktakiler_table);
		
		JLabel lblNewLabel = new JLabel("STOKTAKiLER");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		lblNewLabel.setBounds(29, 20, 164, 30);
		panel_1.add(lblNewLabel);
		
		JButton listele_butonu = new JButton("LiSTELE");
		listele_butonu.setForeground(new Color(0, 0, 0));
		listele_butonu.setBounds(429, 16, 121, 34);
		panel_1.add(listele_butonu);
		
		listele_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			modelim.setRowCount(0);
			ResultSet myRs = baglanti.yap();// Veritabanından tüm ürünleri çek
			modelim.addRow(satirlar); // Tabloyu güncelle
				try {
					while(myRs.next()) {
						// Veritabanından çekilen bilgilerle tabloyu güncelle
						satirlar[0]=myRs.getString("urun_no");
						satirlar[1]=myRs.getString("urun_adi");
						satirlar[2]=myRs.getString("urun_adet");
						satirlar[3]=myRs.getString("urun_kategori");
						satirlar[4]=myRs.getString("urun_beden");
						satirlar[5]=myRs.getString("urun_renk");
						satirlar[6]=myRs.getString("urun_fiyat");			
						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				stoktakiler_table.setModel(modelim);
				
			}
		});
		listele_butonu.setBackground(new Color(192, 192, 192));
		listele_butonu.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 128, 0));
		panel_2.setBounds(502, 324, 241, 162);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel alan_label = new JLabel("ALAN:");
		alan_label.setBounds(27, 13, 82, 18);
		alan_label.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		alan_label.setBackground(new Color(255, 159, 113));
		panel_2.add(alan_label);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ürün No", "Ürün Adı", "Ürün Adedi", "Ürün Kategorisi", "Ürün Bedeni", "Ürün Rengi", "Ürün Fiyatı"}));
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setBounds(97, 10, 116, 25);
		panel_2.add(comboBox);
		
		txt_adsorgu = new JTextField();
		txt_adsorgu.setHorizontalAlignment(SwingConstants.CENTER);
		txt_adsorgu.setFont(new Font("Bookman Old Style", Font.PLAIN, 16));
		txt_adsorgu.setBackground(new Color(128, 128, 128));
		txt_adsorgu.setBounds(37, 48, 176, 36);
		panel_2.add(txt_adsorgu);
		txt_adsorgu.setColumns(10);
		
		JButton sorgula_buttonu = new JButton("SORGULA");
		sorgula_buttonu.setBackground(new Color(192, 192, 192));
		sorgula_buttonu.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		
				sorgula_buttonu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				modelim.setRowCount(0);
				String sql_sorgu=null;
				String alan=txt_adsorgu.getText();
				ResultSet myRs = null;
				int secilen = comboBox.getSelectedIndex();	
				// Kullanıcının girdiği değere göre veritabanında sorgulama yap
				if(secilen == 0) {
					 sql_sorgu= "select * from urunler where urun_no="+ Integer.parseInt(alan);
				}
				else if (secilen == 1) {
					 sql_sorgu= "select * from urunler where urun_adi like '"+alan+"%'";
				}
				else if (secilen == 2) {
					 sql_sorgu= "select * from urunler where urun_adeti=" + Integer.parseInt(alan);
				}
				else if (secilen == 3) {
					 sql_sorgu= "select * from urunler where urun_kategori like '"+alan+"%'";
				}
				else if (secilen == 4) {
					 sql_sorgu= "select * from urunler where urun_beden like '"+alan+"%'";
				}
				else if (secilen == 5) {
					 sql_sorgu= "select * from urunler where urun_renk like '"+alan+"%'";
				}
				else if (secilen == 6) {
					 sql_sorgu= "select * from urunler where urun_fiyat="+ Integer.parseInt(alan);
				}				
				//String sql_sorgu= "select * from urunler where urun_adi like '"+alan+"%'";
				//System.out.println(sql_sorgu);
				myRs = baglanti.sorgula(sql_sorgu);
				
				try {
					while(myRs.next()) {
						satirlar[0]=myRs.getString("urun_no");
						satirlar[1]=myRs.getString("urun_adi");
						satirlar[2]=myRs.getString("urun_adet");
						satirlar[3]=myRs.getString("urun_kategori");
						satirlar[4]=myRs.getString("urun_beden");
						satirlar[5]=myRs.getString("urun_renk");
						satirlar[6]=myRs.getString("urun_fiyat");
						
						modelim.addRow(satirlar);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				
				
				stoktakiler_table.setModel(modelim);// Tabloyu güncelle
				
				
				
			}
		});
		sorgula_buttonu.setBounds(51, 94, 135, 42);
		panel_2.add(sorgula_buttonu);
		
		JButton cikis_buttonu = new JButton("ÇIKIŞ YAP");
		cikis_buttonu.setForeground(new Color(0, 0, 0));
        cikis_buttonu.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
        cikis_buttonu.setBackground(new Color(192, 192, 192));
        cikis_buttonu.setBounds(830, 429, 127, 37);
        contentPane.add(cikis_buttonu);

        cikis_buttonu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);//Sistemden atar
            }
        });
        cikis_buttonu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseExited(MouseEvent e) {
        		cikis_buttonu.setBackground(getBackground());       	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		cikis_buttonu.setBackground(Color.red);
        	}
        });
	}
}

