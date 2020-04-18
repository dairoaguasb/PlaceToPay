package dairo.aguas.data.repository.user

import dairo.aguas.data.model.user.User

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
interface UserRepository {

    suspend fun setUser(user: User)

    suspend fun authUser(email: String, password: String): User
}