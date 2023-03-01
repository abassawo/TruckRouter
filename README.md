### TruckRouter 

An application showcasing a few things: unidirectional data flow, MVVM, jetpack compose, kotlin multiplatform to name a few.

### Approach

I chose to work on this problem as I would a full system, with the thought in mind that parts of the algorithm or perhaps the input may change
in the future. The ScheduleDomainMapper is the main evaluator of our algorithm, but it relies on a number of interceptors.


### Features

This app supports the Google Maps integration: to get it to work, you will need to either
  - update the local.properties file and add 

### Architecture

MVVM

### Android

### Other


### Data layer design

I chose to architect this as if the data may be coming from some varying source in the future. 