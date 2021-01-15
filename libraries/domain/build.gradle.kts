import Dependencies.Others
import Dependencies.Test

plugins {
    kotlinLibrary
}

dependencies {
    implementation(Others.jodaTimeAndroid)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
}
