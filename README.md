### TruckRouter 

An application showcasing a few things: unidirectional data flow, MVVM, jetpack compose, kotlin multiplatform to name a few.

### Approach

I chose to work on this problem as I would a full system, with the thought in mind that parts of the algorithm or perhaps the input may change
in the future. The ScheduleDomainMapper is the main evaluator of our algorithm, but it relies on a number of interceptors.


### Features

In progress:
  - Would like to work on a phone/tablet paradigm
  - Bonus: Native desktop version of this
  - Share this schedule with a contact.
  - Espresso tests
  - More unit tests

### Architecture

MVVM

### Android

### Other


### Data layer design

I chose to architect this as if the data may be coming from some varying source in the future. 