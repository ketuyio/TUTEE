package com.example.tutee.ui.theme.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tutee.R
import com.example.tutee.navigation.HOME_URL
import com.example.tutee.navigation.TUTORS_URL
import com.example.tutee.ui.theme.TUTEETheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var selected by remember { mutableIntStateOf(0) }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(text = bottomNavItem.badges.toString())
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title)
                                }

                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            })
                    }
                }
            },


            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "menu")
                    }
                }
            },
            //Content Section
            content = @Composable{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    val mContext = LocalContext.current

                    //top up bar
                    TopAppBar(title = { Text(text = "Tutors", color = Color.Black,
                        textAlign = TextAlign.Center) },
                        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.White),
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigate(HOME_URL)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.Black
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "Notification",
                                    tint = Color.Black
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "share",
                                    tint = Color.Black
                                )
                            }

                        }
                    )
                    //end of top up bar

                    var search by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = search,
                        onValueChange = { search = it },
                        placeholder = { Text(text = "Search") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "searchbar"
                            )
                        },
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = CircleShape
                            )
                            .fillMaxWidth()
                    )

                    //end of searchbar
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                        Box(modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()) {
                            Image(painter = painterResource(id = R.drawable.math),
                                contentDescription = "Nairobi exchange",
                                modifier = Modifier
                                    .background(Color.Cyan)
                                    .clip(shape = CircleShape)
                                    .size(100.dp),
                                contentScale = ContentScale.Crop)
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Box(modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()) {
                            Image(painter = painterResource(id = R.drawable.biology),
                                contentDescription = "Nairobi exchange",
                                modifier = Modifier
                                    .background(Color.Green)
                                    .clip(shape = CircleShape)
                                    .size(100.dp),
                                contentScale = ContentScale.Crop)
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Box(modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()) {
                            Image(painter = painterResource(id = R.drawable.chemistry),
                                contentDescription = "Nairobi exchange",
                                modifier = Modifier
                                    .background(Color.Magenta)
                                    .clip(shape = CircleShape)
                                    .size(100.dp),
                                contentScale = ContentScale.Crop)
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Box(modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()) {
                            Image(painter = painterResource(id = R.drawable.physics),
                                contentDescription = "Nairobi exchange",
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .clip(shape = CircleShape)
                                    .size(100.dp),
                                contentScale = ContentScale.Crop)
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Column {
                        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

                            //card 1
                            Card(
                                modifier = Modifier
                                    .height(290.dp)
                                    .width(250.dp)
                            ) {
                                Column {
                                    Row {
                                        Image(
                                            painter = painterResource(id = R.drawable.maths),
                                            contentDescription = "Nairobi exchange",
                                            modifier = Modifier
                                                .background(Color.LightGray)
                                                .clip(shape = CircleShape)
                                                .size(80.dp),
                                            contentScale = ContentScale.Crop
                                        )

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = "Christopher Douglas",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp
                                            )
                                            Text(text = "Mathematics tutor")
                                        }


                                    }
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mathematics),
                                            contentDescription = "picture",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )

                                    }
                                    Spacer(modifier = Modifier.height(10.dp))

                                }
                            }
                            //end of card
                            Spacer(modifier = Modifier.width(20.dp))
                            //card 1
                            Card(
                                modifier = Modifier
                                    .height(290.dp)
                                    .width(250.dp)
                            ) {
                                Column {
                                    Row {
                                        Image(
                                            painter = painterResource(id = R.drawable.physics),
                                            contentDescription = "Nairobi exchange",
                                            modifier = Modifier
                                                .background(Color.LightGray)
                                                .clip(shape = CircleShape)
                                                .size(80.dp),
                                            contentScale = ContentScale.Crop
                                        )

                                        Spacer(modifier = Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = "George Kingston",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp
                                            )
                                            Text(text = "Physics tutor")
                                        }


                                    }
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(200.dp)
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.phyc),
                                            contentDescription = "picture",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )

                                    }

                                }
                            }
                            //end of card
                        }

                    }
                }
            })


    }

}

val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="home",
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=0
    ),



    BottomNavItem(
        title = "Tutors",
        route="upload",
        selectedIcon=Icons.Filled.Person,
        unselectedIcon=Icons.Outlined.Person,
        hasNews = true,
        badges=5
    ),

    BottomNavItem(
        title = "Students",
        route="tutors",
        selectedIcon=Icons.Filled.Face,
        unselectedIcon=Icons.Outlined.Face,
        hasNews = true,
        badges=1
    ),


    )



data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon : ImageVector,
    val hasNews :Boolean,
    val badges :Int
)




@Composable
@Preview(showBackground = true)
fun TutorsVideoScreenPreview(){
   TUTEETheme {
        HomeScreen(navController = rememberNavController())
    }
}