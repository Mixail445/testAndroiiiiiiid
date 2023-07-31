package com.example.testandoid.ui.main.data

data class Critic(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)