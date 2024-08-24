package com.example.note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.ui.theme.NoteDatabase
import com.example.note.ui.theme.NoteTheme
import com.example.note.ui.theme.entity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Screen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun card(noteContent : entity, onSave: (String) -> Unit, onDelete : (entity) -> Unit){

    val context = LocalContext.current
    var text by remember { mutableStateOf(noteContent.text) }
    var edit by remember { mutableStateOf(true) }
    var done by remember { mutableStateOf(false) }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.End)
        ) {
            IconButton(onClick = {
                onDelete(noteContent)
                 },
                modifier = Modifier) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Button")
            }
            IconButton(onClick = { edit = true
                                 done = false},
                modifier = Modifier) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Text")
            }
            IconButton(onClick = {  edit = false
                if (!done){
                    Toast.makeText(context, "Saved!!", Toast.LENGTH_SHORT).show()
                }
                done = true },
                modifier = Modifier) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Done Text")
            }




            //This one line saves the text in database
            onSave(text)






            IconButton(
                onClick = {
                    if (edit){
                                text = ""
                    } },
                modifier = Modifier) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear all")
            }
        }
        Box(modifier = Modifier){

            if(edit){
                TextField(value = text,
                    onValueChange = { newText -> text = newText },
                    placeholder = { Text(text = "Write your Note",
                        modifier = Modifier)},
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )
                )
            }
            else{
                Box (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ){
                    Text(text = text,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                }
            }


        }
    }
}



@Composable
fun nonote(){
    Box(modifier = Modifier
        .fillMaxSize()){
        Card(
            modifier = Modifier
                .align(alignment = Alignment.Center),

        ) {
            Text(text = " No Notes \n Add New",
                fontSize = 45.sp,
                style = TextStyle(
                    lineHeight = 45.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(){

    Scaffold (
        topBar = {

                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_making_notes_80),
                                contentDescription = "notes app icon",
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(50.dp)
                            )
                            Text(
                                text = "Notes",
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                            )
                        }
                    },
                    modifier = Modifier.height(45.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                    )

        },
        content = {padding ->

            val context = LocalContext.current
            val mdb = remember { NoteDatabase.getInstance(context) }
            val notesList by mdb.NoteDAO().getNotes().collectAsState(initial = emptyList())





            if (notesList.isEmpty()) {
                nonote()
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        items(notesList) { note ->
                            card(
                                noteContent = note,

                                onSave = { updatedContent ->
                                    CoroutineScope(Dispatchers.IO).launch {
                                        mdb.NoteDAO().NewNote(note.copy(text = updatedContent))
                                    }
                                }, onDelete = { noteToDelete ->
                                    CoroutineScope(Dispatchers.IO).launch {
                                        mdb.NoteDAO().Deleteid(noteToDelete.id)
                                    }
                                })
                        }
                    }


                }
            }
            Box(modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(end = 15.dp, bottom = 15.dp)) {
                IconButton(
                    onClick = {
                        val newNote = entity(text = "", id = 0)
                        CoroutineScope(Dispatchers.IO).launch {
                            mdb.NoteDAO().NewNote(newNote)
                        }

                    },
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(end = 15.dp, bottom = 15.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add new note",
                        Modifier.size(65.dp)
                    )
                }
            }
        }
    )
}


@Composable
fun SplashScreen(onTimeout: () -> Unit){

    LaunchedEffect(Unit){
        kotlinx.coroutines.delay(1500)
        onTimeout()
    }

    Box(modifier = Modifier
        .fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.icons8_making_notes_80),
            contentDescription = "Splash Screen image",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .size(150.dp))
        Text(text = "Notes",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(top = 205.dp),
            fontSize = 45.sp)
    }


}


@Composable
fun Final(){
    var show by remember { mutableStateOf(true) }

    if(show){
        SplashScreen(onTimeout = { show = false})
    }
    else{
        Screen()
    }

}



@Preview
@Composable
fun pre(){
    Final()


}