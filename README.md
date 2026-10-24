# README - Jetpack Compose State Challenge

Aplikasi Android sederhana berbasis Jetpack Compose yang dibuat untuk memenuhi Tugas Pertemuan 4 (Interactive Compose State Challenge).

# Tema: Form Buku / Reading Tracker

# Fitur Utama
1) Material 3 UI: Desain antarmuka modern yang rapi dan konsisten.
2) Input Teks Ganda: Form untuk memasukkan Judul Buku dan Nama Penulis.
3) State Numerik (Rating): Komponen interaktif untuk mengatur rating buku (1-5) menggunakan konsep state hoisting.
4) Switch Interaktif: Tombol geser (Switch) untuk status "Selesai Dibaca".
5) Validasi Form: Tombol "Simpan Buku" otomatis dinonaktifkan jika input belum lengkap.
6) Pesan Feedback: Menampilkan kartu konfirmasi saat data berhasil disimpan.
7) Tombol Reset: Mengembalikan seluruh isian form ke nilai awal.
8) rememberSaveable: Memastikan data state aman saat terjadi perubahan konfigurasi seperti rotasi layar.

# Struktur State
State | Tipe Data | Owner | Dipakai Oleh
---|---|---|---
bookTitle | String | ReadingTrackerScreen | OutlinedTextField (Judul), Validasi
author | String | ReadingTrackerScreen | OutlinedTextField (Penulis), Validasi
rating | Int | ReadingTrackerScreen | RatingCounter (State Hoisting)
isCompleted | Boolean | ReadingTrackerScreen | Switch
showSuccessMessage | Boolean | ReadingTrackerScreen | Feedback Card

# Cara Menjalankan
1) Clone repository ini ke komputer.
2) Buka project melalui Android Studio.
3) Jalankan aplikasi pada emulator atau perangkat fisik Android (minimal versi API yang disarankan sesuai Compose).
