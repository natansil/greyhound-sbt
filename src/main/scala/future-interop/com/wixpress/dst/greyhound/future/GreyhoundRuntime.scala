package com.wixpress.dst.greyhound.future

import java.util.concurrent.Executors

import com.wixpress.dst.greyhound.core.metrics.GreyhoundMetrics
import zio.blocking.Blocking
import zio.clock.Clock
import zio.console.Console
import zio.internal.Platform
import zio.random.Random
import zio.system.System
import zio.{Has, Runtime}

import scala.concurrent.ExecutionContext

trait GreyhoundRuntime extends Runtime[GreyhoundRuntime.Env] {
  override val platform: Platform = Platform.default
}

object GreyhoundRuntime {
  implicit val executionContext: concurrent.ExecutionContextExecutor = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  type ZEnv = Clock with Console with System with Random with Blocking

  val zenv: Has[Clock.Service] with Has[Console.Service] with Has[System.Service] with Has[Random.Service] with Has[Blocking.Service] = Has.allOf[Clock.Service, Console.Service, System.Service, Random.Service, Blocking.Service](
    Clock.Service.live,
    Console.Service.live,
    System.Service.live,
    Random.Service.live,
    Blocking.Service.live
  )(izumi.reflect.Tag.tagFromTagMacro, izumi.reflect.Tag.tagFromTagMacro, izumi.reflect.Tag.tagFromTagMacro, izumi.reflect.Tag.tagFromTagMacro, izumi.reflect.Tag.tagFromTagMacro)

  type Env = ZEnv with GreyhoundMetrics

  val Live: GreyhoundRuntime = GreyhoundRuntimeBuilder().build
}

case class GreyhoundRuntimeBuilder(metricsReporter: GreyhoundMetrics.Service = GreyhoundMetrics.Service.Live) {
  def withMetricsReporter(reporter: GreyhoundMetrics.Service): GreyhoundRuntimeBuilder =
    copy(metricsReporter = reporter)

  def build: GreyhoundRuntime =
    new GreyhoundRuntime {
      override val environment: Has[Clock.Service] with Has[Console.Service] with Has[System.Service] with Has[Random.Service] with Has[Blocking.Service] with Has[GreyhoundMetrics.Service] = GreyhoundRuntime.zenv ++ Has.apply[GreyhoundMetrics.Service](metricsReporter)(izumi.reflect.Tag.tagFromTagMacro)
    }
}
