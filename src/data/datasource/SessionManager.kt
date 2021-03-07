package com.foodie.api.data.datasource

import domain.model.auth.Session

interface SessionManager {
    fun addSession(session: Session)
    fun getSession(name: String, password: String): Session?
}

class UserSessionManager : SessionManager {
    private val sessions = mutableListOf<Session>()

    override fun addSession(session: Session) {
        sessions.add(session)
    }

    override fun getSession(name: String, password: String): Session? {
        return sessions.firstOrNull { name == it.user.name && password == it.user.password }
    }

}