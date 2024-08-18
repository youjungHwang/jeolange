package yjh.jeolange.redis.sortedset

import redis.clients.jedis.JedisPool

fun main() {
    // Redis에 연결하기 위한 JedisPool을 생성합니다.
    val jedisPool = JedisPool("127.0.0.1", 6379)
    val jedis = jedisPool.getResource()

    // 사용자와 점수를 저장할 HashMap을 생성합니다.
    val score = HashMap<String, Double>()
    score["users1"] = 100.0
    score["users2"] = 30.0
    score["users3"] = 50.0
    score["users4"] = 80.0
    score["users5"] = 15.0

    /**
     * "game:score"라는 이름의 sorted set에 사용자와 점수를 추가합니다.
     * 추가된 데이터는 점수에 따라 자동으로 정렬되며, 기본적으로 오름차순으로 저장됩니다.
     */
    jedis.zadd("game:score", score)

    /**
     * sorted set의 모든 요소를 출력
     * - elementsInAscendingOrder : 오름차순
     * - elementsInDescendingOrder : 내림차순
     */
    val elementsInAscendingOrder = jedis.zrange("game:score", 0, -1)
    val elementsInDescendingOrder = jedis.zrevrange("game:score", 0, -1)
    elementsInDescendingOrder.forEach { println(it) }

    /**
     * sorted set의 총 요소 수를 출력합니다.
     * 결과: 5
     */
    println(jedis.zcard("game:score"))

    /**
     * 특정 사용자의 점수를 증가시킵니다.
     */
    jedis.zincrby("game:score", 100.0, "users5")

    /**
     * 점수와 함께 sorted set의 모든 요소를 출력합니다.
     * - ascendingRank : 오름차순
     * - descendingRank : 내림차순
     */
    val ascendingRank = jedis.zrangeWithScores("game:score", 0, -1)
    val descendingRank = jedis.zrevrangeWithScores("game:score", 0, -1)
    descendingRank.forEach { println("${it.element} ${it.score}") }

}

/**
 * " 출력 "
 * users1
 * users4
 * users3
 * users2
 * users5
 * 5
 * users5 115.0
 * users1 100.0
 * users4 80.0
 * users3 50.0
 * users2 30.0
 */
