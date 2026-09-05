package com.example.cleanarchtemplate

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

object NavigationKeys {
    @Serializable data object Route {
        @Serializable data object LOGIN : NavKey
        @Serializable data object HOME : NavKey
    }
}
