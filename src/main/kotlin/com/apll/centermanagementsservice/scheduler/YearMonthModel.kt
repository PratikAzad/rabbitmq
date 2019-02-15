package com.apll.centermanagementsservice.scheduler

import com.apll.centermanagementsservice.config.YearMonthDeserializer
import com.apll.centermanagementsservice.config.YearMonthSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.YearMonth


class YearMonthModel {

    @JsonSerialize(using = YearMonthSerializer::class)
    @JsonDeserialize(using = YearMonthDeserializer::class)
    var yearMonth:YearMonth?=null

    constructor()
    constructor(yearMonth: YearMonth?) {
        this.yearMonth = yearMonth
    }

    override fun toString(): String {
        return "YearMonthModel(yearMonth=$yearMonth)"
    }


}