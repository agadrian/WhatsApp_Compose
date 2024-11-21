package com.compose_1.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

data class Message(
    val text: String,
    val sentByUser: Boolean,
    val time: String
)

// Crea el Saver para Message
val messageSaver = listSaver<SnapshotStateList<Message>, List<List<Any>>>(
    save = { list ->
        // Convertir el mensaje a una lista de sus propiedades
        listOf(list.map { listOf(it.text, it.sentByUser, it.time) })
    },
    restore = { savedList ->
        // Restaurar el mensaje desde la lista
        savedList.map {
            Message(it[0] as String, it[1] as Boolean, it[2] as String)
        }.toMutableStateList()
    }
)