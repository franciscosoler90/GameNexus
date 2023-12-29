/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import interfaces.NavigationInterface
import ui.components.search.SearchView
import ui.theme.AppTheme

class SearchActivity : ComponentActivity(), NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchView(this@SearchActivity)
                }
            }
        }
    }

    override fun home() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun search() {
        //Nada
    }

    override fun favorite() {
        //Nada
    }
}