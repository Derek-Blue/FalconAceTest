package com.farris.falconacetest.repository

enum class FilterType(val title: String) {

    Divider("divider"),

    News("news");

    companion object {
        fun fromType(type: String): FilterType? {
            return values().find { it.title == type }
        }
    }
}