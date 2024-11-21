package com.compose_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.compose_1.model.Message
import com.compose_1.navegacion.AppNavigation
import com.compose_1.pantallas.ChatScreen
import com.compose_1.pantallas.WhatsApp
import com.compose_1.ui.theme.Compose_1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


fun customToast(context: Context, msg: String){
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

// TODO: Arreglar que el leido no se guarde cuando cambia de pantalla
// TODO: Poder escribir en el textfield y que se sume a la listga de mensajes enviados


