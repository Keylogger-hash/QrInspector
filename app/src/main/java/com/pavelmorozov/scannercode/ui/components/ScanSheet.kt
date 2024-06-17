package com.pavelmorozov.scannercode.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pavelmorozov.scannercode.ui.theme.DarkGrey
import com.pavelmorozov.scannercode.ui.theme.LightYellow
import com.pavelmorozov.scannercode.model.Scan
import com.pavelmorozov.scannercode.model.ScanType
import com.pavelmorozov.scannercode.R
import com.pavelmorozov.scannercode.http.makeHttpPostRequest
import com.pavelmorozov.scannercode.http.parseJsonData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException

@Composable
fun ScanSheet(
    scan: Scan,
    onShareClicked: () -> Unit,
    onCopyClicked: () -> Unit,
    onWebClicked: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var url by remember { mutableStateOf(TextFieldValue("https://667067fb0900b5f8724a886e.mockapi.io/api/v1/scantext")) }
    var dataItems by remember { mutableStateOf<List<JSONObject>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(12.dp)
                .width(50.dp)
                .padding(top = 5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkGrey,
                contentColor = LightYellow
            )
        ) {}
        Text(
            text = stringResource(id = scan.scanFormatId),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        if (scan.displayValue.isNotBlank()) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = scan.displayValue,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Divider()
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ExtendedFloatingActionButton(
                text = { Text("Отправить данные") },
                onClick = {
                    GlobalScope.launch {
                        isLoading = true
                        error = ""
                        try {
                            val response = makeHttpPostRequest(url.text,scan.displayValue.toString())
                        } catch (e: IOException) {
                            error = "Ошибка: ${e.message}"
                        } finally {
                            isLoading = false
                        }
                    }
                    navController.navigate("StartPreview")
                },
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}