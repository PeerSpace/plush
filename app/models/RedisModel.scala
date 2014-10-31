package models

import com.redis._
import play.Logger
import play.api.Play.current
import play.api.Play

trait RedisConnection {

  val redisHost = Play.application.configuration.getString("plush.redis.host")
  val redisPort = Play.application.configuration.getInt("plush.redis.port")
  Logger.debug(s"Connecting to redis at ${redisHost.get}:${redisPort.get}")
  val redis = new RedisClient(redisHost.get, redisPort.get)

}

trait RedisModel extends RedisConnection {

  def createOrUpdateHash(key: String, hash: Map[String, Any]) = {
    val noneFields = noneFieldsFromMap(hash)
    val valuesHash = valuesMapFromMap(hash)

    redis.pipeline { p =>
      if (noneFields.nonEmpty) p.hdel(key, noneFields.head, noneFields.tail.toSeq:_*)
      p.hmset(key, valuesHash)
    } match {
      // TODO: there should be a better way!
      case Some(List(Some(_), true)) => true
      case Some(List(true)) => true
      case _ => false
    }
  }

  def noneFieldsFromMap(m: Map[String, Any]) =
    m collect { case (k, None) => k }

  def valuesMapFromMap(m: Map[String, Any]) =
    (m -- noneFieldsFromMap(m)) map { case (k, Some(v)) => k -> v; case (k, v) => k -> v }

}