package de.rogallab.mobile.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.rogallab.mobile.shared.domain.utilities.Alog
import de.rogallab.mobile.ui.people.create_detail.PersonViewModel
import de.rogallab.mobile.ui.people.create_detail.comp.PersonAdapter
import de.rogallab.mobile.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      // Enable edge-to-edge display for the activity.
      enableEdgeToEdge()

      // Set the content of the activity to a Composable function.
      setContent {

         // New-/DetailScreen
         val personViewModel = koinViewModel<PersonViewModel> {
            //parametersOf(null) // New Person
            parametersOf("01000000-0000-0000-0000-000000000000")
         }


         AppTheme {

            Scaffold(
               modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

               PersonAdapter(
                  viewModel = personViewModel,
                  modifier = Modifier
                     .padding(innerPadding)
                     .padding(horizontal = 8.dp)

               )
            }
         }
      }
   }

   companion object {
      private const val TAG = "<-MainActivity"
   }
}