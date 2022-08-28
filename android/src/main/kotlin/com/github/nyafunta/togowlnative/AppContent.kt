package com.github.nyafunta.togowlnative

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.nyafunta.togowlnative.navigation.rememberCustomNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppContent(
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    val navController = rememberCustomNavController()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()

    TodoListScreen(
        systemUiController,
        navController,
        coroutineScope,
        scaffoldState,
        listState,
    )
}

@Composable
fun TodoListScreen(
    systemUiController: SystemUiController,
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    listState: LazyListState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar()
        },
        floatingActionButton = {

        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomAppBar()
        }
    ) {
        TodoListContent(
            listState = listState,
            paddingValues = it
        )
    }
}

@Composable
fun TopAppBar() {
    androidx.compose.material.TopAppBar() {

    }
}

@Composable
fun TodoListContent(
    listState: LazyListState,
    paddingValues: PaddingValues
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            contentPadding = paddingValues
        ) {
        }
    }
}

@Composable
fun TodoListItem(

) {

}

@Composable
fun FloatingActionButton(onClick: () -> Unit) {
    androidx.compose.material.FloatingActionButton(onClick = onClick) {

    }
}

@Composable
fun BottomAppBar() {
    androidx.compose.material.BottomAppBar(
        modifier = Modifier,
    ) {

    }
}