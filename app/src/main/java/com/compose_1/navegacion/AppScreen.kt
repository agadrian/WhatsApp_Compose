package com.compose_1.navegacion

sealed class AppScreen(
    val route: String
) {
    object WhatsAppScreen: AppScreen("WhatsAppScreen")
    object ChatScreen : AppScreen("ChatScreen/{contactName}/{lastMessage}/{time}/{imageId}") {
        /**
         * Permite navegar directamente a la ruta del contacto que se le pase.
         */
        fun createRoute(
            contactName: String,
            lastMessage: String,
            time: String,
            imageId: Int
        ): String {
            return "ChatScreen/$contactName/$lastMessage/$time/$imageId"
        }
    }
}