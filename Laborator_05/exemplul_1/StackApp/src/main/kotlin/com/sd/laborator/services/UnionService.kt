package com.sd.laborator.services

import com.sd.laborator.interfaces.CartesianProductOperation
import com.sd.laborator.interfaces.UnionOperation
import com.sd.laborator.model.Stack
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@set:Autowired lateinit var cartesianProductOperation: CartesianProductOperation

@Service
class UnionService: UnionOperation {
    override fun executeOperation(A: Stack?, B: Stack?): Set<Pair<Int, Int>> {
        return cartesianProductOperation.executeOperation(A!!.data, B!!.data) union cartesianProductOperation.executeOperation(B.data, A.data)
    }

}