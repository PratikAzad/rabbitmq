package com.apll.centermanagementsservice.customreport.repository

import com.apll.centermanagementsservice.customreport.model.CustomReport
import com.apll.centermanagementsservice.customreport.model.CustomReportId
import org.springframework.data.jpa.repository.JpaRepository

interface CustomReportRepo:JpaRepository<CustomReport,CustomReportId> {


}