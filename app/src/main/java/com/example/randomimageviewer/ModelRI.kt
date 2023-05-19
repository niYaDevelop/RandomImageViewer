package com.example.randomimageviewer

class ModelRI {
    val imageUrl1: String = "https://randomwordgenerator.com/img/picture-generator/57e2d146425aa414f1dc8460962e33791c3ad6e04e50744076287ad3904ecd_640.jpg"
    val imageUrl2 = "https://randomwordgenerator.com/img/picture-generator/54e9d7464b55a514f1dc8460962e33791c3ad6e04e507441722973d49548c4_640.jpg"
    val imageUrl3 = "https://randomwordgenerator.com/img/picture-generator/54e0d04a4c5ba414f1dc8460962e33791c3ad6e04e507440722d72d59345c2_640.jpg"
    val imageUrl4 = "https://randomwordgenerator.com/img/picture-generator/53e6dc404951b10ff3d8992cc12c30771037dbf852547848702e7ed19348_640.jpg"

    fun getRandomList(): List<RandomImage>{

        return listOf(RandomImage(imageUrl1), RandomImage(imageUrl2), RandomImage(imageUrl3), RandomImage(imageUrl4))
    }

    fun getFavList(): List<RandomImage> {

        return listOf()
    }

}