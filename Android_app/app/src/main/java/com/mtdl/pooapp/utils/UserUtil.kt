package com.mtdl.pooapp.utils

import com.mtdl.pooapp.board.Board
import com.mtdl.pooapp.user.User

interface UserUtil {
    fun addBoard(userID : Int, b : Board?)
    fun deleteBoard(userID : Int, b : Board?)
    fun changeBoardDefaultAlias(b : Board?, newAlias : String)
    fun addUserToDb()
}