# Tip Calculator 

## *Esther Goldstein*

**Tippy** computes the tip and total amount for a bill. The app uses the base amount and tip percentage to calculate the amount owed, and it also describes the quality of service based on the tip.

Time spent: **3.5** hours spent in total

## Functionality 

The following **required** functionality is completed:

* [x] User can enter in a bill amount (total amount to tip on)
* [x] User can enter a tip percentage (what % the user wants to tip).
* [x] The tip and total amount are updated immediately when any of the inputs changes.
* [x] The user sees a label or color update based on the tip amount. 

The following **extensions** are implemented:

* [x] Custom colors palette selected
* [x] Replaced tip percentage labels with emojis
* [x] User can split bill for up to 8 people

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/YHrslp9.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Challenges: I used a Spinner to create the dropdown for selecting how many people to split the bill with. The official Android documentation (https://developer.android.com/guide/topics/ui/controls/spinner) says to make a string array of the dropdown values and then reference it using R.array.<string array name>. However, Android Studio kept giving me an error, saying it couldn't resolve array, so I had to find an alternative way to make the array.

## License

    Copyright 2020 Esther

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.