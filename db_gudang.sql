-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 13 Jan 2025 pada 17.44
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_gudang`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `kode_barang` varchar(50) DEFAULT NULL,
  `nama_barang` varchar(100) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `stok` int(11) DEFAULT 0,
  `harga` decimal(10,2) DEFAULT NULL,
  `tanggal_dibuat` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `kode_barang`, `nama_barang`, `kategori`, `stok`, `harga`, `tanggal_dibuat`) VALUES
(1, 'BRG001', 'Laptop Dell', 'Elektronik', 13, 8500000.00, '2024-12-29 17:24:00'),
(2, 'BRG002', 'Meja Kayu', 'Furniture', 15, 1200000.00, '2024-12-29 17:24:00'),
(3, 'BRG003', 'Smartphone', 'Elektronik', 40, 5000000.00, '2024-12-29 17:24:00'),
(16, 'BRG004', 'Kursi', 'Furmniture', 12, 100000.00, '2025-01-09 15:33:05');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_stok`
--

CREATE TABLE `transaksi_stok` (
  `id_transaksi` int(11) NOT NULL,
  `kode_barang` varchar(50) NOT NULL,
  `jenis_transaksi` enum('masuk','keluar') NOT NULL,
  `jumlah` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi_stok`
--

INSERT INTO `transaksi_stok` (`id_transaksi`, `kode_barang`, `jenis_transaksi`, `jumlah`, `tanggal`) VALUES
(1, 'BRG001', 'masuk', 1, '2024-12-30 16:07:43'),
(2, 'BRG001', 'keluar', 1, '2024-12-30 16:07:49'),
(3, 'BRG001', 'keluar', 1, '2024-12-30 16:08:04'),
(4, 'BRG001', 'masuk', 1, '2024-12-30 16:08:10'),
(5, 'BRG001', 'masuk', 2, '2024-12-30 16:08:26'),
(6, 'BRG001', 'masuk', 2, '2024-12-30 16:08:47'),
(8, 'BRG001', 'masuk', 2, '2024-12-30 16:13:55'),
(9, 'BRG001', 'masuk', 2, '2024-12-30 16:16:33'),
(10, 'BRG001', 'masuk', 2, '2024-12-30 16:20:03'),
(11, 'BRG001', 'masuk', 1, '2024-12-30 16:23:31'),
(13, 'BRG003', 'masuk', 5, '2025-01-09 15:08:43'),
(15, 'BRG006', 'masuk', 3, '2025-01-09 15:23:00'),
(16, 'BRG003', 'masuk', 8, '2025-01-09 15:30:32');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` enum('admin','staff') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin123', 'admin'),
(2, 'staff1', 'staff123', 'staff');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD UNIQUE KEY `kode_barang` (`kode_barang`);

--
-- Indeks untuk tabel `transaksi_stok`
--
ALTER TABLE `transaksi_stok`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `transaksi_stok`
--
ALTER TABLE `transaksi_stok`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
