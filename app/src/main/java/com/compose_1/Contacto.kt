package com.compose_1

data class Contacto(
    val nombre: String,
    val idImagen: Int,
    val ultimoMsj: String,
    val hora: String,
    var leido: Boolean
)
