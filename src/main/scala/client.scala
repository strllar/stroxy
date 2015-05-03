
import java.net._
import akka.stream.scaladsl.{Source, Flow, Sink, Tcp=>StreamTcp}
import StreamTcp.{ServerBinding, IncomingConnection}
import akka.stream.stage.{StatefulStage, PushStage, Context, Directive}
import scala.concurrent._
import scala.concurrent.duration._

import scala.annotation.tailrec

import akka.util.ByteString

object ClientConfig {

}

object ClientApp extends App{
  implicit val sys = akka.actor.ActorSystem();
  import sys.dispatcher
  implicit val mat = akka.stream.ActorFlowMaterializer()

  println("client daemon running")

  val connections: Source[IncomingConnection, Future[ServerBinding]] = StreamTcp().bind("127.0.0.1", 8888)

  //start watchdog for test
  sys.scheduler.scheduleOnce(45 seconds) {
    sys.shutdown()
  }

  connections runForeach { connection =>
    println(s"New connection from: ${connection.remoteAddress}")

    val repl = Flow[ByteString]
      .map(text => {println("Server: " + text);text})

    connection.handleWith(repl)
  }

  ////not work in "fork in run" mode
  //readLine(Console.GREEN_B + "Hit ENTER to shutdown ...") 
  //sys.shutdown()
  //sys.awaitTermination()
}
