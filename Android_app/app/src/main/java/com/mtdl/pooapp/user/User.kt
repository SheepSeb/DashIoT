package com.mtdl.pooapp.user


import com.mtdl.pooapp.board.Board

class User {

    var firstName : String = ""
    var lastName : String = ""
    var email : String = ""

    private var boardList: ArrayList<Board> = ArrayList()

    companion object{
        var id: Int = 0
        @JvmStatic
        private var user : User = User()


        @JvmStatic
        public fun userInstance() : User {

            return user;
        }
    }



    public fun addBoard(b: Board){
        boardList.add(b);

    }
    public fun getBoards(): MutableList<Board> {
        return boardList;
    }




}