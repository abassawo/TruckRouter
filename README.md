### TruckRouter 

An application showcasing a few things: unidirectional data flow, MVVM, jetpack compose, kotlin multiplatform to name a few.

### Approach

I chose to work on this problem as I would a full system, with the thought in mind that parts of the algorithm or perhaps the input may change
in the future. The ScheduleDomainMapper is the main evaluator of our algorithm, but it relies on a number of interceptors.

  - StreetNameExtractor - extracts street name from a String
  - SuitabilityScorer - Calculates a score for a potential match-up between a driver and a street name
  - FindBestSuitedDriver = Takes as input a single shipment destination and a list of drivers, returns the best suited driver

Tests for all of these interactors can be found in the shared/commonTest folder
  
More on the Suitability Scorer:

I thought of the first two conditions (even and odd) as base suitability determinants
whereas the 3rd requirement as being an additional multiplier

### Features

This app supports the Google Maps integration: to get it to work.
As such, you will need to enter your google maps API key to your local.properties file in Android Studio.
  - MAPS_API_KEY="..."

The app supports toggling this feature on or off by relying on a FeatureFlag coordinated by SharedPreferences.

### Architecture

This relies on MVVM where the ViewModel emits a given state and our UI renders it accordingly.
THe bulk of the logic lives in the shared layer, which may be helpful in the future if we wanted to 
extend this solution to other platforms like iOS or Desktop Native.

### Data layer design

I chose to architect this as if the data may be coming from some varying source in the future
so our AppDataSource is the main entrypoint to the schedule data though at this point, it relies 
on a local implementation that reads from a raw json.

