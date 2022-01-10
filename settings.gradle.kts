pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "TogowlNative"

include(
    ":android",
//    ":web",
//    ":desktop",
)

include(
    ":android-features:main",
    ":android-features:core"
)

include(
    ":usecase",
    ":domain",
    ":model",
)
project(":usecase").projectDir = file("./common/usecase")
project(":domain").projectDir = file("./common/domain")
project(":model").projectDir = file("./common/model")

include(
    ":infra:api",
    ":infra:db"
)
project(":infra:api").projectDir = file("./common/infra/api")
project(":infra:db").projectDir = file("./common/infra/db")
