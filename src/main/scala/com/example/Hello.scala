package com.example

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

import scala.concurrent.ExecutionContext.Implicits.global

object Hello {
  def main(args: Array[String]): Unit = {
    val system =ActorSystem("mySystem")
    implicit val timeout = Timeout(5 seconds)

    // アクターの生成
    val props = Props[MyActor]
    val actor = system.actorOf(props, name = "myActor")

    // Futureを生成(ask)
    val future: Future[Any] = actor ? "hi"

    // コールバックの書き方(その1)
    // 成功時
    future onSuccess {
      case s: String => {
        println(s) // hi
      }
      case _ => {
      }
    }
    // 失敗時
    future onFailure {
      case e: Exception => {
      }
    }

    // コールバックの書き方(その2)
    // 完了時
    future onComplete {
      case Success(result) => {
        println(result) // hi
      }
      case Failure(failure ) => {
      }
    }

    // コールバックの書き方(その3)
    // 連鎖
    future andThen {
      case Success(result) => {
        println("success") // success 1. これが発動してから
      }
      case Failure(failure) => {
        println("failure")
      }
    } andThen {
      case _ => {
        println("finalize") // finalize 2. これが発動
      }
    }

    system.terminate()
  }
}
