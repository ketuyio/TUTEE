package com.example.tutee.models

class Product {
    var name:String = ""
    var profession:String = ""
    var price:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String,profession: String, price: String, imageUrl: String, id: String) {
        this.name = name
        this.profession = profession
        this.price = price
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}