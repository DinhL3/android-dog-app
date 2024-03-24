# Dinh's Android Dog App

## 1. Overview
This is a simple Android App, written with Kotlin and Jetpack Compose. Users can search for dog breeds and their details. Users can also use the camera to take picture (of their dog maybe?).  
The purpose of the project is to demonstrate basics of Android development: modern UI with Jetpack Compose, http requests, device permission (camera).  
All information including pictures are fetched from [The Dog Api](https://thedogapi.com/)

## 2. Home screen
<img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711241897/dog-app-demo/home_fzmfwd.png" width="200" />
Clicking "See dog breeds" will lead to "Dog Breeds" screen.

## 3. Dog Breeds screen
By default, users can see all dog breeds. User can use the search bar to search for a specific dog breed. 
  
<img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711241889/dog-app-demo/breeds_p6ygq3.png" width="200" /> <img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711241890/dog-app-demo/breeds-search_kab6ts.png" width="200" />  

Clicking on a dog breed will lead to Details screen.

## 4. Breed details screen
Here, the user can see all the details of a spefic dog breed and a representing image. There are some minor logic to handle the aspect ratio of the image, and the "unknown" details.

<img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711241895/dog-app-demo/details-square_sjxgr0.png" width="200" /> <img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711241893/dog-app-demo/details-rectangle_zwctgh.png" width="200" />  

## 5. Camera screen
Chooseing "Take a picture..." on the home screen will open the camera screen. Here, user can take a picture, switch between front/back camera, and see the pictures taken in the session.  

<img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711309147/dog-app-demo/camera_qvansl.png" width="200" /> <img src="https://res.cloudinary.com/doeoghxhd/image/upload/v1711309146/dog-app-demo/gallery_xveypz.png" width="200" />  
