package com.example

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Hello {
  def main(args: Array[String]): Unit = {
    val system =ActorSystem("mySystem")
    implicit val timeout = Timeout(5 seconds)

    val props = Props[MyActor]
    val actor = system.actorOf(props, name = "myActor")

    val future: Future[Any] = actor ? "hi"

    future onSuccess {
      case s: String => {
        println(s)
      }
      case _ => {
      }
    }
    future onFailure {
      case e: Exception => {
      }
    }

    future onComplete {
      case Success(result) => {
        println(result)
      }
      case Failure(failure ) => {
      }
    }

    future andThen {
      case Success(result) => {
        println("success")
      }
      case Failure(failure) => {
        println("failure")
      }
    } andThen {
      case _ => {
        println("finalize")
      }
    }
  }
}
