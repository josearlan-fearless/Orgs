package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.model.Produto
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            val bindingFormularioImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormularioImagem.formularioImagemBotaoCarregar.setOnClickListener {
                val url = bindingFormularioImagem.formularioImagemUrl.text.toString()
                bindingFormularioImagem.formularioImagemImageview.load(url)
            }

            AlertDialog.Builder(this)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = bindingFormularioImagem.formularioImagemUrl.text.toString()
                    binding.activityFormularioProdutoImagem.load(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .setView(bindingFormularioImagem.root)
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoSalvar
        botaoSalvar.setOnClickListener {
            val produtoCriado = criaProduto()
            dao.adiciona(produtoCriado)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isEmpty()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }
        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }
}