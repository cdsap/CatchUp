/*
 * Copyright (c) 2018 Zac Sweers
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
  alias(libs.plugins.android.library)
  alias(libs.plugins.sgp.base)
  alias(libs.plugins.anvil)
}

kotlin {
  // region KMP Targets
  androidTarget()
  jvm()
  // endregion

  applyDefaultHierarchyTemplate()

  sourceSets {
    commonMain {
      dependencies {
        api(libs.kotlin.datetime)
        api(libs.sqldelight.coroutines)
        api(libs.sqldelight.runtime)
        api(projects.bookmarks.db)

        implementation(projects.serviceApi)
      }
    }
    val jvmMain by getting {
      dependencies {
        implementation(libs.anvil.annotations)
      }
    }
    val androidMain by getting {
      dependencies {
        implementation(libs.anvil.annotations)
      }
    }
  }
}

android { namespace = "catchup.bookmarks" }

slack {
  features {
    dagger()
  }
}
