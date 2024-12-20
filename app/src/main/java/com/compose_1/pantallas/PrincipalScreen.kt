package com.compose_1.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.compose_1.model.Contacto
import com.compose_1.R
import com.compose_1.model.Message
import com.compose_1.navegacion.AppScreen


@Preview
@Composable
fun PreviewWhats(){
    WhatsApp(rememberNavController())
}


@Composable
fun WhatsApp(navController: NavHostController, modifier: Modifier = Modifier) {

    val listaContactos = listOf(
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
        Contacto(stringResource(R.string.contacto1), R.drawable.userh, "Hola como estas?", "12:20", true, false, mutableListOf<Message>(
            Message("Hola que tal estas", false, "23:23"),
            Message("Adios", true, "12:21")
        )),
    )


    var contactos by rememberSaveable() { mutableStateOf(listaContactos) }

    var soloNoLeidos by rememberSaveable() { mutableStateOf(false) }

    // Filtrar los contactos
    val contactosFiltrados = if (soloNoLeidos) {
        contactos.filter { !it.leido }
    } else {
        contactos
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
    ){

        // Cabecera
        Header()

        // Linea divisora gris
        HorizontalDivider(
            color = colorResource(id = R.color.lightgrey),
            thickness = 0.1.dp
        )

        Spacer(Modifier.padding(5.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()

        ){
            // Scroll
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // Agrupaciones
                item {
                    GroupButtons(
                        // Cambia el estado de soloNoLeidos a true o false
                        onToogleSoloNoLeidos = { soloNoLeidos = it}
                    )
                }

                // Archivados
                item {
                    Archived()
                }


                // Contactos
                items(contactosFiltrados) { contacto ->
                    ContactRow(
                        nombreContacto = contacto.nombre,
                        imagenId = contacto.idImagen,
                        ultimoMsj = contacto.ultimoMsj,
                        hora = contacto.hora,
                        leido = contacto.leido,
                        enviadoPorUser = contacto.enviadoPorUsuario,
                        onClick = {

                            // Acceder pantalla contacto
                            navController.navigate(
                                route = AppScreen.ChatScreen.createRoute(
                                    contactName = contacto.nombre,
                                    lastMessage = contacto.ultimoMsj,
                                    time = contacto.hora,
                                    imageId = contacto.idImagen
                                )
                            )

                            // Cambia el estado leido
                            val newContactos = contactos.map {
                                // Si el contacto que estamos iterando es al que hacemois click, se hace una copia del contacto cambiandole el valor de leido, si no, se hace la copia excata de como estaba
                                if (it == contacto) it.copy(leido = !it.leido) else it
                            }
                            contactos = newContactos // Actualizar la lista
                        }
                    )
                }
            }

            // Linea divisora gris
            HorizontalDivider(
                color = colorResource(id = R.color.lightgrey),
                thickness = 0.1.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 66.dp)
            )

            // Barra inferior
            Footer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}


@Composable
fun Header(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
        .fillMaxWidth()
        .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,


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
            Spacer(Modifier.padding(end = 25.dp))


            Image(
                painter = painterResource(id = R.drawable.lupa),
                contentDescription = "Lupa Picture",
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(Modifier.padding(end = 15.dp))


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
fun GroupButtons(onToogleSoloNoLeidos: (Boolean) -> Unit){
    var selectedButton by rememberSaveable { mutableStateOf("All") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val buttons = listOf("All", "Unread", "Favourites", "Groups", "+")

        buttons.forEach { buttonName ->
            GroupButton(
                text = buttonName,
                selected = selectedButton == buttonName,
                onClick = {
                    selectedButton = buttonName

                    // Funcionalidad filtrar por unread
                    if (buttonName == "Unread"){
                        onToogleSoloNoLeidos(true)
                    }else{
                        onToogleSoloNoLeidos(false)
                    }

                }
            )
        }
    }
}


@Composable
fun GroupButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .height(30.dp)
            .clickable(onClick = onClick)
            .background(
                color = if (selected) colorResource(id = R.color.buttonGroupSelected) else colorResource(
                    id = R.color.buttonGroupUnselected
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 12.sp,
            color = if (selected) colorResource(id = R.color.fontColorSelectedButton)
            else colorResource(id = R.color.fontColorUnselectedButton)
        )
    }
}

/*

@Composable
fun GroupButton(text: String, selected: Boolean, onClick: () -> Unit){
    Button(
        modifier = Modifier
            .padding(end = 10.dp)
            .height(30.dp)
            .then(Modifier.requiredWidthIn(min = 45.dp)), // !!!!!!!!!!!!!!!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!  PUTA   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        contentPadding = PaddingValues(horizontal = 12.dp), // Eliminar cualquier padding extra
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) colorResource(id = R.color.buttonGroupSelected) else colorResource(id = R.color.buttonGroupUnselected),

            contentColor = if (selected) colorResource(id = R.color.fontColorSelectedButton) else colorResource(id = R.color.fontColorUnselectedButton)
        ),
    ){
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

 */


@Composable
fun Archived(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 15.dp)
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        Image(
            painter = painterResource(id = R.drawable.archived),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(24.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp, end = 15.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Archived",
                fontSize = 14.sp,
                color = colorResource(id = R.color.fontColor),
                modifier = Modifier

            )

            Text(
                text = "10",
                fontSize = 12.sp,
                color = colorResource(id = R.color.archivedNumber)
            )
        }


    }
}



@Composable
fun ContactRow(
    nombreContacto: String,
    imagenId: Int,
    ultimoMsj: String,
    hora: String,
    leido: Boolean,
    enviadoPorUser: Boolean,
    onClick: () -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 15.dp)
        .clickable(onClick = onClick),
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
            // Nombre contacto
            Text(
                text = nombreContacto,
                color = colorResource(id = R.color.white),
                fontSize = 18.sp
            )

            // Row para ticks y ultimo msj
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                // Que aparezca el tick solo si el que lo envia es el propio usuario
                if (enviadoPorUser){
                    // Ticks
                    if (leido){
                        Image(
                            painter = painterResource(id = R.drawable.tick_blue),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(16.dp)
                        )
                    } else{
                        Image(
                            painter = painterResource(id = R.drawable.tick_grey),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(16.dp)
                        )
                    }
                    Spacer(Modifier.width(4.dp))
                }



                // Ultimo msj
                Text(
                    text = ultimoMsj,
                    color = colorResource(id = R.color.grey),
                    fontSize = 14.sp
                )
            }

        }

        // Hora
        Text(
            text = hora,
            color = colorResource(id = R.color.grey),
            fontSize = 12.sp
        )
    }
}



@Composable
fun Footer(modifier: Modifier = Modifier){

    var selectedButton by rememberSaveable { mutableStateOf("Chats") }

    val pest = listOf(
        "Chats" to R.drawable.chats,
        "Updates" to R.drawable.updates,
        "Communities" to R.drawable.community,
        "Calls" to R.drawable.calls
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(colorResource(R.color.background)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){

        pest.forEach { (nombre, img) ->
            FooterButton(
                text = nombre,
                image = img,
                selected = (selectedButton == nombre),
                onClick = { selectedButton = nombre }
            )
        }
    }
}



@Composable
fun FooterButton(
    text: String,
    image: Int,
    selected: Boolean,
    onClick: () -> Unit
){
    val buttonBackground = if (selected) colorResource(id = R.color.buttonGroupSelected) else Color.Transparent

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .width(55.dp)
                .height(30.dp)
                .background(buttonBackground, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = text,
                modifier = Modifier
                    .size(24.dp)

            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            fontSize = 13.sp,
            color = colorResource(id = R.color.fontColor)
        )
    }

}



//@Preview(showBackground = true)
//@Composable
//fun WhatsAppPreview(modifier: Modifier = Modifier) {
//    WhatsApp(modifier)
//}