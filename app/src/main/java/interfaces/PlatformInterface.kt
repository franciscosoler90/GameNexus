/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.ParentPlatform

interface PlatformInterface {
    fun onClickPlatform(platform: ParentPlatform.Platform)
}