package com.mtdl.pooapp.user

import com.mtdl.pooapp.board.Board

class User {
    companion object {
        var id: Int = 0
    }
    var firstName : String = ""
    var lastName : String = ""
    var email : String = ""
    var boardList : ArrayList<Board> = ArrayList()

}