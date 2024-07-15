import java.net.URI

include(":admobAds:app_open")


include(":admobAds:banner_ads")


pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://artifacts.applovin.com/android") }

        maven { setUrl("https://jitpack.io") }
        /*
        maven { setUrl("https://jcenter.bintray.com") }
        maven { setUrl("https://artifact.bytedance.com/repository/pangle") }
        maven { setUrl("https://android-sdk.is.com/") }
        maven { setUrl("https://cboost.jfrog.io/artifactory/chartboost-ads/") }
        maven { setUrl("https://sdk.tapjoy.com") }
        maven { setUrl("https://android-sdk.is.com/") }
        maven { setUrl("https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea") }
        maven { setUrl("https://s3.amazonaws.com/smaato-sdk-releases/") }
        maven { setUrl("https://artifactory.bidmachine.io/bidmachine") }
        maven { setUrl("https://verve.jfrog.io/artifactory/verve-gradle-release") }
        maven { setUrl("https://maven.ogury.co") }
        maven { setUrl("https://imobile-maio.github.io/maven") }
        maven { setUrl("https://maven.ogury.co") }
        maven {
            setUrl(
                "https://repository.ikameglobal.com/api/v4/projects/3/packages/maven"
            )
        }
        maven {
            url = uri("https://repository.ikameglobal.com/api/v4/projects/1/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "Private-Token"
                value = "ikame-zYxdkbXQBWe7p3vUy83U"
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
*/
    }
}

rootProject.name = "AdsXml"
include(":app")
include(":admobAds")
include(":admobAds:core")
include(":admobAds:inter")
include(":admobAds:native_ads")
