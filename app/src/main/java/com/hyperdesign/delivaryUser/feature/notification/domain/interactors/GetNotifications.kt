package com.hyperdesign.delivaryUser.feature.notification.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification
import com.hyperdesign.delivaryUser.feature.notification.domain.repository.INotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotifications(private val repository: INotificationRepository) :
    BaseUseCase<Flow<Resource<List<Notification>>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<List<Notification>>> = flowExecute {
        repository.getNotifications()
    }
}