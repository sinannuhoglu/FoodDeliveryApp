package com.sinannuhoglu.fooddeliveryapp.ui.register

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
fun RegisterPage(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val error by viewModel.errorMessage.collectAsState()
    val success by viewModel.registerSuccess.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(success) {
        if (success) {
            snackbarHostState.showSnackbar("Kayıt başarılı! Giriş yapabilirsiniz.")
            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    RegisterPageContent(
        navController = navController,
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        errorMessage = error,
        onEmailChange = { viewModel.email.value = it },
        onPasswordChange = { viewModel.password.value = it },
        onConfirmPasswordChange = { viewModel.confirmPassword.value = it },
        onRegisterClick = { viewModel.onRegisterClick() },
        onLoginClick = { navController.navigate("login") },
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun RegisterPageContent(
    navController: NavController,
    email: String,
    password: String,
    confirmPassword: String,
    errorMessage: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
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
                    title = "Kayıt Ol",
                    subtitle = "Hesabınızı oluşturun ve başlayın"
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
                        SmallSpace()
                        PasswordTextField(
                            password = confirmPassword,
                            onValueChange = onConfirmPasswordChange
                        )

                        errorMessage?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        MediumSpace()
                        GradientButton(text = "KAYIT OL", onClick = onRegisterClick)
                        SmallSpace()
                        Text(
                            text = "Zaten bir hesabınız var mı? Giriş yap",
                            color = Color.Gray,
                            modifier = Modifier.clickable(onClick = onLoginClick)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewRegisterPage() {
    val navController = rememberNavController()
    val dummySnackbarHost = remember { SnackbarHostState() }

    RegisterPageContent(
        navController = navController,
        email = "example@mail.com",
        password = "123456",
        confirmPassword = "123456",
        errorMessage = "Passwords do not match.",
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onRegisterClick = {},
        onLoginClick = {},
        snackbarHostState = dummySnackbarHost
    )
}
