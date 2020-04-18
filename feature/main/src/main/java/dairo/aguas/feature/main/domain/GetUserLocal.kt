package dairo.aguas.feature.main.domain

import dairo.aguas.data.repository.user.UserRepository

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class GetUserLocal(private val userRepository: UserRepository) {

    suspend fun execute() =
        userRepository.getUser()
}