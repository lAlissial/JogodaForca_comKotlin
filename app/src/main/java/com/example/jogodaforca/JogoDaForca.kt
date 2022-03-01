package com.example.jogodaforca

import kotlin.random.Random

class JogoDaForca(nome_do_map: MutableMap<String, String>) {

    private var N:Int = 0                                                                           // quantidade de palavras (lido do Map)*
    private var palavras = mutableListOf<String>()                                                  // um array com as N palavras (lidas do Map)
    private var dicas = mutableListOf<String>()                                                     // um array com as N dicas (lidas do arquivo)
    private var palavra:String? = String()                                                          // a palavra sorteada
    private var indice:Int = 0                                                                      // posição (0 a N-1) da palavra sorteada no array(indice da palavra sorteadado jogo)
    private var acertos = 0                                                                         // total de acertos do jogo
    private var erros = 0                                                                           // total de erros do jogo
    private var penalidades = mutableListOf("perna", "perna", "braço", "braço", "tronco", "cabeça")
    private var traco_palavra:StringBuffer = StringBuffer()                                         //guarda as letrinhas descobertas na posição certinha
    private var aux_palavra:String? = String()                                                      //guarda as letras que AINDA NÃO foram descobertas da palavra sorteada
    private var guarda_letras_erradas:StringBuffer = StringBuffer()                                 //guardas letras erradas digitadas pelo usuario
    private var letras_distintas = mutableListOf<Char>()                                            //guarda as letras distintas da palavra


    init{
       this.N = nome_do_map.size

       for (i in nome_do_map){
           this.palavras.add(i.key)
           this.dicas.add(i.value)
       }
   }

    fun iniciar() {

        var sortearPalavrita = Random.nextInt(N)
        this.indice = sortearPalavrita
        this.palavra = this.palavras[this.indice].uppercase()

        for (e in 0 until this.palavra!!.length) {
            this.traco_palavra.append("_")
        }
        this.aux_palavra = "${this.palavra}"

        this.letras_distintas = this.palavra!!.toList().distinct() as MutableList<Char>
    }

    @Throws(Exception::class)
    fun adivinhou(letra: String): Boolean {
        var letra = letra.uppercase()

        var padraozito = "[A-Z]".toRegex()


        if (!padraozito.matches(letra)) {
            throw Exception("Digite UMA LETRA!")
        }
        if (this.traco_palavra.toString().contains(letra)) {
            throw Exception("Digite uma letra ainda não informada")
        }
        if (terminou()) {
            throw Exception("Jogo já terminou inicie novamente!")
        }
        if (this.guarda_letras_erradas.toString().contains(letra)) {
            throw Exception("Letra já foi escrita anteriormente")
        }
        if (this.palavra!!.contains(letra)) {
            for (k in 0 until this.aux_palavra!!.length) {
                if (letra == this.aux_palavra!!.substring(k, k + 1)) {
                    this.acertos++
                    this.traco_palavra = this.traco_palavra.replace(k, k + 1, letra)
                }
            }
            this.aux_palavra = this.aux_palavra!!.replace(letra, "~")
        } else {
            this.erros++
            this.guarda_letras_erradas.append("${letra}-")
        }
        return this.palavra!!.contains(letra)
    }

    fun terminou(): Boolean {
        return this.acertos == this.palavra!!.length || this.erros == 6
    }

    fun getPalavra(): String {
        return this.traco_palavra.toString()
    }

     fun getDica(): String {
         return this.dicas[this.indice]
     }

     fun getPenalidade():String {
        return this.penalidades[this.erros - 1]
     }

    fun getAcertos():Int {
        return this.acertos
    }

    fun getErros():Int {
        return this.erros
    }

     fun getResultado():String {
        return if (this.acertos == this.palavra!!.length) {
            "GANHOU O JOGO!"
        } else {
            "VOCÊ FOI ENFORCADO"
        }
     }

     fun getLetrasErradas(): String {
         return guarda_letras_erradas.toString()
     }

    fun getLetrasDistintas(): MutableList<Char> {
        return letras_distintas
    }

}