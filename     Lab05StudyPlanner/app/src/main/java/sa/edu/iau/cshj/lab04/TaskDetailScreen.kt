package sa.edu.iau.cshj.lab04

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.lab04.ui.theme.Lab04AttendanceTheme

// Task Detail UI State
data class TaskDetailUiState(
    val title: String = ""
)

// Stateless Task Detail Screen
@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Task Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = uiState.title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onShareClick
        ) {
            Text("Share")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}

// Stateful Route: Handles Share Intent
@Composable
fun TaskDetailRoute(
    title: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    TaskDetailScreen(
        uiState = TaskDetailUiState(title = title),

        onShareClick = {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(
                    Intent.EXTRA_TEXT,
                    "I'm studying: $title"
                )
                type = "text/plain"
            }

            val chooser = Intent.createChooser(
                sendIntent,
                "Share my task"
            )

            context.startActivity(chooser)
        },

        onBackClick = onBackClick
    )
}

// Preview 1
@Preview(
    showBackground = true,
    name = "Task Detail - Review Kotlin"
)
@Composable
fun TaskDetailPreviewOne() {
    Lab04AttendanceTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState(
                title = "Review Kotlin"
            ),
            onShareClick = {},
            onBackClick = {}
        )
    }
}

// Preview 2
@Preview(
    showBackground = true,
    name = "Task Detail - Complete Lab 5"
)
@Composable
fun TaskDetailPreviewTwo() {
    Lab04AttendanceTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState(
                title = "Complete Lab 5"
            ),
            onShareClick = {},
            onBackClick = {}
        )
    }
}