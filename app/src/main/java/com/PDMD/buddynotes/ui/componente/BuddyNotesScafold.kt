package com.PDMD.buddynotes.ui.componente // o ui.navigation / ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.PDMD.buddynotes.R
import com.PDMD.buddynotes.ui.page.PantallaHistorico
import com.PDMD.buddynotes.ui.page.PantallaInicio
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuddyNotesScaffold(
    tituloTopBar: String,
    rutaActual: String?,
    mostrarBotonVolver: Boolean,
    onVolverClick: () -> Unit,
    onInicioClick: () -> Unit,
    onHistoricoClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = tituloTopBar,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                actions={
                    Box(){
                        Image(
                            painter = painterResource(R.drawable.buddy_notes_icon_app),
                            modifier = Modifier.size(34.dp),
                            contentDescription=null
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                if (mostrarBotonVolver){
                    NavigationBarItem(
                        selected = false,
                        onClick = onVolverClick,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = stringResource(R.string.backIcon_description),
                            )
                        },
                        label = {
                            Text(
                                text = "Volver",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    )
                }else {
                    NavigationBarItem(
                        selected = rutaActual == "inicio",
                        onClick = onInicioClick,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home),
                                contentDescription = stringResource(R.string.homeIcon_BuddyNotesScafold),
                            )
                        },
                        label = {
                            Text(
                                text = "Inicio",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = rutaActual == "historico",
                        onClick = onHistoricoClick,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_history),
                                contentDescription = stringResource(R.string.libros_aplilados_BuddyNotesScafold),
                            )
                        },
                        label = {
                            Text(
                                text = "Historico",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    )
                }

            }
        },
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun BuddyNotesScaffoldPreview() {
    BuddyNotesTheme {
        BuddyNotesScaffold(
            tituloTopBar = stringResource(R.string.app_name),
            rutaActual = "historico",
            mostrarBotonVolver = false,
            onVolverClick = {},
            onInicioClick = {},
            onHistoricoClick = {}
        ) { innerPadding ->
            PantallaHistorico({}, modifier = Modifier.padding(innerPadding))
        }

    }
}