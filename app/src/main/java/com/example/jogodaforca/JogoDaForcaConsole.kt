package com.example.jogodaforca

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var dicionario_com_palavras = mutableMapOf("OBJETO" to "é uma instância da classe", "BOLSA" to "guarda objetos pessoais","COLA" to "gruda as coisas","SALA" to "local para ver TV")
    var jogo: JogoDaForca? = null
    try {
        jogo = JogoDaForca(dicionario_com_palavras)
    } catch (e: Exception) {
        println(e.message)
        exitProcess(0)
    }

    jogo!!.iniciar()
    var letra: String
    println("==================================")
    println("           JOGO DA FORCA          ")
    println("==================================")
    do {
        println("\nPalavra = ${jogo.getPalavra()}")
        println("Quantidade de letras = ${jogo.getPalavra().length}")
        println("Quantidade de letras distintas = ${jogo.getLetrasDistintas().size}")
        println("Dica = " + jogo.getDica())
        println("----------------------------------")
        print("Digite uma letra da palavra: ")
        letra = readLine().toString()
        try {
            println()
            if (jogo.adivinhou(letra)) {
                println("Voce acertou a letra " + letra.uppercase())
                println("Total de acertos = " + jogo.getAcertos())
            } else {
                println("Voce errou a letra $letra")
                println("Total de erros = " + jogo.getErros())
                println("Erros já digitados = " + jogo.getLetrasErradas())
                println("Penalidade: " + jogo.getPenalidade())
            }
        } catch (e: Exception) {
            println(e.message)
        }
    } while (!jogo.terminou())
    println("\nPalavra = ${jogo.getPalavra()}")

    println("----------------------------------")
    println("Resultado final = " + jogo.getResultado())
    println("==================================")
}