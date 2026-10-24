package com.example.readingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.readingtracker.ui.theme.ReadingTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadingTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReadingTrackerScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingTrackerScreen() {
    // State Form
    var bookTitle by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var genre by rememberSaveable { mutableStateOf("Fiksi") }
    var rating by rememberSaveable { mutableStateOf(1) }
    var isCompleted by rememberSaveable { mutableStateOf(false) }
    var showSuccessMessage by rememberSaveable { mutableStateOf(false) }
    var reviewNotes by rememberSaveable { mutableStateOf("") }

    // Logika Validasi (Judul min 3 karakter & Penulis tidak boleh kosong)
    val isTitleValid = bookTitle.length >= 3 || bookTitle.isEmpty()
    val isFormValid = bookTitle.length >= 3 && author.isNotBlank()

    fun resetForm() {
        bookTitle = ""
        author = ""
        genre = "Fiksi"
        rating = 1
        isCompleted = false
        showSuccessMessage = false
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Reading Tracker - Tugas Compose") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Input Judul dengan Pesan Error Validasi
            OutlinedTextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text("Judul Buku") },
                isError = !isTitleValid,
                supportingText = {
                    if (!isTitleValid) {
                        Text(
                            "Judul minimal harus 3 karakter",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Input Penulis
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Nama Penulis") },
                modifier = Modifier.fillMaxWidth()
            )

            // Pilihan Genre
            Text("Genre Buku:", style = MaterialTheme.typography.bodyLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Fiksi", "Non-Fiksi", "Teknologi").forEach { item ->
                    FilterChip(
                        selected = genre == item,
                        onClick = { genre = item },
                        label = { Text(item) }
                    )
                }
            }

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Rating Buku:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { if (rating > 1) rating-- },
                    enabled = rating > 1
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Kurang Rating")
                }
                Text("$rating / 5", style = MaterialTheme.typography.titleMedium)
                IconButton(
                    onClick = { if (rating < 5) rating++ },
                    enabled = rating < 5
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah Rating")
                }
            }

            // Switch Selesai
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sudah Selesai Dibaca?")
                Switch(
                    checked = isCompleted,
                    onCheckedChange = { isCompleted = it }
                )
            } // <--- Tutup Row di sini!

// OutlinedTextField ditaruh DI LUAR Row
            OutlinedTextField(
                value = reviewNotes,
                onValueChange = { reviewNotes = it },
                label = { Text("Catatan / Review Singkat") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )


            // Pesan Sukses
            // Pesan Sukses (Diperbarui untuk menampilkan review jika ada)
            if (showSuccessMessage) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "Buku \"$bookTitle\" ($genre) karya $author berhasil disimpan!",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (reviewNotes.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Catatan: \"$reviewNotes\"",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}
// Footer Tugas
Text(
    text = "Jetpack Compose State Challenge • Tugas 04",
    style = MaterialTheme.typography.labelSmall,
    color = MaterialTheme.colorScheme.outline,
    modifier = Modifier.align(Alignment.CenterHorizontally)
)

Spacer(modifier = Modifier.weight(1f))