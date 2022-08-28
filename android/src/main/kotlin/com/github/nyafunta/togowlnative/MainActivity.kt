package com.github.nyafunta.togowlnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.nyafunta.togowlnative.domain.infra.auth.TodoistAuthenticationRepository
import com.github.nyafunta.togowlnative.domain.infra.auth.TogglAuthenticationRepository
import com.github.nyafunta.togowlnative.infra.api.TodoistApi
import com.github.nyafunta.togowlnative.infra.api.TogglApi
import com.github.nyafunta.togowlnative.infra.api.initLogger
import com.github.nyafunta.togowlnative.model.auth.TodoistAuthToken
import com.github.nyafunta.togowlnative.model.auth.TogglAuthToken
import com.github.nyafunta.togowlnative.ui.theme.TogowlNativeTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val todoistApi: TodoistApi by inject()
    private val togglApi: TogglApi by inject()
    private val todoistTokenRepository: TodoistAuthenticationRepository by inject()
    private val togglTokenRepository: TogglAuthenticationRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLogger()
        setContent {
            TogowlNativeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Greeting()
                }
            }
        }
    }

    @Composable
    fun Greeting() {
        val todoistInput = rememberSaveable { mutableStateOf("") }
        val togglInput = rememberSaveable { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                androidx.compose.material.TopAppBar(
                    title = { Text("TopAppBar") },
                )
            },
            floatingActionButton = {
                androidx.compose.material.FloatingActionButton(onClick = {}) {
                    Text("X")
                }
            },
            bottomBar = { androidx.compose.material.BottomAppBar { Text("BottomAppBar") } },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = todoistInput.value,
                        onValueChange = { todoistInput.value = it },
                        modifier = Modifier.weight(0.6F),
                        label = { Text("Todoist Api Token") },
                        placeholder = { Text("Enter Todoist Api Token") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        modifier = Modifier.weight(0.4F),
                        onClick = {
                            scope.launch {
                                todoistTokenRepository.save(
                                    TodoistAuthToken(todoistInput.value)
                                )
                            }
                        }
                    ) {
                        Text(text = "Save Todoist Api")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = togglInput.value,
                        onValueChange = { togglInput.value = it },
                        modifier = Modifier.weight(0.6F),
                        label = { Text("Toggl Api Token") },
                        placeholder = { Text("Enter Toggl Api Token") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        modifier = Modifier.weight(0.4F),
                        onClick = {
                            scope.launch {
                                togglTokenRepository.save(
                                    TogglAuthToken(togglInput.value)
                                )
                            }
                        }
                    ) {
                        Text(text = "Save Toggl Api")
                    }
                }

                Button(
                    onClick = {
                        todoistApi.runCatching {
//                            projects()
//                                .onEach { projects ->
//                                    projects.forEach {
//                                        android.util.Log.d(
//                                            "nyafunta",
//                                            "Todoist Project:${it.name}"
//                                        )
//                                    }
//                                }
//                                .onCompletion {
//                                    if (it != null) {
//                                        android.util.Log.d("nyafunta", it.stackTraceToString())
//                                    }
//                                }
//                                .launchIn(scope)
//                            tasks()
//                                .onEach { tasks ->
//                                    tasks.forEach { task ->
//                                        android.util.Log.d(
//                                            "nyafunta",
//                                            "Todoist Task:$task"
//                                        )
//                                    }
//                                }
//                                .onCompletion {
//                                    if (it != null) {
//                                        android.util.Log.d("nyafunta", it.stackTraceToString())
//                                    }
//                                }
//                                .launchIn(scope)
                            sync()
                                .onEach { response ->
                                    android.util.Log.d(
                                        "nyafunta",
                                        "Todoist sync:$response"
                                    )
                                }
                                .onCompletion {
                                    if (it != null) {
                                        android.util.Log.d("nyafunta", it.stackTraceToString())
                                    }
                                }
                                .launchIn(scope)
                        }
                    }
                ) {
                    Text(text = "Test Todoist Api")
                }

                Button(
                    onClick = {
                        scope.launch {
                            togglApi.workspaces()
                                .onEach { projects ->
                                    projects.forEach { project ->
                                        android.util.Log.d(
                                            "nyafunta",
                                            "Toggl project:$project"
                                        )
                                    }
                                }
                                .onCompletion {
                                    if (it != null) {
                                        android.util.Log.d("nyafunta", it.stackTraceToString())
                                    }
                                }
                                .launchIn(scope)
                        }
                    }
                ) {
                    Text(text = "Test Toggl Api")
                }
            }
        }
    }

    @Preview(
        showBackground = true,
        showSystemUi = true
    )
    @Composable
    fun DefaultPreview() {
        TogowlNativeTheme {
            Greeting()
        }
    }

    @Composable
    private fun SetupNavigation(navController: NavHostController) {
        navController.navigate(route = "loggingIn")

        NavHost(navController = navController, startDestination = "root") {
            composable("root") {
                // rootの画面, loadingのみで, 必要なtokenがあればloggingIn graphへ移動する. なければlogIn graphへ移動する.
            }
            loggingInGraph(navController = navController)
            logInGraph(navController = navController)
        }
    }

    private fun NavGraphBuilder.loggingInGraph(navController: NavHostController) {
        navigation(startDestination = "taskList", route = "loggingIn") {
            composable("taskList") {
            }
        }
    }

    private fun NavGraphBuilder.logInGraph(navController: NavHostController) {
        navigation(startDestination = "logIn", route = "logIn") {
            composable("login") {
            }
        }
    }
}