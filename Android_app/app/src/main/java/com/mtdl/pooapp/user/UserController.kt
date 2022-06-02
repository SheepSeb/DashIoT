package com.mtdl.pooapp.user

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.google.firebase.database.*
import com.mtdl.pooapp.MapsActivity
import com.mtdl.pooapp.board.Board
import com.mtdl.pooapp.utils.DatabaseRef
import com.mtdl.pooapp.utils.UserUtil

class UserController() : UserUtil {
    var user : User = User.userInstance()
    var userL : List<User>? = null
    var dbRef = FirebaseDatabase.getInstance().reference.database.reference
    override fun addBoard(userID : Int, b: Board?) {
        dbRef.child("Users").child("1").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        user.boardList.add(b!!)
        Board.id++
        dbRef.child("Users").child(userID.toString()).child("boardList").child(Board.id.toString()).setValue(user.boardList)
    }


    override fun deleteBoard(userID : Int, b: Board?) {
        user.boardList.remove(b!!)
        dbRef.child("Users").child(userID.toString()).child("boardList").setValue(user.boardList)
    }

    override fun changeBoardDefaultAlias(b: Board?, newName : String) {
        b!!.alias = newName
    }

    override fun addUserToDb() {
        dbRef.child("Users").child(User.id.toString()).setValue(user)
    }
}