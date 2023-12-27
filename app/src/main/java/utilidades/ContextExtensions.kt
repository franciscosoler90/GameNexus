/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package utilidades

import android.content.Context
import android.content.Intent
import common.Constant
import entidades.Game

//Compartir
fun Context.shareLink(game : Game) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, Constant.appName)
        putExtra(Intent.EXTRA_TEXT, Constant.urlGames + game.slug)
    }
    startActivity(Intent.createChooser(shareIntent, null))

}