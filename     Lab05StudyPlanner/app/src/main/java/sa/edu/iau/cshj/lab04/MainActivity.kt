package sa.edu.iau.cshj.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.lab04.ui.theme.Lab04AttendanceTheme

// UI State
data class PlannerUiState(
    val sessionTitle: String = "Mobile App Programming",
    val totalTasks: Int = 3,
    val doneCount: Int = 0,
    val isStudying: Boolean = false,
    val tasks: List<String> = emptyList()
)

// Stateless Home Screen
@Composable
fun PlannerHomeScreen(
    state: PlannerUiState,
    onCheckIn: () -> Unit,
    onTaskClick: (String) -> Unit,
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
            text = state.sessionTitle,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tasks completed: ${state.doneCount} of ${state.totalTasks}"
        )

        Text(
            text = if (state.isStudying) {
                "Studying now"
            } else {
                "Not studying yet"
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Check-in button from Lab 4
        Button(
            onClick = onCheckIn,
            enabled = !state.isStudying
        ) {
            Text("Check in")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "My Study Tasks",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Task buttons
        state.tasks.forEach { task ->
            Button(
                onClick = { onTaskClick(task) }
            ) {
                Text(task)
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// Stateful Home Route
@Composable
fun PlannerHomeRoute(
    onTaskClick: (String) -> Unit = {}
) {
    var uiState by remember {
        mutableStateOf(
            PlannerUiState(
                tasks = listOf(
                    "Review Kotlin",
                    "Complete Lab 5",
                    "Study Navigation"
                )
            )
        )
    }

    PlannerHomeScreen(
        state = uiState,
        onCheckIn = {
            uiState = uiState.copy(
                doneCount = uiState.doneCount + 1,
                isStudying = true
            )
        },
        onTaskClick = onTaskClick
    )
}

// Main Activity
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab04AttendanceTheme {
                StudyPlannerApp()
            }
        }
    }
}

// Preview 1
@Preview(
    showBackground = true,
    name = "Before Check In"
)
@Composable
fun PlannerPreviewBefore() {
    Lab04AttendanceTheme {
        PlannerHomeScreen(
            state = PlannerUiState(
                tasks = listOf(
                    "Review Kotlin",
                    "Complete Lab 5",
                    "Study Navigation"
                )
            ),
            onCheckIn = {},
            onTaskClick = {}
        )
    }
}

// Preview 2
@Preview(
    showBackground = true,
    name = "After Check In"
)
@Composable
fun PlannerPreviewAfter() {
    Lab04AttendanceTheme {
        PlannerHomeScreen(
            state = PlannerUiState(
                doneCount = 1,
                isStudying = true,
                tasks = listOf(
                    "Review Kotlin",
                    "Complete Lab 5",
                    "Study Navigation"
                )
            ),
            onCheckIn = {},
            onTaskClick = {}
        )
    }
}