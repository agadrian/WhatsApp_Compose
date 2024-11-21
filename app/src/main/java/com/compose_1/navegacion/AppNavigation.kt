package com.compose_1.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose_1.pantallas.ChatScreen
import com.compose_1.pantallas.WhatsApp


@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navControlador = rememberNavController()

    NavHost(
        navController = navControlador,
        startDestination = AppScreen.WhatsAppScreen.route
    ) {

        // Ruta especifica de WhatsAppScreen
        composable(
            route = AppScreen.WhatsAppScreen.route
        ){
            WhatsApp(
                navControlador,
                modifier
            )
        }

        // Ruta especifica de ChatScreen
        composable (
            route = AppScreen.ChatScreen.route,
            // Configura el argumento que recibe
            arguments = listOf(
                navArgument(name = "contactName") { type = NavType.StringType },
                navArgument(name = "lastMessage") { type = NavType.StringType },
                //navArgument(name = "time") { type = NavType.StringType },
                navArgument(name = "imageId") { type = NavType.IntType },
            )
        ) {
            // Recuuperamos los argumentos para pasarselo y darle uso en la funcion ChatScreen

            val contactName = it.arguments?.getString("contactName") ?: "Nose"
            val lastMessage = it.arguments?.getString("lastMessage") ?: ""
            //val time = it.arguments?.getString("time") ?: ""
            val imageId = it.arguments?.getInt("imageId") ?: 0

            ChatScreen(
                navController = navControlador,
                contactName = contactName,
                lastMessage = lastMessage,
                //time = time,
                imageId = imageId,
                modifier = modifier
            )
        }
    }
}


//// Recuperamos el argumento para darle uso
//it.arguments?.getString("contactName")?.let { contactName ->
//    ChatScreen(
//        navController = navControlador,
//        contactName = contactName,
//        modifier = modifier
//    )
//}