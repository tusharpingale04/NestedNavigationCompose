package com.tushar.moviescompose.utils

import java.util.Collections

data class ImmutableList<T> private constructor(
    val list: List<T>
): List<T> by list {
    companion object {
        operator fun <T> invoke(list: List<T>): ImmutableList<T> {
            return ImmutableList(list = Collections.unmodifiableList(list))
        }
    }
}