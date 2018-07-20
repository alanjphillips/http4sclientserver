package com.alaphi.http4sserver

import cats.effect.IO
import com.alaphi.common.HelloThere
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.circe._
import io.circe.syntax._
import io.circe.generic.auto._

object HelloWorldRoutes {

  val routes = HttpRoutes.of[IO]  {
    case GET -> Root / "hello" / name =>
      Ok(HelloThere("Hi", name).asJson)
  }

}
