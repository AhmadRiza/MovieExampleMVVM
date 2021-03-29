package com.github.ahmadriza.movie

import org.junit.Test

import org.junit.Assert.*
import kotlin.collections.*
import kotlin.math.ceil

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(0, binary(32))
//        val a: IntArray
//
//       val result =
        shiftArray(intArrayOf(3, 5, 3, 9, 5, 9, 7), 1)

//        assertEquals(7, result)
    }


    fun binary(N: Int): Int {

        val binary = Integer.toBinaryString(N)
        var gap = 0
        var maxGap = 0
        binary.forEach {

            if (it == '1') {
                if (gap > maxGap) maxGap = gap
                gap = 0
            } else {
                gap++
            }
            return@forEach

        }
        return maxGap
    }

    fun oddArray(A: IntArray): Int {
        var result = 0
        A.forEach {
            result = result.xor(it)
        }
        return result
    }

    fun oddArray2(A: IntArray): Int {
        var result = 0
        val group = A.groupBy { it }
        group.forEach { (t: Int, u: List<Int>) ->
            if (u.size < 2) {
                result = t
                return@forEach
            }
        }
        return result
    }

    //    done
    fun shiftArray(A: IntArray, K: Int): IntArray {

        if (A.size == 0) return A

        for (i in 0 until K) {

            val temp = A[A.lastIndex]
            for (i in A.lastIndex downTo 1) {
                A[i] = A[i - 1]
            }
            A[0] = temp
        }

        return A

    }

    fun frogJump(X: Int, Y: Int, D: Int): Int {
        if (X >= Y) return 0
        return ceil((Y - X).toDouble() / D.toDouble()).toInt()
    }

    fun frogRiver(X: Int, A: IntArray): Int {
        if(A.size == 1) return 0

        var steps = X
        val maps = BooleanArray(A.size + 1)
        for (i in 0 until A.size) {
            if (!maps[A[i]]) {
                maps[A[i]] = true
                steps--
                if (steps == 0) return i
            }
        }

        return -1

    }

}