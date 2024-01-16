package com.example.ecommerce.common.extensions

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.safeDeepLinkNavigate(
    deepLinkRequest: NavDeepLinkRequest,
    animType: NavAnimType = NavAnimType.Forward,
    isSingleTop: Boolean = false,
    extras: Navigator.Extras? = null,
    onError: (()-> Unit)? = null,
    applyExtraOptions: (NavOptions.Builder.() -> Unit)? = null,

) {
    val navOptions = NavOptions
        .Builder()
        .apply {
            if(isSingleTop) {
                setLaunchSingleTop(true)
                graph.matchDeepLink(deepLinkRequest)?.destination?.id?.let {
                    popBackStack(it, true)
                }
            }
            applyExtraOptions?.invoke(this)
        }
        .build()
//    safeDeepLinkNavigate(deepLinkRequest, extras,navOptions,onError)
}


sealed class NavAnimType {
    object Present: NavAnimType()
    object  Forward: NavAnimType()
    object BackForward: NavAnimType()
    object None: NavAnimType()
}

