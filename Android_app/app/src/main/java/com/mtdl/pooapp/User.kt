package com.mtdl.pooapp

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseUserMetadata
import com.google.firebase.auth.MultiFactor
import com.google.firebase.auth.UserInfo
import com.mtdl.pooapp.board.Board

class User()  {
    private var id : String = "";
    private var name : String = "";
    private var emai : String = "";

    private var boards: MutableList<Board> = mutableListOf()

    private lateinit var fireBaseInstance : FirebaseUser;
    companion object{
        @JvmStatic
        private var user : User = User()


        @JvmStatic
        public fun userInstance() : User{

            return user;
        }
    }



    public fun addBoard(b: Board){
        boards.add(b);

    }
    public fun getBoards(): MutableList<Board> {
        return boards;
    }

    public fun setFirebaseInstance(f:FirebaseUser){
        fireBaseInstance = f;
    }

    public fun getFirebaseInstance():FirebaseUser{
        return fireBaseInstance;
    }



}