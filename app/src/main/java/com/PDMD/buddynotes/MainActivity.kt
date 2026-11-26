package com.PDMD.buddynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.PDMD.buddynotes.ui.componente.BuddyNotesScaffold
import com.PDMD.buddynotes.ui.page.PantallaDetalleHistorico
import com.PDMD.buddynotes.ui.page.PantallaHistorico
import com.PDMD.buddynotes.ui.page.PantallaInicio
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuddyNotesTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                val tituloTopBar = stringResource(R.string.app_name)
                BuddyNotesScaffold(
                    tituloTopBar = tituloTopBar,
                    rutaActual = currentRoute,
                    mostrarBotonVolver = false,
                    onVolverClick = {},
                    onInicioClick = {navController.navigate("inicio")},
                    onHistoricoClick = {navController.navigate("historico")}
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "inicio",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        composable("inicio") {
                            PantallaInicio()
                        }

                        composable("historico") {
                            PantallaHistorico(
                                onListaClick = { idDia ->
                                    navController.navigate("detalleHistorico/$idDia")
                                }
                            )
                        }

                        composable(
                            route = "detalleHistorico/{idDia}",
                            arguments = listOf(
                                navArgument("idDia") { type = NavType.IntType }
                            )
                        ) { entry ->
                            val idDia = entry.arguments?.getInt("idDia") ?: return@composable
                            PantallaDetalleHistorico(idDia = idDia)
                        }
                    }
                }
            }
        }
    }
}
