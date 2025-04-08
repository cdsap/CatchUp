/*
 * Copyright (c) 2024 Zac Sweers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.foundry.base)
  alias(libs.plugins.sqldelight)
  alias(libs.plugins.metro)
}

kotlin {
  // region KMP Targets
  jvm()
  // endregion

  applyDefaultHierarchyTemplate()

  sourceSets {
    commonMain {
      dependencies {
        api(libs.compose.runtime)
        api(libs.kotlin.coroutines)
        api(projects.libraries.di)
        api(projects.libraries.kotlinutil)
        api(projects.libraries.sqldelightExtensions)
      }
    }
    jvmMain {
      dependencies {
        api(libs.okhttp.core)
        implementation(libs.misc.unfurl)
      }
    }
  }
}

sqldelight { databases { create("UnfurlerDatabase") { packageName.set("catchup.unfurler") } } }
