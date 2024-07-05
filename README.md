# Sarvah Bhojan - Food for All 
## An Urban Alleviation Project Under Ministry of Housing and Urban Affairs, Government of India.

Welcome to Sarvah Bhojan - Food for All, a user-friendly food delivery application designed to bring delicious meals to your doorstep. This project is built with love and care using Android Studio, leveraging the power of Java and XML to create a seamless and enjoyable experience for our users.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Screens](#screens)
- [Architecture](#architecture)
- [Database](#database)
- [Design](#design)
- [Getting Started](#getting-started)
- [Contact](#contact)

## Overview

Sarvah Bhojan is an initiative to ensure that everyone has access to good food. Whether you're at home or at work, our application makes it easy to order meals from your favorite restaurants and have them delivered right to your door, from the community kitchens of your very city made with love and care by our loving Self Help Groups. 

> An Initiative of Ministry Of Housing and Urban Affairs, Government of India

## Features

- **MVVM Architecture**: Ensures a clear separation of concerns and makes the app easier to maintain.
- **Room Database**: For efficient local storage and data persistence.
- **Live Data**: Keeps UI components in sync with the data.
- **Google Material Design 3**: Provides a modern and intuitive user interface.
- **Multiple Activities and Fragments**: For a comprehensive and navigable user experience.

## Screens

Here's a glimpse of the various screens and activities that make up Sarvah Bhojan:

1. **Splash Screen**: Welcomes users and sets the stage.
2. **Login and Registration Activities**: Secure and easy access for new and returning users.
3. **Home Activity**:
   - **Home Fragment**: Displays restaurant options and featured meals.
   - **Account Fragment**: Manage personal details and preferences.
   - **Cart and Orders Fragment**: View current orders and order history.
4. **Restaurant Activity**: Explore menu items and place orders.
5. **User Information Editor Activity**: Update personal information.
6. **Address Picker and Address Editor**: Manage delivery addresses.
7. **Rating and Tracking Activities**: Rate orders and track deliveries in real-time.
8. **Logout and Delete**: Manage account settings.
9. **Customer Support AlertDialogBoxes**: Quick access to customer support.

## Architecture

The application follows the Model-View-ViewModel (MVVM) architecture pattern, ensuring a clean separation of concerns:

- **Models**: Represent the data structure, including User, Address, Restaurant, Menu Item, Cart Item, Order, and Payment.
- **ViewModels**: Handle the logic and prepare data for the UI.
- **Views**: Activities and Fragments that display the data.
- **LiveData** : To handle the data changes and reflect in UI.

## Database

We use the Room Database for local data storage. The data flow is managed by DAOs (Data Access Objects) and Repositories:

- **DAOs**: Provide methods to interact with the database.
- **Repositories**: Abstract data sources and provide a clean API for data access.

## Design

Our app follows the guidelines of Google Material Design 3, ensuring a clean, modern, and intuitive user interface.

## Getting Started

To get started with Sarvah Bhojan:

1. Clone the repository:
   ```sh
   git clone https://github.com/ujjwalr-y20cs161/Sarvah-Bhojan-User.git
   ```
2. Open the project in Android Studio.
3. Build and run the application on an emulator or physical device.
4. Or Check Release to Download and Install Official APKS


## Contact

If you have any questions or need further assistance, please feel free to reach out:

- **Email**: ujjwalrsanagapati@gmail.com 

Thank you for being a part of Sarvah Bhojan - where food is for all!

---

Let's work together to ensure that everyone can enjoy delicious meals, anytime and anywhere.

---

Made with ❤️ by the Sarvah Bhojan Team
