package com.dot2line.vocabar.model

import io.realm.RealmObject

open class VocaPair : RealmObject() {

    var id: Long = 0
    var origin: String? = null
    var meaning: String? = null
}
