package com.vkarmaedu.vkarma.data

object UserRepo {
    var name : String? = null
    var uID : String? = null

    fun setCred(n : String, u : String){
        name = n
        uID = u
    }
}