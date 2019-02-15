package com.apll.centermanagementsservice.visitingrequest.repository


import com.apll.centermanagementsservice.visitingrequest.model.RequestId
import com.apll.centermanagementsservice.visitingrequest.model.VisitingRequest
import org.springframework.data.jpa.repository.JpaRepository


interface VisitingRequestRepo:JpaRepository<VisitingRequest, RequestId>
{

}

