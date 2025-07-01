package com.sinannuhoglu.fooddeliveryapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sinannuhoglu.fooddeliveryapp.ui.components.*

@Composable
fun LoginPage(
    navController: NavController, viewModel: LoginViewModel = viewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val error by viewModel.errorMessage.collectAsState()
    val success by viewModel.loginSuccess.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(success) {
        if (success) {
            snackbarHostState.showSnackbar("Giriş başarılı!")
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    LoginPageContent(
        navController = navController,
        email = email,
        password = password,
        errorMessage = error,
        onEmailChange = { viewModel.email.value = it },
        onPasswordChange = { viewModel.password.value = it },
        onLoginClick = { viewModel.onLoginClick() },
        onRegisterClick = { navController.navigate("register") },
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun LoginPageContent(
    navController: NavController,
    email: String,
    password: String,
    errorMessage: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFFE347C), Color(0xFFF674A0))
                    )
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                MediumSpace()
                TitleSection(
                    title = "Giriş Yap",
                    subtitle = "Tekrar hoş geldiniz, sizi özledik"
                )
                ExtraLargeSpace()
                Card(
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmailTextField(email = email, onValueChange = onEmailChange)
                        SmallSpace()
                        PasswordTextField(password = password, onValueChange = onPasswordChange)

                        errorMessage?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        MediumSpace()
                        GradientButton(text = "GİRİŞ YAP", onClick = onLoginClick)
                        SmallSpace()
                        Text(
                            text = "Hesabınız yok mu? Kayıt olun",
                            color = Color.Gray,
                            modifier = Modifier.clickable(onClick = onRegisterClick)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewLoginPage() {
    val navController = rememberNavController()
    val dummySnackbarHost = remember { SnackbarHostState() }

    LoginPageContent(
        navController = navController,
        email = "test@example.com",
        password = "password",
        errorMessage = "Invalid credentials",
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onRegisterClick = {},
        snackbarHostState = dummySnackbarHost
    )
}
