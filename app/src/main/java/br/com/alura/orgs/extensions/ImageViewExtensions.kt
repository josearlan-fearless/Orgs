package br.com.alura.orgs.extensions

import android.widget.ImageView
import br.com.alura.orgs.R
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load

fun ImageView.tentaCarregarImagem(url: String? = null, imageLoader: ImageLoader) {
    load(url, imageLoader) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}