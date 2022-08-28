package com.github.nyafunta.togowlnative.infra.api.model.toggl

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: Long,
    val name: String,
    val profile: Int,
    val premium: Boolean,
    val admin: Boolean,
    @SerialName("default_hourly_rate")
    val defaultHourlyRate: Int,
    @SerialName("default_currency")
    val defaultCurrency: String,
    @SerialName("only_admins_may_create_projects")
    val onlyAdminsMayCreateProjects: Boolean,
    @SerialName("only_admins_see_billable_rates")
    val onlyAdminsSeeBillableRates: Boolean,
    @SerialName("only_admins_see_team_dashboard")
    val onlyAdminsSeeTeamDashboard: Boolean,
    @SerialName("projects_billable_by_default")
    val projectsBillableByDefault: Boolean,
    val rounding: Int,
    @SerialName("rounding_minutes")
    val roundingMinutes: Int,
    @SerialName("api_token")
    val apiToken: String,
    val at: String, // Date
    @SerialName("ical_enabled")
    val iCalEnabled: Boolean
)

typealias Projects = List<Project>