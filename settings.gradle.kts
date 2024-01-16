pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ecommerce"
include(":app")
include(":feature")
include(":feature:login")
include(":feature:card")
include(":feature:login_1")
include(":data")
include(":core")
include(":core:backbase")
include(":core:common")
include(":core:model")
include(":domain")
include(":engagement")
include(":feature:onboarding")
include(":feature:onboarding1")
include(":feature:login1")
