package com.sd.laborator.services

import com.sd.laborator.interfaces.CachingDAO
import org.springframework.stereotype.Service

@Service
class CachingDAOService: CachingDAO {
    override fun exists(query: String): String? {
        TODO("Not yet implemented")
    }

    override fun addToCache(query: String, result: String) {
        TODO("Not yet implemented")
    }

}