package com.ssafy.fundyou.data.remote.repository

import android.util.Log
import com.ssafy.fundyou.data.remote.datasource.funding.FundingDataSource
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingIdRequestDto
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingItemIdRequestDto
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.funding.*
import com.ssafy.fundyou.domain.repository.FundingRepository
import javax.inject.Inject

internal class FundingRepositoryImpl @Inject constructor(
    private val fundingDataSource: FundingDataSource
) : FundingRepository {
    override suspend fun getFundingInfo(fundingId: Long): FundingInfoModel {
        val request = FundingIdRequestDto(fundingId)
        return fundingDataSource.getFundingInfo(request).toDomainModel()
    }

    override suspend fun createFunding(endDate: Long, fundingName: String): Long {
        val request = FundingCreateRequestDto(endDate, fundingName)
        return fundingDataSource.createFunding(request)
    }

    override suspend fun getMyOngoingFunding(): List<FundingTotalModel> =
        fundingDataSource.getMyOngoingFunding().map { it.toDomainModel() }

    override suspend fun getMyClosedFunding(): List<FundingTotalModel> {
        val response = fundingDataSource.getMyClosedFunding()
        return fundingDataSource.getMyClosedFunding().map { it.toDomainModel() }
    }


    override suspend fun getFundingItemList(fundingId: Long): List<FundingItemInfoModel> {
        val request = FundingIdRequestDto(fundingId)
        return fundingDataSource.getFundingItemList(request).map { it.toDomainModel() }
    }

    override suspend fun terminateFundingItem(fundingItemId: Long): Boolean {
        val request = FundingItemIdRequestDto(fundingItemId)
        return fundingDataSource.terminateFundingItem(request)
    }

    override suspend fun getFundingStatistics(fundingId: Long): List<FundingStatisticsModel> {
        val request = FundingIdRequestDto(fundingId)
        return fundingDataSource.getFundingStatisticsList(request).map { it.toDomainModel() }
    }

    override suspend fun getFundingItem(fundingItemId: Long): FundingItemInfoModel {
        val request = FundingItemIdRequestDto(fundingItemId)
        return fundingDataSource.getFundingItem(request).toDomainModel()
    }

    override suspend fun getFundingParticipateMessageList(fundingItemId: Long): List<FundingMessageModel> {
        val request = FundingItemIdRequestDto(fundingItemId)
        return fundingDataSource.getFundingItemParticipateList(request).map { it.toDomainModel() }
    }

    override suspend fun addOngoingFundingItem(): Long {
        return fundingDataSource.addOngoingFundingItem()
    }

    override suspend fun getFundingHostInfo(fundingId: Long): FundingHostInfoModel {
        val request = FundingIdRequestDto(fundingId)
        return fundingDataSource.getFundingHostInfo(request).toDomainModel()
    }
}