package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class FormularioImagemDialog(private val context: Context) {

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    fun mostra() {
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))
        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentaCarregarImagem(url, imageLoader)
        }

        AlertDialog.Builder(context)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                Log.i("FormularioImagemDialog", "mostra: $url")
//                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url, imageLoader)
            }
            .setNegativeButton("Cancelar") { _, _ -> }
            .setView(binding.root)
            .show()
    }
}