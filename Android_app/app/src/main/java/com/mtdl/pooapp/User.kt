package com.mtdl.pooapp

import com.mtdl.pooapp.board.Board

class User {
    private var id : Int = 0;
    private var name : String = "";
    private var emai : String = "";

    private var boards: MutableList<Board> = mutableListOf()
}