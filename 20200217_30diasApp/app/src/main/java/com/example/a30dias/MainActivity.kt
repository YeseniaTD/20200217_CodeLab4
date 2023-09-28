package com.example.a30dias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30dias.data.DataSource
import com.example.a30dias.model.Dias
import com.example.a30dias.ui.theme._30DiasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DiasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiasApp()
                }
            }
        }
    }
}

@Composable
fun DiasCard(dias: Dias, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(modifier = modifier.clickable { isExpanded = !isExpanded }) {
        Column (modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )){
            Text(
                text = LocalContext.current.getString(dias.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            Image(
                painter = painterResource(dias.imageResourceId),
                contentDescription = stringResource(dias.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            if (isExpanded) {
                Text(
                    text = LocalContext.current.getString(dias.stringTextId),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
@Composable
fun DiasList(diasList: List<Dias>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(diasList) { dias ->
            DiasCard(
                dias = dias,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun DiaDescripcion(
    @StringRes diaText: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(diaText),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@Composable
fun DiasApp(modifier: Modifier = Modifier) {
    DiasList(
        diasList = DataSource().loadDias(),
    )


}

@Preview(showBackground = true)
@Composable
private fun DiasCardPreview() {
    DiasCard(Dias(R.string.titulo1, R.drawable.imagen1, R.string.dia1))
}