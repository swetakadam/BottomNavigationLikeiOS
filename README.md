# BottomNavigationLikeiOS
This is a sample andriod project that allows bottom navigation using activities and support stack for each tab using 
Android Tasks

The reason for this project is sometimes you have an existing Android App with drawer navigation and you want to move 
to Bottom Navigation 

Migration Bottom Navigation possible blockers 

1. Existing App may be Single Activity approach -- go for Navigation Component by Google 
2. Existing App may be having 50-100's of screens which might be a mix of Activities and Fragments 
   In such scenario it is very hard to refactor the entire app using "Navigation Component" and we need to do something fast 
   like get something out in weeks on production App 
   
   
This project is for Case 2 blocker 

Another important point related to Bottom Navigation material design behaviour is 


https://material.io/design/components/bottom-navigation.html

Bottom navigation behaves differently on Android and iOS. When you select a bottom navigation item (one that’s not currently selected), each platform displays different outcomes:

On Android: the app navigates to a destination’s top-level screen. Any prior user interactions and temporary screen states are reset, such as scroll position, tab selection, and in-line search.
On iOS: the destination reflects the user’s prior interaction. If the user previously visited that section of the app, they return to the last screen viewed (with its prior state preserved, if possible). Otherwise, the app navigates to the top-level screen.
Default platform navigation can be overridden when needed to improve the user experience. For example, an Android app that requires frequent switching between sections can preserve each section’s state. Or, an iOS app can return users to the top-level screen 
(or reset their scroll position) if it better suits the use case.



However it is possible sometimes product might want BottomNavigation to behave same like iOS 
This is possible sample project for such scenario 


I am using Android Tasks and Affinity feature for each tab root activity which is defined in Manifest file.

Home tab 
Dashboard tab
Notification tab 


Important Reads 
https://developer.android.com/guide/components/activities/tasks-and-back-stack.html#Starting
https://www.youtube.com/watch?v=4Y3JMvbcxQE
https://medium.com/google-developers/dominating-the-overview-screen-eef18d2195d
https://android.jlelse.eu/https-medium-com-yashsoniweb-android-tasks-ffbd547ff5b8



This is just a sample project 


![bottomnavigation-api28](https://user-images.githubusercontent.com/5216040/54570964-e7d99400-499d-11e9-84b7-f7be0341f42a.gif)

