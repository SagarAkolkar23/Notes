package com.example.note

import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.ui.theme.NoteTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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

                        card()

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun card(){
    var text by remember { mutableStateOf("") }
    var edit by remember { mutableStateOf(true) }
    var done by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
            IconButton(onClick = { /*TODO*/ },
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

@Preview
@Composable
fun pre(){
    card()
}


