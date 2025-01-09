/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author user
 */


public class TransaksiStokForm extends javax.swing.JFrame {

    /**
     * Creates new form TransaksiStokForm
     */
    public TransaksiStokForm() {
        initComponents();
        cmbJenisTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masuk", "keluar" }));
        btnKembali = new javax.swing.JButton();
btnKembali.setText("Kembali ke Dashboard");
btnKembali.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnKembaliActionPerformed(evt);
    }
});
    }
    
    private void tampilkanTransaksi() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID");
    model.addColumn("Kode Barang");
    model.addColumn("Jenis Transaksi");
    model.addColumn("Jumlah");
    model.addColumn("Tanggal");

    try {
        String sql = "SELECT * FROM transaksi_stok";
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        model.setRowCount(0); // Reset data tabel
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_transaksi"),
                rs.getString("kode_barang"),
                rs.getString("jenis_transaksi"),
                rs.getInt("jumlah"),
                rs.getTimestamp("tanggal")
            });
        }
        tblTransaksiStok.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

// Deklarasikan resetForm di luar metode tampilkanTransaksi
private void resetForm() {
    txtKodeBarang.setText("");
    txtJumlah.setText("");
    cmbJenisTransaksi.setSelectedIndex(0);
}



    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        cmbJenisTransaksi = new javax.swing.JComboBox<>();
        txtJumlah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksiStok = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Jenis Transaksi");

        jLabel3.setText("Jumlah");

        cmbJenisTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masuk", "Keluar" }));
        cmbJenisTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenisTransaksiActionPerformed(evt);
            }
        });

        tblTransaksiStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Kode Barang", "Jenis Transaksi", "Jumlah", "Tanggal"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksiStok);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel4.setText("TRANSAKSI_STOK");

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbJenisTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnKembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJenisTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKembali)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
     // Validasi data input
    if (txtKodeBarang.getText().isEmpty() || txtJumlah.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pastikan semua field terisi!");
        return;
    }

    int jumlah;
    try {
        jumlah = Integer.parseInt(txtJumlah.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
        return;
    }

    String kodeBarang = txtKodeBarang.getText();
    String jenisTransaksi = cmbJenisTransaksi.getSelectedItem().toString();

    try {
        // Simpan transaksi ke tabel transaksi_stok
        String sqlInsert = "INSERT INTO transaksi_stok (kode_barang, jenis_transaksi, jumlah) VALUES (?, ?, ?)";
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement(sqlInsert);
        pst.setString(1, kodeBarang);
        pst.setString(2, jenisTransaksi);
        pst.setInt(3, jumlah);
        pst.executeUpdate();

        // Perbarui stok di tabel barang
        String sqlUpdateStok;
        switch (jenisTransaksi) {
            case "masuk":
                sqlUpdateStok = "UPDATE barang SET stok = stok + ? WHERE kode_barang = ?";
                break;
            case "keluar":
                sqlUpdateStok = "UPDATE barang SET stok = stok - ? WHERE kode_barang = ? AND stok >= ?";
                break;
            default:
                JOptionPane.showMessageDialog(this, "Jenis transaksi tidak valid!");
                return;
        }

        PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdateStok);
        pstUpdate.setInt(1, jumlah);
        pstUpdate.setString(2, kodeBarang);
        if (jenisTransaksi.equals("keluar")) {
            pstUpdate.setInt(3, jumlah);
        }
        int rowsAffected = pstUpdate.executeUpdate();

        // Validasi stok tidak mencukupi
        if (jenisTransaksi.equals("keluar") && rowsAffected == 0) {
            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi untuk transaksi keluar!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Transaksi berhasil ditambahkan!");
        tampilkanTransaksi(); // Refresh tabel transaksi
        resetForm(); // Reset input form
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
     int selectedRow = tblTransaksiStok.getSelectedRow();
    if (selectedRow != -1) {
        try {
            int idTransaksi = Integer.parseInt(tblTransaksiStok.getValueAt(selectedRow, 0).toString());
            String sqlDelete = "DELETE FROM transaksi_stok WHERE id_transaksi=?";
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sqlDelete);
            pst.setInt(1, idTransaksi);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Transaksi berhasil dihapus!");
            tampilkanTransaksi(); // Refresh tabel transaksi
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih transaksi yang ingin dihapus!");
    }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
    txtKodeBarang.setText("");
    cmbJenisTransaksi.setSelectedIndex(0);
    txtJumlah.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
      int selectedRow = tblTransaksiStok.getSelectedRow();
    if (selectedRow != -1) {
        try {
            int idTransaksi = Integer.parseInt(tblTransaksiStok.getValueAt(selectedRow, 0).toString());
            String kodeBarang = txtKodeBarang.getText();
            String jenisTransaksiBaru = cmbJenisTransaksi.getSelectedItem().toString();
            int jumlahBaru = Integer.parseInt(txtJumlah.getText());

            Connection conn = Koneksi.getKoneksi();

            // Ambil data transaksi lama
            String sqlGetOld = "SELECT jenis_transaksi, jumlah FROM transaksi_stok WHERE id_transaksi=?";
            PreparedStatement pstOld = conn.prepareStatement(sqlGetOld);
            pstOld.setInt(1, idTransaksi);
            ResultSet rsOld = pstOld.executeQuery();

            if (rsOld.next()) {
                String jenisTransaksiLama = rsOld.getString("jenis_transaksi");
                int jumlahLama = rsOld.getInt("jumlah");

                // Hitung stok baru berdasarkan perubahan
                int stokChange = 0;
                if (jenisTransaksiLama.equals("Masuk")) stokChange -= jumlahLama;
                else stokChange += jumlahLama;

                if (jenisTransaksiBaru.equals("Masuk")) stokChange += jumlahBaru;
                else stokChange -= jumlahBaru;

                // Validasi stok tidak negatif
                String sqlCheckStok = "SELECT stok FROM barang WHERE kode_barang=?";
                PreparedStatement pstCheck = conn.prepareStatement(sqlCheckStok);
                pstCheck.setString(1, kodeBarang);
                ResultSet rsCheck = pstCheck.executeQuery();

                if (rsCheck.next()) {
                    int stokSaatIni = rsCheck.getInt("stok");
                    if (stokSaatIni + stokChange < 0) {
                        JOptionPane.showMessageDialog(this, "Stok tidak mencukupi untuk perubahan transaksi!");
                        return; // Batalkan update
                    }
                }

                // Update transaksi
                String sqlUpdateTransaksi = "UPDATE transaksi_stok SET kode_barang=?, jenis_transaksi=?, jumlah=? WHERE id_transaksi=?";
                PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdateTransaksi);
                pstUpdate.setString(1, kodeBarang);
                pstUpdate.setString(2, jenisTransaksiBaru);
                pstUpdate.setInt(3, jumlahBaru);
                pstUpdate.setInt(4, idTransaksi);
                pstUpdate.executeUpdate();

                // Update stok
                String sqlUpdateStok = "UPDATE barang SET stok = stok + ? WHERE kode_barang=?";
                PreparedStatement pstUpdateStok = conn.prepareStatement(sqlUpdateStok);
                pstUpdateStok.setInt(1, stokChange);
                pstUpdateStok.setString(2, kodeBarang);
                pstUpdateStok.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data transaksi berhasil diperbarui");
                tampilkanTransaksi();
                resetForm();
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin diubah terlebih dahulu!");
    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void cmbJenisTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisTransaksiActionPerformed
cmbJenisTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masuk", "keluar" }));
  
    }//GEN-LAST:event_cmbJenisTransaksiActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
     new DashboardForm("admin").setVisible(true); // Ganti "admin" jika role diambil secara dinamis
    this.dispose(); // Menutup TransaksiStokForm
    }//GEN-LAST:event_btnKembaliActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiStokForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiStokForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiStokForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiStokForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TransaksiStokForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbJenisTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksiStok;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKodeBarang;
    // End of variables declaration//GEN-END:variables

    
}
