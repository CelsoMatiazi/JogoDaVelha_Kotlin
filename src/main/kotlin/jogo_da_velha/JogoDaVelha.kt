package jogo_da_velha

class JogoDaVelha {

    var tabuleiro: MutableList<Char> = mutableListOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    var helpPosition: List<Char> = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8')

    init {
        print("POSIÇÕES")
        showTable(helpPosition)
        getPlayer()
    }

    fun showTable(tabuleiro : List<Char> = this.tabuleiro) {
        println()
        println("${tabuleiro[0]} | ${tabuleiro[1]} | ${tabuleiro[2]}")
        println("${tabuleiro[3]} | ${tabuleiro[4]} | ${tabuleiro[5]}")
        println("${tabuleiro[6]} | ${tabuleiro[7]} | ${tabuleiro[8]}")
        println()
    }

    private fun getPlayer(){

        if(!endGame() && !win()){
            println("Digite a posição da sua jogada 0-8")
            val getPosition  = readLine()?.toIntOrNull() ?: 10

            if(getPosition in 0..8 && tabuleiro[getPosition].isWhitespace()){
                tabuleiro[getPosition] = 'X'
                showTable()
                autoPlayer()
            }else{
                println("Posição invalida, tente novamente!")
                getPlayer()
            }

        }else{
            println("FIM DE JOGO!")
        }
    }

    private fun autoPlayer(){

        if(!endGame() && !win()){

            val position = (0..8).random()
            if(tabuleiro[position].isWhitespace()){
                tabuleiro[position] = 'O'

                Thread.sleep(1_000)

                showTable()
                getPlayer()

            }else{
                autoPlayer()
            }

        }else{
            println("FIM DE JOGO!")
        }
    }

    private fun win():Boolean{
        if (comparePosition(0,1,2)) return true
        if (comparePosition(0,3,6)) return true
        if (comparePosition(0,4,7)) return true
        if (comparePosition(1,4,7)) return true
        if (comparePosition(2,5,8)) return true
        if (comparePosition(2,4,6)) return true
        if (comparePosition(3,4,5)) return true
        if (comparePosition(6,7,8)) return true
        return false
    }

    private fun comparePosition(pos1: Int, pos2: Int, pos3: Int): Boolean{
        return ((tabuleiro[pos1] == tabuleiro[pos2] &&
                tabuleiro[pos2] == tabuleiro[pos3]) &&
                tabuleiro[pos1] != ' '
        )
    }


    private fun endGame(): Boolean{
        return !tabuleiro.contains(' ')
    }

}