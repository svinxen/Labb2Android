package com.example.labb_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labb_2.ui.theme.DarkTeal
import com.example.labb_2.ui.theme.Labb2Theme
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.style.TextAlign

import com.example.labb_2.ui.theme.LightTeal



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labb2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@Preview
@Composable
fun MyApp() {


    val buttonColor = Color.White
    val navController = rememberNavController()

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = DarkTeal
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {Home(navController)}
            composable("login") {Login(navController, null, null)}
            composable("about") {About(navController)}
            composable("end/{username}") {navBackStackEntry ->
                val username = navBackStackEntry.arguments?.getString("username") ?: ""
                End(navController, username)
            }

            composable (
                "loginPage/{username}/{password}",
                arguments = listOf (
                    navArgument("username") {type = NavType.StringType},
                    navArgument("password") {type = NavType.StringType}
                )
            ) {navBackStackEntry ->
                val username = navBackStackEntry.arguments?.getString("username") ?: ""
                val password = navBackStackEntry.arguments?.getString("password") ?: ""
                Login(navController, username, password)

            }
        }
    }
}

@Composable
fun Home(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Hello!",
                fontSize = 30.sp,
                color = LightTeal,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(50.dp)

            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()

        ) {

            Text(text = "This project is following my ongoing theme for the love of my dog Atlas. He's the best dog I know, and also the coolest little guy. You would be lucky to meet him!",
                fontSize = 20.sp,
                color = LightTeal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                )
    }

        Button(onClick = {navController.navigate("about")},
            colors = ButtonDefaults.buttonColors(LightTeal),
            modifier = Modifier
                .padding(60.dp)) {
            Text(text = "About")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.atlas_berg),
            contentDescription = "atlasifjäll",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(bottom = 10.dp)
        )
    }
}


@Composable
fun Login(navController: NavController, username: String?, password: String?) {
    var userNameValue by remember {mutableStateOf(username ?: "")}
    var passwordValue by remember {mutableStateOf(password ?: "")}


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Login",
            fontSize = 24.sp,
            color = LightTeal,
            modifier = Modifier
                .padding(16.dp)
        )

        TextField(
            value = userNameValue,
            onValueChange = { userNameValue = it },
            label = { Text("Username") }
        )
        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text("Password  **123**") }
        )

                Image(
            painter = painterResource(id = R.drawable.atlas_hatt),
            contentDescription = "tacoatlas",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 50.dp)
        )

        Text(text = "I hope you remembered my name!",
            fontSize = 14.sp,
            color = LightTeal,
            textAlign = TextAlign.Center,
        )

        Button(onClick = {
            if (userNameValue in Atlas && passwordValue == "123") {
                navController.navigate("end/$userNameValue")
            }
        },
            modifier = Modifier.padding(32.dp),
            colors = ButtonDefaults.buttonColors(LightTeal)

        ) {
            Text(text="Login")
        }
    }
}


@Composable
fun About(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightTeal) {

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "About this App",
            fontSize = 24.sp,
            color = DarkTeal,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(50.dp)
        )

        Text(text = "In the next step you will be asked to login with a username, the username will be the name of the cool little dog who climbed Kebnekaise this summer. I hope you remember his name...",
            fontSize = 18.sp,
            color = DarkTeal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(36.dp)
        )


        Button(onClick = {navController.navigate("login")},
            colors = ButtonDefaults.buttonColors(DarkTeal)) {
            Text(text = "To Login")

        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.atlas_topp),
            contentDescription = "kebnekaisetopp",
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )
    }
}

@Composable
fun End(navController: NavController, username: String) {

    Column(
        horizontalAlignment = Alignment.Start

    ) {

        Text(text = "You remembered $username !",
            fontSize = 30.sp,
            color = LightTeal,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.atlas_helikopter),
            contentDescription = "helikopterdoggo",
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )

        Text(text = "Did you know that he climbed Kebnekaise 8 months after a vet said he was suffering and I should put him down? He clearly doesn't and I want to give him the best years possible. I think anyone would. We also took a helicopter ride this summer, he did so good! He is the best dog ever and WE thank you for checking out this little project!",
            fontSize = 14.sp,
            color = LightTeal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                //.align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Button(onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(LightTeal),
            /*modifier = Modifier
                .padding(24.dp)*/) {
            Text(text = "Back to start")
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}

val Atlas = arrayOf(
    "Atlas", "atlas"
)