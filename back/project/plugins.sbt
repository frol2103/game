addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.7")
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.11.0")

resolvers += Resolver.url("Upstart Commerce", url("https://upstartcommerce.bintray.com/generic/"))(
  Resolver.ivyStylePatterns
)


addSbtPlugin("org.upstartcommerce" % "sbt-openapi-generator" % "0.1.1")
