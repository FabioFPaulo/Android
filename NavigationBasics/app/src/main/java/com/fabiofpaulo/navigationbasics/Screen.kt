package com.fabiofpaulo.navigationbasics

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Detail: Screen("detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}"){
        fun passNameAndId(id: Int, name: String): String{
            return this.route
                .replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id.toString())
                .replace(oldValue = "{$DETAIL_ARGUMENT_KEY2}", newValue = name)
        }
    }

}