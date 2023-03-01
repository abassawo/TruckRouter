### TruckRouter 

An application showcasing a few things: unidirectional data flow, MVVM, jetpack compose, kotlin multiplatform to name a few.

### Build Instructions
- Open in Android studio
- Add google maps api key to local.properties ```MAPS_API_KEY="xxxx"```
- Also go to the AndroidManifest.xml and replace the meta data block with this when the above is done:
   ```
      <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="${MAPS_API_KEY}"/>
   ```
- Run the androidApp configuration on either an emulator or physical device in android studio

### Approach

I chose to work on this problem as I would a full system, with the thought in mind that parts of the algorithm or perhaps the input may change
in the future. 
I also represent the problem as a mapping from Data layer to Domain layer to Presentation layer.

Data layer: AppDataSource Yields an instance of RawScheduleResponse
Domain layer: ScheduleDomainMapper maps a RawScheduleResponse to a ScheduleDomainEntity, which is really just a typealias for List<Pair<DriverDomainEntity, ShipmentDomainEntity>> that meet the requirements.


The ScheduleDomainMapper is the main evaluator of our algorithm, but it relies on a number of interceptors.


  - StreetNameExtractor - extracts street name from a String
  - SuitabilityScorer - Calculates a score for a potential match-up between a driver and a street name
  - FindBestSuitedDriver = Takes as input a single shipment destination and a list of drivers, returns the best suited driver

Unit Tests for these interactors can be found in the shared/commonTest folder
  
More on the Suitability Scorer:

I thought of the first two conditions (even and odd) as base suitability determinants
whereas the 3rd requirement as being an additional multiplier.

### Features

This app supports the Google Maps integration: to get it to work.
As such, you will need to enter your google maps API key to your local.properties file in Android Studio.
  - MAPS_API_KEY="..."

The app supports toggling this feature on or off by relying on a FeatureFlag coordinated by SharedPreferences.

### Architecture

This relies on MVVM where the ViewModel emits a given state and our UI renders it accordingly.
THe bulk of the logic lives in the shared layer, which may be helpful in the future if we wanted to 
extend this solution to other platforms like iOS or Desktop Native.

I chose to architect this as if the data may be coming from some varying source in the future
so our AppDataSource is the main entrypoint to the schedule data though at this point, it relies 
on a local implementation that reads from a raw json.

All of the core logic of the data parsing and schedule matching lives in a shared modules so that this could potentially
support other platforms easily in the future.

### App Design

The UI is built with Jetpack Compose, and supports portrait and landscape mode, I've tried to demonstrate the popular paradigm of reusing a list component
and a detail component on larger devices. 



<img width="346" alt="Screenshot 2023-03-01 at 12 42 17 PM" src="https://user-images.githubusercontent.com/8889247/222219564-8460481b-fe9e-4a75-a0d0-01e907a51577.png">



![Screenshot 2023-03-01 at 12 43 19 PM](https://user-images.githubusercontent.com/8889247/222219739-9307ad17-3fda-48d1-b34c-f3961f33d9ad.png)


