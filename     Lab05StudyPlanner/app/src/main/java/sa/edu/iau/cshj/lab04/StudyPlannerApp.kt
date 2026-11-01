package sa.edu.iau.cshj.lab04

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import sa.edu.iau.cshj.lab04.navigation.PlannerHome
import sa.edu.iau.cshj.lab04.navigation.TaskDetail

@Composable
fun StudyPlannerApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PlannerHome
    ) {

        // Home Screen
        composable<PlannerHome> {
            PlannerHomeRoute(
                onTaskClick = { title ->
                    navController.navigate(
                        TaskDetail(title = title)
                    )
                }
            )
        }

        // Task Detail Screen
        composable<TaskDetail> { backStackEntry ->

            val detail = backStackEntry.toRoute<TaskDetail>()

            TaskDetailRoute(
                title = detail.title,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}