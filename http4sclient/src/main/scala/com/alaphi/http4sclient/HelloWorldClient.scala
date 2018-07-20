package com.alaphi.http4sclient

import org.http4s.client.blaze.Http1Client
import cats.effect.IO
import com.alaphi.common.HelloThere
import org.http4s.circe._
import io.circe.generic.auto._
import org.http4s.Uri
import org.http4s.client.dsl.io._
import org.http4s.headers._
import org.http4s.MediaType
import org.http4s.dsl.Http4sDsl

object HelloWorldClient extends Http4sDsl[IO]{

  implicit val userDecoder = jsonOf[IO, HelloThere]

  def hello(name: String): IO[HelloThere] = {
    val request = GET(Uri.uri("http://localhost:8080/hello/") / name, Accept(MediaType.application.json))
    val responseBody = Http1Client[IO]().flatMap(_.expect[HelloThere](request))
    responseBody
  }

}
