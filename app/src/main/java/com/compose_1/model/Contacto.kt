package com.compose_1.model

data class Contacto(
    val nombre: String,
    val idImagen: Int,
    val ultimoMsj: String,
    val hora: String,
    var leido: Boolean,
    val enviadoPorUsuario: Boolean,
    val historialMessage: MutableList<Message>
)
