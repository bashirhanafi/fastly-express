/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastlyexpress;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class DashboardPage extends javax.swing.JFrame {

    /**
     * Creates new form AgenPage
     */
    String user = "root";
    String pswd = "";
    String host = "localhost";
    String db = "db_fastly";
    Connection con;
    PreparedStatement ps;
    ResultSet res;
    Statement st;
    DefaultTableModel tb_data_pelanggan_dtm, tb_pengiriman_dtm, tb_transaksi_dtm, tb_layanan_dtm, tb_agent_dtm;
    public static DashboardPage Instance;
    public JLabel label, label2, label3;

    public DashboardPage() throws SQLException {
        initComponents();
        callScaleImage();
        Connect();
        showTable();
        table();
        Instance = this;
        label = hiLabel2;
        label2 = namaPemilikLabel;
        label3 = emailLabel;
    }

    public void callScaleImage() {
        scaleImage("D:\\Documents\\NetBeansProjects\\FastlyExpress\\src\\image\\fastly4-removebg-preview.png", jLabel1);
        scaleImage("D:\\Documents\\NetBeansProjects\\FastlyExpress\\src\\image\\icons8_contacts_208px.png", profileLabel);
        scaleImage("D:\\Documents\\NetBeansProjects\\FastlyExpress\\src\\image\\fastly4.PNG", jLabel49);
        scaleImage("D:\\Documents\\NetBeansProjects\\FastlyExpress\\src\\image\\5481497_2769504 (1).jpg", welcomeLabel);
    }

    public void scaleImage(String path, JLabel label) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }

    public void scaleImage(String path, JButton button) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        button.setIcon(scaledIcon);
    }

    public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            setBackground(new java.awt.Color(17, 153, 158));
            setForeground(new Color(255, 255, 255));
            return this;
        }

    }

    public void table() {
        setBackground(new Color(0, 0, 0, 0));
        tbl_agent.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        tbl_agent.getTableHeader().setOpaque(false);
        tbl_agent.getTableHeader().setBackground(new Color(17, 153, 158));
        tbl_agent.getTableHeader().setDefaultRenderer(new HeaderColor());
        tbl_agent.setRowHeight(25);
        tbl_layanan.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        tbl_layanan.getTableHeader().setOpaque(false);
        tbl_layanan.getTableHeader().setBackground(new Color(17, 153, 158));
        tbl_layanan.getTableHeader().setDefaultRenderer(new HeaderColor());
        tbl_layanan.setRowHeight(25);
        tb_data_pelanggan.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        tb_data_pelanggan.getTableHeader().setOpaque(false);
        tb_data_pelanggan.getTableHeader().setBackground(new Color(17, 153, 158));
        tb_data_pelanggan.getTableHeader().setDefaultRenderer(new HeaderColor());
        tb_data_pelanggan.setRowHeight(25);
        tbl_pengiriman.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        tbl_pengiriman.getTableHeader().setOpaque(false);
        tbl_pengiriman.getTableHeader().setBackground(new Color(17, 153, 158));
        tbl_pengiriman.getTableHeader().setDefaultRenderer(new HeaderColor());
        tbl_pengiriman.setRowHeight(25);
        tbl_transaksi.getTableHeader().setFont(new Font("Segou UI", Font.BOLD, 12));
        tbl_transaksi.getTableHeader().setOpaque(false);
        tbl_transaksi.getTableHeader().setBackground(new Color(17, 153, 158));
        tbl_transaksi.getTableHeader().setDefaultRenderer(new HeaderColor());
        tbl_transaksi.setRowHeight(25);
    }

    public void Connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + "/" + db + "?&user=" + user + "&password" + pswd;
            con = DriverManager.getConnection(url);
            System.out.println("Connection Succesfully!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignInAgentForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignInAgentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showTable() throws SQLException {
        showTablePelanggan();
        showTablePengiriman();
        showTableTransaksi();
        showTableLayanan();
        showTableAgent();
    }

    public void showTableTransaksi() {
        String query3 = "SELECT * FROM tbl_transaksi";

        try {
            ps = con.prepareStatement(query3);
            res = ps.executeQuery();
            tb_transaksi_dtm = (DefaultTableModel) tbl_transaksi.getModel();
            tb_transaksi_dtm.setRowCount(0);

            while (res.next()) {
                tb_transaksi_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }

    public void showTablePengiriman() {
        String query2 = "SELECT tbl_pengiriman.id, tbl_data_pelanggan.nama, tbl_kategori_layanan.jenis_pengiriman, concat('Cabang ', tbl_login_agent.nama_jalan) as jenis_agen, tbl_pengiriman.jumlah, tbl_pengiriman.berat, tbl_pengiriman.tanggal_pengiriman, tbl_pengiriman.tanggal_terima, (berat*tbl_kategori_layanan.biaya) as total_biaya FROM tbl_pengiriman INNER JOIN tbl_data_pelanggan ON tbl_pengiriman.id_data_pelanggan = tbl_data_pelanggan.id INNER JOIN tbl_kategori_layanan ON tbl_pengiriman.id_kategori_layanan = tbl_kategori_layanan.id INNER JOIN tbl_login_agent ON tbl_pengiriman.jenis_agent = tbl_login_agent.id;";

        try {
            ps = con.prepareStatement(query2);

            res = ps.executeQuery();
            tb_pengiriman_dtm = (DefaultTableModel) tbl_pengiriman.getModel();
            tb_pengiriman_dtm.setRowCount(0);

            while (res.next()) {
                tb_pengiriman_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }

        // load combo box
        loadKodePelanggan();
        loadKategoriLayanan();
        loadAgent();
    }

    public void showTablePelanggan() throws SQLException {
        String query1 = "SELECT * FROM tbl_data_pelanggan";

        try {
            ps = con.prepareStatement(query1);

            res = ps.executeQuery();
            tb_data_pelanggan_dtm = (DefaultTableModel) tb_data_pelanggan.getModel();
            tb_data_pelanggan_dtm.setRowCount(0);

            while (res.next()) {
                tb_data_pelanggan_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }

    }

    public void showTableLayanan() {
        String query3 = "SELECT * FROM tbl_kategori_layanan";

        try {
            ps = con.prepareStatement(query3);
            res = ps.executeQuery();
            tb_layanan_dtm = (DefaultTableModel) tbl_layanan.getModel();
            tb_layanan_dtm.setRowCount(0);

            while (res.next()) {
                tb_layanan_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }

    public void showTableAgent() {
        String query = "SELECT id, concat('Fastly Cabang ', nama_jalan) as Agent, no_telp, email, alamat FROM tbl_login_agent";

        try {
            ps = con.prepareStatement(query);
            res = ps.executeQuery();
            tb_agent_dtm = (DefaultTableModel) tbl_agent.getModel();
            tb_agent_dtm.setRowCount(0);

            while (res.next()) {
                tb_agent_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        berandaBtn1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        profileLabel = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        hiLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dataPelangganBtn = new javax.swing.JButton();
        tentangBtn = new javax.swing.JButton();
        pengirimanBtn = new javax.swing.JButton();
        transaksiBtn = new javax.swing.JButton();
        lacakBtn = new javax.swing.JButton();
        layananBtn = new javax.swing.JButton();
        berandaBtn = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        berandaPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        pengirimanPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_pengiriman = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        searchButton2 = new javax.swing.JButton();
        idfield2 = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        transaksiPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        refreshButtonTransaksi = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        idField3 = new javax.swing.JTextField();
        lacakPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        idField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        nomorResiLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        dikirimTanggalLabel = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        dikirimOlehLabel = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        dikirimKeLabel = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        diterimaTanggalLabel = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        deskripsiLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        dataPelangganPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_data_pelanggan = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        idField = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        layananPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_layanan = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_agent = new javax.swing.JTable();
        questionButton1 = new javax.swing.JButton();
        questionButton2 = new javax.swing.JButton();
        insertDataPelangganPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        namaPenerimaField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        noTelpField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lakiLakiRadio = new javax.swing.JRadioButton();
        perempuanRadio = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatField = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        provinsiField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        kotaField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        kecamatanField = new javax.swing.JTextField();
        kelurahanField = new javax.swing.JTextField();
        insertDataPelangganButton2 = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        editDataPelangganPanel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        namaPenerimaField2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        emailField2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        noTelpField2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        lakiRadio2 = new javax.swing.JRadioButton();
        perempuanRadio2 = new javax.swing.JRadioButton();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        alamatField2 = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        provinsiField2 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        kotaField2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        kecamatanField2 = new javax.swing.JTextField();
        kelurahanField2 = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        Back1 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        editPengirimanPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jumlah2 = new javax.swing.JTextField();
        berat2 = new javax.swing.JTextField();
        editButton2 = new javax.swing.JButton();
        Back2 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        kodePelangganCB2 = new javax.swing.JComboBox<>();
        layananCB2 = new javax.swing.JComboBox<>();
        agentCB2 = new javax.swing.JComboBox<>();
        tanggalPengiriman2 = new com.toedter.calendar.JDateChooser();
        tanggalTerima2 = new com.toedter.calendar.JDateChooser();
        inputPengirimanPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jumlahField = new javax.swing.JTextField();
        beratField = new javax.swing.JTextField();
        insertPengirimanButton = new javax.swing.JButton();
        Back3 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        kodePelangganCB = new javax.swing.JComboBox<>();
        layananCB = new javax.swing.JComboBox<>();
        agentCB = new javax.swing.JComboBox<>();
        tanggalPengirimanDate = new com.toedter.calendar.JDateChooser();
        tanggalTerimaDate = new com.toedter.calendar.JDateChooser();
        tentangPanel = new javax.swing.JPanel();
        namaPemilikLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();

        berandaBtn1.setBackground(new java.awt.Color(255, 255, 255));
        berandaBtn1.setText("Beranda");
        berandaBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                berandaBtn1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1080, 720));
        setSize(new java.awt.Dimension(1080, 720));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        profileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileLabelMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel51.setText("Hi,");

        hiLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        hiLabel2.setText("<name>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 708, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hiLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51)
                        .addComponent(hiLabel2))
                    .addComponent(profileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        jPanel4.setBackground(new java.awt.Color(17, 153, 158));

        dataPelangganBtn.setBackground(new java.awt.Color(255, 255, 255));
        dataPelangganBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        dataPelangganBtn.setForeground(new java.awt.Color(51, 51, 51));
        dataPelangganBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-user-male-24.png")); // NOI18N
        dataPelangganBtn.setText("DATA PELANGGAN");
        dataPelangganBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dataPelangganBtn.setIconTextGap(10);
        dataPelangganBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataPelangganBtnActionPerformed(evt);
            }
        });

        tentangBtn.setBackground(new java.awt.Color(255, 255, 255));
        tentangBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        tentangBtn.setForeground(new java.awt.Color(51, 51, 51));
        tentangBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-info-24.png")); // NOI18N
        tentangBtn.setText("TENTANG");
        tentangBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tentangBtn.setIconTextGap(10);
        tentangBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tentangBtnActionPerformed(evt);
            }
        });

        pengirimanBtn.setBackground(new java.awt.Color(255, 255, 255));
        pengirimanBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        pengirimanBtn.setForeground(new java.awt.Color(51, 51, 51));
        pengirimanBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-paper-plane-24.png")); // NOI18N
        pengirimanBtn.setText("PENGIRIMAN");
        pengirimanBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pengirimanBtn.setIconTextGap(10);
        pengirimanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengirimanBtnActionPerformed(evt);
            }
        });

        transaksiBtn.setBackground(new java.awt.Color(255, 255, 255));
        transaksiBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        transaksiBtn.setForeground(new java.awt.Color(51, 51, 51));
        transaksiBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-refresh-24.png")); // NOI18N
        transaksiBtn.setText("TRANSAKSI");
        transaksiBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transaksiBtn.setIconTextGap(10);
        transaksiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiBtnActionPerformed(evt);
            }
        });

        lacakBtn.setBackground(new java.awt.Color(255, 255, 255));
        lacakBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        lacakBtn.setForeground(new java.awt.Color(51, 51, 51));
        lacakBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-packing-24.png")); // NOI18N
        lacakBtn.setText("LACAK PENGIRIMAN");
        lacakBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lacakBtn.setIconTextGap(10);
        lacakBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lacakBtnActionPerformed(evt);
            }
        });

        layananBtn.setBackground(new java.awt.Color(255, 255, 255));
        layananBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        layananBtn.setForeground(new java.awt.Color(51, 51, 51));
        layananBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-trolley-24.png")); // NOI18N
        layananBtn.setText("LAYANAN & PRODUK");
        layananBtn.setIconTextGap(10);
        layananBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layananBtnActionPerformed(evt);
            }
        });

        berandaBtn.setBackground(new java.awt.Color(255, 255, 255));
        berandaBtn.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        berandaBtn.setForeground(new java.awt.Color(51, 51, 51));
        berandaBtn.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-home-24 (1).png")); // NOI18N
        berandaBtn.setText("BERANDA");
        berandaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        berandaBtn.setHideActionText(true);
        berandaBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        berandaBtn.setIconTextGap(10);
        berandaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                berandaBtnActionPerformed(evt);
            }
        });

        logoutButton.setBackground(new java.awt.Color(255, 255, 255));
        logoutButton.setIcon(new javax.swing.ImageIcon("D:\\Downloads\\icons8-logout-24.png")); // NOI18N
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(berandaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataPelangganBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pengirimanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(transaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lacakBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(layananBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tentangBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(berandaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dataPelangganBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pengirimanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(transaksiBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lacakBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(layananBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tentangBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(56, 56, 56))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 260, 650));

        mainPanel.setLayout(new java.awt.CardLayout());

        berandaPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("BERANDA");

        javax.swing.GroupLayout berandaPanelLayout = new javax.swing.GroupLayout(berandaPanel);
        berandaPanel.setLayout(berandaPanelLayout);
        berandaPanelLayout.setHorizontalGroup(
            berandaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(berandaPanelLayout.createSequentialGroup()
                .addGroup(berandaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(berandaPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3))
                    .addGroup(berandaPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        berandaPanelLayout.setVerticalGroup(
            berandaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(berandaPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        mainPanel.add(berandaPanel, "card2");

        pengirimanPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("PENGIRIMAN");

        tbl_pengiriman.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_pengiriman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No. Pengiriman", "Nama Pelanggan", "Jenis Pengiriman", "Jenis Agen", "Jumlah", "Berat", "Tanggal Pengiriman", "Tanggal Terima", "Total Ongkir"
            }
        ));
        tbl_pengiriman.setRowHeight(25);
        tbl_pengiriman.setShowVerticalLines(false);
        tbl_pengiriman.getTableHeader().setReorderingAllowed(false);
        tbl_pengiriman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pengirimanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_pengiriman);

        jButton11.setBackground(new java.awt.Color(27, 108, 138));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("INSERT");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(27, 108, 138));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(27, 108, 138));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("EDIT");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(27, 108, 138));
        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("RELOAD");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        searchButton2.setBackground(new java.awt.Color(27, 108, 138));
        searchButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchButton2.setForeground(new java.awt.Color(255, 255, 255));
        searchButton2.setText("Search");
        searchButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton2ActionPerformed(evt);
            }
        });

        idfield2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idfield2ActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(27, 108, 138));
        submitButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("SUBMIT");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pengirimanPanelLayout = new javax.swing.GroupLayout(pengirimanPanel);
        pengirimanPanel.setLayout(pengirimanPanelLayout);
        pengirimanPanelLayout.setHorizontalGroup(
            pengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pengirimanPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitButton)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pengirimanPanelLayout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addGap(287, 287, 287)
                        .addComponent(idfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pengirimanPanelLayout.setVerticalGroup(
            pengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pengirimanPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(deleteButton)
                    .addComponent(jButton13)
                    .addComponent(refreshButton)
                    .addComponent(searchButton2)
                    .addComponent(idfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitButton)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        mainPanel.add(pengirimanPanel, "card3");

        transaksiPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("TRANSAKSI");

        tbl_transaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Pengiriman", "Agen", "Tanggal Pengiriman", "Tanggal Terima", "Status", "Deskripsi"
            }
        ));
        tbl_transaksi.setRowHeight(25);
        tbl_transaksi.setShowVerticalLines(false);
        tbl_transaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tbl_transaksi);

        refreshButtonTransaksi.setBackground(new java.awt.Color(27, 108, 138));
        refreshButtonTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButtonTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        refreshButtonTransaksi.setText("RELOAD");
        refreshButtonTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonTransaksiActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(27, 108, 138));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("SEARCH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        idField3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout transaksiPanelLayout = new javax.swing.GroupLayout(transaksiPanel);
        transaksiPanel.setLayout(transaksiPanelLayout);
        transaksiPanelLayout.setHorizontalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksiPanelLayout.createSequentialGroup()
                                .addComponent(refreshButtonTransaksi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idField3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                        .addGap(29, 29, 29))))
        );
        transaksiPanelLayout.setVerticalGroup(
            transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksiPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(jLabel25))
                    .addGroup(transaksiPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(transaksiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshButtonTransaksi)
                            .addComponent(jButton2)
                            .addComponent(idField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        mainPanel.add(transaksiPanel, "card4");

        lacakPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("LACAK PENGIRIMAN");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("MASUKKAN NOMOR RESI");

        idField4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idField4ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(27, 108, 138));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("SEARCH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(17, 153, 158));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        nomorResiLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        nomorResiLabel.setText("NOMOR RESI :  ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(nomorResiLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomorResiLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setText("DIKIRIM TANGGAL :");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setText("DIKIRIM OLEH :");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setText("DITERIMA TANGGAL (PERKIRAAN) :");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("DIKIRIM KE :");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("STATUS :");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setText("DESKRIPSI :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dikirimTanggalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dikirimOlehLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dikirimKeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(diterimaTanggalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46))
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel52)
                            .addComponent(jLabel54)
                            .addComponent(jLabel56)
                            .addComponent(jLabel58)
                            .addComponent(jLabel60))
                        .addGap(0, 156, Short.MAX_VALUE))
                    .addComponent(deskripsiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dikirimTanggalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dikirimOlehLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dikirimKeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diterimaTanggalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deskripsiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel62.setText("Pencarian tidak ditemukan!");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(308, 308, 308))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel62)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout lacakPanelLayout = new javax.swing.GroupLayout(lacakPanel);
        lacakPanel.setLayout(lacakPanelLayout);
        lacakPanelLayout.setHorizontalGroup(
            lacakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lacakPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(lacakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel6)
                    .addGroup(lacakPanelLayout.createSequentialGroup()
                        .addComponent(idField4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(lacakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lacakPanelLayout.createSequentialGroup()
                    .addContainerGap(47, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(22, 22, 22)))
        );
        lacakPanelLayout.setVerticalGroup(
            lacakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lacakPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lacakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(idField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(lacakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lacakPanelLayout.createSequentialGroup()
                    .addContainerGap(167, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(51, 51, 51)))
        );

        mainPanel.add(lacakPanel, "card5");

        dataPelangganPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("DATA PELANGGAN");

        tb_data_pelanggan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tb_data_pelanggan.setForeground(new java.awt.Color(51, 51, 51));
        tb_data_pelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "E-mail", "No. Telp", "JK", "Alamat", "Provinsi", "Kota", "Kelurahan", "Kecamatan"
            }
        ));
        tb_data_pelanggan.setRowHeight(25);
        tb_data_pelanggan.setSelectionBackground(new java.awt.Color(27, 108, 138));
        tb_data_pelanggan.setShowVerticalLines(false);
        tb_data_pelanggan.getTableHeader().setReorderingAllowed(false);
        tb_data_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_data_pelangganMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_data_pelanggan);

        jButton5.setBackground(new java.awt.Color(27, 108, 138));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("INSERT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(27, 108, 138));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("DELETE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(27, 108, 138));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("EDIT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(27, 108, 138));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("SEARCH");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        idField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFieldActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(27, 108, 138));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("RELOAD");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataPelangganPanelLayout = new javax.swing.GroupLayout(dataPelangganPanel);
        dataPelangganPanel.setLayout(dataPelangganPanelLayout);
        dataPelangganPanelLayout.setHorizontalGroup(
            dataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPelangganPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(dataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addGroup(dataPelangganPanelLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addGap(4, 4, 4)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(261, 261, 261)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addComponent(jScrollPane2))
                .addGap(28, 28, 28))
        );
        dataPelangganPanelLayout.setVerticalGroup(
            dataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPelangganPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6)
                        .addComponent(jButton5)
                        .addComponent(jButton7)
                        .addComponent(jButton9))
                    .addGroup(dataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainPanel.add(dataPelangganPanel, "card6");

        layananPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("LAYANAN & PRODUK");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("JENIS LAYANAN PENGIRIMAN");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setText("AGENT FASTLY");

        tbl_layanan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_layanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Jenis Pengiriman", "Lama Pengiriman", "Ongkir", "Tanggal Berlaku"
            }
        ));
        tbl_layanan.setRowHeight(25);
        tbl_layanan.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbl_layanan);

        tbl_agent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbl_agent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Agent", "Nama Agent", "No. Telp", "E-mail", "Alamat"
            }
        ));
        tbl_agent.setRowHeight(25);
        tbl_agent.setShowVerticalLines(false);
        tbl_agent.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tbl_agent);

        questionButton1.setBackground(new java.awt.Color(27, 108, 138));
        questionButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        questionButton1.setForeground(new java.awt.Color(255, 255, 255));
        questionButton1.setText("?");
        questionButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionButton1ActionPerformed(evt);
            }
        });

        questionButton2.setBackground(new java.awt.Color(27, 108, 138));
        questionButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        questionButton2.setForeground(new java.awt.Color(255, 255, 255));
        questionButton2.setText("?");
        questionButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layananPanelLayout = new javax.swing.GroupLayout(layananPanel);
        layananPanel.setLayout(layananPanelLayout);
        layananPanelLayout.setHorizontalGroup(
            layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layananPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layananPanelLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(questionButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addGroup(layananPanelLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(questionButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layananPanelLayout.setVerticalGroup(
            layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layananPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(questionButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layananPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(questionButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(309, 309, 309))
        );

        mainPanel.add(layananPanel, "card7");

        insertDataPelangganPanel.setBackground(new java.awt.Color(27, 108, 138));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("INPUT DATA PELANGGAN");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama Penerima");

        namaPenerimaField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("E-mail");

        emailField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("No. Telp");

        noTelpField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Jenis Kelamin");

        lakiLakiRadio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(lakiLakiRadio);
        lakiLakiRadio.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lakiLakiRadio.setForeground(new java.awt.Color(255, 255, 255));
        lakiLakiRadio.setText("Laki-Laki");

        perempuanRadio.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(perempuanRadio);
        perempuanRadio.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        perempuanRadio.setForeground(new java.awt.Color(255, 255, 255));
        perempuanRadio.setText("Perempuan");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Alamat");

        alamatField.setColumns(20);
        alamatField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        alamatField.setRows(5);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, alamatField, org.jdesktop.beansbinding.ELProperty.create("True"), alamatField, org.jdesktop.beansbinding.BeanProperty.create("lineWrap"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(alamatField);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Provinsi");

        provinsiField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Kabupaten/Kota");

        kotaField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Kecamatan");

        kecamatanField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        kelurahanField.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        insertDataPelangganButton2.setBackground(new java.awt.Color(255, 255, 255));
        insertDataPelangganButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        insertDataPelangganButton2.setText("SAVE");
        insertDataPelangganButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertDataPelangganButton2ActionPerformed(evt);
            }
        });

        Back.setBackground(new java.awt.Color(255, 255, 255));
        Back.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Back.setText("<");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Kelurahan");

        javax.swing.GroupLayout insertDataPelangganPanelLayout = new javax.swing.GroupLayout(insertDataPelangganPanel);
        insertDataPelangganPanel.setLayout(insertDataPelangganPanelLayout);
        insertDataPelangganPanelLayout.setHorizontalGroup(
            insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insertDataPelangganPanelLayout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                                        .addComponent(lakiLakiRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(perempuanRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16))
                                .addGap(96, 96, 96)
                                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(provinsiField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(99, 99, 99))
            .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                        .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Back, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaPenerimaField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noTelpField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                        .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addComponent(kotaField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kecamatanField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(kelurahanField))))
                        .addGap(99, 99, 99))
                    .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                        .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(insertDataPelangganButton2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        insertDataPelangganPanelLayout.setVerticalGroup(
            insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Back)
                .addGap(9, 9, 9)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel5)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaPenerimaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lakiLakiRadio)
                    .addComponent(perempuanRadio)
                    .addComponent(provinsiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, insertDataPelangganPanelLayout.createSequentialGroup()
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(noTelpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                            .addComponent(kotaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(insertDataPelangganPanelLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(insertDataPelangganPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(kecamatanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kelurahanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(insertDataPelangganButton2)
                .addGap(275, 275, 275))
        );

        mainPanel.add(insertDataPelangganPanel, "card9");

        editDataPelangganPanel.setBackground(new java.awt.Color(27, 108, 138));
        editDataPelangganPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("EDIT DATA PELANGGAN");
        editDataPelangganPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 62, -1, 44));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Nama Penerima");
        editDataPelangganPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 168, -1, -1));

        namaPenerimaField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(namaPenerimaField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 193, 174, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("E-mail");
        editDataPelangganPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 224, -1, -1));

        emailField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(emailField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 244, 174, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("No. Telp");
        editDataPelangganPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 275, -1, -1));

        noTelpField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(noTelpField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 295, 174, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Jenis Kelamin");
        editDataPelangganPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 168, -1, -1));

        buttonGroup1.add(lakiRadio2);
        lakiRadio2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lakiRadio2.setForeground(new java.awt.Color(255, 255, 255));
        lakiRadio2.setText("Laki-Laki");
        editDataPelangganPanel.add(lakiRadio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 192, 81, -1));

        buttonGroup1.add(perempuanRadio2);
        perempuanRadio2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        perempuanRadio2.setForeground(new java.awt.Color(255, 255, 255));
        perempuanRadio2.setText("Perempuan");
        editDataPelangganPanel.add(perempuanRadio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 192, 91, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Alamat");
        editDataPelangganPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 224, -1, -1));

        alamatField2.setColumns(20);
        alamatField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        alamatField2.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, alamatField2, org.jdesktop.beansbinding.ELProperty.create("True"), alamatField2, org.jdesktop.beansbinding.BeanProperty.create("lineWrap"));
        bindingGroup.addBinding(binding);

        jScrollPane6.setViewportView(alamatField2);

        editDataPelangganPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 244, 215, 83));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Provinsi");
        editDataPelangganPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 168, -1, -1));

        provinsiField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(provinsiField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 193, 174, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Kabupaten/Kota");
        editDataPelangganPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 224, -1, -1));

        kotaField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(kotaField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 244, 174, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Kecamatan");
        editDataPelangganPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 275, -1, -1));

        kecamatanField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(kecamatanField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(547, 295, 84, -1));

        kelurahanField2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editDataPelangganPanel.add(kelurahanField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 295, 80, -1));

        editButton.setBackground(new java.awt.Color(255, 255, 255));
        editButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        editDataPelangganPanel.add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 342, 88, -1));

        Back1.setBackground(new java.awt.Color(255, 255, 255));
        Back1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Back1.setText("<");
        Back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back1ActionPerformed(evt);
            }
        });
        editDataPelangganPanel.add(Back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 30, -1, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Kelurahan");
        editDataPelangganPanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 275, -1, -1));

        mainPanel.add(editDataPelangganPanel, "card9");

        editPengirimanPanel.setBackground(new java.awt.Color(27, 108, 138));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("EDIT PENGIRIMAN");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Pilih Kode Pelanggan");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pilih Kategori Layanan");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Pilih Agen");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Jumlah");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Tanggal Pengiriman");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Tanggal Terima (Perkiraan)");

        jumlah2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        berat2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        editButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton2.setText("EDIT");
        editButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton2ActionPerformed(evt);
            }
        });

        Back2.setBackground(new java.awt.Color(255, 255, 255));
        Back2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Back2.setText("<");
        Back2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back2ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Berat");

        kodePelangganCB2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kodePelangganCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        layananCB2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        layananCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        agentCB2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agentCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        agentCB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agentCB2ActionPerformed(evt);
            }
        });

        tanggalPengiriman2.setBackground(new java.awt.Color(255, 255, 255));

        tanggalTerima2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout editPengirimanPanelLayout = new javax.swing.GroupLayout(editPengirimanPanel);
        editPengirimanPanel.setLayout(editPengirimanPanelLayout);
        editPengirimanPanelLayout.setHorizontalGroup(
            editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editPengirimanPanelLayout.createSequentialGroup()
                .addGap(0, 209, Short.MAX_VALUE)
                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                        .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(layananCB2, 0, 174, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kodePelangganCB2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(agentCB2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21)
                            .addComponent(jLabel40)
                            .addComponent(tanggalPengiriman2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tanggalTerima2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41)
                                    .addComponent(berat2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(202, 202, 202))
            .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(Back2))
                    .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jLabel12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editPengirimanPanelLayout.setVerticalGroup(
            editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Back2)
                .addGap(12, 12, 12)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel13)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(berat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kodePelangganCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                        .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editPengirimanPanelLayout.createSequentialGroup()
                                .addComponent(layananCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(editPengirimanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel40)))
                            .addComponent(tanggalPengiriman2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentCB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tanggalTerima2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(editButton2)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        mainPanel.add(editPengirimanPanel, "card9");

        inputPengirimanPanel.setBackground(new java.awt.Color(27, 108, 138));
        inputPengirimanPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("INPUT PENGIRIMAN");
        inputPengirimanPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 61, -1, 44));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Pilih Kode Pelanggan");
        inputPengirimanPanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 168, -1, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Pilih Kategori Layanan");
        inputPengirimanPanel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 224, -1, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Pilih Agen");
        inputPengirimanPanel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 275, -1, -1));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Jumlah");
        inputPengirimanPanel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 168, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Tanggal Pengiriman");
        inputPengirimanPanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 224, -1, -1));

        jLabel46.setBackground(new java.awt.Color(255, 255, 255));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Tanggal Terima (Perkiraan)");
        inputPengirimanPanel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 275, -1, -1));

        jumlahField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        inputPengirimanPanel.add(jumlahField, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 193, 84, -1));

        beratField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        beratField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beratFieldActionPerformed(evt);
            }
        });
        inputPengirimanPanel.add(beratField, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 193, 84, -1));

        insertPengirimanButton.setBackground(new java.awt.Color(255, 255, 255));
        insertPengirimanButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        insertPengirimanButton.setText("SAVE");
        insertPengirimanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertPengirimanButtonActionPerformed(evt);
            }
        });
        inputPengirimanPanel.add(insertPengirimanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 333, 70, -1));

        Back3.setBackground(new java.awt.Color(255, 255, 255));
        Back3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Back3.setText("<");
        Back3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back3ActionPerformed(evt);
            }
        });
        inputPengirimanPanel.add(Back3, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 30, -1, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Berat");
        inputPengirimanPanel.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(546, 168, -1, -1));

        kodePelangganCB.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        kodePelangganCB.setForeground(new java.awt.Color(255, 255, 255));
        kodePelangganCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputPengirimanPanel.add(kodePelangganCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 193, 174, -1));

        layananCB.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        layananCB.setForeground(new java.awt.Color(255, 255, 255));
        layananCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputPengirimanPanel.add(layananCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 244, 174, -1));

        agentCB.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        agentCB.setForeground(new java.awt.Color(255, 255, 255));
        agentCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        inputPengirimanPanel.add(agentCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 295, 174, -1));

        tanggalPengirimanDate.setBackground(new java.awt.Color(255, 255, 255));
        inputPengirimanPanel.add(tanggalPengirimanDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 244, 186, -1));

        tanggalTerimaDate.setBackground(new java.awt.Color(255, 255, 255));
        inputPengirimanPanel.add(tanggalTerimaDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 295, 186, -1));

        mainPanel.add(inputPengirimanPanel, "card9");

        tentangPanel.setBackground(new java.awt.Color(255, 255, 255));

        namaPemilikLabel.setForeground(new java.awt.Color(255, 255, 255));
        namaPemilikLabel.setText("jLabel2");

        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("jLabel53");

        javax.swing.GroupLayout tentangPanelLayout = new javax.swing.GroupLayout(tentangPanel);
        tentangPanel.setLayout(tentangPanelLayout);
        tentangPanelLayout.setHorizontalGroup(
            tentangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tentangPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(tentangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailLabel)
                    .addComponent(namaPemilikLabel))
                .addContainerGap(716, Short.MAX_VALUE))
        );
        tentangPanelLayout.setVerticalGroup(
            tentangPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tentangPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(namaPemilikLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailLabel)
                .addContainerGap(546, Short.MAX_VALUE))
        );

        mainPanel.add(tentangPanel, "card8");

        jPanel1.add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 820, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dataPelangganBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataPelangganBtnActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(dataPelangganPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_dataPelangganBtnActionPerformed

    private void tentangBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tentangBtnActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        
        JOptionPane.showMessageDialog(null,"Developed by Muhammad Bashir Hanafi\ngithub.com/bashirhanafi");  
        berandaBtn.doClick();
    }//GEN-LAST:event_tentangBtnActionPerformed

    private void pengirimanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengirimanBtnActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pengirimanPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_pengirimanBtnActionPerformed

    private void transaksiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiBtnActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(transaksiPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_transaksiBtnActionPerformed

    private void lacakBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lacakBtnActionPerformed
        // TODO add your handling code here:        
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(lacakPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

        lacakPanel.remove(jPanel3);
        lacakPanel.repaint();
        lacakPanel.revalidate();

        lacakPanel.remove(jPanel8);
        lacakPanel.repaint();
        lacakPanel.revalidate();
    }//GEN-LAST:event_lacakBtnActionPerformed

    private void layananBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layananBtnActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(layananPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_layananBtnActionPerformed

    private void berandaBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_berandaBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_berandaBtn1ActionPerformed

    private void berandaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_berandaBtnActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        mainPanel.add(berandaPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_berandaBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String query3 = "SELECT tbl_transaksi.id, tbl_transaksi.tanggal_pengiriman, tbl_data_pelanggan.alamat, tbl_transaksi.tanggal_terima, tbl_transaksi.status, tbl_transaksi.deskripsi FROM tbl_transaksi INNER JOIN tbl_pengiriman ON tbl_transaksi.id_pengiriman = tbl_pengiriman.id INNER JOIN tbl_data_pelanggan ON tbl_pengiriman.id_data_pelanggan = tbl_data_pelanggan.id WHERE tbl_transaksi.id LIKE '%" + idField4.getText() + "%'";
        try {
            if(idField4.getText().equals("")) {
                lacakBtn.doClick();
            } else {
                ps = con.prepareStatement(query3);
                res = ps.executeQuery();
                if (res.next()) {
                    lacakPanel.remove(jPanel8);
                    lacakPanel.add(jPanel3);
                    lacakPanel.repaint();
                    lacakPanel.revalidate();
                    nomorResiLabel.setText("NOMOR RESI : " + res.getString(1));
                    dikirimTanggalLabel.setText(res.getString(2));
                    dikirimKeLabel.setText(res.getString(3));
                    diterimaTanggalLabel.setText(res.getString(4));
                    statusLabel.setText(res.getString(5));
                    deskripsiLabel.setText(res.getString(6));
                    dikirimOlehLabel.setText(hiLabel2.getText());
                } else {
                    lacakPanel.remove(jPanel3);
                    lacakPanel.repaint();
                    lacakPanel.revalidate();

                    lacakPanel.add(jPanel8);
                    lacakPanel.repaint();
                    lacakPanel.revalidate();
                }
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(insertDataPelangganPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(dataPelangganPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_BackActionPerformed

    private void Back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back1ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(dataPelangganPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_Back1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(editDataPelangganPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        int row = tb_data_pelanggan.getSelectedRow();
        String namaPenerima = namaPenerimaField2.getText();
        String email = emailField2.getText();
        String no_telp = noTelpField2.getText();
        String alamat = alamatField2.getText();
        String provinsi = provinsiField2.getText();
        String kota = kotaField2.getText();
        String kecamatan = kecamatanField2.getText();
        String kelurahan = kelurahanField2.getText();
        String id = tb_data_pelanggan.getModel().getValueAt(row, 0).toString();

        String jk = "";
        if (lakiRadio2.isSelected()) {
            jk = lakiRadio2.getText();
        } else if (perempuanRadio2.isSelected()) {
            jk = perempuanRadio2.getText();
        }

        if (namaPenerima.equals("") || email.equals("") || no_telp.equals("") || alamat.equals("") || provinsi.equals("") || kota.equals("") || kecamatan.equals("") || kelurahan.equals("")) {
            JOptionPane.showMessageDialog(this, "Kolom isian tidak boleh kosong!");
        } else {
            try {
                String query2 = "UPDATE tbl_data_pelanggan SET nama=?, email=?, no_telp=?, jenis_kelamin=?, alamat=?, provinsi=?, kabupaten_kota=?, kelurahan=?, kecamatan=? where id='" + id + "'";
                ps = con.prepareStatement(query2);
                ps.setString(1, namaPenerima);
                ps.setString(2, email);
                ps.setString(3, no_telp);
                ps.setString(4, jk);
                ps.setString(5, alamat);
                ps.setString(6, provinsi);
                ps.setString(7, kota);
                ps.setString(8, kelurahan);
                ps.setString(9, kecamatan);
                int execute = ps.executeUpdate();

                if (execute == 1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui");
                    namaPenerimaField2.setText("");
                    emailField2.setText("");
                    noTelpField2.setText("");
                    buttonGroup1.clearSelection();
                    alamatField2.setText("");
                    provinsiField2.setText("");
                    kotaField2.setText("");
                    kecamatanField2.setText("");
                    kelurahanField2.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal diperbarui");
                }
                showTable();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void idfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idfield2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idfield2ActionPerformed

    private void Back2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back2ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pengirimanPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_Back2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(inputPengirimanPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back3ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(pengirimanPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_Back3ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        mainPanel.add(editPengirimanPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            // TODO add your handling code here:
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void insertDataPelangganButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertDataPelangganButton2ActionPerformed
        // TODO add your handling code here:
        String namaPenerima = namaPenerimaField.getText();
        String email = emailField.getText();
        String no_telp = noTelpField.getText();
        String alamat = alamatField.getText();
        String provinsi = provinsiField.getText();
        String kota = kotaField.getText();
        String kecamatan = kecamatanField.getText();
        String kelurahan = kelurahanField.getText();

        String jk = "";
        if (lakiLakiRadio.isSelected()) {
            jk = lakiLakiRadio.getText();
        } else if (perempuanRadio.isSelected()) {
            jk = perempuanRadio.getText();
        }

        if (namaPenerima.equals("") || email.equals("") || no_telp.equals("") || alamat.equals("") || provinsi.equals("") || kota.equals("") || kecamatan.equals("") || kelurahan.equals("")) {
            JOptionPane.showMessageDialog(this, "Kolom isian tidak boleh kosong!");
        } else {
            try {
                ps = con.prepareStatement("insert into tbl_data_pelanggan(nama, email, no_telp, jenis_kelamin, alamat, provinsi, kabupaten_kota, kelurahan, kecamatan) values(?,?,?,?,?,?,?,?,?)");
                ps.setString(1, namaPenerima);
                ps.setString(2, email);
                ps.setString(3, no_telp);
                ps.setString(4, jk);
                ps.setString(5, alamat);
                ps.setString(6, provinsi);
                ps.setString(7, kota);
                ps.setString(8, kecamatan);
                ps.setString(9, kelurahan);
                int execute = ps.executeUpdate();

                if (execute == 1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
                    namaPenerimaField.setText("");
                    emailField.setText("");
                    noTelpField.setText("");
                    buttonGroup1.clearSelection();
                    alamatField.setText("");
                    provinsiField.setText("");
                    kotaField.setText("");
                    kecamatanField.setText("");
                    kelurahanField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal disimpan");
                }
                showTable();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_insertDataPelangganButton2ActionPerformed

    private void idFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFieldActionPerformed

    private void tb_data_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_data_pelangganMouseClicked
        // TODO add your handling code here:
        int i = tb_data_pelanggan.getSelectedRow();

        if (i > -1) {
            namaPenerimaField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 1).toString());
            emailField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 2).toString());
            noTelpField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 3).toString());

            String genderField = tb_data_pelanggan_dtm.getValueAt(i, 4).toString();
            if (genderField.equals("Laki-Laki")) {
                lakiRadio2.setSelected(true);
            } else if (genderField.equals("Perempuan")) {
                perempuanRadio2.setSelected(true);
            }

            alamatField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 5).toString());
            provinsiField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 6).toString());
            kotaField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 7).toString());
            kecamatanField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 8).toString());
            kelurahanField2.setText(tb_data_pelanggan_dtm.getValueAt(i, 9).toString());
        }
    }//GEN-LAST:event_tb_data_pelangganMouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        String query1 = "SELECT * FROM tbl_data_pelanggan WHERE id like '%" + idField.getText() + "%'";

        try {
            ps = con.prepareStatement(query1);
            res = ps.executeQuery();
            tb_data_pelanggan_dtm = (DefaultTableModel) tb_data_pelanggan.getModel();
            tb_data_pelanggan_dtm.setRowCount(0);

            while (res.next()) {
                tb_data_pelanggan_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int row = tb_data_pelanggan.getSelectedRow();
        String id = tb_data_pelanggan.getModel().getValueAt(row, 0).toString();
        int input = JOptionPane.showConfirmDialog(null, "Apakah Anda ingin menghapus data pelanggan dengan ID: " + id + "?", "Peringatan", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String queryDelete = "DELETE FROM tbl_data_pelanggan WHERE id='" + id + "'";
        if (input == JOptionPane.YES_OPTION) {
            try {
                ps = con.prepareStatement(queryDelete);
                boolean execute = ps.execute();

                if (execute == false) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus");
                }
                showTable();
            } catch (SQLException ex) {

            }
        } else if (input == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void insertPengirimanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertPengirimanButtonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String kd_pelanggan = kodePelangganCB.getSelectedItem().toString();
        String kategori_layanan = layananCB.getSelectedItem().toString();
        String agent = agentCB.getSelectedItem().toString();
        String jumlah = jumlahField.getText();
        String berat = beratField.getText();
        String tanggalPengiriman = sdf.format(tanggalPengirimanDate.getDate());
        String tanggalTerima = sdf.format(tanggalTerimaDate.getDate());

        String queryInsertPengiriman = "insert into tbl_pengiriman(id_data_pelanggan, id_kategori_layanan, jenis_agent, jumlah, berat, tanggal_pengiriman, tanggal_terima) values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(queryInsertPengiriman);
            ps.setString(1, kd_pelanggan);
            ps.setString(2, kategori_layanan);
            ps.setString(3, agent);
            ps.setString(4, jumlah);
            ps.setString(5, berat);
            ps.setString(6, tanggalPengiriman);
            ps.setString(7, tanggalTerima);
            int execute = ps.executeUpdate();

            if (execute == 1) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
                jumlahField.setText("");
                beratField.setText("");
                tanggalPengirimanDate.setDateFormatString("");
                tanggalTerimaDate.setDateFormatString("");
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal disimpan");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_insertPengirimanButtonActionPerformed

    private void beratFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beratFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beratFieldActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int row = tbl_pengiriman.getSelectedRow();
        String id = tbl_pengiriman.getModel().getValueAt(row, 0).toString();
        int input = JOptionPane.showConfirmDialog(null, "Apakah Anda ingin menghapus data pengiriman dengan ID: " + id + "?", "Peringatan", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String queryDelete = "DELETE FROM tbl_pengiriman WHERE id='" + id + "'";
        if (input == JOptionPane.YES_OPTION) {
            try {
                ps = con.prepareStatement(queryDelete);
                boolean execute = ps.execute();

                if (execute == false) {
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus");
                }
                showTable();
            } catch (SQLException ex) {

            }
        } else if (input == JOptionPane.NO_OPTION) {

        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton2ActionPerformed
        // TODO add your handling code here:
        String query2 = "SELECT tbl_pengiriman.id, tbl_data_pelanggan.nama, tbl_kategori_layanan.jenis_pengiriman, concat('Cabang ', tbl_login_agent.nama_jalan) as jenis_agen, tbl_pengiriman.jumlah, tbl_pengiriman.berat, tbl_pengiriman.tanggal_pengiriman, tbl_pengiriman.tanggal_terima, (berat*tbl_kategori_layanan.biaya) as total_biaya FROM tbl_pengiriman INNER JOIN tbl_data_pelanggan ON tbl_pengiriman.id_data_pelanggan = tbl_data_pelanggan.id INNER JOIN tbl_kategori_layanan ON tbl_pengiriman.id_kategori_layanan = tbl_kategori_layanan.id INNER JOIN tbl_login_agent ON tbl_pengiriman.jenis_agent = tbl_login_agent.id WHERE tbl_pengiriman.id like '%" + idfield2.getText() + "%'";

        try {
            ps = con.prepareStatement(query2);
            res = ps.executeQuery();
            tb_pengiriman_dtm = (DefaultTableModel) tbl_pengiriman.getModel();
            tb_pengiriman_dtm.setRowCount(0);

            while (res.next()) {
                tb_pengiriman_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }//GEN-LAST:event_searchButton2ActionPerformed

    private void tbl_pengirimanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pengirimanMouseClicked
        // TODO add your handling code here:
        loadKodePelanggan();

        int i = tbl_pengiriman.getSelectedRow();

        if (i > -1) {

            jumlah2.setText(tb_pengiriman_dtm.getValueAt(i, 4).toString());
            berat2.setText(tb_pengiriman_dtm.getValueAt(i, 5).toString());
            
             
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) tb_pengiriman_dtm.getValueAt(tbl_pengiriman.getSelectedRow(), 6));
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String) tb_pengiriman_dtm.getValueAt(tbl_pengiriman.getSelectedRow(), 7));
                tanggalPengiriman2.setDate(date);
                tanggalTerima2.setDate(date2);
            } catch (ParseException ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tbl_pengirimanMouseClicked

    private void editButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton2ActionPerformed
        // TODO add your handling code here:
        int row = tbl_pengiriman.getSelectedRow();
        String id = tb_pengiriman_dtm.getValueAt(row, 0).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String kd_pelanggan = kodePelangganCB2.getSelectedItem().toString();
        String kategori_layanan = layananCB2.getSelectedItem().toString();
        String agent = agentCB2.getSelectedItem().toString();
        String jumlah = jumlah2.getText();
        String berat = berat2.getText();
        String tanggalPengiriman = sdf.format(tanggalPengiriman2.getDate());
        String tanggalTerima = sdf.format(tanggalTerima2.getDate());

        if (kd_pelanggan.equals("") || kategori_layanan.equals("") || agent.equals("") || jumlah.equals("") || berat.equals("") || tanggalPengiriman.equals("") || tanggalTerima.equals("")) {
            JOptionPane.showMessageDialog(this, "Kolom isian tidak boleh kosong!");
        } else {
            try {
                String query3 = "UPDATE tbl_pengiriman SET id=?, id_data_pelanggan=?, id_kategori_layanan=?, jenis_agent=?,jumlah=?,berat=?,tanggal_pengiriman=?,tanggal_terima=? WHERE id='" + id + "'";
                ps = con.prepareStatement(query3);
                ps.setString(1, id);
                ps.setString(2, kd_pelanggan);
                ps.setString(3, kategori_layanan);
                ps.setString(4, agent);
                ps.setString(5, jumlah);
                ps.setString(6, berat);
                ps.setString(7, tanggalPengiriman);
                ps.setString(8, tanggalTerima);
                int execute = ps.executeUpdate();

                if (execute == 1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui");
                    jumlah2.setText("");
                    berat2.setText("");
                    tanggalPengiriman2.setDateFormatString("");
                    tanggalTerima2.setDateFormatString("");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal diperbarui");
                }
                showTable();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_editButton2ActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        int row = tbl_pengiriman.getSelectedRow();
        String id_pengiriman = tbl_pengiriman.getModel().getValueAt(row, 0).toString();
        String jenis_agent = tbl_pengiriman.getModel().getValueAt(row, 3).toString();
        String tanggal_pengiriman = tbl_pengiriman.getModel().getValueAt(row, 6).toString();
        String tanggal_terima = tbl_pengiriman.getModel().getValueAt(row, 7).toString();
        String status = "Sedang diajukan";
        String deskripsi = "-";
        int input = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin mengirim data pengiriman dengan ID: " + id_pengiriman + " ke agent Fastly " + jenis_agent, "Peringatan", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String queryInsertToTransaksi = "insert into tbl_transaksi(id_pengiriman, jenis_agent, tanggal_pengiriman, tanggal_terima, status, deskripsi) values(?,?,?,?,?,?)";

        if (input == JOptionPane.YES_OPTION) {
            try {
                ps = con.prepareStatement(queryInsertToTransaksi);
                ps.setString(1, id_pengiriman);
                ps.setString(2, jenis_agent);
                ps.setString(3, tanggal_pengiriman);
                ps.setString(4, tanggal_terima);
                ps.setString(5, status);
                ps.setString(6, deskripsi);
                int execute = ps.executeUpdate();

                if (execute == 1) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diajukan ke agent");
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal diajukan ke agent");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }

    }//GEN-LAST:event_submitButtonActionPerformed

    private void refreshButtonTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonTransaksiActionPerformed
        // TODO add your handling code here:
        showTableTransaksi();
    }//GEN-LAST:event_refreshButtonTransaksiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String query3 = "SELECT * FROM tbl_transaksi WHERE id like '%" + idField3.getText() + "%'";

        try {
            ps = con.prepareStatement(query3);
            res = ps.executeQuery();
            tb_transaksi_dtm = (DefaultTableModel) tbl_transaksi.getModel();
            tb_transaksi_dtm.setRowCount(0);

            while (res.next()) {
                tb_transaksi_dtm.addRow(new String[]{
                    res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7)
                });
            }
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void agentCB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agentCB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agentCB2ActionPerformed

    private void idField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idField4ActionPerformed

    private void questionButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionButton1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Total ongkir dihitung dari ongkir awal pada setiap jenis layanan dikali dengan berat barang");
    }//GEN-LAST:event_questionButton1ActionPerformed

    private void profileLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileLabelMouseClicked
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("D:\\Documents\\NetBeansProjects\\FastlyExpress\\src\\image\\icons8-user-male-24.png");
        JOptionPane.showMessageDialog(this, "Nama Bisnis : " + hiLabel2.getText() + "\nNama Pemilik : " + namaPemilikLabel.getText() + "\nE-mail : " + emailLabel.getText(), "Profile User - Business", WIDTH, icon);
    }//GEN-LAST:event_profileLabelMouseClicked

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin keluar?", "WARNING",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                SignInBusinessForm sibf = new SignInBusinessForm();
                sibf.setVisible(true);
                dispose();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void questionButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionButton2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Agent Fastly yang terdaftar di tabel data ini merupakan agent yang telah terverifikasi oleh Fastly Express");
    }//GEN-LAST:event_questionButton2ActionPerformed

    void loadKodePelanggan() {
        try {
            String queryLoadKodePelanggan = "SELECT id FROM tbl_data_pelanggan";
            ps = con.prepareStatement(queryLoadKodePelanggan);
            res = ps.executeQuery();
            kodePelangganCB.removeAllItems();
            kodePelangganCB2.removeAllItems();

            while (res.next()) {
                kodePelangganCB.addItem(res.getString(1));
                kodePelangganCB2.addItem(res.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadKategoriLayanan() {
        try {
            String queryLoadKategoriLayanan = "SELECT id FROM tbl_kategori_layanan";
            ps = con.prepareStatement(queryLoadKategoriLayanan);
            res = ps.executeQuery();
            layananCB.removeAllItems();
            layananCB2.removeAllItems();

            while (res.next()) {
                layananCB.addItem(res.getString(1));
                layananCB2.addItem(res.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadAgent() {
        try {
            String queryLoadAgent = "SELECT * FROM tbl_login_agent";
            ps = con.prepareStatement(queryLoadAgent);
            res = ps.executeQuery();
            agentCB.removeAllItems();
            agentCB2.removeAllItems();

            while (res.next()) {
                agentCB.addItem(res.getString(1));
                agentCB2.addItem(res.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DashboardPage().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Back1;
    private javax.swing.JButton Back2;
    private javax.swing.JButton Back3;
    private javax.swing.JComboBox<String> agentCB;
    private javax.swing.JComboBox<String> agentCB2;
    private javax.swing.JTextArea alamatField;
    private javax.swing.JTextArea alamatField2;
    private javax.swing.JButton berandaBtn;
    private javax.swing.JButton berandaBtn1;
    private javax.swing.JPanel berandaPanel;
    private javax.swing.JTextField berat2;
    private javax.swing.JTextField beratField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton dataPelangganBtn;
    private javax.swing.JPanel dataPelangganPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel deskripsiLabel;
    private javax.swing.JLabel dikirimKeLabel;
    private javax.swing.JLabel dikirimOlehLabel;
    private javax.swing.JLabel dikirimTanggalLabel;
    private javax.swing.JLabel diterimaTanggalLabel;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editButton2;
    private javax.swing.JPanel editDataPelangganPanel;
    private javax.swing.JPanel editPengirimanPanel;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField emailField2;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel hiLabel2;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField idField3;
    private javax.swing.JTextField idField4;
    private javax.swing.JTextField idfield2;
    private javax.swing.JPanel inputPengirimanPanel;
    private javax.swing.JButton insertDataPelangganButton2;
    private javax.swing.JPanel insertDataPelangganPanel;
    private javax.swing.JButton insertPengirimanButton;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jumlah2;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextField kecamatanField;
    private javax.swing.JTextField kecamatanField2;
    private javax.swing.JTextField kelurahanField;
    private javax.swing.JTextField kelurahanField2;
    private javax.swing.JComboBox<String> kodePelangganCB;
    private javax.swing.JComboBox<String> kodePelangganCB2;
    private javax.swing.JTextField kotaField;
    private javax.swing.JTextField kotaField2;
    private javax.swing.JButton lacakBtn;
    private javax.swing.JPanel lacakPanel;
    private javax.swing.JRadioButton lakiLakiRadio;
    private javax.swing.JRadioButton lakiRadio2;
    private javax.swing.JButton layananBtn;
    private javax.swing.JComboBox<String> layananCB;
    private javax.swing.JComboBox<String> layananCB2;
    private javax.swing.JPanel layananPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel namaPemilikLabel;
    private javax.swing.JTextField namaPenerimaField;
    private javax.swing.JTextField namaPenerimaField2;
    private javax.swing.JTextField noTelpField;
    private javax.swing.JTextField noTelpField2;
    private javax.swing.JLabel nomorResiLabel;
    private javax.swing.JButton pengirimanBtn;
    private javax.swing.JPanel pengirimanPanel;
    private javax.swing.JRadioButton perempuanRadio;
    private javax.swing.JRadioButton perempuanRadio2;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JTextField provinsiField;
    private javax.swing.JTextField provinsiField2;
    private javax.swing.JButton questionButton1;
    private javax.swing.JButton questionButton2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButtonTransaksi;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton searchButton2;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton submitButton;
    private com.toedter.calendar.JDateChooser tanggalPengiriman2;
    private com.toedter.calendar.JDateChooser tanggalPengirimanDate;
    private com.toedter.calendar.JDateChooser tanggalTerima2;
    private com.toedter.calendar.JDateChooser tanggalTerimaDate;
    private javax.swing.JTable tb_data_pelanggan;
    private javax.swing.JTable tbl_agent;
    private javax.swing.JTable tbl_layanan;
    private javax.swing.JTable tbl_pengiriman;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JButton tentangBtn;
    private javax.swing.JPanel tentangPanel;
    private javax.swing.JButton transaksiBtn;
    private javax.swing.JPanel transaksiPanel;
    private javax.swing.JLabel welcomeLabel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
