package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChatChannel(val channelName : String, val channelIcon : String, val channelKey : String) {
    constructor() : this("", "", "")
}