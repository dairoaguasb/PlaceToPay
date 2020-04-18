package dairo.aguas.feature.login.domain

import dairo.aguas.data.model.user.User
import dairo.aguas.data.repository.user.UserRepository

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class SetUserLocal(private val userRepository: UserRepository) {

    suspend fun execute(user: User) {
        userRepository.setUser(user)
    }
}