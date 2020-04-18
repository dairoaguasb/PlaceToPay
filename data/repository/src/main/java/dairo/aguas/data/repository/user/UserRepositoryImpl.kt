package dairo.aguas.data.repository.user

import dairo.aguas.data.local.dao.UserDao
import dairo.aguas.data.model.user.User

/**
 * Created by Dairo Aguas B on 18/04/2020.
 */
class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun setUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun authUser(email: String, password: String) =
        userDao.authUser(email, password)

    override suspend fun getUser() =
        userDao.getUser()
}