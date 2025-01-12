# MuhammadFajarF-2210010748-TB
 UAS PBO 2
# APLIKASI GUDANG 

## Deskripsi

Proyek ini adalah aplikasi berbasis Java untuk manajemen barang dan transaksi stok. Aplikasi ini menggunakan GUI (Graphical User Interface) berbasis Swing, terintegrasi dengan database MySQL untuk penyimpanan data. Aplikasi ini mendukung dua jenis pengguna, yaitu admin dan staf, dengan hak akses yang berbeda.

## Fitur Utama

1. **Manajemen Barang**

   - Menambahkan barang baru.
   - Mengedit data barang yang sudah ada.
   - Menghapus barang.
   - Melihat daftar barang yang tersedia.

2. **Transaksi Stok**

   - Mencatat transaksi stok masuk dan keluar.
   - Mengupdate stok barang berdasarkan transaksi.
   - Mencatat log transaksi di tabel `transaksi_stok`.

3. **Sistem Login**

   - Admin dapat mengakses semua fitur, termasuk barang dan transaksi stok.
   - Staf hanya dapat mengakses fitur manajemen barang.

4. **Dashboard**

   - Menampilkan informasi selamat datang sesuai dengan role pengguna.
   - Navigasi ke form barang atau transaksi stok berdasarkan hak akses.
   - Tombol logout untuk kembali ke form login.

## Struktur Database

### Tabel `barang`

| Kolom         | Tipe Data   | Keterangan                  |
| ------------- | ----------- | --------------------------- |
| `id_barang`   | INT         | Primary key, auto increment |
| `kode_barang` | VARCHAR(20) | Kode unik untuk barang      |
| `nama_barang` | VARCHAR(50) | Nama barang                 |
| `stok`        | INT         | Jumlah stok barang          |

### Tabel `transaksi_stok`

| Kolom             | Tipe Data   | Keterangan                          |
| ----------------- | ----------- | ----------------------------------- |
| `id_transaksi`    | INT         | Primary key, auto increment         |
| `kode_barang`     | VARCHAR(20) | Kode barang terkait transaksi       |
| `jenis_transaksi` | ENUM        | Jenis transaksi (`masuk`, `keluar`) |
| `jumlah`          | INT         | Jumlah barang dalam transaksi       |

### Tabel `users`

| Kolom      | Tipe Data   | Keterangan                       |
| ---------- | ----------- | -------------------------------- |
| `id_user`  | INT         | Primary key, auto increment      |
| `username` | VARCHAR(20) | Nama pengguna                    |
| `password` | VARCHAR(50) | Kata sandi pengguna              |
| `role`     | ENUM        | Role pengguna (`admin`, `staff`) |

# BarangForm

**BarangForm** adalah sebuah GUI yang dirancang menggunakan Java Swing untuk mengelola data barang dalam aplikasi Gudang. Form ini memungkinkan pengguna untuk melakukan operasi CRUD (Create, Read, Update, Delete) terhadap data barang yang tersimpan di dalam database.

## Fitur Utama
- **Tambah Data Barang**: Pengguna dapat menambahkan data barang baru.
- **Edit Data Barang**: Pengguna dapat memperbarui data barang yang sudah ada.
- **Hapus Data Barang**: Pengguna dapat menghapus data barang.
- **Reset Form**: Mengosongkan semua input di form.
- **Kembali ke Dashboard**: Mengarahkan pengguna kembali ke DashboardForm.

## Kode Program

```java
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BarangForm extends javax.swing.JFrame {

    public BarangForm() {
        initComponents();
        tampilkanDataBarang();
    }

    private void tampilkanDataBarang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Kategori");
        model.addColumn("Stok");
        model.addColumn("Harga");

        try {
            String sql = "SELECT * FROM barang";
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_barang"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getInt("stok"),
                    rs.getDouble("harga")
                });
            }
            tblBarang.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void resetForm() {
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        txtKategori.setText("");
        txtStok.setText("");
        txtHarga.setText("");
    }

    private void initComponents() {
        // Komponen GUI diinisialisasi di sini
        // Misalnya tombol, label, tabel, dll.
    }

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, stok, harga) VALUES (?, ?, ?, ?, ?)";
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtKodeBarang.getText());
            pst.setString(2, txtNamaBarang.getText());
            pst.setString(3, txtKategori.getText());
            pst.setInt(4, Integer.parseInt(txtStok.getText()));
            pst.setDouble(5, Double.parseDouble(txtHarga.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Berhasil Ditambahkan");
            tampilkanDataBarang();
            resetForm();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String sql = "UPDATE barang SET nama_barang=?, kategori=?, stok=?, harga=? WHERE kode_barang=?";
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaBarang.getText());
            pst.setString(2, txtKategori.getText());
            pst.setInt(3, Integer.parseInt(txtStok.getText()));
            pst.setDouble(4, Double.parseDouble(txtHarga.getText()));
            pst.setString(5, txtKodeBarang.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Berhasil Diperbarui");
            tampilkanDataBarang();
            resetForm();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String sql = "DELETE FROM barang WHERE kode_barang=?";
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtKodeBarang.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Berhasil Dihapus");
            tampilkanDataBarang();
            resetForm();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        new DashboardForm().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BarangForm().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtHarga;
    // End of variables declaration
}
```
# TransaksiStokForm.java

`TransaksiStokForm.java` adalah bagian dari aplikasi pengelolaan stok barang berbasis Java Swing. File ini bertanggung jawab untuk menangani logika dan antarmuka pengguna terkait transaksi stok barang. Transaksi dapat berupa barang yang masuk atau keluar, dan setiap perubahan stok akan dicatat di database serta memengaruhi stok barang yang tersimpan.

## Fitur Utama
- **Input Data Transaksi Stok:** Pengguna dapat menambahkan transaksi baru, baik barang masuk maupun keluar.
- **Update Stok Barang:** Secara otomatis memperbarui stok di tabel barang berdasarkan transaksi yang dilakukan.
- **Hapus Transaksi:** Menghapus transaksi tertentu dari tabel transaksi.
- **Validasi:** Menjamin stok tidak menjadi negatif saat barang keluar.
- **Navigasi ke Dashboard:** Tombol untuk kembali ke dashboard utama.

## Struktur Kode

### Import Library
```java
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
```
Kode ini mengimpor pustaka yang diperlukan untuk koneksi database, manipulasi tabel, dan antarmuka grafis.

### Deklarasi Kelas dan Variabel
```java
public class TransaksiStokForm extends javax.swing.JFrame {

    public TransaksiStokForm() {
        initComponents();
        tampilkanTransaksi();
    }
```
Konstruktor `TransaksiStokForm` akan memanggil `initComponents` untuk menginisialisasi elemen GUI dan memuat data transaksi dari database ke tabel.

### Metode Utama
1. **`tampilkanTransaksi()`**
   - Menampilkan semua data transaksi stok dari database ke tabel di form.

2. **`btnTambahActionPerformed`**
   - Menambahkan transaksi baru.
   - Mengupdate stok barang berdasarkan jenis transaksi.

3. **`btnHapusActionPerformed`**
   - Menghapus transaksi yang dipilih dari tabel dan database.

4. **`btnResetActionPerformed`**
   - Membersihkan input form agar siap digunakan untuk transaksi baru.

5. **`btnDashboardActionPerformed`**
   - Mengarahkan pengguna kembali ke DashboardForm.

### Navigasi ke Dashboard
```java
private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {                                          
    new DashboardForm("admin").setVisible(true);
    this.dispose();
}                                         
```
Kode ini memungkinkan pengguna untuk kembali ke dashboard utama.

### Validasi Stok Tidak Negatif
```java
if (jenisTransaksi.equalsIgnoreCase("keluar") && stokBarang < jumlah) {
    JOptionPane.showMessageDialog(this, "Stok tidak mencukupi untuk transaksi keluar!");
    return;
}
```
Logika ini menjamin bahwa stok barang tidak akan menjadi negatif setelah transaksi keluar.

## Contoh Kode Lengkap
```java
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TransaksiStokForm extends javax.swing.JFrame {

    public TransaksiStokForm() {
        initComponents();
        tampilkanTransaksi();
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

            model.setRowCount(0);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String kodeBarang = txtKodeBarang.getText();
            String jenisTransaksi = cmbJenisTransaksi.getSelectedItem().toString();
            int jumlah = Integer.parseInt(txtJumlah.getText());

            String sqlBarang = "SELECT stok FROM barang WHERE kode_barang=?";
            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pstBarang = conn.prepareStatement(sqlBarang);
            pstBarang.setString(1, kodeBarang);
            ResultSet rs = pstBarang.executeQuery();

            if (rs.next()) {
                int stokBarang = rs.getInt("stok");
                if (jenisTransaksi.equalsIgnoreCase("keluar") && stokBarang < jumlah) {
                    JOptionPane.showMessageDialog(this, "Stok tidak mencukupi untuk transaksi keluar!");
                    return;
                }

                int stokBaru = jenisTransaksi.equalsIgnoreCase("masuk") ? stokBarang + jumlah : stokBarang - jumlah;

                String updateStok = "UPDATE barang SET stok=? WHERE kode_barang=?";
                PreparedStatement pstUpdate = conn.prepareStatement(updateStok);
                pstUpdate.setInt(1, stokBaru);
                pstUpdate.setString(2, kodeBarang);
                pstUpdate.executeUpdate();

                String sqlTransaksi = "INSERT INTO transaksi_stok (kode_barang, jenis_transaksi, jumlah) VALUES (?, ?, ?)";
                PreparedStatement pstTransaksi = conn.prepareStatement(sqlTransaksi);
                pstTransaksi.setString(1, kodeBarang);
                pstTransaksi.setString(2, jenisTransaksi);
                pstTransaksi.setInt(3, jumlah);
                pstTransaksi.executeUpdate();

                JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan.");
                tampilkanTransaksi();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "Kode barang tidak ditemukan.");
            }
        } catch (SQLException | HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void resetForm() {
        txtKodeBarang.setText("");
        cmbJenisTransaksi.setSelectedIndex(0);
        txtJumlah.setText("");
    }

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        new DashboardForm("admin").setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new TransaksiStokForm().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JComboBox<String> cmbJenisTransaksi;
    private javax.swing.JTable tblTransaksiStok;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtJumlah;
    // End of variables declaration
}
```
# LoginForm.java

## Deskripsi
File `LoginForm.java` merupakan bagian dari aplikasi manajemen gudang. Form ini berfungsi sebagai gerbang utama untuk otentikasi pengguna sebelum mereka dapat mengakses fitur-fitur lainnya, seperti pengelolaan barang atau transaksi stok. LoginForm mendukung dua jenis pengguna, yaitu **admin** dan **staff**, dengan masing-masing memiliki hak akses yang berbeda.

## Fitur Utama
1. **Otentikasi Pengguna**:
   - Memverifikasi username, password, dan peran (role) pengguna.
   - Role yang didukung: `admin` dan `staff`.
2. **Akses Berdasarkan Role**:
   - **Admin**: Dapat mengakses semua fitur, termasuk barang dan transaksi stok.
   - **Staff**: Hanya dapat mengakses fitur barang.
3. **Notifikasi Kesalahan**:
   - Menampilkan pesan kesalahan jika login gagal.
4. **Navigasi Dinamis**:
   - Setelah login berhasil, pengguna akan diarahkan ke DashboardForm dengan akses yang sesuai dengan role mereka.

## Struktur Kode
### Variabel Utama
- `txtUsername`: Field untuk memasukkan username.
- `txtPassword`: Field untuk memasukkan password.
- `cmbRole`: Dropdown untuk memilih role (admin atau staff).
- `btnLogin`: Tombol untuk memproses login.

### Method Utama
#### 1. **`btnLoginActionPerformed`**
Method ini menangani logika login pengguna.
- Mengambil data dari `txtUsername`, `txtPassword`, dan `cmbRole`.
- Mengecek kredensial di database menggunakan query SQL.
- Menentukan form yang akan dibuka berdasarkan role pengguna.

#### 2. **`main`**
Menjalankan aplikasi dengan membuka form login sebagai gerbang utama.

### Kode Utama
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String role = cmbRole.getSelectedItem().toString();

        try {
            Connection conn = Koneksi.getKoneksi();
            String sql = "SELECT * FROM users WHERE username=? AND password=? AND role=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, role);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Berhasil");
                if (role.equals("admin")) {
                    DashboardForm dashboardForm = new DashboardForm("admin");
                    dashboardForm.setVisible(true);
                } else if (role.equals("staff")) {
                    DashboardForm dashboardForm = new DashboardForm("staff");
                    dashboardForm.setVisible(true);
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }

    // Variables declaration                     
    private javax.swing.JButton btnLogin;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration
}
```

## Cara Menggunakan
1. **Buka Aplikasi**: Jalankan `LoginForm` sebagai form awal aplikasi.
2. **Masukkan Kredensial**:
   - Isi `Username` dan `Password` sesuai data di tabel `users` pada database.
   - Pilih `Role` (admin/staff).
3. **Klik Login**:
   - Jika berhasil, akan diarahkan ke DashboardForm.
   - Jika gagal, akan muncul pesan kesalahan.

## Catatan
- Pastikan tabel `users` pada database memiliki kolom berikut:
  - `id` (int, primary key, auto-increment)
  - `username` (varchar)
  - `password` (varchar)
  - `role` (enum: admin, staff)
- Sesuaikan koneksi database (`Koneksi.getKoneksi()`) agar sesuai dengan konfigurasi Anda.

---

# DashboardForm.java

`DashboardForm.java` adalah bagian dari aplikasi sistem manajemen gudang yang berfungsi sebagai jendela utama (dashboard) setelah pengguna berhasil login. Dashboard ini menampilkan opsi berdasarkan peran pengguna (`admin` atau `staff`) dan memberikan akses ke fitur-fitur tertentu sesuai dengan hak akses mereka.

## Fitur

1. **Selamat Datang**
   - Menampilkan pesan selamat datang sesuai dengan peran pengguna.

2. **Navigasi**
   - **Barang Form**: Tombol untuk mengakses form barang.
   - **Transaksi Stok Form**: Tombol untuk mengakses form transaksi stok (hanya tersedia untuk admin).

3. **Logout**
   - Tombol untuk keluar dari dashboard dan kembali ke form login.

## Struktur Program

### Variabel Utama

- `role` - Menyimpan peran pengguna yang sedang login (`admin` atau `staff`).
- `btnBarang` - Tombol untuk membuka `BarangForm`.
- `btnTransaksiStok` - Tombol untuk membuka `TransaksiStokForm`.
- `btnLogout` - Tombol untuk logout dan kembali ke `LoginForm`.
- `lblWelcome` - Label untuk menampilkan pesan selamat datang.

### Fungsi Utama

#### `DashboardForm(String role)`
Konstruktor yang menerima parameter `role` untuk menentukan hak akses pengguna dan mengatur tampilan dashboard.

#### `setupDashboard()`
Fungsi untuk mengatur elemen pada dashboard sesuai dengan peran pengguna. Jika pengguna adalah `staff`, tombol untuk mengakses transaksi stok akan dinonaktifkan.

#### `btnBarangActionPerformed(java.awt.event.ActionEvent evt)`
Event handler untuk membuka `BarangForm`.

#### `btnTransaksiStokActionPerformed(java.awt.event.ActionEvent evt)`
Event handler untuk membuka `TransaksiStokForm`. Hanya dapat diakses oleh pengguna dengan peran `admin`. Jika `staff` mencoba mengakses, akan muncul pesan "Akses Ditolak".

#### `btnLogoutActionPerformed(java.awt.event.ActionEvent evt)`
Event handler untuk logout dari dashboard dan kembali ke `LoginForm`.

## Cara Kerja

1. Setelah login berhasil, `LoginForm` akan membuka `DashboardForm` dengan parameter peran pengguna.
2. `DashboardForm` akan mengatur tampilan berdasarkan peran:
   - **Admin**: Bisa mengakses semua fitur (BarangForm dan TransaksiStokForm).
   - **Staff**: Hanya bisa mengakses BarangForm. Tombol TransaksiStokForm dinonaktifkan.
3. Pengguna dapat memilih:
   - Masuk ke BarangForm untuk mengelola barang.
   - (Admin) Masuk ke TransaksiStokForm untuk mencatat transaksi.
   - Logout untuk kembali ke LoginForm.

## Cuplikan Kode Utama

```java
private void setupDashboard() {
    if (role.equalsIgnoreCase("staff")) {
        btnTransaksiStok.setEnabled(false); // Nonaktifkan tombol Transaksi untuk Staff
    }
    lblWelcome.setText("Selamat Datang, " + role); // Tampilkan peran di dashboard
}

private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {
    new BarangForm().setVisible(true);
}

private void btnTransaksiStokActionPerformed(java.awt.event.ActionEvent evt) {
    if (role.equalsIgnoreCase("admin")) {
        new TransaksiStokForm().setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Akses Ditolak: Anda tidak memiliki hak untuk membuka halaman ini.");
    }
}

private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
    new LoginForm().setVisible(true);
    this.dispose();
}
```

## Persyaratan

- **Java Development Kit (JDK)**: Versi 8 atau lebih baru.
- **Database**: Tabel `users` harus sudah diatur dengan kolom `username`, `password`, dan `role`.
- **Dependency**: Koneksi database yang sesuai sudah diatur dalam kelas `Koneksi`.

## Panduan Penggunaan

1. Jalankan aplikasi.
2. Login menggunakan `username` dan `password` yang valid.
3. Akses fitur sesuai dengan peran:
   - Admin dapat mengakses `BarangForm` dan `TransaksiStokForm`.
   - Staff hanya dapat mengakses `BarangForm`.
4. Gunakan tombol "Logout" untuk keluar dari dashboard.





## Petunjuk Penggunaan
1. **Menambah Barang**: Isi semua field, lalu klik tombol **Tambah**.
2. **Mengedit Barang**: Pilih barang dari tabel, ubah data, lalu klik tombol **Edit**.
3. **Menghapus Barang**: Pilih barang dari tabel, lalu klik tombol **Hapus**.
4. **Reset Form**: Klik tombol **Reset** untuk mengosongkan field input.
5. **Kembali ke Dashboard**: Klik tombol **Dashboard** untuk kembali ke DashboardForm.

## Dependensi
- **Database**: Pastikan tabel `barang` sudah dibuat di database dengan kolom sesuai kode.
- **Koneksi**: Gunakan kelas `Koneksi` untuk mengatur koneksi ke database.

## Catatan
- Pastikan library JDBC sudah ditambahkan ke proyek Anda.
- Uji setiap fitur untuk memastikan aplikasi berjalan sesuai dengan yang diharapkan.



## Langkah Menjalankan Aplikasi

1. **Persiapan Database**

   - Buat database MySQL baru, misalnya `db_gudang`.
   - Jalankan skrip SQL berikut untuk membuat tabel:
     ```sql
     CREATE TABLE barang (
         id_barang INT AUTO_INCREMENT PRIMARY KEY,
         kode_barang VARCHAR(20) NOT NULL,
         nama_barang VARCHAR(50) NOT NULL,
         stok INT DEFAULT 0
     );

     CREATE TABLE transaksi_stok (
         id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
         kode_barang VARCHAR(20) NOT NULL,
         jenis_transaksi ENUM('masuk', 'keluar') NOT NULL,
         jumlah INT NOT NULL
     );

     CREATE TABLE users (
         id_user INT AUTO_INCREMENT PRIMARY KEY,
         username VARCHAR(20) NOT NULL,
         password VARCHAR(50) NOT NULL,
         role ENUM('admin', 'staff') NOT NULL
     );
     ```
   - Masukkan beberapa data awal ke tabel `users` untuk login.

2. **Konfigurasi Koneksi Database**

   - Pastikan file `Koneksi.java` memiliki konfigurasi yang benar untuk koneksi ke database:
     ```java
     public class Koneksi {
         public static Connection getKoneksi() {
             try {
                 String url = "jdbc:mysql://localhost:3306/db_gudang";
                 String user = "root";
                 String pass = "";
                 return DriverManager.getConnection(url, user, pass);
             } catch (SQLException e) {
                 throw new RuntimeException("Koneksi database gagal: " + e.getMessage());
             }
         }
     }
     ```

3. **Menjalankan Aplikasi**

   - Buka proyek di NetBeans.
   - Jalankan `LoginForm.java` sebagai entry point.
   - Login sebagai `admin` atau `staff` sesuai dengan data di tabel `users`.

## Struktur Proyek

- `src`
  - `BarangForm.java` : Form untuk manajemen barang.
  - `TransaksiStokForm.java` : Form untuk transaksi stok.
  - `LoginForm.java` : Form login untuk pengguna.
  - `DashboardForm.java` : Dashboard utama setelah login.
  - `Koneksi.java` : Koneksi ke database.

## Hak Akses

- **Admin**:
  - Akses penuh ke BarangForm, TransaksiStokForm, dan fitur lainnya.
- **Staff**:
  - Hanya dapat mengakses BarangForm.

## Catatan Tambahan

- Pastikan MySQL berjalan sebelum menjalankan aplikasi.
- Periksa kredensial database di file `Koneksi.java` jika ada masalah koneksi.
- Gunakan JDK yang kompatibel dengan NetBeans untuk menghindari error pada runtime.

## Pembuat
- Nama: Muhammad Fajar Fitrianto

- NIM: 2210010748

- Proyek: Tugas Besar Aplikasi Gudang







