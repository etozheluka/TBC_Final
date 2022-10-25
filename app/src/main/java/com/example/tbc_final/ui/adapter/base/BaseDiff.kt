package com.example.tbc_final.ui.adapter.base

abstract class BaseDiff<T> {

    abstract val inner: T
    abstract val uniqueValue: Any
    abstract fun compareTo(other: Any?):Boolean
    override fun equals(other:Any?):Boolean{
        return compareTo(other)
    }

    override fun hashCode(): Int {
        var result = inner?.hashCode() ?:0
        result = 31 * result+uniqueValue.hashCode()
        return result
    }
}