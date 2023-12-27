/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package utilidades

import android.content.Context
import android.content.Intent
import common.Constant
import viewmodels.GameInfoViewModel

//Compartir
fun Context.shareLink(gameInfoViewModel : GameInfoViewModel) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, Constant.appName)
        putExtra(Intent.EXTRA_TEXT, Constant.urlGames + gameInfoViewModel.game.slug)
    }
    startActivity(Intent.createChooser(shareIntent, null))

}