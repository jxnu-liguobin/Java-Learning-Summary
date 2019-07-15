package cn.edu.jxnu.scala.utils

import play.api.http.{ContentTypeOf, ContentTypes, Writeable}
import play.api.libs.json.Writes

/**
 * 编解码工具
 *
 * @author 梦境迷离
 * @version 1.0, 2019-07-15
 */
object CodecUtils {

  /** 将DTO写入流，所需要的隐式参数
   *
   * {{{
   *   Ok(dto)
   * }}}
   *
   * @param writes
   * @param codec
   * @tparam A
   * @return
   */
  implicit def jsonWritable[A](implicit writes: Writes[A], codec: play.api.mvc.Codec): Writeable[A] = {
    implicit val contentType = ContentTypeOf[A](Some(ContentTypes.JSON))
    val transform = Writeable.writeableOf_JsValue.transform compose writes.writes
    Writeable(transform)
  }

}