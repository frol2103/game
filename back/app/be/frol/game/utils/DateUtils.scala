package be.frol.game.utils

import org.joda.time.DateTime
import play.api.libs.json.{Format, Reads, Writes}

import java.sql.Timestamp
import java.time.{Instant, LocalDate, OffsetDateTime, ZoneId}

object DateUtils {

  def ts = new Timestamp(System.currentTimeMillis())

  implicit def toRichTimestamp(t:Timestamp) = new AnyRef{
    def toOffsetDateTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(t.getTime), DateUtils.ZONE_ID)
    def toJodaDateTime = new DateTime(t.getTime)
  }

  implicit def toRichOffsetDateTime(t:OffsetDateTime) = new AnyRef{
    def toTimestamp = new Timestamp(t.toInstant.toEpochMilli)
  }

  implicit def toRichLocalDate(t:LocalDate) = new AnyRef{
    def toJodaDateTime = new DateTime(t.toEpochDay)
  }

  def toOffsetDateTime(t:Timestamp) = t.toOffsetDateTime
  def toTimestamp(offsetDateTime: OffsetDateTime) = offsetDateTime.toTimestamp
  val ZONE_ID = ZoneId.of("Europe/Brussels")
}
