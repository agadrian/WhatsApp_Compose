package com.compose_1.pantallas

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose_1.R
import com.compose_1.customToast
import com.compose_1.model.Message
import com.compose_1.model.messageSaver


/*
@Composable
fun ChatScreen(
    navController: NavController,
    contactName: String,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigateUp() // Vuelve a la pantalla superior (sistema de pila)
            }
        ) {
            Text(
                text = "Whatsap"
            )
        }

        Text(
            text = "Chat con $contactName"
        )
    }

}
*/



@Composable
fun ChatScreen(
    navController: NavController,
    contactName: String,
    lastMessage: String,
    imageId: Int,
    //chatHistory: List<Message>,
    modifier: Modifier = Modifier
){

    val context = LocalContext.current

    // Textfield
    var inputText by rememberSaveable { mutableStateOf("") }


    // Lista local de mensajes para este chat
    val messages2 = rememberSaveable (saver = messageSaver){
        mutableStateListOf(
            Message(lastMessage, false, "15:30"),
            //*chatHistory.toTypedArray()
        )
    }




    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.background )),
    ) {

        HeaderChat(
            contactName,
            contactImage = imageId,
            onBackClick = { navController.navigateUp() },
            onVideoCallClick = { customToast(context, "Videocalling $contactName") },
            onCallClick = { customToast(context, "Calling $contactName") },
            onMenuClick = { customToast(context, "Menu...") }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ){

            // Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.background3),
                contentDescription = "Fondo",
                modifier = Modifier
                    .fillMaxSize()
                ,
                contentScale = ContentScale.Crop // Que ocupe tod o
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, bottom = 70.dp)
            ) {
                items(messages2) { message ->
                    MessagesChat(message)
                }
            }


            FooterChat(
                modifier
                    .align(Alignment.BottomCenter),
                text = inputText,
                onTextChange = { inputText = it },
                onSendClick = {
                    if (inputText.isNotBlank()){
                        messages2.add(Message(inputText, true, "12:30"))
                        inputText = ""
                    }

                },
                onEmojiClick = {},
                onAttachClick = {},
                onCameraClick = {},
                onAudioClick = {}
            )
        }
    }

}



@Composable
fun HeaderChat(
    contactName: String,
    contactImage: Int,
    onBackClick: () -> Unit,
    onVideoCallClick: () -> Unit,
    onCallClick: () -> Unit,
    onMenuClick: () -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Row flecha, foto, nombre
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            // Flechita
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = colorResource(R.color.white)
                )
            }

            Spacer(Modifier.padding(end = 5.dp))

            // Foto perfil
            Image(
                painter = painterResource(contactImage),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(36.dp)
            )

            Spacer(Modifier.padding(end = 10.dp))

            // Nombre contacto
            Text(
                text = contactName,
                color = colorResource(R.color.fontColor),
                fontSize = 18.sp
            )
        }


        // Row videollamada, llamada, menu
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Videollamada
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onVideoCallClick,
            ) {
                Icon(
                    imageVector = Icons.Default.VideoCall, // Cambia por el ícono adecuado
                    contentDescription = "Video Call",
                    tint = colorResource(R.color.white)
                )
            }


            // Llamada
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onCallClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Phone, // Cambia por el ícono adecuado
                    contentDescription = "Call",
                    tint = colorResource(R.color.white)
                )
            }


            // Menu
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onMenuClick
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert, // Cambia por el ícono adecuado
                    contentDescription = "Menu",
                    tint = colorResource(R.color.white)
                )
            }
        }
    }
}



@Composable
fun FooterChat(
    modifier: Modifier = Modifier,
    text: String ,
    onSendClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onEmojiClick: () -> Unit,
    onAttachClick: () -> Unit,
    onCameraClick: () -> Unit,
    onAudioClick: () -> Unit
){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        // Campo redondeado emoji,texto,clip,camara
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 5.dp)
                .background(colorResource(R.color.backgroundTextFieldChat), RoundedCornerShape(32.dp))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {

            // Emoji
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onEmojiClick
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEmotions, // Cambia por el ícono adecuado
                    contentDescription = "Emoji",
                    tint = colorResource(R.color.chatItemsColor)
                )
            }

            // Barra texto (Para hcer mas pequeño su atura, usar un BasicTextrField y modificar todos los valores para poder quitar el contentpadding default)
            TextField(
                value = text,
                onValueChange = onTextChange,
                placeholder = {
                    Text(
                        text = "Message", color = colorResource(R.color.chatItemsColor)
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    //.height(40.dp)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),

            )

            // Clip
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = onAttachClick
            ) {
                Icon(
                    imageVector = Icons.Default.AttachFile, // Cambia por el ícono adecuado
                    contentDescription = "Attach",
                    tint = colorResource(R.color.chatItemsColor)
                )
            }

            Spacer(Modifier.padding(horizontal = 5.dp))


            // Quitar camara si hay texto en el texfield
            if (text.isBlank()){
                // Camara
                IconButton(
                    modifier = Modifier
                        .size(24.dp),
                    onClick = onCameraClick
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Camera",
                        tint = colorResource(R.color.chatItemsColor)
                    )
                }
            }
        }


        // Quitar si hay texto en el textfield

        if (text.isBlank()){
            // Audio
            IconButton(
                onClick = onAudioClick,
                modifier = Modifier
                    .size(48.dp)
                    .background(colorResource(R.color.greenAudio), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Mic, // Cambia por el ícono adecuado
                    contentDescription = "Audio",
                    tint = colorResource(R.color.background)
                )
            }
        }else{
            // Boton enviar
            IconButton(
                onClick = onSendClick,
                modifier = Modifier
                    .size(48.dp)
                    .background(colorResource(R.color.greenAudio), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Send, // Cambia por el ícono adecuado
                    contentDescription = "Send",
                    tint = colorResource(R.color.background)
                )
            }
        }


    }

}


@Composable
fun MessagesChat(
    message: Message
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = if (message.sentByUser) Arrangement.End else Arrangement.Start
    ){
        Box (
            modifier = Modifier
                .background(
                    color = if (message.sentByUser){
                        colorResource(R.color.messageUser)
                    }else{
                        colorResource(R.color.messageContact)
                    },
                    //CircleShape
                    RoundedCornerShape(0.dp, 16.dp, 16.dp, 16.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ){

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = message.text,
                    color = colorResource(R.color.fontColor),
                    fontSize = 16.sp
                )

                Spacer(Modifier.padding(horizontal = 5.dp))

                Text(
                    text = message.time,
                    color = colorResource(R.color.grey),
                    fontSize = 12.sp
                )
            }

        }
    }
}


@Preview
@Composable
fun PreviewChat (){
    ChatScreen(
        navController = rememberNavController(),
        "Adri",
        "hola",
        R.drawable.userh,
//        listOf<Message>(
//            Message("Adios", false, "12:21"),
//            Message("Adios", true, "12:21"),
//        )
    )
}