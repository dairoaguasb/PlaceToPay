package dairo.aguas.feature.login.domain

import dairo.aguas.data.repository.user.UserRepository

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class AuthUserLocal(private val userRepository: UserRepository) {

    suspend fun execute(email: String, password: String) =
        userRepository.authUser(email, password)
}