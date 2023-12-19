/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package utilidades

import android.content.Context
import android.content.Intent
import android.widget.Toast
import common.Constant
import viewmodels.GameInfoViewModel

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.shareLink(game : GameInfoViewModel) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, Constant.appName)
        putExtra(Intent.EXTRA_TEXT, Constant.urlGames + game.slug)
    }
    startActivity(Intent.createChooser(shareIntent, null))

}