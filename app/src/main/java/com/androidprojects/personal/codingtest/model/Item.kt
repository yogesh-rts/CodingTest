package com.androidprojects.personal.codingtest.model

/*
* @Class Item consolidates both items and the header title into a single list
* @Class GridItem and Header represents the model data for items and header title correspondingly
* which are responsible for supplying the data to UI
* */
sealed class Item {

    class GridItem(
            var imageResource: Int,
            var medalName: String,
            var runTimer: String
    ): Item()

    class Header(var headerTitle: String): Item()
}