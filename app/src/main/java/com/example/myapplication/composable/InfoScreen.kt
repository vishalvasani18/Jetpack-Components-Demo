package com.example.myapplication.composable

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun InfoScreen() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient() // open links inside WebView
                settings.javaScriptEnabled = true

                // Disable cache to fix ERR_CACHE_MISS
                settings.cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE
                clearCache(true)
                clearHistory()

                loadUrl("https://jsonplaceholder.typicode.com/")
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}