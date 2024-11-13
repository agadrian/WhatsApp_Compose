package com.compose_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose_1.ui.theme.Compose_1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WhatsApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun WhatsApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ){

        val contactos = listOf(
            Contacto(stringResource(R.string.contacto1), R.drawable.user),
            Contacto(stringResource(R.string.contacto2), R.drawable.user),
            Contacto(stringResource(R.string.contacto3), R.drawable.user),
            Contacto(stringResource(R.string.contacto4), R.drawable.user),
            Contacto(stringResource(R.string.contacto5), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
            Contacto(stringResource(R.string.contacto6), R.drawable.user),
        )

        // Cabecera
        Header()

        HorizontalDivider(
            color = colorResource(id = R.color.lightgrey),
            thickness = 0.1.dp
        )

        Spacer(Modifier.padding(5.dp))

        // Listado contactos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(contactos) { contacto ->
                ContactRow(nombreContacto = contacto.nombre, imagenId = contacto.idImagen)
            }
        }
    }
}



@Composable
fun Header(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp, vertical = 10.dp),

        verticalAlignment = Alignment.CenterVertically

    ){
        Text(
            text = stringResource(R.string.inicio),
            color = colorResource(id = R.color.white),
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Row(modifier = Modifier
            .weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.camara),
                contentDescription = "Camera Picture",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(Modifier.padding(end = 12.dp))


            Image(
                painter = painterResource(id = R.drawable.lupa),
                contentDescription = "Lupa Picture",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(Modifier.padding(end = 10.dp))


            Image(
                painter = painterResource(id = R.drawable.dots),
                contentDescription = "Dots Picture",
                modifier = Modifier
                    .size(24.dp)
            )

        }
    }
}



@Composable
fun ContactRow(nombreContacto: String, imagenId: Int){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 15.dp ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(48.dp)
        )

        Spacer(Modifier.width(10.dp))

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = nombreContacto,
                color = colorResource(id = R.color.white)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WhatsAppPreview(modifier: Modifier = Modifier) {
    WhatsApp(modifier)
}