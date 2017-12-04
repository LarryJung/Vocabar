package com.dot2line.vocabar.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class VocaBook : RealmObject {

    @PrimaryKey
    @Required
    var id: String? = null
    var bookName: String? = null
    var vocaPairList: RealmList<VocaPair>? = null

    constructor() {}

    constructor(id: String, bookName: String, vocaPairList: RealmList<VocaPair>) {
        this.id = id
        this.bookName = bookName
        this.vocaPairList = vocaPairList
    }
}
