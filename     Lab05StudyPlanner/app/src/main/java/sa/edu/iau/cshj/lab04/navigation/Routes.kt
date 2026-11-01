package sa.edu.iau.cshj.lab04.navigation

import kotlinx.serialization.Serializable

@Serializable
object PlannerHome

@Serializable
data class TaskDetail(val title: String)