package com.service_exchange.api_services.dao.complaint

import com.service_exchange.entities.Complaint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors


interface ComplaintInterface {
    fun getComplaintById(complaintId: Int): Complaint?
    fun getComplaintsByState(state: String, page: Int): List<Complaint>
    fun getComplaintByDate(date: Date, page: Int): List<Complaint>
    fun getUnseenComplaints(page: Int): List<Complaint>
    fun saveComplaint(complaint: Complaint): Complaint?
    fun getCountOfComplaint(): Long
    fun getCountOfAcceptedComplaint(): Long
    fun getCountOfRejectedComplaint(): Long
    fun getCountOfOnReviewComplaint(): Long
    fun getCountOfNotReviewComplaint(): Long
    fun getCountBasedOnType(type: String): Long


}

@Component
private class ComplaintImpl : ComplaintInterface {


    @Autowired
    lateinit var complaintData: ComplaintDaoInterface

    override fun getComplaintById(complaintId: Int): Complaint? =
            complaintData.findById(complaintId).let { optional ->
                if (optional.isPresent)
                    optional.get()
                else null
            }

    override fun getComplaintsByState(state: String, page: Int): List<Complaint> =
            complaintData.findAllByStateEqualsOrderByDate(state, PageRequest.of(page, 20))
                    .stream().collect(Collectors.toList())

    override fun getComplaintByDate(date: Date, page: Int): List<Complaint> =
            complaintData.findAllByDateIsNearOrderByDate(date, PageRequest.of(page, 20))
                    .stream().collect(Collectors.toList())

    override fun getUnseenComplaints(page: Int): List<Complaint> =
            complaintData.findAllByReviewedByNullOrderByDate(PageRequest.of(page, 20)).stream()
                    .collect(Collectors.toList())

    override fun saveComplaint(complaint: Complaint): Complaint? {
        return complaintData.save(complaint)
    }

    override fun getCountOfComplaint(): Long =
            complaintData.countAllByIdIsNotNull()

    override fun getCountOfAcceptedComplaint(): Long =
            complaintData.countAllByStateEquals(Complaint.ACCEPTED_STATE)

    override fun getCountOfRejectedComplaint(): Long =
            complaintData.countAllByStateEquals(Complaint.REJECTED_STATE)

    override fun getCountOfNotReviewComplaint(): Long =
            complaintData.countAllByStateEquals(Complaint.NOT_REVIEWED_STATE)

    override fun getCountOfOnReviewComplaint(): Long =
            complaintData.countAllByStateEquals(Complaint.ON_REVIEW_STATE)

    override fun getCountBasedOnType(type: String): Long =
            complaintData.countAllByStateEquals(type)
}