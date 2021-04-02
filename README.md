# OpenTripPlanner Client Library [![Java with Gradle](https://github.com/CUTR-at-USF/opentripplanner-client-library/actions/workflows/gradle.yml/badge.svg)](https://github.com/CUTR-at-USF/opentripplanner-client-library/actions/workflows/gradle.yml)

A full-featured kotlin multiplatform library designed to minimize developer friction in creating complex API calls to the Open Trip Planner v2 web service.

## Prerequisites
1) Install Android Studio (preferably version `4.1.3`).
2) Before you import the project, install Kotlin MultiPlatform Mobile plugin. You can install it by navigating through Settings (Preferences if on MacOS) -> Plugins -> Kotlin Multplatform Mobile -> Install

## Build
Import the project and click on the green play button on top.

## Testing
1) If you're testing on a Physical device, make sure to change the host to `localhost` or `127.0.0.1` in the API constructors and also make note of the port number.
2) After installation, pay attention to the logs. The json responses will be logged in the `debug` with the tag `MainActivity`.

## Release
To create a AAR release of the library, run the following command. This command generates AAR files in library/build/outputs/aar
```
./gradlew :library:build
```

## License
```
/*
 * Copyright (C) 2021 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```
