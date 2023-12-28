/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.ParentPlatform

interface PlatformInterface {
    fun onPlatformClicked(platform: ParentPlatform.Platform)
}