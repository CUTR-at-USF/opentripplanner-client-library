# OpenTripPlanner Client Library [![Gradle CI](https://github.com/CUTR-at-USF/opentripplanner-client-library/actions/workflows/gradle.yml/badge.svg)](https://github.com/CUTR-at-USF/opentripplanner-client-library/actions/workflows/gradle.yml)

A Kotlin Multiplatform library for making API requests and parsing responses from an [OpenTripPlanner v2 server](http://www.opentripplanner.org/) for the following [OTP2 REST API](http://dev.opentripplanner.org/apidoc/2.0.0/index.html) endpoints:
* [`/plan`](http://dev.opentripplanner.org/apidoc/2.0.0/resource_PlannerResource.html) - Trip planning from an origin to a destination
* [`/bike_rental`](http://dev.opentripplanner.org/apidoc/2.0.0/resource_BikeRental.html) - List of bike rental stations
* [`/otp`](http://dev.opentripplanner.org/apidoc/2.0.0/resource_ServerInfo.html) - Provides information about the OTP server (version, etc.)

## Example use (Android)

#### Plan API

```kotlin
private lateinit var planApi: PlanApi
...
val requestParameters = RequestParameters(
  fromPlace = latLong(41.84712, -87.64678),
  toPlace = latLong(41.84584, -87.65214),
  arriveBy = "false")
planApi = PlanApi("https://10.0.2.2:8080/otp/routers/default/plan",  requestParameters)
planApi.getPlan(
    success = { launch (Main) {
        Log.d(TAG, "logData: $it")
    } },
    failure = ::handleError
)
```

#### Bike Rental API

```kotlin
private lateinit var bikeRentalApi: BikeRentalApi
...
bikeRentalApi = BikeRentalApi("https://10.0.2.2:8080/otp/routers/default/bike_rental",
    lowerLeft = latLong(41.81712, -87.62678),
    upperRight = latLong(41.84584, -87.65214))

bikeRentalApi.getBikeRental(
    success = {launch (Main) { 
        Log.d(TAG, "logData: $it") 
    }},
    failure = ::handleError
)
```

#### Server Info API

```kotlin
private lateinit var serverInfoApi: ServerInfoApi
...
serverInfoApi = ServerInfoApi("https://10.0.2.2:8080/otp")
serverInfoApi.getServerInfo(
    success = {launch (Main) { logData(it) }},
    failure = ::handleError
)
```

## Prerequisites
1) Install [Android Studio](https://developer.android.com/studio) (preferably version `4.1.3` or higher).
2) Before you import the project, install Kotlin MultiPlatform Mobile plugin via Settings (Preferences if on MacOS) -> Plugins -> Kotlin Multplatform Mobile -> Install

## Build
Import the project into Android Studio

## Testing
1. [Set up an OTP2 server](http://docs.opentripplanner.org/en/latest/Basic-Tutorial/) on your local machine, or get the URL for a public OTP2 server
1. If you're testing the demo Android app on a physical device, make sure to change the host to `localhost` or `127.0.0.1` in the API constructors and also make note of the port number your OTP server is running on.
1. Click on the green play button on top to run the Android demo app. OTP API responses will be logged in LogCat with the tag `MainActivity`.

## Release
To create a release of the library on each platform, run the following commands.

#### Android

This command generates AAR files in `library/build/outputs/aar`:

```
./gradlew :library:build
```

#### iOS

TODO

#### Desktop Java/Kotlin

TODO

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
