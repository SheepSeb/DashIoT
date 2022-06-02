package com.mtdl.pooapp.user


import com.mtdl.pooapp.board.Board

class User {

    var firstName : String = ""
    var lastName : String = ""
    var email : String = ""

    var boardList: ArrayList<Board> = ArrayList()


    companion object{
        var id: Int = 0
        private var currentLocation : Triple<Double, Double, Double> = Triple(20.0,20.0,20.0);
        @JvmStatic
        private var user : User = User()


        @JvmStatic
        public fun userInstance() : User {

            return user;
        }


        @JvmStatic
        public fun getUserId() : Int {
            return id;
        }

        @JvmStatic
        public fun setCurentLocation(lat : Double, lng : Double)  {
            currentLocation = Triple(lat, lng, 20.0);
        }

        @JvmStatic
        public fun getCurentLocation() : Triple<Double, Double, Double> {
           return currentLocation
        }
    }



    public fun addBoard(b: Board){
        boardList.add(b);

    }
    public fun getBoards(): MutableList<Board> {
        return boardList;
    }




}