package com.stringconcat.people.businessPeople

fun interface QuotesProvider {
    fun randomQuote(): Quote
}

typealias Quote = String