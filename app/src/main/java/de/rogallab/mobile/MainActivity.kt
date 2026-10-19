package de.rogallab.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import de.rogallab.mobile.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      // Enable edge-to-edge display for the activity.
      enableEdgeToEdge()

      // Set the content of the activity to a Composable function.
      setContent {

         AppTheme {

            Scaffold(
               modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

            }
         }
      }
   }

   companion object {
      private const val TAG = "<-MainActivity"
   }
}